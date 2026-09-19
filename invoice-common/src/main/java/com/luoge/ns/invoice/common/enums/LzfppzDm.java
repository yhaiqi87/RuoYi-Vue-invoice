package com.luoge.ns.invoice.common.enums;

/**
 * 蓝字发票票种代码（lzfppzDm）：取值范围 "01"(增值税专用发票) / "02"(普通发票) /
 * "03"(机动车统一销售发票) / "04"(二手车统一销售发票)。见 {@code ApplyRedConfirmReq#lzfppzDm}。
 */
public enum LzfppzDm {
    SPECIAL_VAT("01", "增值税专用发票"),
    GENERAL("02", "普通发票"),
    MOTOR("03", "机动车统一销售发票"),
    USED_CAR("04", "二手车统一销售发票");

    private final String code;
    private final String desc;

    LzfppzDm(String code, String desc) {
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
