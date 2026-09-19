package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 归集响应：批量增值税代扣代缴完税凭证下载申请（PLZZSDKDJWSPZXZSQ）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 8「批量增值税代扣代缴完税凭证下载申请」返回参数补全。
 */
@Getter
@Setter
public class GjBatchVatWithholdingCertDownloadApplyRes extends GjResponse {
    /**
     * 批次流水号（String，32，否，返回码非 00 时不返回）
     */
    private String pclsh;
}
