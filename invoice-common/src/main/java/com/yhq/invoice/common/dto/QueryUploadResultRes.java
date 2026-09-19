package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 乐企响应：QueryUploadResult。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class QueryUploadResultRes extends InvoiceResponse {
    private String fphm;
    private String status;
    private String message;
    private String Code;
}
