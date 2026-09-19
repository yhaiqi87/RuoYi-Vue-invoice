package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：获取税款所属期代扣代缴完税凭证抵扣勾选处理结果（HQDQSKSSQDKDJWSPZDKGXCLJG）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 14「获取税款所属期代扣代缴完税凭证抵扣勾选处理结果」返回参数补全。
 */
@Getter
@Setter
public class GxGetPeriodWithholdDeductionResultRes extends GxResponse {

    /**
     * 扣缴义务人识别号（String，30，否）。
     */
    private String kjywrsbh;

    /**
     * 税款所属期（String，6，否）。
     */
    private String skssq;

    /**
     * 代扣代缴完税凭证号（String，22，否）。
     */
    private String dkdjwspzh;

    /**
     * 被扣缴义务人统一社会信用代码/纳税人识别号（String，30，否）。
     */
    private String bkjnsrsbh;
}
