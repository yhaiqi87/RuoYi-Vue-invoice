package com.luoge.ns.invoice.common.enums;

/**
 * 是否纸质发票标志（sfzzfpbz）：取值范围 "Y"(纸质发票) / "N"(电子发票)。见 {@code ApplyRedConfirmReq#sfzzfpbz}。
 */
public enum Sfzzfpbz {
    PAPER("Y", "纸质发票"),
    ELECTRONIC("N", "电子发票");

    private final String code;
    private final String desc;

    Sfzzfpbz(String code, String desc) {
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
