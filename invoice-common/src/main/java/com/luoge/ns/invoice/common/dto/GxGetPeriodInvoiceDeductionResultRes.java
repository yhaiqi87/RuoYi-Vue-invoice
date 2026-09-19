package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：获取税款所属期发票抵扣勾选处理结果（HQDQSKSSQFPDKGXCLJG）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 10「获取税款所属期发票抵扣勾选处理结果」返回参数补全。
 */
@Getter
@Setter
public class GxGetPeriodInvoiceDeductionResultRes extends GxResponse {

    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，必填）。
     */
    private String gfsbh;

    /**
     * 税款所属期（String，6，否）。
     */
    private String skssq;

    /**
     * 发票代码（String，12，否）。
     */
    private String fpdm;

    /**
     * 发票号码（String，20，否）。
     */
    private String fphm;

    /**
     * 出口转内销证明编号（String，30，否）。
     */
    private String ckznxzmbh;
}
