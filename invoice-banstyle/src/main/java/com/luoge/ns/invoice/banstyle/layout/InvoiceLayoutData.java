package com.luoge.ns.invoice.banstyle.layout;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 渲染用的归一化发票数据，与乐企上传 DTO 解耦，便于单元测试与多数据源接入。
 * 字段均为「已格式化、可直接落版」的字符串。
 */
@Getter
@Setter
public class InvoiceLayoutData {

    /**
     * 应税明细行（已格式化）。
     */
    private final List<DetailRow> details = new ArrayList<>();
    /**
     * 票头文字：电子发票（增值税专用发票）/ 电子发票（普通发票）。
     */
    private String invoiceTitle;
    /**
     * 发票号码。
     */
    private String invoiceNo;
    /**
     * 开票日期，形如 2023 年 01 月 28 日。
     */
    private String issueDate;
    /**
     * 是否红字发票。
     */
    private boolean redLetter;
    /**
     * 监制章下环：国家税务总局xx（区、市）税务局。
     */
    private String taxBureauName;
    /**
     * 票面二维码（已叠加中心"税"字）。
     */
    private java.awt.image.BufferedImage qrImage;
    /**
     * 左上角标签：差额征税-差额开票 / 差额征税-全额开票 / null。
     */
    private String labelText;
    private String sellerName;
    private String sellerTaxId;
    private String buyerName;
    private String buyerTaxId;
    /**
     * 地址电话 / 银行账号展示开关（§1.6）。
     */
    private boolean showSellerContact;
    private boolean showSellerBank;
    private boolean showBuyerContact;
    private boolean showBuyerBank;
    private String sellerAddr;
    private String sellerTel;
    private String sellerBank;
    private String sellerAccount;
    private String buyerAddr;
    private String buyerTel;
    private String buyerBank;
    private String buyerAccount;
    /**
     * 合计金额 / 合计税额（已格式化，含 ¥）。
     */
    private String totalAmount;
    private String totalTax;
    /**
     * 价税合计（大写），可能含「（负数）」前缀。
     */
    private String totalAmountUpper;
    /**
     * 价税合计（小写），含 ¥ 前缀。
     */
    private String totalAmountLower;

    /**
     * 备注（已按 §1.6 顺序拼接）。
     */
    private String remark;
    /**
     * 开票人。
     */
    private String drawer;

    @Getter
    @Setter
    public static class DetailRow {
        private final String name;       // 项目名称
        private final String spec;       // 规格型号
        private final String unit;       // 单位
        private final String quantity;   // 数量
        private final String price;      // 单价
        private final String amount;     // 金额
        private final String taxRate;    // 税率/征收率
        private final String tax;        // 税额

        public DetailRow(String name, String spec, String unit, String quantity,
                         String price, String amount, String taxRate, String tax) {
            this.name = name;
            this.spec = spec;
            this.unit = unit;
            this.quantity = quantity;
            this.price = price;
            this.amount = amount;
            this.taxRate = taxRate;
            this.tax = tax;
        }
    }
}
