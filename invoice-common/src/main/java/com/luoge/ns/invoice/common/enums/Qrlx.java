package com.luoge.ns.invoice.common.enums;

/**
 * 确认类型（qrlx）：取值范围 "Y"(同意) / "N"(不同意) / "C"(撤销)。见 {@code ConfirmRedConfirmReq#qrlx}。
 */
public enum Qrlx {
    AGREE("Y", "同意"),
    DISAGREE("N", "不同意"),
    CANCEL("C", "撤销");

    private final String code;
    private final String desc;

    Qrlx(String code, String desc) {
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
