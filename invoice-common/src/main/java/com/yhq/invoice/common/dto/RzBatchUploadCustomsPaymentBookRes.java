package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 发票入账响应：批量上传入账海关缴款书（PLSCRZHGJKS）。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 5「批量上传入账海关缴款书」返回参数补全。
 */
@Getter
@Setter
public class RzBatchUploadCustomsPaymentBookRes extends RzResponse {

    /**
     * 批次流水号（String，32，是）
     */
    private String pclsh;
}
