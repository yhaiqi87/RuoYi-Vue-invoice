package com.yhq.invoice.banstyle.draw.seal;

import com.yhq.invoice.banstyle.draw.font.FontLoader;
import com.yhq.invoice.banstyle.draw.layout.Layout;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.util.Matrix;

import java.awt.*;
import java.io.IOException;

/**
 * 发票监制章绘制（规范 §1.3 + 附件二）。
 * 椭圆 30×20mm，外圈 1mm，内加刻一细线；上环「全国统一发票监制章」、中间「国家税务总局」、下环「xx税务局」，楷体 7pt 大红。
 * 因 PDFBox 无原生椭圆 API，椭圆以 4 段三次贝塞尔曲线近似。
 */
public final class SupervisionSeal {

    private static final float KAPPA = 0.5522847498f;

    private SupervisionSeal() {
    }

    public static void draw(PDPageContentStream cs, Layout layout, FontLoader fonts, String taxBureauName)
            throws IOException {
        float cx = layout.x(Layout.SEAL_CENTER_X_MM);
        float cy = layout.y(Layout.SEAL_CENTER_Y_MM);
        float rx = Layout.SEAL_WIDTH_MM * Layout.MM / 2f;
        float ry = Layout.SEAL_HEIGHT_MM * Layout.MM / 2f;

        Color red = new Color(Layout.COLOR_SEAL_RED[0], Layout.COLOR_SEAL_RED[1], Layout.COLOR_SEAL_RED[2]);

        // 外圈（1mm 粗）
        cs.setStrokingColor(red);
        cs.setLineWidth(Layout.SEAL_RING_THICK_MM * Layout.MM);
        ellipsePath(cs, cx, cy, rx, ry);
        cs.stroke();

        // 内细线（内缩约 2mm）
        float inset = 2f * Layout.MM;
        cs.setLineWidth(0.3f * Layout.MM);
        ellipsePath(cs, cx, cy, rx - inset, ry - inset);
        cs.stroke();

        float size = Layout.SEAL_FONT_PT;
        PDFont kai = fonts.kai();

        // 上环：全国统一发票监制章
        arcText(cs, cx, cy, rx - inset * 0.4f, ry - inset * 0.4f, 158f, 22f, true,
                "全国统一发票监制章", kai, size, red);
        // 下环：国家税务总局xx税务局
        String bottom = taxBureauName != null ? taxBureauName : "国家税务总局";
        arcText(cs, cx, cy, rx - inset * 0.4f, ry - inset * 0.4f, 202f, 338f, false,
                bottom, kai, size, red);
        // 中间：国家税务总局
        cs.beginText();
        cs.setFont(kai, size);
        cs.setNonStrokingColor(red);
        cs.setTextMatrix(Matrix.getTranslateInstance(cx, cy - size * 0.35f));
        cs.showText("国家税务总局");
        cs.endText();
    }

    /**
     * 椭圆路径（不闭合描边，需调用方 stroke/fill）。
     */
    private static void ellipsePath(PDPageContentStream cs, float cx, float cy, float rx, float ry)
            throws IOException {
        cs.moveTo(cx + rx, cy);
        cs.curveTo(cx + rx, cy + ry * KAPPA, cx + rx * KAPPA, cy + ry, cx, cy + ry);
        cs.curveTo(cx - rx * KAPPA, cy + ry, cx - rx, cy + ry * KAPPA, cx - rx, cy);
        cs.curveTo(cx - rx, cy - ry * KAPPA, cx - rx * KAPPA, cy - ry, cx, cy - ry);
        cs.curveTo(cx + rx * KAPPA, cy - ry, cx + rx, cy - ry * KAPPA, cx + rx, cy);
    }

    /**
     * 沿椭圆弧排布文字。top=true 文字正向（上半弧），false 为下半弧（印章式反向）。
     */
    private static void arcText(PDPageContentStream cs, float cx, float cy, float rx, float ry,
                                float aStartDeg, float aEndDeg, boolean top, String text,
                                PDFont font, float size, Color color) throws IOException {
        int n = text.length();
        if (n == 0) {
            return;
        }
        for (int i = 0; i < n; i++) {
            float a = (float) Math.toRadians(aStartDeg + (aEndDeg - aStartDeg) * i / (n - 1));
            float x = cx + rx * (float) Math.cos(a);
            float y = cy + ry * (float) Math.sin(a);
            double tangent = a + (top ? Math.PI / 2 : -Math.PI / 2);
            cs.beginText();
            cs.setFont(font, size);
            cs.setNonStrokingColor(color);
            cs.setTextMatrix(Matrix.getRotateInstance(tangent, x, y));
            cs.showText(String.valueOf(text.charAt(i)));
            cs.endText();
        }
    }
}
