package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 退税勾选Req明细：批量上传退税发票（PLFPTSGXQR）的「fpmx」明细数据。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 1「批量上传退税发票」明细小节补全。
 */
@Getter
@Setter
public class TsBatchUploadRefundInvoiceReqFpmx {

    /**
     * 开票日期（String，22，必填），格式 YYYYMMDD。
     */
    private String kprq;

    /**
     * 发票代码（String，12，必填，发票类型为 01、08 时传纸质发票代码，其他类型无需传入）。
     */
    private String fpdm;

    /**
     * 发票号码（String，20，必填）。
     */
    private String fphm;

    /**
     * 发票类型（String，2，必填）：01 增值税专用发票 / 08 增值税电子专用发票 / 81 电子发票(增值税专用发票) / 85 纸质发票(增值税专用发票)。
     */
    private String fplx;
}
