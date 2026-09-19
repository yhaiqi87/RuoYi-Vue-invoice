package com.yhq.invoice.common.enums;

/**
 * 发票票种代码（fppzDm）：见基础版 V6.006 文档 §11.2 请求参数。
 * <ul>
 *     <li>01：增值税专用发票</li>
 *     <li>02：普通发票</li>
 *     <li>03：机动车统一销售发票</li>
 *     <li>04：二手车统一销售发票</li>
 *     <li>05：铁路客票电子发票</li>
 *     <li>06：航空运输电子客票行程单</li>
 * </ul>
 */
public enum FppzDm {
    VAT_SPECIAL("01", "增值税专用发票"),
    GENERAL("02", "普通发票"),
    MOTOR_VEHICLE("03", "机动车统一销售发票"),
    USED_CAR("04", "二手车统一销售发票"),
    RAILWAY_ELECTRONIC("05", "铁路客票电子发票"),
    AIR_ELECTRONIC("06", "航空运输电子客票行程单");

    private final String code;
    private final String desc;

    FppzDm(String code, String desc) {
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
