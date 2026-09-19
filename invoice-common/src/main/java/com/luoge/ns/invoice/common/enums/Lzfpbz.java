package com.luoge.ns.invoice.common.enums;

/**
 * 蓝字发票标志（lzfpbz）：取值范围 "0"(蓝字) / "1"(红字)。见 {@code BaseUploadInvoiceReq#lzfpbz}。
 */
public enum Lzfpbz {
    BLUE("0", "蓝字发票"),
    RED("1", "红字发票");

    private final String code;
    private final String desc;

    Lzfpbz(String code, String desc) {
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
