package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：申请税款所属期变更（SQSKSSQBG）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 24「申请税款所属期变更」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxApplyPeriodChangeReq extends GxRequest {

    public GxApplyPeriodChangeReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购方纳税人识别号/统一社会信用代码（String，30，必填）。
     */
    @NotBlank(message = "购方纳税人识别号/统一社会信用代码(gfsbh)为必填项")
    @Size(max = 30, message = "购方纳税人识别号/统一社会信用代码(gfsbh)长度不能超过 30")
    private String gfsbh;

    /**
     * 变更属期（String，6，必填）。
     */
    @NotBlank(message = "变更属期(bgsq)为必填项")
    @Size(max = 6, message = "变更属期(bgsq)长度不能超过 6")
    private String bgsq;
}
