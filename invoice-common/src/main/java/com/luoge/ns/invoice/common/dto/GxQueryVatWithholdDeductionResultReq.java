package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：查询增值税代扣代缴完税凭证抵扣勾选处理结果（CXZZSDKDJWSPZDKGXCLJG）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 13「查询增值税代扣代缴完税凭证抵扣勾选处理结果」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxQueryVatWithholdDeductionResultReq extends GxRequest {

    public GxQueryVatWithholdDeductionResultReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 批次流水号（String，32，必填）。
     */
    @NotBlank(message = "批次流水号(pclsh)为必填项")
    @Size(max = 32, message = "批次流水号(pclsh)长度不能超过 32")
    private String pclsh;
}
