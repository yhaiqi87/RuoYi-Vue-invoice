package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：查询批量上传税务机关代开农产品发票补录信息处理结果（CXPLSCSWJGDKNCPFPBLXXCLJG）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 20「查询批量上传税务机关代开农产品发票补录信息处理结果」返回参数补全。
 */
@Getter
@Setter
public class GxQueryTaxAuthAgriInvoiceSupplementResultRes extends GxResponse {

    /**
     * 批次流水号（String，32，必填）。
     */
    private String pclsh;
}
