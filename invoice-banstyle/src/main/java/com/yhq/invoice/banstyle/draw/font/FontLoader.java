package com.yhq.invoice.banstyle.draw.font;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 系统 CJK 字体加载器。规范要求的「楷体 / 宋体」在本机以开源替代字体承载：
 * <ul>
 *   <li>楷体 → AR PL UKai（ukai.ttc 首张字面，TrueType，用于版面元素、监制章、票头）</li>
 *   <li>宋体 → AR PL UMing（uming.ttc 首张字面，TrueType 明体，用于填充信息）</li>
 *   <li>等宽（税号 / ¥）→ PDF 标准 14 字体 COURIER（度量等同 Courier New，免授权）</li>
 * </ul>
 *
 * <p>注：PDFBox 3.x 仅支持嵌入 TrueType（含 glyf 表）字体，不支持 CFF/OTF（如
 * NotoSerifCJK-Regular.ttc，其字面为 OpenType/CFF）。故「宋体」以同为 TrueType 的
 * AR PL UMing 明体替代，「楷体」以 AR PL UKai 替代，二者均以 .ttc 集合形式存在。</p>
 *
 * <p>因 PDFBox 无法对 .ttc 集合整体嵌入，故先经 {@link TtcExtractor} 抽取所需字面为
 * 独立 TrueType 字体，再子集化嵌入以减小体积。</p>
 */
public final class FontLoader {

    private static final String KAI_PATH = "/usr/share/fonts/truetype/arphic/ukai.ttc";
    private static final String SONG_PATH = "/usr/share/fonts/truetype/arphic/uming.ttc";

    private final PDFont kai;
    private final PDFont song;
    private final PDFont courier;

    public FontLoader(PDDocument doc) throws IOException {
        byte[] kaiBytes = TtcExtractor.extractFace(KAI_PATH, 0);
        byte[] songBytes = TtcExtractor.extractFace(SONG_PATH, 0);
        if (kaiBytes.length == 0 || songBytes.length == 0) {
            throw new IOException("字体抽取结果为空，请确认字体文件存在: "
                    + KAI_PATH + ", " + SONG_PATH);
        }
        this.kai = PDType0Font.load(doc, new ByteArrayInputStream(kaiBytes),
                !TtcExtractor.isCff(kaiBytes));
        // CFF/OTF 无 glyf 表，不能子集化，必须整体嵌入
        this.song = PDType0Font.load(doc, new ByteArrayInputStream(songBytes),
                !TtcExtractor.isCff(songBytes));
        this.courier = new PDType1Font(Standard14Fonts.FontName.COURIER);
    }

    public PDFont kai() {
        return kai;
    }

    public PDFont song() {
        return song;
    }

    public PDFont courier() {
        return courier;
    }
}
