package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选Req明细：批量上传税务机关代开农产品发票补录信息（PLSCSWJGDKNCPFPBLXX）的「fpmx」明细数据。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 19「批量上传税务机关代开农产品发票补录信息」明细小节补全。
 */
@Getter
@Setter
public class GxBatchUploadTaxAuthAgriInvoiceSupplementReqFpmx {

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
     * 发票类型（String，2，必填）。
     */
    private String fplx;

    /**
     * 有效抵扣税额（String，18,2，必填）。
     */
    private String yxdkse;

    /**
     * 农产品部分金额（String，18,2，必填）。
     */
    private String ncpbfje;

    /**
     * 农产品部分税额（String，18,2，必填）。
     */
    private String ncpbfse;
}
