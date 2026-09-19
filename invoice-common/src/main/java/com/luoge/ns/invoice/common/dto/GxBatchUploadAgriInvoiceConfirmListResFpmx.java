package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选Res明细：批量上传待处理农产品发票确认清单（PLSCDCLNCPFPQRQD）的「fpmx」明细数据。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 17「批量上传待处理农产品发票确认清单」明细小节补全。
 */
@Getter
@Setter
public class GxBatchUploadAgriInvoiceConfirmListResFpmx {

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
     * 农产品部分金额（String，18,2，否）。
     */
    private String ncpbfje;
}
