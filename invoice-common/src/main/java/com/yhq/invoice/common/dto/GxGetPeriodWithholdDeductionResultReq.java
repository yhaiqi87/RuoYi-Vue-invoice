package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：获取税款所属期代扣代缴完税凭证抵扣勾选处理结果（HQDQSKSSQDKDJWSPZDKGXCLJG）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 14「获取税款所属期代扣代缴完税凭证抵扣勾选处理结果」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxGetPeriodWithholdDeductionResultReq extends GxRequest {

    public GxGetPeriodWithholdDeductionResultReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 扣缴义务人识别号（String，30，否）。
     */
    @Size(max = 30, message = "扣缴义务人识别号(kjywrsbh)长度不能超过 30")
    private String kjywrsbh;

    /**
     * 税款所属期（String，6，否）。
     */
    @Size(max = 6, message = "税款所属期(skssq)长度不能超过 6")
    private String skssq;

    /**
     * 代扣代缴完税凭证号（String，22，否）。
     */
    @Size(max = 22, message = "代扣代缴完税凭证号(dkdjwspzh)长度不能超过 22")
    private String dkdjwspzh;

    /**
     * 被扣缴义务人统一社会信用代码/纳税人识别号（String，30，否）。
     */
    @Size(max = 30, message = "被扣缴义务人统一社会信用代码/纳税人识别号(bkjnsrsbh)长度不能超过 30")
    private String bkjnsrsbh;
}
