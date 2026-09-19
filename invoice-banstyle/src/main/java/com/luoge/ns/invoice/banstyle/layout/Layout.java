package com.luoge.ns.invoice.banstyle.layout;

/**
 * 数字化电子发票（基础版）版面几何与坐标引擎。
 *
 * <p>规范依据：《数字化电子发票版式及XML规范V6.000》§三（一）1.1~1.7。
 * 所有尺寸以毫米(mm)为基准常量，渲染时统一换算为 PDF 用户坐标(pt，1pt=1/72inch，1mm=72/25.4pt)。
 * PDF 坐标原点在左下角、y 轴向上；而规范描述多用「距票面上边缘/左边缘」，
 * 故提供 {@link #x(double)}（距左缘 mm→pt）与 {@link #y(double)}（距上缘 mm→pt）两个换算助手。
 *
 * <p>页面高度随明细行数动态增长：n≤8 时 140mm；超过 8 行每行按 4.375mm 递增（与 §1.5 各区间一致）。
 */
public final class Layout {

    /**
     * 1mm 对应的 PDF 点数（pt）。
     */
    public static final float MM = 72f / 25.4f;

    // ===== 页面与外框 =====
    public static final float PAGE_WIDTH_MM = 210f;
    public static final float PAGE_MIN_HEIGHT_MM = 140f;
    /**
     * 内框宽 201mm，水平居中 → 左右边距各 4.5mm。
     */
    public static final float FRAME_LEFT_MM = (PAGE_WIDTH_MM - 201f) / 2f;   // 4.5
    public static final float FRAME_RIGHT_MM = FRAME_LEFT_MM + 201f;          // 205.5
    public static final float FRAME_WIDTH_MM = 201f;

    // ===== 票头双线 =====
    public static final float DOUBLE_LINE_TOP_CENTER_Y_MM = 18f;   // 上沿线中心距上缘
    public static final float DOUBLE_LINE_GAP_MM = 1f;             // 两线中心间隔
    public static final float DOUBLE_LINE_LEN_MM = 73f;            // 水平居中
    public static final float LINE_THICK_MM = 0.25f;              // 线条高度

    // ===== 票头文字 =====
    public static final float HEADER_TEXT_TOP_MM = 8f;            // 文字上边缘距上缘
    public static final float HEADER_FONT_PT = 20f;

    // ===== 发票号码 / 开票日期（右上）=====
    public static final float INVOICE_NO_LEFT_MM = 155f;          // 左缘
    public static final float INVOICE_NO_WIDTH_MM = 20f;
    public static final float INVOICE_NO_ROW_GAP_MM = 3f;

    // ===== 二维码 / 标签（左上）=====
    public static final float QR_LEFT_MM = 7f;
    public static final float QR_TOP_MM = 6f;
    public static final float QR_SIZE_MM = 20f;
    public static final float QR_TAX_SIZE_MM = 5.36f;             // 中心"税"字方块
    public static final float LABEL_LEFT_MM = 29f;
    public static final float LABEL_TOP_MM = 6f;
    public static final float LABEL_WIDTH_MM = 28f;
    public static final float LABEL_HEIGHT_MM = 20f;

    // ===== 监制章 =====
    public static final float SEAL_WIDTH_MM = 30f;
    public static final float SEAL_HEIGHT_MM = 20f;
    public static final float SEAL_CENTER_X_MM = 100.5f;
    public static final float SEAL_CENTER_Y_MM = 18f;
    public static final float SEAL_RING_THICK_MM = 1f;

    // ===== 购销方区域 =====
    public static final float BUYSELL_TOP_Y_MM = 30f;
    public static final float BUYSELL_HEIGHT_MM = 22f;
    /**
     * 4 列宽：6 / 94.5 / 6 / 94.5。
     */
    public static final float[] BUYSELL_COL_W_MM = {6f, 94.5f, 6f, 94.5f};
    public static final float BUYSELL_NAME_TOP_OFFSET_MM = 6.5f;  // 名称距区域上边框
    public static final float BUYSELL_TAXID_BOTTOM_OFFSET_MM = 6.6f; // 税号距区域下边框

    // ===== 应税明细 + 合计区域 =====
    public static final float DETAIL_TOP_Y_MM = BUYSELL_TOP_Y_MM + BUYSELL_HEIGHT_MM; // 52
    public static final float TOTAL_ROW_HEIGHT_MM = 4.5f;         // 列名 / 合计行高
    public static final float PRICETOTAL_ROW_MM = 8f;             // 价税合计行高
    /**
     * 8 列宽：37/24/12/25/25/26/25/27。
     */
    public static final float[] DETAIL_COL_W_MM = {37f, 24f, 12f, 25f, 25f, 26f, 25f, 27f};
    public static final float DETAIL_ROW_MM = 4.375f;             // n≤8 时单行高

    // ===== 备注区域 =====
    public static final float NOTE_HEIGHT_MM = 20f;
    public static final float NOTE_BOTTOM_OFFSET_MM = 16f;        // 底线中心距下缘
    public static final float[] NOTE_COL_W_MM = {6f, 195f};

    // ===== 票尾 =====
    public static final float FOOTER_BOTTOM_MM = 7.5f;            // 开票人下边距下缘

    // ===== 字体 / 颜色（规范 §1.2、§1.3）=====
    public static final float FILL_FONT_PT = 9f;                  // 填充信息宋体
    public static final float ELEMENT_FONT_PT = 9f;               // 版面元素楷体
    public static final float TAXID_FONT_PT = 12f;               // 税号 Courier New
    public static final float YUAN_FONT_PT = 11f;                // ¥ Courier New
    public static final float SEAL_FONT_PT = 7f;                 // 监制章楷体
    public static final float QR_TAX_FONT_PT = 9f;               // 二维码"税"字宋体

    /**
     * 版面元素名称与线条色：红褐 RGB(128,0,0)。
     */
    public static final float[] COLOR_ELEMENT = {128f / 255f, 0f, 0f};
    /**
     * 右框金色带 RGB(246,237,225)。
     */
    public static final float[] COLOR_GOLD = {246f / 255f, 237f / 255f, 225f / 255f};
    /**
     * 右框咖色带 RGB(118,89,84)。
     */
    public static final float[] COLOR_BROWN = {118f / 255f, 89f / 255f, 84f / 255f};
    /**
     * 监制章大红。
     */
    public static final float[] COLOR_SEAL_RED = {255f / 255f, 0f, 0f};
    /**
     * 二维码中心"税"字底浅黄 RGB(250,239,173)。
     */
    public static final float[] COLOR_TAX_YELLOW = {250f / 255f, 239f / 255f, 173f / 255f};

    private final float pageHeightMm;
    private final int rowCount;

    private Layout(float pageHeightMm, int rowCount) {
        this.pageHeightMm = pageHeightMm;
        this.rowCount = rowCount;
    }

    /**
     * 按明细行数计算页面几何。n≤8 高 140mm；之后每行 4.375mm 递增（与 §1.5 各区间一致），超过 42 行仍按单页增长（分页为后续任务）。
     */
    public static Layout of(int rowCount) {
        float extra = Math.max(0, rowCount - 8) * DETAIL_ROW_MM;
        float height = Math.max(PAGE_MIN_HEIGHT_MM, PAGE_MIN_HEIGHT_MM + extra);
        return new Layout(height, rowCount);
    }

    public float pageWidthPt() {
        return PAGE_WIDTH_MM * MM;
    }

    public float pageHeightPt() {
        return pageHeightMm * MM;
    }

    public float pageHeightMm() {
        return pageHeightMm;
    }

    /**
     * 距左缘 mm → PDF x(pt)。
     */
    public float x(double mmFromLeft) {
        return (float) (mmFromLeft * MM);
    }

    /**
     * 距上缘 mm → PDF y(pt)。
     */
    public float y(double mmFromTop) {
        return pageHeightPt() - (float) (mmFromTop * MM);
    }

    /**
     * 备注区底线中心 y（距下缘 16mm）。
     */
    public float noteBottomCenterY() {
        return y(pageHeightMm - NOTE_BOTTOM_OFFSET_MM);
    }

    /**
     * 应税明细+合计区底（= 备注区顶）。
     */
    public float detailBottomY() {
        return y(pageHeightMm - NOTE_BOTTOM_OFFSET_MM - NOTE_HEIGHT_MM);
    }

    /**
     * 价税合计行顶（= 明细区底 - 8mm）。
     */
    public float priceTotalTopY() {
        return detailBottomY() + PRICETOTAL_ROW_MM * MM;
    }

    /**
     * 票尾开票人基线 y。
     */
    public float footerY() {
        return y(pageHeightMm - FOOTER_BOTTOM_MM);
    }
}
