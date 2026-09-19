package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 归集请求：发票用途状态信息查询（FPZTXXCX）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 5「发票用途状态信息查询」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GjQueryInvoiceUsageStatusReq extends GjRequest {

    public GjQueryInvoiceUsageStatusReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 发票类型（String，2，必填）
     */
    @NotBlank(message = "发票类型(fplx)为必填项")
    @Size(max = 2, message = "发票类型(fplx)长度不能超过 2")
    private String fplx;
    /**
     * 发票代码（String，12，否，纸质发票类型传代码，其他无需传入）
     */
    @Size(max = 12, message = "发票代码(fpdm)长度不能超过 12")
    private String fpdm;
    /**
     * 发票号码（String，20，必填，数字化电子发票 20 位）
     */
    @NotBlank(message = "发票号码(fphm)为必填项")
    @Size(max = 20, message = "发票号码(fphm)长度不能超过 20")
    private String fphm;
    /**
     * 开票日期（String，8，必填，YYYYMMDD）
     */
    @NotBlank(message = "开票日期(kprq)为必填项")
    @Size(max = 8, message = "开票日期(kprq)长度不能超过 8")
    private String kprq;
}
