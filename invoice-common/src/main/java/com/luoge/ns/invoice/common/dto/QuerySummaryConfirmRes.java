package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 乐企响应：QuerySummaryConfirm（查询发票汇总确认信息）。
 * Data 信封内除销售方纳税人识别号外，另含发票汇总确认信息列表 fphzqrxxList。
 */
@Getter
@Setter
public class QuerySummaryConfirmRes extends InvoiceResponse {
    /**
     * 销售方纳税人识别号（类型 varchar，长度 20，必填 是）
     */
    private String xsfnsrsbh;
    /**
     * 发票汇总确认信息列表
     */
    private List<Fphzqrxx> fphzqrxxList;

    /**
     * 发票汇总确认信息（列表项）。
     */
    @Getter
    @Setter
    public static class Fphzqrxx {
        /**
         * 平台编号（类型 varchar，长度 20，必填 否）
         */
        private String ptbh;
        /**
         * 月份（类型 varchar，长度 7，必填 否，格式：yyyy-MM）
         */
        private String yf;
        /**
         * 蓝字发票数量（类型 number，长度 10，必填 否）
         */
        private BigDecimal lzfpsl;
        /**
         * 蓝字发票金额（类型 number，长度 18,2，必填 否）
         */
        private BigDecimal lzfpje;
        /**
         * 蓝字发票税额（类型 number，长度 18,2，必填 否）
         */
        private BigDecimal lzfpse;
        /**
         * 红字发票数量（类型 number，长度 10，必填 否）
         */
        private BigDecimal hzfpsl;
        /**
         * 红字发票金额（类型 number，长度 18,2，必填 否）
         */
        private BigDecimal hzfpje;
        /**
         * 红字发票税额（类型 number，长度 18,2，必填 否）
         */
        private BigDecimal hzfpse;
    }
}
