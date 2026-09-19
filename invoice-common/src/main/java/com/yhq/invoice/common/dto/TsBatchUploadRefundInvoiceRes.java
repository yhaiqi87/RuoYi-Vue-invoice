package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 退税勾选响应：批量上传退税发票（PLFPTSGXQR）。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 1「批量上传退税发票」返回参数补全。
 */
@Getter
@Setter
public class TsBatchUploadRefundInvoiceRes extends TsResponse {

    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，否）。
     */
    private String gfsbh;

    /**
     * 批次流水号（String，32，否，返回码非 00 时不返回）。
     */
    private String pclsh;

    /**
     * fpmx明细（List<TsBatchUploadRefundInvoiceResFpmx>）。
     */
    private List<TsBatchUploadRefundInvoiceResFpmx> fpmx;
}
