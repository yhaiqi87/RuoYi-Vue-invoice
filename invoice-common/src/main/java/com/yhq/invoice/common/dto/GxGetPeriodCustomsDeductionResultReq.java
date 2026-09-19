package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：获取税款所属期海关缴款书抵扣勾选处理结果（HQDQSKSSQHGJKSDKGXCLJG）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 11「获取税款所属期海关缴款书抵扣勾选处理结果」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxGetPeriodCustomsDeductionResultReq extends GxRequest {

    public GxGetPeriodCustomsDeductionResultReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，必填）。
     */
    @NotBlank(message = "购买方统一社会信用代码/纳税人识别号(gfsbh)为必填项")
    @Size(max = 30, message = "购买方统一社会信用代码/纳税人识别号(gfsbh)长度不能超过 30")
    private String gfsbh;

    /**
     * 税款所属期（String，6，否）。
     */
    @Size(max = 6, message = "税款所属期(skssq)长度不能超过 6")
    private String skssq;

    /**
     * 缴款书号码（String，22，否）。
     */
    @Size(max = 22, message = "缴款书号码(jkshm)长度不能超过 22")
    private String jkshm;

    /**
     * 出口转内销证明编号（String，30，否）。
     */
    @Size(max = 30, message = "出口转内销证明编号(ckznxzmbh)长度不能超过 30")
    private String ckznxzmbh;
}
