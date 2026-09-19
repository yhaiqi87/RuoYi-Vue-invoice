package com.yhq.invoice.banstyle.html;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link HtmlBanstyleGenerator} 单元测试：验证由乐企上传 JSON 生成的 HTML/CSS 版式
 * 文档结构、关键票面文本、页面尺寸与内嵌二维码 PNG 均符合要求。
 */
class HtmlBanstyleGeneratorTest {

    private String generate() throws Exception {
        try (InputStream json = getClass().getResourceAsStream("/basic-sample.json")) {
            assertNotNull(json, "测试样例 basic-sample.json 缺失");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            HtmlBanstyleGenerator.generateHtml(json, out);
            return out.toString(StandardCharsets.UTF_8);
        }
    }

    @Test
    void generateHtml_producesBasicInvoice() throws Exception {
        String html = generate();

        // 基本形态
        assertTrue(html.length() > 1000, "生成的 HTML 不应为空");
        assertTrue(html.startsWith("<!DOCTYPE html>"), "应以 DOCTYPE 开头");
        assertTrue(html.trim().endsWith("</html>"), "应以 </html> 结尾");

        // 页面尺寸 210×140mm
        assertTrue(html.contains("width:210mm;height:140mm"), "页面尺寸应为 210×140mm");

        // 关键票面文本
        assertTrue(html.contains("电子发票（普通发票）"), "应含票头");
        assertTrue(html.contains("24412000000012345678"), "应含发票号码");
        assertTrue(html.contains("深圳市数电科技有限公司"), "应含销售方名称");
        assertTrue(html.contains("广州采购贸易有限公司"), "应含购买方名称");
        assertTrue(html.contains("价税合计"), "应含价税合计");
        assertTrue(html.contains("壹仟贰佰") || html.contains("壹仟贰佰陆拾玖圆捌角捌分"), "应含价税合计大写");
        assertTrue(html.contains("开票人"), "应含开票人");
        assertTrue(html.contains("备注"), "应含备注区");

        // 监制章、右框色带（标签框仅差额征税发票出现，样例为普通票故不强制）
        assertTrue(html.contains("全国统一发票监制章"), "监制章上环文字");
        assertTrue(html.contains("国家税务总局"), "监制章中环文字");
        assertTrue(html.contains("广东省税务局"), "监制章下环税务机关名");
        assertTrue(html.contains("band gold"), "应含金色色带");
        assertTrue(html.contains("band brown"), "应含咖色色带");

        // 8 列表头齐全
        for (String col : new String[]{"项目名称", "规格型号", "单位", "数量", "单价", "金额", "税率/征收率", "税额"}) {
            assertTrue(html.contains(col), "明细表应含列：" + col);
        }
    }

    @Test
    void generateHtml_embedsValidQrPng() throws Exception {
        String html = generate();
        String prefix = "data:image/png;base64,";
        int idx = html.indexOf(prefix);
        assertTrue(idx >= 0, "应内嵌二维码 PNG");
        int end = html.indexOf("\"", idx);
        assertTrue(end > idx, "二维码 data URI 应被正确引号闭合");
        String b64 = html.substring(idx + prefix.length(), end);
        byte[] raw = Base64.getDecoder().decode(b64);
        assertEquals((byte) 0x89, raw[0], "PNG 头应为 0x89");
        assertEquals('P', (char) raw[1]);
        assertEquals('N', (char) raw[2]);
        assertEquals('G', (char) raw[3]);
    }
}
