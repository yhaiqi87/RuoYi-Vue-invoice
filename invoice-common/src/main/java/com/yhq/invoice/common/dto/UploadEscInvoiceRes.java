package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 乐企响应：二手车销售统一发票上传（ESCXSTYFPSC）。
 * 字段依据《乐企数字化电子发票（二手车）开票能力说明文档-V1.003》第 12 节。
 */
@Getter
@Setter
public class UploadEscInvoiceRes extends InvoiceResponse {
    /**
     * 受理流水号（String，40，是）
     */
    private String sllsh;
}
