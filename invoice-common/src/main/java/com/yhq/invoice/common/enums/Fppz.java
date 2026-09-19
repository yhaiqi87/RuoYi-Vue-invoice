package com.yhq.invoice.common.enums;

/**
 * 发票票种（fppz）：取值范围 "01" / "02"。见 {@code BaseUploadInvoiceReq#fppz}。
 */
public enum Fppz {
    SPECIAL("01", "数电专票"),
    GENERAL("02", "数电普票");

    private final String code;
    private final String desc;

    Fppz(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
