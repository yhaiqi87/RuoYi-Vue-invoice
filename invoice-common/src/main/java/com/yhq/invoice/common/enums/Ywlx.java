package com.yhq.invoice.common.enums;

/**
 * 业务类型（ywlx）：取值范围 "0"(确认) / "1"(取消)。见基础版 V6.006 文档 §15.2 请求参数，
 * 对应 {@code UploadSummaryConfirmReq#ywlx}。
 */
public enum Ywlx {
    CONFIRM("0", "确认"),
    CANCEL("1", "取消");

    private final String code;
    private final String desc;

    Ywlx(String code, String desc) {
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
