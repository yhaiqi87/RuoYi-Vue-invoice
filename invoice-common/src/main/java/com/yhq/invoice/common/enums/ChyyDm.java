package com.yhq.invoice.common.enums;

/**
 * 红字发票冲红原因代码（chyyDm）：取值范围 "01"(开票有误) / "02"(销货退回) /
 * "03"(服务中止) / "04"(销售折让)。见 {@code ApplyRedConfirmReq#chyyDm}。
 */
public enum ChyyDm {
    INVOICE_ERROR("01", "开票有误"),
    SALE_RETURN("02", "销货退回"),
    SERVICE_STOP("03", "服务中止"),
    SALES_DISCOUNT("04", "销售折让");

    private final String code;
    private final String desc;

    ChyyDm(String code, String desc) {
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
