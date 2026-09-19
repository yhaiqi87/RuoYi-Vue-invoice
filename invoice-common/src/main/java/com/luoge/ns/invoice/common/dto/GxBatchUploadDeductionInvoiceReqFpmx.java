package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选Req明细：批量上传抵扣发票（PLFPDKGX）的「fpmx」明细数据。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 2「批量上传抵扣发票」明细小节补全。
 */
@Getter
@Setter
public class GxBatchUploadDeductionInvoiceReqFpmx {

    /**
     * 开票日期（String，8，必填）。
     */
    private String kprq;

    /**
     * 发票代码（String，12，否）。
     */
    private String fpdm;

    /**
     * 发票号码（String，20，必填）。
     */
    private String fphm;

    /**
     * 出口转内销证明编号（String，30，否）。
     */
    private String ckznxzmbh;

    /**
     * 发票类型（String，2，必填）。
     */
    private String fplx;

    /**
     * 不抵扣类型（String，2，否）。
     */
    private String bdklx;

    /**
     * 不抵扣原因（String，300，否）。
     */
    private String bdkyy;
}
