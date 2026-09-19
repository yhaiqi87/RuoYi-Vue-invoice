package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：获取税款所属期发票抵扣勾选处理结果（HQDQSKSSQFPDKGXCLJG）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 10「获取税款所属期发票抵扣勾选处理结果」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxGetPeriodInvoiceDeductionResultReq extends GxRequest {

    public GxGetPeriodInvoiceDeductionResultReq(CapabilityCode capabilityCode) {
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
     * 发票代码（String，12，否）。
     */
    @Size(max = 12, message = "发票代码(fpdm)长度不能超过 12")
    private String fpdm;

    /**
     * 发票号码（String，20，否）。
     */
    @Size(max = 20, message = "发票号码(fphm)长度不能超过 20")
    private String fphm;

    /**
     * 出口转内销证明编号（String，30，否）。
     */
    @Size(max = 30, message = "出口转内销证明编号(ckznxzmbh)长度不能超过 30")
    private String ckznxzmbh;
}
