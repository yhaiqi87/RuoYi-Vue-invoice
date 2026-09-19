package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 退税勾选请求：查询海关缴款书退税勾选处理结果（CXHGJKSTSGXQRCLJG）。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 4「查询海关缴款书退税勾选处理结果」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class TsQueryCustomsRefundResultReq extends TsRequest {

    public TsQueryCustomsRefundResultReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 批次流水号（String，32，必填）。
     */
    @NotBlank(message = "批次流水号(pclsh)为必填项")
    @Size(max = 32, message = "批次流水号(pclsh)长度不能超过 32")
    private String pclsh;
}
