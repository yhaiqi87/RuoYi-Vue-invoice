package com.luoge.ns.invoice.banstyle.render;

import com.luoge.ns.invoice.banstyle.font.FontLoader;
import com.luoge.ns.invoice.banstyle.layout.InvoiceLayoutData;
import com.luoge.ns.invoice.banstyle.layout.InvoiceLayoutData.DetailRow;
import com.luoge.ns.invoice.banstyle.layout.Layout;
import com.luoge.ns.invoice.banstyle.seal.SupervisionSeal;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.IOException;

/**
 * 基础版票面渲染器（规范 §三（一）1.1~1.7）。按坐标绘制 票头 / 购销方 / 应税明细与合计 / 备注 / 票尾，
 * 并叠加 二维码、监制章、右框色带、差额征税标签、红字水印。
 */
public final class BasicBanstyleRenderer {

    private final PDDocument doc;
    private final Layout layout;
    private final InvoiceLayoutData data;
    private final Color ELEMENT = new Color(Layout.COLOR_ELEMENT[0], Layout.COLOR_ELEMENT[1], Layout.COLOR_ELEMENT[2]);
    private final Color BLACK = Color.BLACK;
    private final float[] COL_X;   // 明细 8 列边界（pt，距左）

    public BasicBanstyleRenderer(PDDocument doc, InvoiceLayoutData data) {
        this.doc = doc;
        this.data = data;
        this.layout = Layout.of(Math.max(1, data.getDetails().size()));
        float start = layout.x(Layout.FRAME_LEFT_MM);
        this.COL_X = new float[Layout.DETAIL_COL_W_MM.length + 1];
        COL_X[0] = start;
        for (int i = 0; i < Layout.DETAIL_COL_W_MM.length; i++) {
            COL_X[i + 1] = COL_X[i] + Layout.DETAIL_COL_W_MM[i] * Layout.MM;
        }
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    // ===== 边框 / 双线 =====

    private static String stripYuan(String s) {
        if (s == null) {
            return "";
        }
        return s.startsWith("¥") ? s.substring(1) : s;
    }

    public void render(PDPage page, FontLoader fontLoader) throws IOException {
        FontLoader fonts = fontLoader;
        PDPageContentStream cs = new PDPageContentStream(doc, page);
        try {
            drawOuterBorder(cs);
            drawHeaderDoubleLine(cs);
            drawHeaderText(cs, fonts);
            drawInvoiceNoDate(cs, fonts);
            drawQr(cs, fonts);
            drawLabel(cs, fonts);
            SupervisionSeal.draw(cs, layout, fonts, data.getTaxBureauName());
            drawBuySell(cs, fonts);
            drawDetailTable(cs, fonts);
            drawRemark(cs, fonts);
            drawFooter(cs, fonts);
            drawRightColorBands(cs);
            if (data.isRedLetter()) {
                drawRedWatermark(cs, fonts);
            }
        } finally {
            cs.close();
        }
    }

    // ===== 票头文字 / 发票号码日期 =====

    private void drawOuterBorder(PDPageContentStream cs) throws IOException {
        cs.setStrokingColor(ELEMENT);
        cs.setLineWidth(0.5f * Layout.MM);
        float m = layout.x(4.5f);
        float w = layout.pageWidthPt() - 2 * m;
        float h = layout.pageHeightPt() - 2 * m;
        cs.addRect(m, m, w, h);
        cs.stroke();
    }

    private void drawHeaderDoubleLine(PDPageContentStream cs) throws IOException {
        cs.setStrokingColor(ELEMENT);
        cs.setLineWidth(Layout.LINE_THICK_MM * Layout.MM);
        float y1 = layout.y(Layout.DOUBLE_LINE_TOP_CENTER_Y_MM);          // 上沿线中心
        float y2 = layout.y(Layout.DOUBLE_LINE_TOP_CENTER_Y_MM + Layout.DOUBLE_LINE_GAP_MM); // 下沿线中心
        float x1 = layout.x((Layout.PAGE_WIDTH_MM - Layout.DOUBLE_LINE_LEN_MM) / 2f);
        float x2 = layout.x((Layout.PAGE_WIDTH_MM + Layout.DOUBLE_LINE_LEN_MM) / 2f);
        cs.moveTo(x1, y1);
        cs.lineTo(x2, y1);
        cs.moveTo(x1, y2);
        cs.lineTo(x2, y2);
        cs.stroke();
    }

    // ===== 二维码 / 标签 =====

    private void drawHeaderText(PDPageContentStream cs, FontLoader fonts) throws IOException {
        float baseline = layout.y(Layout.HEADER_TEXT_TOP_MM) - Layout.HEADER_FONT_PT * 0.8f;
        textCenter(cs, data.getInvoiceTitle(), fonts.kai(), Layout.HEADER_FONT_PT, layout.pageWidthPt() / 2f, baseline, ELEMENT);
    }

    private void drawInvoiceNoDate(PDPageContentStream cs, FontLoader fonts) throws IOException {
        float left = layout.x(Layout.INVOICE_NO_LEFT_MM);
        float yNo = layout.y(11f);
        float yDate = layout.y(15.5f);
        text(cs, "发票号码：" + nullToEmpty(data.getInvoiceNo()), fonts.song(), Layout.FILL_FONT_PT, left, yNo, BLACK);
        text(cs, "开票日期：" + nullToEmpty(data.getIssueDate()), fonts.song(), Layout.FILL_FONT_PT, left, yDate, BLACK);
    }

    // ===== 购销方 =====

    private void drawQr(PDPageContentStream cs, FontLoader fonts) throws IOException {
        if (data.getQrImage() == null) {
            return;
        }
        PDImageXObject img = LosslessFactory.createFromImage(doc, data.getQrImage());
        float w = Layout.QR_SIZE_MM * Layout.MM;
        float x = layout.x(Layout.QR_LEFT_MM);
        float y = layout.y(Layout.QR_TOP_MM + Layout.QR_SIZE_MM); // 左上角 → 左下
        cs.drawImage(img, x, y, w, w);
    }

    private void drawLabel(PDPageContentStream cs, FontLoader fonts) throws IOException {
        if (data.getLabelText() == null) {
            return;
        }
        float x = layout.x(Layout.LABEL_LEFT_MM);
        float y = layout.y(Layout.LABEL_TOP_MM + Layout.LABEL_HEIGHT_MM);
        float w = Layout.LABEL_WIDTH_MM * Layout.MM;
        float h = Layout.LABEL_HEIGHT_MM * Layout.MM;
        cs.setStrokingColor(ELEMENT);
        cs.setLineWidth(0.5f * Layout.MM);
        cs.addRect(x, y, w, h);
        cs.stroke();
        float baseline = y + h * 0.5f + Layout.FILL_FONT_PT * 0.35f;
        textCenter(cs, data.getLabelText(), fonts.kai(), Layout.FILL_FONT_PT, x + w / 2f, baseline, ELEMENT);
    }

    private void drawBuySell(PDPageContentStream cs, FontLoader fonts) throws IOException {
        float top = layout.y(Layout.BUYSELL_TOP_Y_MM);
        float bottom = layout.y(Layout.BUYSELL_TOP_Y_MM + Layout.BUYSELL_HEIGHT_MM);
        float left = layout.x(Layout.FRAME_LEFT_MM);
        float right = layout.x(Layout.FRAME_RIGHT_MM);
        // 外框 + 中间竖线
        cs.setStrokingColor(ELEMENT);
        cs.setLineWidth(Layout.LINE_THICK_MM * Layout.MM);
        cs.addRect(left, bottom, right - left, top - bottom);
        cs.stroke();
        float mid = layout.x(105f);
        cs.moveTo(mid, top);
        cs.lineTo(mid, bottom);
        cs.stroke();

        float cellL = left + 6f * Layout.MM;
        float cellR = mid - 6f * Layout.MM;
        float cellR2 = right - 6f * Layout.MM;

        // 购买方（左）
        text(cs, "购买方", fonts.kai(), Layout.ELEMENT_FONT_PT, cellL, top - 5f * Layout.MM, ELEMENT);
        text(cs, "名称：" + nullToEmpty(data.getBuyerName()), fonts.song(), Layout.FILL_FONT_PT,
                cellL, top - 10.5f * Layout.MM, BLACK);
        text(cs, "纳税人识别号：" + nullToEmpty(data.getBuyerTaxId()), fonts.song(), Layout.FILL_FONT_PT,
                cellL, top - 16.5f * Layout.MM, BLACK);

        // 销售方（右）
        text(cs, "销售方", fonts.kai(), Layout.ELEMENT_FONT_PT, mid + 6f * Layout.MM, top - 5f * Layout.MM, ELEMENT);
        text(cs, "名称：" + nullToEmpty(data.getSellerName()), fonts.song(), Layout.FILL_FONT_PT,
                mid + 6f * Layout.MM, top - 10.5f * Layout.MM, BLACK);
        text(cs, "纳税人识别号：" + nullToEmpty(data.getSellerTaxId()), fonts.song(), Layout.FILL_FONT_PT,
                mid + 6f * Layout.MM, top - 16.5f * Layout.MM, BLACK);

        // 地址电话 / 银行账号（§1.6 展示开关）
        float contactY = bottom + 4f * Layout.MM;
        if (data.isShowBuyerContact() || data.isShowBuyerBank()) {
            text(cs, buyerContact(), fonts.song(), Layout.FILL_FONT_PT, cellL, contactY, BLACK);
        }
        if (data.isShowSellerContact() || data.isShowSellerBank()) {
            text(cs, sellerContact(), fonts.song(), Layout.FILL_FONT_PT, mid + 6f * Layout.MM, contactY, BLACK);
        }
    }

    // ===== 应税明细与合计 =====

    private String buyerContact() {
        StringBuilder sb = new StringBuilder();
        if (data.isShowBuyerContact()) {
            sb.append("购方地址:").append(nullToEmpty(data.getBuyerAddr()))
                    .append("; 电话:").append(nullToEmpty(data.getBuyerTel())).append("; ");
        }
        if (data.isShowBuyerBank()) {
            sb.append("购方开户银行:").append(nullToEmpty(data.getBuyerBank()))
                    .append("; 银行账号:").append(nullToEmpty(data.getBuyerAccount())).append(";");
        }
        return sb.toString();
    }

    private String sellerContact() {
        StringBuilder sb = new StringBuilder();
        if (data.isShowSellerContact()) {
            sb.append("销方地址:").append(nullToEmpty(data.getSellerAddr()))
                    .append("; 电话:").append(nullToEmpty(data.getSellerTel())).append("; ");
        }
        if (data.isShowSellerBank()) {
            sb.append("销方开户银行:").append(nullToEmpty(data.getSellerBank()))
                    .append("; 银行账号:").append(nullToEmpty(data.getSellerAccount())).append(";");
        }
        return sb.toString();
    }

    private void drawDetailTable(PDPageContentStream cs, FontLoader fonts) throws IOException {
        float tableTop = layout.y(Layout.DETAIL_TOP_Y_MM);
        float priceTotalTop = layout.priceTotalTopY();
        float left = layout.x(Layout.FRAME_LEFT_MM);
        float right = layout.x(Layout.FRAME_RIGHT_MM);

        // 外框
        cs.setStrokingColor(ELEMENT);
        cs.setLineWidth(Layout.LINE_THICK_MM * Layout.MM);
        cs.addRect(left, priceTotalTop, right - left, tableTop - priceTotalTop);
        cs.stroke();

        // 列竖线
        for (int i = 1; i < COL_X.length - 1; i++) {
            cs.moveTo(COL_X[i], tableTop);
            cs.lineTo(COL_X[i], priceTotalTop);
        }

        // 表头底线（表头高 4.5mm）
        float headerBottom = layout.y(Layout.DETAIL_TOP_Y_MM + Layout.TOTAL_ROW_HEIGHT_MM);
        cs.moveTo(left, headerBottom);
        cs.lineTo(right, headerBottom);
        cs.stroke();

        // 列名
        String[] headers = {"项目名称", "规格型号", "单位", "数量", "单价", "金额", "税率/征收率", "税额"};
        boolean[] center = {false, false, true, false, false, false, true, false};
        boolean[] rightAlign = {false, false, false, true, true, true, false, true};
        float headBase = headerBottom + Layout.TOTAL_ROW_HEIGHT_MM * Layout.MM * 0.5f - Layout.ELEMENT_FONT_PT * 0.3f;
        for (int i = 0; i < headers.length; i++) {
            float cx = (COL_X[i] + COL_X[i + 1]) / 2f;
            if (center[i]) {
                textCenter(cs, headers[i], fonts.kai(), Layout.ELEMENT_FONT_PT, cx, headBase, ELEMENT);
            } else if (rightAlign[i]) {
                textRight(cs, headers[i], fonts.kai(), Layout.ELEMENT_FONT_PT, COL_X[i + 1] - 1f * Layout.MM, headBase, ELEMENT);
            } else {
                text(cs, headers[i], fonts.kai(), Layout.ELEMENT_FONT_PT, COL_X[i] + 1f * Layout.MM, headBase, ELEMENT);
            }
        }

        // 明细行（自表头下沿向下，每行 4.375mm）
        float dataTop = headerBottom;
        float rowH = Layout.DETAIL_ROW_MM * Layout.MM;
        int n = data.getDetails().size();
        for (int r = 0; r < n; r++) {
            float cellTop = dataTop - (r + 1) * rowH;
            float base = cellTop + rowH * 0.5f + Layout.FILL_FONT_PT * 0.3f;
            DetailRow row = data.getDetails().get(r);
            drawCell(cs, fonts, 0, row.getName(), false, base);
            drawCell(cs, fonts, 1, row.getSpec(), false, base);
            drawCell(cs, fonts, 2, row.getUnit(), true, base);
            drawCell(cs, fonts, 3, row.getQuantity(), true, base);
            drawCell(cs, fonts, 4, row.getPrice(), true, base);
            drawCell(cs, fonts, 5, row.getAmount(), true, base);
            drawCell(cs, fonts, 6, row.getTaxRate(), true, base);
            drawCell(cs, fonts, 7, row.getTax(), true, base);
        }

        // 合计行（末行，4.5mm）
        float totalTop = dataTop - Math.max(n, 8) * rowH - 1f;
        float totalBase = totalTop + Layout.TOTAL_ROW_HEIGHT_MM * Layout.MM * 0.5f + Layout.FILL_FONT_PT * 0.3f;
        text(cs, "合计", fonts.kai(), Layout.ELEMENT_FONT_PT, COL_X[0] + 1f * Layout.MM, totalBase, ELEMENT);
        textRight(cs, data.getTotalAmount(), fonts.song(), Layout.FILL_FONT_PT, COL_X[6] - 1f * Layout.MM, totalBase, BLACK);
        textRight(cs, data.getTotalTax(), fonts.song(), Layout.FILL_FONT_PT, COL_X[8] - 1f * Layout.MM, totalBase, BLACK);
        // 合计行上横线
        cs.setStrokingColor(ELEMENT);
        cs.setLineWidth(Layout.LINE_THICK_MM * Layout.MM);
        cs.moveTo(left, totalTop);
        cs.lineTo(right, totalTop);
        cs.stroke();

        // 价税合计行
        drawPriceTotal(cs, fonts);
    }

    private void drawCell(PDPageContentStream cs, FontLoader fonts, int col, String value, boolean center, float base) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        PDFont f = fonts.song();
        if (center) {
            float cx = (COL_X[col] + COL_X[col + 1]) / 2f;
            textCenter(cs, value, f, Layout.FILL_FONT_PT, cx, base, BLACK);
        } else {
            drawAligned(col, value, f, base, cs);
        }
    }

    // ===== 备注 =====

    private void drawAligned(int col, String value, PDFont font, float base, PDPageContentStream cs) throws IOException {
        // 数量/单价/金额/税额 右对齐，其它左对齐
        boolean rightAlign = col == 3 || col == 4 || col == 5 || col == 7;
        if (rightAlign) {
            textRight(cs, value, font, Layout.FILL_FONT_PT, COL_X[col + 1] - 1f * Layout.MM, base, BLACK);
        } else {
            text(cs, value, font, Layout.FILL_FONT_PT, COL_X[col] + 1f * Layout.MM, base, BLACK);
        }
    }

    private void drawPriceTotal(PDPageContentStream cs, FontLoader fonts) throws IOException {
        float top = layout.priceTotalTopY();
        float bottom = layout.detailBottomY();
        float left = layout.x(Layout.FRAME_LEFT_MM);
        float right = layout.x(Layout.FRAME_RIGHT_MM);
        float divider = layout.x(129.5f); // 大写(左) / 小写(右) 分隔

        cs.setStrokingColor(ELEMENT);
        cs.setLineWidth(Layout.LINE_THICK_MM * Layout.MM);
        cs.addRect(left, bottom, right - left, top - bottom);
        cs.moveTo(divider, bottom);
        cs.lineTo(divider, top);
        cs.stroke();

        float base = bottom + Layout.PRICETOTAL_ROW_MM * Layout.MM * 0.5f + Layout.FILL_FONT_PT * 0.3f;
        text(cs, "价税合计（大写）", fonts.kai(), Layout.ELEMENT_FONT_PT, left + 1f * Layout.MM, base, ELEMENT);
        text(cs, data.getTotalAmountUpper(), fonts.song(), Layout.FILL_FONT_PT, left + 30f * Layout.MM, base, BLACK);
        text(cs, "价税合计（小写）", fonts.kai(), Layout.ELEMENT_FONT_PT, divider + 1f * Layout.MM, base, ELEMENT);
        textRight(cs, "¥" + stripYuan(data.getTotalAmountLower()), fonts.courier(), Layout.YUAN_FONT_PT,
                right - 1f * Layout.MM, base, BLACK);
    }

    // ===== 票尾 =====

    private void drawRemark(PDPageContentStream cs, FontLoader fonts) throws IOException {
        float bottom = layout.noteBottomCenterY();
        float top = bottom + Layout.NOTE_HEIGHT_MM * Layout.MM;
        float left = layout.x(Layout.FRAME_LEFT_MM);
        float right = layout.x(Layout.FRAME_RIGHT_MM);
        cs.setStrokingColor(ELEMENT);
        cs.setLineWidth(Layout.LINE_THICK_MM * Layout.MM);
        cs.addRect(left, bottom, right - left, top - bottom);
        cs.stroke();

        text(cs, "备注", fonts.kai(), Layout.ELEMENT_FONT_PT, left + Layout.NOTE_COL_W_MM[0] * Layout.MM * 0.5f, top - 4f * Layout.MM, ELEMENT);

        String remark = data.getRemark();
        if (remark == null || remark.isBlank()) {
            return;
        }
        float x = left + Layout.NOTE_COL_W_MM[0] * Layout.MM + 1f * Layout.MM;
        float y = top - 5f * Layout.MM;
        float maxW = right - x - 1f * Layout.MM;
        // 按换行符拆分；过长自动折行
        for (String line : remark.split("\n", -1)) {
            drawWrapped(cs, fonts, line, x, y, maxW);
            y -= 4.2f * Layout.MM;
        }
    }

    // ===== 右框色带 =====

    private void drawWrapped(PDPageContentStream cs, FontLoader fonts, String text, float x, float y, float maxW)
            throws IOException {
        if (text == null || text.isEmpty()) {
            return;
        }
        PDFont f = fonts.song();
        float size = Layout.FILL_FONT_PT;
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < text.length(); ) {
            int cp = text.codePointAt(i);
            String ch = new String(Character.toChars(cp));
            if (f.getStringWidth(line.toString() + ch) / 1000f * size <= maxW) {
                line.append(ch);
            } else {
                cs.setFont(f, size);
                cs.setNonStrokingColor(BLACK);
                cs.beginText();
                cs.newLineAtOffset(x, y);
                cs.showText(line.toString());
                cs.endText();
                line = new StringBuilder(ch);
                y -= 4.2f * Layout.MM;
            }
            i += Character.charCount(cp);
        }
        if (line.length() > 0) {
            cs.setFont(f, size);
            cs.setNonStrokingColor(BLACK);
            cs.beginText();
            cs.newLineAtOffset(x, y);
            cs.showText(line.toString());
            cs.endText();
        }
    }

    // ===== 红字水印（附件三：简化为斜向"红字"水印）=====

    private void drawFooter(PDPageContentStream cs, FontLoader fonts) throws IOException {
        float y = layout.footerY();
        text(cs, "开票人：" + nullToEmpty(data.getDrawer()), fonts.song(), Layout.FILL_FONT_PT,
                layout.x(Layout.FRAME_LEFT_MM) + 1f * Layout.MM, y, BLACK);
    }

    // ===== 文本助手 =====

    private void drawRightColorBands(PDPageContentStream cs) throws IOException {
        float rightX = layout.x(Layout.FRAME_RIGHT_MM);
        float bottom = layout.y(Layout.PAGE_MIN_HEIGHT_MM - 4.5f); // 右框线下 1/2 起
        float half = (layout.pageHeightPt() - 2 * layout.x(4.5f)) / 2f;
        float bandH = 0.54f * Layout.MM;
        float len = half;
        // 金色带
        cs.setNonStrokingColor(new Color(Layout.COLOR_GOLD[0], Layout.COLOR_GOLD[1], Layout.COLOR_GOLD[2]));
        cs.addRect(rightX - 1f * Layout.MM, bottom - bandH, 1f * Layout.MM, bandH);
        cs.fill();
        // 咖色带
        cs.setNonStrokingColor(new Color(Layout.COLOR_BROWN[0], Layout.COLOR_BROWN[1], Layout.COLOR_BROWN[2]));
        cs.addRect(rightX - 1f * Layout.MM, bottom - 2 * bandH, 1f * Layout.MM, bandH);
        cs.fill();
    }

    private void drawRedWatermark(PDPageContentStream cs, FontLoader fonts) throws IOException {
        cs.setFont(fonts.kai(), 48f);
        cs.setNonStrokingColor(new Color(255, 0, 0, 60));
        float cx = layout.pageWidthPt() / 2f;
        float cy = layout.pageHeightPt() / 2f;
        for (int row = -1; row <= 1; row++) {
            for (int col = -1; col <= 1; col++) {
                float x = cx + col * 70f;
                float y = cy + row * 60f;
                cs.beginText();
                cs.setTextMatrix(org.apache.pdfbox.util.Matrix.getRotateInstance(-0.4, x, y));
                cs.showText("红字");
                cs.endText();
            }
        }
    }

    private void text(PDPageContentStream cs, String s, PDFont f, float size, float x, float y, Color c) throws IOException {
        if (s == null || s.isEmpty()) {
            return;
        }
        cs.setFont(f, size);
        cs.setNonStrokingColor(c);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(s);
        cs.endText();
    }

    private void textRight(PDPageContentStream cs, String s, PDFont f, float size, float xRight, float y, Color c) throws IOException {
        if (s == null || s.isEmpty()) {
            return;
        }
        float w = f.getStringWidth(s) / 1000f * size;
        text(cs, s, f, size, xRight - w, y, c);
    }

    private void textCenter(PDPageContentStream cs, String s, PDFont f, float size, float xCenter, float y, Color c) throws IOException {
        if (s == null || s.isEmpty()) {
            return;
        }
        float w = f.getStringWidth(s) / 1000f * size;
        text(cs, s, f, size, xCenter - w / 2f, y, c);
    }
}
