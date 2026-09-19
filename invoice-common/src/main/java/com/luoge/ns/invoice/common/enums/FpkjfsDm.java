package com.luoge.ns.invoice.common.enums;

/**
 * 发票开具方式代码（fpkjfsDm）：取值范围 "4"(第三方平台开票) / "5"(自建平台开票)。
 * 见 {@code BaseUploadInvoiceReq#fpkjfsDm}。
 */
public enum FpkjfsDm {
    THIRD_PARTY("4", "第三方平台开票（乐企联用、他用开票）"),
    SELF_BUILT("5", "自建平台开票（乐企自用开票）");

    private final String code;
    private final String desc;

    FpkjfsDm(String code, String desc) {
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
