package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企响应：QueryTaxRate。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class QueryTaxRateRes extends InvoiceResponse {
    /**
     * 总数量（类型 number，长度 10，必填 是）
     */
    private BigDecimal count;
    /**
     * 参数性质（类型 varchar，长度 2，必填 是，1：税率2：征收率）
     */
    private String csxz;
    /**
     * 税率征收率（类型 number，长度 16,6，必填 是）
     */
    private BigDecimal slzsl;
    /**
     * 原税率标志（类型 varchar，长度 1，必填 是，Y：是原税率N：非原税率）
     */
    private String yslbz;
    /**
     * 对应原税率（类型 varchar，长度 150，必填 否，百分比形式、逗号连接）
     */
    private String dyysl;
    /**
     * 参数状态（类型 varchar，长度 1，必填 是，0：启用1：停用）
     */
    private String cszt;
    /**
     * 有效期起（类型 date，必填 是，时间格式：yyyy-MM-dd）
     */
    private String yxqq;
    /**
     * 有效期止（类型 date，必填 否，时间格式：yyyy-MM-dd）
     */
    private String yxqz;
    /**
     * 停用日期（类型 date，必填 否，时间格式：yyyy-MM-dd）
     */
    private String tyrq;
}
