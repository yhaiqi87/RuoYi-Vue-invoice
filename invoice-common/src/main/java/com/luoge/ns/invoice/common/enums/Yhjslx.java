package com.luoge.ns.invoice.common.enums;

/**
 * 用户角色类型（yhjslx）：取值范围 "0"(销方) / "1"(购方)。见 {@code QueryRedConfirmListReq#yhjslx}。
 */
public enum Yhjslx {
    SELLER("0", "销方"),
    BUYER("1", "购方");

    private final String code;
    private final String desc;

    Yhjslx(String code, String desc) {
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
