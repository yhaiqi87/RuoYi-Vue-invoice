package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 退税勾选请求：查询发票退税勾选处理结果（CXFPTSGXQRCLJG）。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 2「查询发票退税勾选处理结果」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class TsQueryInvoiceRefundResultReq extends TsRequest {

    public TsQueryInvoiceRefundResultReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 批次流水号（String，32，必填）。
     */
    @NotBlank(message = "批次流水号(pclsh)为必填项")
    @Size(max = 32, message = "批次流水号(pclsh)长度不能超过 32")
    private String pclsh;
}
