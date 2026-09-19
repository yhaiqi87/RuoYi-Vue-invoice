package com.luoge.ns.invoice.common.enums;

/**
 * 红字确认单信息状态代码（hzqrxxztDm）：见基础版 V6.006 文档 §9.2 / §11.2 请求参数。
 * <ul>
 *     <li>01：无需确认</li>
 *     <li>02：销方录入待购方确认</li>
 *     <li>03：购方录入待销方确认</li>
 *     <li>04：购销双方已确认</li>
 *     <li>05：作废（销方录入购方否认）</li>
 *     <li>06：作废（购方录入销方否认）</li>
 *     <li>07：作废（超72小时未确认）</li>
 *     <li>08：作废（发起方撤销）</li>
 *     <li>09：作废（确认后撤销）</li>
 *     <li>10：作废（异常凭证）</li>
 *     <li>11：作废（纳税人状态异常阻断）</li>
 *     <li>12：作废（自然人拒收）</li>
 *     <li>13：作废（已开具退税申请单）</li>
 * </ul>
 */
public enum HzqrxxztDm {
    NO_CONFIRM("01", "无需确认"),
    SELLER_WAIT_BUYER("02", "销方录入待购方确认"),
    BUYER_WAIT_SELLER("03", "购方录入待销方确认"),
    BOTH_CONFIRMED("04", "购销双方已确认"),
    CANCEL_SELLER_DENY("05", "作废（销方录入购方否认）"),
    CANCEL_BUYER_DENY("06", "作废（购方录入销方否认）"),
    CANCEL_TIMEOUT("07", "作废（超72小时未确认）"),
    CANCEL_INITIATOR("08", "作废（发起方撤销）"),
    CANCEL_AFTER_CONFIRM("09", "作废（确认后撤销）"),
    CANCEL_ABNORMAL("10", "作废（异常凭证）"),
    CANCEL_TAXPAYER_BLOCK("11", "作废（纳税人状态异常阻断）"),
    CANCEL_NATURAL_REJECT("12", "作废（自然人拒收）"),
    CANCEL_REFUND_APPLIED("13", "作废（已开具退税申请单）");

    private final String code;
    private final String desc;

    HzqrxxztDm(String code, String desc) {
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
