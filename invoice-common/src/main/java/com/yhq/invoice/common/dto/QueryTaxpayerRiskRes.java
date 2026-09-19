package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 乐企响应：QueryTaxpayerRisk。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class QueryTaxpayerRiskRes extends InvoiceResponse {
    /**
     * 纳税人识别号/统一社会信用代码（类型 varchar，长度 20，必填 是）
     */
    private String nsrsbh;
    /**
     * 风险纳税人类型（类型 varchar，长度 2，必填 否，空：非风险纳税人01：Ⅰ类（高风险）纳税人02：Ⅱ类（中风险）纳税人03：Ⅲ类（低风险）纳税人04：Ⅳ类（无风险）纳税人）
     */
    private String fxnsrlx;
    /**
     * 纳税人信用等级（类型 varchar，长度 20，必填 是，ABCDM）
     */
    private String nsrxydj;
    /**
     * 纳税人预警级别（类型 varchar，长度 1，必填 否，空：无预警01：红色预警02：黄色预警03：蓝色预警）
     */
    private String nsryjjb;
    /**
     * 风险纳税人标志（类型 varchar，长度 1，必填 是，Y：风险纳税人N：正常纳税人）
     */
    private String fxnsrbz;
}
