package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企响应：UploadSummaryConfirm。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class UploadSummaryConfirmRes extends InvoiceResponse {
    /**
     * 确认日期（类型 datetime，必填 否，格式：yyyy-MM-dd HH:mm:ss）
     */
    private String qrrq;
    /**
     * 销售方纳税人识别号（类型 varchar，长度 20，必填 是）
     */
    private String xsfnsrsbh;
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
