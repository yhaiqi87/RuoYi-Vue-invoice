package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 归集响应：发票风险信息查询（FPTSTXXXCX）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 4「发票风险信息查询」返回参数补全。
 */
@Getter
@Setter
public class GjQueryInvoiceRiskInfoRes extends GjResponse {
    /**
     * 总数量（String，10，是）
     */
    private String count;
    /**
     * 游标（String，200，否，下次请求时使用）
     */
    private String scrollId;
    /**
     * 发票风险信息明细（fptxxxmx）
     */
    private List<FpRiskItem> fptxxxmx;

    /**
     * 发票风险明细项。
     */
    @Getter
    @Setter
    public static class FpRiskItem {
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
         * 发票状态（String，1，否，0：正常 2：作废 3：已红冲 7：部分红冲 8：全额冲红）
         */
        private String fpzt;
        /**
         * 异常凭证状态（String，2，否，01：正常 02：异常凭证 03：疑似异常凭证）
         */
        private String ycpzzt;
        /**
         * 红字锁定标识（String，1，否，Y：锁定 N：未锁定）
         */
        private String hzsdbs;
        /**
         * 变更日期（String，19，否，YYYY-MM-DD HH:mm:ss）
         */
        private String bgrq;
    }
}
