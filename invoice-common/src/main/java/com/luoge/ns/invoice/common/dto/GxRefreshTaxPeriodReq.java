package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：刷新税款所属期（SXSKSSQ）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 26「刷新税款所属期」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxRefreshTaxPeriodReq extends GxRequest {

    public GxRefreshTaxPeriodReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购方识别号（String，30，必填）。
     */
    @NotBlank(message = "购方识别号(gfsbh)为必填项")
    @Size(max = 30, message = "购方识别号(gfsbh)长度不能超过 30")
    private String gfsbh;

    /**
     * 追溯期标志（String，1，否）。
     */
    @Size(max = 1, message = "追溯期标志(zsqbz)长度不能超过 1")
    private String zsqbz;
}
