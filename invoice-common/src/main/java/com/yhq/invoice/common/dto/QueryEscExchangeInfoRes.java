package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 乐企响应：二手车销售统一发票换开信息单笔查询（ESCXSTYFPHXXXDBCX）。
 * 字段依据《乐企数字化电子发票（二手车）开票能力说明文档-V1.003》第 11 节。
 */
@Getter
@Setter
public class QueryEscExchangeInfoRes extends InvoiceResponse {
    /**
     * 车价合计（number，18,2，否）
     */
    private java.math.BigDecimal cjhj;
    /**
     * 二手车发票换开状态代码（varchar，1，否）
     */
    private String escfphkztDm;
    /**
     * 二手车销售统一发票号码（varchar，20，否）
     */
    private String escxstyfphm;
    /**
     * 二手车销售统一发票开票企业行业性质代码（varchar，2，否）
     */
    private String escxstyfpkpqyhyxzDm;
    /**
     * 二手车销售统一发票开票日期（datetime，否）
     */
    private String escxstyfpkprq;
    /**
     * 二手车销售统一发票正向开票标识（varchar，1，否）
     */
    private String escxstyfpzxkpbz;
    /**
     * 二手车销售统一纸质发票代码（varchar，12，否）
     */
    private String escxstyzzfpDm;
    /**
     * 二手车销售统一纸质发票号码（varchar，20，否）
     */
    private String escxstyzzfphm;
    /**
     * 发票号码（varchar，20，否）
     */
    private String fphm;
    /**
     * 发票类型代码（varchar，2，否）
     */
    private String fplxDm;
    /**
     * 购买方名称（varchar，300，否）
     */
    private String gmfmc;
    /**
     * 购买方身份证件号码（varchar，20，否）
     */
    private String gmfsfzjhm;
    /**
     * 车辆识别代号（varchar，23，否）
     */
    private String jdcclsbdh;
    /**
     * 开票方纳税人识别号（varchar，20，否）
     */
    private String kpfnsrsbh;
    /**
     * 开票日期（varchar，否）
     */
    private String kprq;
    /**
     * 特定要素类型代码（varchar，2，否）
     */
    private String tdyslxDm;
    /**
     * 销售方纳税人识别号（varchar，20，否）
     */
    private String xsfsfzjhm;
    /**
     * 纸质发票代码（varchar，12，否）
     */
    private String zzfpDm;
    /**
     * 纸质发票号码（varchar，20，否）
     */
    private String zzfphm;
}
