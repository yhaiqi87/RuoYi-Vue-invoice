package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企响应：QueryQuota。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class QueryQuotaRes extends InvoiceResponse {
    /** 暂停授信标志（类型 varchar，长度 2，必填 是，Y：暂停N：未暂停） */
    private String ztsxbz;
    /** 本月授信额度（类型 number，长度 18,2，必填 是，本月总授信额度） */
    private BigDecimal bysxed;
    /** 可用剩余额度（类型 number，长度 18,2，必填 是，本月可用授信额度） */
    private BigDecimal kysyed;
    /** 已下载额度（类型 number，长度 18,2，必填 是，本月乐企1.5已下载的全部授信额度） */
    private BigDecimal yxzed;
    /** 已下载未使用额度（类型 number，长度 18,2，必填 是，本月乐企1.5已下载的全部授信额度减已上传的开票日期为本月的发票的总金额） */
    private BigDecimal yxzwsyed;
    /** 属期（类型 varchar，长度 6，必填 是，格式：yyyyMM） */
    private String sq;
}
