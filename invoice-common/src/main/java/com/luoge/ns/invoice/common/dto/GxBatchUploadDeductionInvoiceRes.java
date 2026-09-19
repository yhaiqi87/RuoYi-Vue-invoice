package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：批量上传抵扣发票（PLFPDKGX）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 2「批量上传抵扣发票」返回参数补全。
 */
@Getter
@Setter
public class GxBatchUploadDeductionInvoiceRes extends GxResponse {

    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，必填）。
     */
    private String gfsbh;

    /**
     * 勾选类型（String，2，必填）。
     */
    private String gxlxDm;

    /**
     * 税款所属期（String，6，否）。
     */
    private String skssq;

    /**
     * fpmx明细（List<GxBatchUploadDeductionInvoiceResFpmx>）。
     */
    private java.util.List<GxBatchUploadDeductionInvoiceResFpmx> fpmx;
}
