package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：申请确认抵扣统计（SQQRDKTJ）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 8「申请确认抵扣统计」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxApplyConfirmDeductionStatReq extends GxRequest {

    public GxApplyConfirmDeductionStatReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，必填）。
     */
    @NotBlank(message = "购买方统一社会信用代码/纳税人识别号(gfsbh)为必填项")
    @Size(max = 30, message = "购买方统一社会信用代码/纳税人识别号(gfsbh)长度不能超过 30")
    private String gfsbh;

    /**
     * 申请标志（String，1，必填）。
     */
    @NotBlank(message = "申请标志(sqbz)为必填项")
    @Size(max = 1, message = "申请标志(sqbz)长度不能超过 1")
    private String sqbz;

    /**
     * 税款所属期（String，6，否）。
     */
    @Size(max = 6, message = "税款所属期(skssq)长度不能超过 6")
    private String skssq;
}
