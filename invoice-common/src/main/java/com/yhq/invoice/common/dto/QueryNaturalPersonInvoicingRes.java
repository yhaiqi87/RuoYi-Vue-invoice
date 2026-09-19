package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 查询自然人开票信息响应（接口 CXFXKPTYZRRXX，仅反向开票通用）。
 * 返回自然人当日 / 最近十二个自然月各项目开票金额，用于开票前校验规则处理。
 * 字段依据《乐企数字化电子发票（反向开票通用）开票能力说明文档-V1.002》。
 */
@Getter
@Setter
public class QueryNaturalPersonInvoicingRes extends InvoiceResponse {
    /**
     * 自然人姓名（varchar，100，是）
     */
    private String zrrxm;
    /**
     * 身份证件类型（char，3，是，201：居民身份证）
     */
    private String sfzjlx;
    /**
     * 身份证件号码（varchar，20，是）
     */
    private String sfzjhm;
    /**
     * 阻断反向开票自然人标志（char，1，是，Y：需要阻断 N：无需阻断）
     */
    private String zdfxkpzrrbz;
    /**
     * 开票日期（varchar，10，是，yyyy-MM-dd 系统当前日期）
     */
    private String kprq;
    /**
     * 日开票总金额（number，18,2，是）
     */
    private BigDecimal rkpzje;
    /**
     * 财产转让所得开票金额（number，18,2，是）
     */
    private BigDecimal cczrsdkpje;
    /**
     * 经营所得开票金额（number，18,2，是）
     */
    private BigDecimal jysdkpje;
    /**
     * 劳务报酬开票金额（number，18,2，是）
     */
    private BigDecimal lwbckpje;
    /**
     * 稿酬开票金额（number，18,2，是）
     */
    private BigDecimal gckpje;
    /**
     * 特许权使用费开票金额（number，18,2，是）
     */
    private BigDecimal txqsyfkpje;
    /**
     * 其他项目开票金额（number，18,2，是）
     */
    private BigDecimal qtxmkpje;
    /**
     * 自然人出售者开票信息列表（按开票月份）
     */
    private List<ZrrcszKpxxItem> zrrcszKpxxList;

    /**
     * 自然人出售者开票信息列表项：开票月份与当月累计开票总金额。
     */
    @Getter
    @Setter
    public static class ZrrcszKpxxItem {
        /**
         * 开票月份（varchar，10，是，格式：YYYYMM）
         */
        private String kpyf;
        /**
         * 月开票总金额（number，18,2，是，当月累计开票总金额）
         */
        private BigDecimal ykpzje;
    }
}
