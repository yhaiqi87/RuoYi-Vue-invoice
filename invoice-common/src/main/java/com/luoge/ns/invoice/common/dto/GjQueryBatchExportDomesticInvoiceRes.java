package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 归集响应：批量出口转内销发票信息查询（PLCKZNXFPXXCX）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 6「批量出口转内销发票信息查询」返回参数补全。
 */
@Getter
@Setter
public class GjQueryBatchExportDomesticInvoiceRes extends GjResponse {
    /**
     * 总数量（String，5，是）
     */
    private String count;
    /**
     * 发票明细（fpmxxx）
     */
    private List<ExportDomesticInvoiceItem> fpmxxx;

    /**
     * 出口转内销发票明细项。
     */
    @Getter
    @Setter
    public static class ExportDomesticInvoiceItem {
        /**
         * 发票代码（String，12，是）
         */
        private String fpdm;
        /**
         * 发票号码（String，20，是）
         */
        private String fphm;
        /**
         * 开票日期（String，19，是，YYYY-MM-DD HH:mm:ss）
         */
        private String kprq;
        /**
         * 出口转内销证明编号（String，50，是）
         */
        private String ckznxzmbh;
        /**
         * 销方纳税人识别号（String，20，是）
         */
        private String xfnsrsbh;
        /**
         * 购方纳税人识别号（String，20，是）
         */
        private String gfnsrsbh;
        /**
         * 金额（String，18,2，是）
         */
        private String je;
        /**
         * 税额（String，18,2，是）
         */
        private String se;
    }
}
