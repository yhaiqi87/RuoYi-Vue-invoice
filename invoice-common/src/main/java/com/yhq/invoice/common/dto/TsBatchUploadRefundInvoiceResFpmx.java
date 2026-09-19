package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 退税勾选Res明细：批量上传退税发票（PLFPTSGXQR）的「fpmx」明细数据。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 1「批量上传退税发票」返回参数明细小节补全。
 */
@Getter
@Setter
public class TsBatchUploadRefundInvoiceResFpmx {

    /**
     * 开票日期（String，22，必填），格式 YYYYMMDD。
     */
    private String kprq;

    /**
     * 发票代码（String，12，否，数字化电子发票发票代码可为空）。
     */
    private String fpdm;

    /**
     * 发票号码（String，20，必填）。
     */
    private String fphm;

    /**
     * 上传状态（String，2，必填）：00 成功 / 01 失败。
     */
    private String sczt;

    /**
     * 错误信息（String，2000，否，01 失败时记录失败原因）。
     */
    private String errormsg;
}
