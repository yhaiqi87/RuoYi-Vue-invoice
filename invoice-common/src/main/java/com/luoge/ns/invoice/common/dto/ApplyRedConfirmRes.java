package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 乐企响应：ApplyRedConfirm。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class ApplyRedConfirmRes extends InvoiceResponse {
    private String fphm;
    private String status;
    private String message;
    private String Code;
    /**
     * 红字确认单编号（类型 varchar，长度 20，必填 否）
     */
    private String hzfpxxqrdbh;
    /**
     * 红字确认单UUID（类型 varchar，长度 32，必填 否）
     */
    private String uuid;
    /**
     * 红字确认单状态（类型 varchar，长度 2，必填 否，01：无需确认02：销方录入待购方确认03：购方录入待销方确认04：购销双方已确认05：作废（销方录入购方否认）06：作废（购方录入销方否认）07：作废（超72小时未确认）08：（发起方撤销）09：作废（确认后撤销）10：作废（异常凭证）11：作废（纳税人状态异常阻断）12：作废（自然人拒收）13：作废（已开具退税申请单））
     */
    private String hzqrxxztDm;
}
