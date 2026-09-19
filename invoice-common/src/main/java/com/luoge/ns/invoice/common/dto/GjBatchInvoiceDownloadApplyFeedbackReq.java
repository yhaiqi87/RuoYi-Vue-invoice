package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 归集请求：批量发票下载申请反馈（PLFPXZSQFK）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 2「批量发票下载申请反馈」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GjBatchInvoiceDownloadApplyFeedbackReq extends GjRequest {

    public GjBatchInvoiceDownloadApplyFeedbackReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 流水号（String，32，必填）
     */
    @NotBlank(message = "流水号(lsh)为必填项")
    @Size(max = 32, message = "流水号(lsh)长度不能超过 32")
    private String lsh;
    /**
     * 包号（String，5，必填，首次传 1，按返回总包数顺序下载）
     */
    @NotBlank(message = "包号(packageno)为必填项")
    @Size(max = 5, message = "包号(packageno)长度不能超过 5")
    private String packageno;
}
