package com.yhq.invoice.common.enums;

/**
 * 发票类型代码（fplx）：见基础版 V6.006 文档 §17.2 请求参数。
 * <ul>
 *     <li>01：增值税专用发票</li>
 *     <li>02：货物运输业增值税专用发票</li>
 *     <li>03：机动车销售统一发票</li>
 *     <li>04：增值税普通发票</li>
 *     <li>08：增值税电子专用发票</li>
 *     <li>10：增值税电子普通发票</li>
 *     <li>11：卷式发票</li>
 *     <li>14：通行费发票</li>
 *     <li>15：二手车销售统一发票</li>
 *     <li>51：电子发票（铁路电子客票）</li>
 *     <li>61：电子发票（航空运输客票电子行程单）</li>
 *     <li>81：电子发票（增值税专用发票）</li>
 *     <li>82：电子发票（普通发票）</li>
 *     <li>83：机动车销售电子统一发票</li>
 *     <li>84：二手车销售电子统一发票</li>
 *     <li>85：纸质发票（增值税专用发票）</li>
 *     <li>86：纸质发票（普通发票）</li>
 *     <li>87：纸质发票（机动车销售统一发票）</li>
 *     <li>88：纸质发票（二手车销售统一发票）</li>
 * </ul>
 */
public enum Fplx {
    VAT_SPECIAL("01", "增值税专用发票"),
    FREIGHT_VAT_SPECIAL("02", "货物运输业增值税专用发票"),
    MOTOR_VEHICLE_SALE("03", "机动车销售统一发票"),
    VAT_GENERAL("04", "增值税普通发票"),
    VAT_ELECTRONIC_SPECIAL("08", "增值税电子专用发票"),
    VAT_ELECTRONIC_GENERAL("10", "增值税电子普通发票"),
    ROLL("11", "卷式发票"),
    TOLL("14", "通行费发票"),
    USED_CAR_SALE("15", "二手车销售统一发票"),
    ELECTRONIC_RAILWAY("51", "电子发票（铁路电子客票）"),
    ELECTRONIC_AIR("61", "电子发票（航空运输客票电子行程单）"),
    ELECTRONIC_VAT_SPECIAL("81", "电子发票（增值税专用发票）"),
    ELECTRONIC_GENERAL("82", "电子发票（普通发票）"),
    ELECTRONIC_MOTOR_VEHICLE_SALE("83", "机动车销售电子统一发票"),
    ELECTRONIC_USED_CAR_SALE("84", "二手车销售电子统一发票"),
    PAPER_VAT_SPECIAL("85", "纸质发票（增值税专用发票）"),
    PAPER_GENERAL("86", "纸质发票（普通发票）"),
    PAPER_MOTOR_VEHICLE_SALE("87", "纸质发票（机动车销售统一发票）"),
    PAPER_USED_CAR_SALE("88", "纸质发票（二手车销售统一发票）");

    private final String code;
    private final String desc;

    Fplx(String code, String desc) {
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
