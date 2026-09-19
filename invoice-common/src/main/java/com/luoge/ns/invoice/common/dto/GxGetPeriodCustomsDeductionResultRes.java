package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：获取税款所属期海关缴款书抵扣勾选处理结果（HQDQSKSSQHGJKSDKGXCLJG）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 11「获取税款所属期海关缴款书抵扣勾选处理结果」返回参数补全。
 */
@Getter
@Setter
public class GxGetPeriodCustomsDeductionResultRes extends GxResponse {

    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，必填）。
     */
    private String gfsbh;

    /**
     * 税款所属期（String，6，否）。
     */
    private String skssq;

    /**
     * 缴款书号码（String，22，否）。
     */
    private String jkshm;

    /**
     * 出口转内销证明编号（String，30，否）。
     */
    private String ckznxzmbh;
}
