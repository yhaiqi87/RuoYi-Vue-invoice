package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：获取当前税款所属期与当期税款所属期统计状态（HQDQSKSSQYDQSKSSQTJZT）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 1「获取当前税款所属期与当期税款所属期统计状态」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxGetCurPeriodAndStatReq extends GxRequest {

    public GxGetCurPeriodAndStatReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，必填）。
     */
    @NotBlank(message = "购买方统一社会信用代码/纳税人识别号(gfsbh)为必填项")
    @Size(max = 30, message = "购买方统一社会信用代码/纳税人识别号(gfsbh)长度不能超过 30")
    private String gfsbh;
}
