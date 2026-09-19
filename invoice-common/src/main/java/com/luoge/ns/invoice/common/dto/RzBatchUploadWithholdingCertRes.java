package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 发票入账响应：批量上传增值税入账代扣代缴完税凭证（PLSCRZDKDJWSPZ）。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 3「批量上传增值税入账代扣代缴完税凭证」返回参数补全。
 */
@Getter
@Setter
public class RzBatchUploadWithholdingCertRes extends RzResponse {

    /**
     * 批次流水号（String，32，是）
     */
    private String pclsh;
}
