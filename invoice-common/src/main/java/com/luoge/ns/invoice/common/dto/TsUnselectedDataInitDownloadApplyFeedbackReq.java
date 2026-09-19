package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 退税勾选请求：未勾选数据初始化清单下载申请反馈（WGXSJCSHQDXZSQFKTSGX）。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 6「未勾选数据初始化清单下载申请反馈」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class TsUnselectedDataInitDownloadApplyFeedbackReq extends TsRequest {

    public TsUnselectedDataInitDownloadApplyFeedbackReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 批次流水号（String，32，必填）。
     */
    @NotBlank(message = "批次流水号(pclsh)为必填项")
    @Size(max = 32, message = "批次流水号(pclsh)长度不能超过 32")
    private String pclsh;

    /**
     * 包号（String，5，必填），当数据量过大时会进行分包处理，初始包号 1。
     */
    @NotBlank(message = "包号(packageno)为必填项")
    @Size(max = 5, message = "包号(packageno)长度不能超过 5")
    private String packageno;
}
