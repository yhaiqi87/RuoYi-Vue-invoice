package com.yhq.invoice.banstyle.draw.font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 从 TrueType 字体集合（.ttc）中抽取指定字面为「独立字体」字节流。
 *
 * <p>为什么要这么做：PDFBox 3.x 既不支持对 .ttc 集合整体嵌入，也不支持对集合内的
 * OTF/CFF 字面做子集化（CFF 字体无 glyf 表）。而《规范》要求的「楷体 / 宋体」在本机
 * 恰以 .ttc 集合形式存在。解决办法是先把需要的单个字面从集合中拆出成一份标准 sfnt
 * 字体（TrueType 或 CFF/OTF），再交给 PDFBox 按常规方式加载——TrueType 可子集化，
 * CFF/OTF 可整体嵌入。</p>
 *
 * <p>集合格式：{@code ttcf} 头 + 各字面偏移表（offset table），每个偏移处是一份
 * 标准 sfnt（表目录 + 表数据）。抽取即把某字面从「相对集合的偏移」改写为「相对新文件的
 * 偏移」，重排为标准字体文件。</p>
 */
public final class TtcExtractor {

    private TtcExtractor() {
    }

    /**
     * 抽取集合中的第 {@code faceIndex} 个字面（0 基）。若源文件并非集合（无 {@code ttcf}
     * 头），则原样返回，便于统一处理。
     */
    public static byte[] extractFace(String ttcPath, int faceIndex) throws IOException {
        byte[] all = Files.readAllBytes(Paths.get(ttcPath));
        if (all.length < 4
                || all[0] != 't' || all[1] != 't' || all[2] != 'c' || all[3] != 'f') {
            return all;
        }
        long version = readUInt32(all, 4);
        int numFonts = (int) readUInt32(all, 8);
        if (faceIndex < 0 || faceIndex >= numFonts) {
            throw new IOException("字体集合 " + ttcPath + " 仅有 " + numFonts
                    + " 个字面，无法抽取第 " + faceIndex + " 个");
        }
        // v2.0 在 numFonts 之后另有 3 个 uint32 的 DSIG 字段
        int offsetTablePos = (version == 0x00020000L) ? 24 : 12;
        long faceOffset = readUInt32(all, offsetTablePos + faceIndex * 4);

        int numTables = readUInt16(all, faceOffset + 4);
        long dirStart = faceOffset + 12L;
        long tableDataStart = 12L + (long) numTables * 16L;

        ByteArrayOutputStream dir = new ByteArrayOutputStream();
        ByteArrayOutputStream tables = new ByteArrayOutputStream();
        long cursor = tableDataStart;
        for (int t = 0; t < numTables; t++) {
            long entry = dirStart + (long) t * 16L;
            long tableOff = readUInt32(all, entry + 8);
            long tableLen = readUInt32(all, entry + 12);
            // 表偏移在部分集合中为「相对文件起点」的绝对偏移，部分集合为「相对字面起点」
            // 的偏移。优先取绝对偏移（落在文件内），否则退回到相对字面的偏移。
            long srcStart;
            if (tableOff + tableLen <= all.length) {
                srcStart = tableOff;
            } else if (faceOffset + tableOff + tableLen <= all.length) {
                srcStart = faceOffset + tableOff;
            } else {
                throw new IOException("字体表越界，无法抽取: off=" + tableOff
                        + " len=" + tableLen + " faceOffset=" + faceOffset);
            }
            byte[] tableData = slice(all, srcStart, tableLen);

            // 目录项：tag(4) + checksum(4) + offset(4) + length(4)
            dir.write(slice(all, entry, 4));        // tag
            dir.write(slice(all, entry + 4, 4));    // 沿用原 checksum
            writeUInt32(dir, cursor);               // 新偏移
            writeUInt32(dir, tableLen);             // 长度
            tables.write(tableData);
            cursor += tableLen;
            long pad = (4 - (tableLen % 4)) % 4;
            if (pad > 0) {
                tables.write(new byte[(int) pad]);
                cursor += pad;
            }
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(slice(all, faceOffset, 12)); // sfnt 头（含版本号、表数、搜索参数）
        out.write(dir.toByteArray());
        out.write(tables.toByteArray());
        return out.toByteArray();
    }

    /**
     * 字体是否为 CFF/OTF（sfnt 版本号为 {@code 'OTTO'}）。
     */
    public static boolean isCff(byte[] sfnt) {
        return sfnt.length >= 4
                && sfnt[0] == 'O' && sfnt[1] == 'T' && sfnt[2] == 'T' && sfnt[3] == 'O';
    }

    private static long readUInt32(byte[] b, long pos) {
        return ((long) (b[(int) pos] & 0xFF) << 24)
                | ((long) (b[(int) pos + 1] & 0xFF) << 16)
                | ((long) (b[(int) pos + 2] & 0xFF) << 8)
                | ((long) (b[(int) pos + 3] & 0xFF));
    }

    private static int readUInt16(byte[] b, long pos) {
        return ((b[(int) pos] & 0xFF) << 8) | (b[(int) pos + 1] & 0xFF);
    }

    private static void writeUInt32(ByteArrayOutputStream out, long v) {
        out.write((int) ((v >>> 24) & 0xFF));
        out.write((int) ((v >>> 16) & 0xFF));
        out.write((int) ((v >>> 8) & 0xFF));
        out.write((int) (v & 0xFF));
    }

    private static byte[] slice(byte[] b, long start, long len) {
        byte[] out = new byte[(int) len];
        System.arraycopy(b, (int) start, out, 0, (int) len);
        return out;
    }
}
