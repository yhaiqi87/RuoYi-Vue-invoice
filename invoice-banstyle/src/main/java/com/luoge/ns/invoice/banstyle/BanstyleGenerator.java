package com.luoge.ns.invoice.banstyle;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luoge.ns.invoice.banstyle.font.FontLoader;
import com.luoge.ns.invoice.banstyle.layout.InvoiceLayoutData;
import com.luoge.ns.invoice.banstyle.layout.Layout;
import com.luoge.ns.invoice.banstyle.layout.ReqToLayoutMapper;
import com.luoge.ns.invoice.banstyle.qr.QrGenerator;
import com.luoge.ns.invoice.banstyle.render.BasicBanstyleRenderer;
import com.luoge.ns.invoice.common.dto.BaseUploadInvoiceReq;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

/**
 * 基础版数字化电子发票版式文件（PDF）生成门面。
 * 输入为乐企基础版「发票上传」请求（{@link BaseUploadInvoiceReq} 或等价 JSON），
 * 按《数字化电子发票版式及XML规范V6.000》§三（一）渲染 PDF。
 */
public final class BanstyleGenerator {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private BanstyleGenerator() {
    }

    public static void generatePdf(BaseUploadInvoiceReq req, OutputStream out) throws IOException {
        InvoiceLayoutData data = ReqToLayoutMapper.map(req);
        Layout layout = Layout.of(Math.max(1, data.getDetails().size()));
        String typeCode = "01".equals(req.getFppz()) ? "31" : "32";
        BigDecimal jshj = req.getJshj() != null ? req.getJshj() : BigDecimal.ZERO;
        try {
            data.setQrImage(QrGenerator.generate(req.getFphm(), typeCode, jshj));
        } catch (Exception e) {
            // 二维码生成失败不应阻断版式产出
            System.err.println("二维码生成失败，跳过：" + e.getMessage());
        }

        try (PDDocument doc = new PDDocument()) {
            PDRectangle size = new PDRectangle(layout.pageWidthPt(), layout.pageHeightPt());
            PDPage page = new PDPage(size);
            doc.addPage(page);
            FontLoader fonts = new FontLoader(doc);
            new BasicBanstyleRenderer(doc, data).render(page, fonts);
            doc.save(out);
        }
    }

    public static void generatePdf(InputStream json, OutputStream out) throws IOException {
        BaseUploadInvoiceReq req = MAPPER.readValue(json, BaseUploadInvoiceReq.class);
        generatePdf(req, out);
    }

    /**
     * CLI：java -jar invoice-banstyle.jar <输入.json> <输出.pdf>
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("用法: BanstyleGenerator <输入.json> <输出.pdf>");
            System.exit(1);
        }
        File in = new File(args[0]);
        File out = new File(args[1]);
        try (InputStream is = new java.io.FileInputStream(in);
             OutputStream os = new java.io.FileOutputStream(out)) {
            generatePdf(is, os);
        }
        System.out.println("已生成版式文件: " + out.getAbsolutePath());
    }
}
