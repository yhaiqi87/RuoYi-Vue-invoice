package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：批量上传税务机关代开农产品发票补录信息（PLSCSWJGDKNCPFPBLXX）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 19「批量上传税务机关代开农产品发票补录信息」返回参数补全。
 */
@Getter
@Setter
public class GxBatchUploadTaxAuthAgriInvoiceSupplementRes extends GxResponse {

    /**
     * 购方纳税人识别号/统一社会信用代码（String，30，必填）。
     */
    private String gfsbh;

    /**
     * fpmx明细（List<GxBatchUploadTaxAuthAgriInvoiceSupplementResFpmx>）。
     */
    private java.util.List<GxBatchUploadTaxAuthAgriInvoiceSupplementResFpmx> fpmx;
}
