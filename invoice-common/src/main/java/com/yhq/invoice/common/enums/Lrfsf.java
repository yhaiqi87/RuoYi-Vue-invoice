package com.yhq.invoice.common.enums;

/**
 * 录入方身份（lrfsf）：取值范围 "0"(销方) / "1"(购方)。见 {@code ApplyRedConfirmReq#lrfsf}。
 */
public enum Lrfsf {
    SELLER("0", "销方"),
    BUYER("1", "购方");

    private final String code;
    private final String desc;

    Lrfsf(String code, String desc) {
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
