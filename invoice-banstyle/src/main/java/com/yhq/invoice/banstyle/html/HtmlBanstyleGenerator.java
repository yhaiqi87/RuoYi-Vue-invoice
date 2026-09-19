package com.yhq.invoice.banstyle.html;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yhq.invoice.banstyle.draw.layout.InvoiceLayoutData;
import com.yhq.invoice.banstyle.draw.layout.InvoiceLayoutData.DetailRow;
import com.yhq.invoice.banstyle.draw.layout.ReqToLayoutMapper;
import com.yhq.invoice.banstyle.draw.qr.QrGenerator;
import com.yhq.invoice.common.dto.BaseUploadInvoiceReq;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.imageio.ImageIO;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * 基础版数字化电子发票版式（HTML/CSS 版）。
 *
 * <p>与 {@code com.yhq.invoice.banstyle} 的 PDF 版并行：输入同为乐企基础版「发票上传」请求
 * （{@link BaseUploadInvoiceReq} 或等价 JSON），按《数字化电子发票版式及XML规范V6.000》§三（一）
 * 渲染，但输出一份<strong>自包含 HTML 文档</strong>（内嵌 CSS + base64 二维码），可直接在浏览器查看，
 * 也可经无头浏览器（Chrome / wkhtmltopdf）二次转为 PDF。
 *
 * <p>布局严格沿用规范 mm 坐标，模板 {@code templates/invoice-basic.html} 的 CSS 直接用 {@code mm}
 * 单位 1:1 还原（页面 210×140mm、内框 201mm、双线 18/19mm、监制章椭圆、购销方 4 列、8 列明细表、
 * 价税合计、备注、票尾、右框色带等）。本类仅负责装配数据、生成二维码并交给 Thymeleaf 渲染，
 * 所有版面几何均集中在模板内维护。
 *
 * <p>字体沿用规范命名的「楷体 / 宋体 / Courier New」字体栈；在无对应字体的环境会回退到系统衬线/等宽字体。
 */
public final class HtmlBanstyleGenerator {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final String RED_LETTER_TEXT = "红字";

    /**
     * 模板名（对应 classpath:templates/invoice-basic.html）。
     */
    private static final String TEMPLATE_NAME = "invoice-basic";

    private HtmlBanstyleGenerator() {
    }

    // ===== 对外 API =====

    public static String generateHtml(BaseUploadInvoiceReq req) throws IOException {
        InvoiceLayoutData data = ReqToLayoutMapper.map(req);
        String typeCode = "01".equals(req.getFppz()) ? "31" : "32";
        BigDecimal jshj = req.getJshj() != null ? req.getJshj() : BigDecimal.ZERO;
        String qrUri = "";
        try {
            qrUri = qrDataUri(QrGenerator.generate(req.getFphm(), typeCode, jshj));
        } catch (Exception e) {
            System.err.println("二维码生成失败，跳过：" + e.getMessage());
        }
        return render(data, qrUri);
    }

    public static void generateHtml(InputStream json, OutputStream out) throws IOException {
        BaseUploadInvoiceReq req = MAPPER.readValue(json, BaseUploadInvoiceReq.class);
        out.write(generateHtml(req).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * CLI：java -jar ...HtmlBanstyleGenerator <输入.json> <输出.html>
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("用法: HtmlBanstyleGenerator <输入.json> <输出.html>");
            System.exit(1);
        }
        try (InputStream is = new java.io.FileInputStream(new File(args[0]));
             OutputStream os = new java.io.FileOutputStream(new File(args[1]))) {
            generateHtml(is, os);
        }
        System.out.println("已生成版式 HTML: " + new File(args[1]).getAbsolutePath());
    }

    // ===== 渲染 =====

    private static String render(InvoiceLayoutData data, String qrUri) {
        // 复用的 Thymeleaf 引擎：ClassLoader 解析 classpath:templates/*.html，HTML 模式。
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resolver.setCacheable(false);

        org.thymeleaf.TemplateEngine engine = new org.thymeleaf.TemplateEngine();
        engine.setTemplateResolver(resolver);

        Context ctx = new Context();
        ctx.setVariable("data", data);
        ctx.setVariable("qrUri", qrUri);
        // 明细表固定 8 行（§三（一） 8 列网格），不足部分以空行补齐，保持版面几何一致。
        ctx.setVariable("rows", padRows(data.getDetails()));
        ctx.setVariable("buyerContact", buyerContact(data));
        ctx.setVariable("sellerContact", sellerContact(data));
        ctx.setVariable("redLetterText", RED_LETTER_TEXT);

        return engine.process(TEMPLATE_NAME, ctx);
    }

    private static String buyerContact(InvoiceLayoutData d) {
        StringBuilder sb = new StringBuilder();
        if (d.isShowBuyerContact()) {
            sb.append("购方地址:").append(nz(d.getBuyerAddr())).append("; 电话:")
                    .append(nz(d.getBuyerTel())).append("; ");
        }
        if (d.isShowBuyerBank()) {
            sb.append("购方开户银行:").append(nz(d.getBuyerBank())).append("; 银行账号:")
                    .append(nz(d.getBuyerAccount())).append(";");
        }
        return sb.toString();
    }

    private static String sellerContact(InvoiceLayoutData d) {
        StringBuilder sb = new StringBuilder();
        if (d.isShowSellerContact()) {
            sb.append("销方地址:").append(nz(d.getSellerAddr())).append("; 电话:")
                    .append(nz(d.getSellerTel())).append("; ");
        }
        if (d.isShowSellerBank()) {
            sb.append("销方开户银行:").append(nz(d.getSellerBank())).append("; 银行账号:")
                    .append(nz(d.getSellerAccount())).append(";");
        }
        return sb.toString();
    }

    // ===== 小工具 =====

    /**
     * 明细行补齐到 8 行，多余截断，空位以空行（非 null）占位，便于模板直接 row.xxx 取值。
     */
    private static List<DetailRow> padRows(List<DetailRow> src) {
        List<DetailRow> rows = new ArrayList<>(src == null ? new ArrayList<>() : src);
        while (rows.size() < 8) {
            rows.add(new DetailRow("", "", "", "", "", "", "", ""));
        }
        return rows.subList(0, Math.min(rows.size(), 8));
    }

    private static String qrDataUri(java.awt.image.BufferedImage img) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "PNG", baos);
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    private static String nz(String s) {
        return s == null ? "" : s;
    }
}
