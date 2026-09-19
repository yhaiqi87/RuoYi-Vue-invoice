package com.yhq.invoice.banstyle.draw.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;

/**
 * 发票票面二维码生成（规范 §六）。
 * <ul>
 *   <li>版本号 01；发票类型 31(专票)/32(普票)；发票代码为空；发票号码 20 位；合计金额取价税合计(两位小数)。</li>
 *   <li>CRC16_X25：多项式 x15+x2+1(0x8005)、初始 0x0000、低位在前高位在后、异或 0。</li>
 *   <li>QR 版本5、M 级纠错、字母数字字符集；中心叠加浅黄底「税」字（5.36mm，宋体 9pt）。</li>
 * </ul>
 * 字段间以英文逗号分隔（沿用现有规则），组合串用于计算 CRC 并作为二维码内容。
 */
public final class QrGenerator {

    private static final String VERSION = "01";
    private static final String SONG_TTC = "/usr/share/fonts/opentype/noto/NotoSerifCJK-Regular.ttc";
    private static final int QR_PX = 600;            // 二维码像素（对应票面 20mm，30px/mm）
    private static final double MM_PER_PX = 20.0 / QR_PX;
    private static final double TAX_BOX_MM = 5.36;

    private QrGenerator() {
    }

    /**
     * @param fphm     发票号码（数电电票 20 位）
     * @param typeCode 31(专票)/32(普票)
     * @param jshj     价税合计（用于「合计金额」）
     */
    public static BufferedImage generate(String fphm, String typeCode, BigDecimal jshj) throws WriterException {
        String content = buildContent(fphm, typeCode, jshj);
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, QR_PX, QR_PX,
                new java.util.EnumMap<>(EncodeHintType.class) {{
                    put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
                }});
        BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
        overlayTaxChar(image);
        return image;
    }

    /**
     * 拼接二维码内容并计算 CRC（低位在前的 4 位十六进制）。
     */
    static String buildContent(String fphm, String typeCode, BigDecimal jshj) {
        String amount = jshj == null ? "0.00"
                : jshj.setScale(2, RoundingMode.HALF_UP).toPlainString();
        // 版本号,发票类型,发票代码(空),发票号码,合计金额,CRC
        String core = VERSION + "," + typeCode + ",," + nullToEmpty(fphm) + "," + amount;
        String crc = crc16x25Hex(core.getBytes(StandardCharsets.ISO_8859_1));
        return core + "," + crc;
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    /**
     * CRC16_X25（多项式 0x8005，初始 0，低位在前，异或 0），返回低位在前的 4 位十六进制。
     */
    public static String crc16x25Hex(byte[] data) {
        int crc = crc16x25(data);
        int low = crc & 0xFF;
        int high = (crc >> 8) & 0xFF;
        return String.format("%02x%02x", low, high);
    }

    static int crc16x25(byte[] data) {
        int poly = 0xA001; // 0x8005 的低位在前（反射）形式
        int crc = 0x0000;
        for (byte b : data) {
            crc ^= (b & 0xFF);
            for (int i = 0; i < 8; i++) {
                if ((crc & 0x0001) != 0) {
                    crc = (crc >>> 1) ^ poly;
                } else {
                    crc >>>= 1;
                }
            }
        }
        return crc & 0xFFFF;
    }

    /**
     * 在二维码中心叠加浅黄底「税」字。
     */
    private static void overlayTaxChar(BufferedImage image) {
        Graphics2D g = image.createGraphics();
        int boxPx = (int) Math.round(TAX_BOX_MM / MM_PER_PX);
        int x = (image.getWidth() - boxPx) / 2;
        int y = (image.getHeight() - boxPx) / 2;

        g.setColor(new Color(250, 239, 173)); // 浅黄 RGB(250,239,173)
        g.fillRect(x, y, boxPx, boxPx);
        g.setColor(new Color(192, 0, 0));      // 税字用深红
        g.setStroke(new BasicStroke(1f));

        Font font = loadSongFont();
        int fontSize = (int) Math.round((9.0 * 72.0 / 25.4) / MM_PER_PX); // 9pt → px
        g.setFont(font.deriveFont((float) fontSize));
        g.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING,
                java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        java.awt.FontMetrics fm = g.getFontMetrics();
        int textX = x + (boxPx - fm.stringWidth("税")) / 2;
        int textY = y + (boxPx - fm.getHeight()) / 2 + fm.getAscent();
        g.drawString("税", textX, textY);
        g.dispose();
    }

    private static Font loadSongFont() {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(SONG_TTC))
                    .deriveFont((float) (9.0 * 72.0 / 25.4));
        } catch (Exception e) {
            return new Font("Serif", Font.PLAIN, 12);
        }
    }

    /**
     * 调试/导出助手：将二维码保存为 PNG。
     */
    public static void writePng(BufferedImage img, File out) throws IOException {
        ImageIO.write(img, "PNG", out);
    }
}
