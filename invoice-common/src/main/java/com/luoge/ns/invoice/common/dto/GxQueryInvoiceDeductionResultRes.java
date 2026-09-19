package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：查询发票抵扣勾选处理结果（CXFPDKGXCLJG）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 3「查询发票抵扣勾选处理结果」返回参数补全。
 */
@Getter
@Setter
public class GxQueryInvoiceDeductionResultRes extends GxResponse {

    /**
     * 批次流水号（String，32，必填）。
     */
    private String pclsh;
}
