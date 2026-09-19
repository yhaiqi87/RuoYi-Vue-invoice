package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 发票入账响应：批量上传入账发票（PLSCRZFP）。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 1「批量上传入账发票」返回参数补全。
 */
@Getter
@Setter
public class RzBatchUploadInvoiceRes extends RzResponse {

    /**
     * 批次流水号（String，32，是）
     */
    private String pclsh;
}
