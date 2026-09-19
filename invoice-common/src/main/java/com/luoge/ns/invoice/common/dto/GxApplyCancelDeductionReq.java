package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：申请注销勾选（SQZXGX）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 25「申请注销勾选」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxApplyCancelDeductionReq extends GxRequest {

    public GxApplyCancelDeductionReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购买方纳税人识别号/统一社会信用代码（String，30，必填）。
     */
    @NotBlank(message = "购买方纳税人识别号/统一社会信用代码(gfsbh)为必填项")
    @Size(max = 30, message = "购买方纳税人识别号/统一社会信用代码(gfsbh)长度不能超过 30")
    private String gfsbh;

    /**
     * 申请标志（String，1，必填）。
     */
    @NotBlank(message = "申请标志(sqbz)为必填项")
    @Size(max = 1, message = "申请标志(sqbz)长度不能超过 1")
    private String sqbz;
}
