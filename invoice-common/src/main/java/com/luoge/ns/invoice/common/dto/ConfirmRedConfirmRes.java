package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 乐企响应：ConfirmRedConfirm。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class ConfirmRedConfirmRes extends InvoiceResponse {
    private String hzfpxxqrdbh;
    private String uuid;
    private String hzqrxxztDm;
    /**
     * 确认日期（类型 datetime，必填 否，格式：yyyy-MM-dd HH:mm:ss）
     */
    private String qrrq;
}
