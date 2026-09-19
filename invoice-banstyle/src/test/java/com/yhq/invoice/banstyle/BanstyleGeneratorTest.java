package com.yhq.invoice.banstyle;

import com.yhq.invoice.banstyle.draw.BanstyleGenerator;
import com.yhq.invoice.banstyle.draw.qr.QrGenerator;
import com.yhq.invoice.banstyle.draw.util.AmountToChinese;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BanstyleGeneratorTest {

    private static final float MM_PT = 72f / 25.4f;
    private static final float DELTA = 1.0f; // PDF 坐标容差（pt）

    @Test
    void generatePdf_producesBasicInvoice() throws Exception {
        InputStream json = getClass().getResourceAsStream("/basic-sample.json");
        assertNotNull(json, "测试样例 basic-sample.json 缺失");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BanstyleGenerator.generatePdf(json, out);
        byte[] bytes = out.toByteArray();
        assertTrue(bytes.length > 1000, "生成的 PDF 不应为空");

        try (PDDocument doc = Loader.loadPDF(bytes)) {
            assertEquals(1, doc.getNumberOfPages());
            var box = doc.getPage(0).getMediaBox();
            assertEquals(210f * MM_PT, box.getWidth(), DELTA, "页面宽度应为 210mm");
            assertEquals(140f * MM_PT, box.getHeight(), DELTA, "≤8 行时页面高度应为 140mm");

            String text = new PDFTextStripper().getText(doc);
            assertTrue(text.contains("电子发票（普通发票）"), "应含票头");
            assertTrue(text.contains("24412000000012345678"), "应含发票号码");
            assertTrue(text.contains("深圳市数电科技有限公司"), "应含销售方名称");
            assertTrue(text.contains("广州采购贸易有限公司"), "应含购买方名称");
            assertTrue(text.contains("价税合计"), "应含价税合计");
            assertTrue(text.contains("壹仟贰佰陆拾玖圆捌角捌分") || text.contains("壹仟贰佰"), "应含价税合计大写");
            assertTrue(text.contains("开票人"), "应含开票人");
            assertTrue(text.contains("备注"), "应含备注区");
        }
    }

    @Test
    void amountToChinese_basic() {
        assertEquals("贰仟壹佰叁拾圆整", AmountToChinese.toChinese(new BigDecimal("2130")));
        assertEquals("壹佰圆整", AmountToChinese.toChinese(new BigDecimal("100")));
        assertEquals("贰圆壹角贰分", AmountToChinese.toChinese(new BigDecimal("2.12")));
        assertEquals("壹仟圆整", AmountToChinese.toChinese(new BigDecimal("1000")));
    }

    @Test
    void qrCrc_isStableFourHexLowFirst() {
        String a = QrGenerator.crc16x25Hex("01,32,,24412000000012345678,1269.88".getBytes(java.nio.charset.StandardCharsets.ISO_8859_1));
        String b = QrGenerator.crc16x25Hex("01,32,,24412000000012345678,1269.88".getBytes(java.nio.charset.StandardCharsets.ISO_8859_1));
        assertEquals(4, a.length(), "CRC 应为 4 位十六进制");
        assertEquals(a, b, "CRC 应稳定可复现");
    }
}
