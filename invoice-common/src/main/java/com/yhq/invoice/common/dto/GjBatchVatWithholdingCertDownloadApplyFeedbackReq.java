package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 归集请求：批量增值税代扣代缴完税凭证下载申请反馈（PLZZSDKDJWSPZXZSQFK）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 9「批量增值税代扣代缴完税凭证下载申请反馈」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GjBatchVatWithholdingCertDownloadApplyFeedbackReq extends GjRequest {

    public GjBatchVatWithholdingCertDownloadApplyFeedbackReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 批次流水号（String，32，必填）
     */
    @NotBlank(message = "批次流水号(pclsh)为必填项")
    @Size(max = 32, message = "批次流水号(pclsh)长度不能超过 32")
    private String pclsh;
    /**
     * 包号（String，5，必填，初次为 1）
     */
    @NotBlank(message = "包号(packageno)为必填项")
    @Size(max = 5, message = "包号(packageno)长度不能超过 5")
    private String packageno;
}
