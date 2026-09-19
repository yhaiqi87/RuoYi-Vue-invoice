package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 乐企响应：UploadInvoice。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class UploadInvoiceRes extends InvoiceResponse {
    /**
     * 受理流水号（类型 String，长度 40，必填 是）
     */
    private String sllsh;
}
