package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企响应：QueryRedConfirmList。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class QueryRedConfirmListRes extends InvoiceResponse {
    /**
     * 总数量（类型 varchar，长度 10，必填 是）
     */
    private String total;
    /**
     * 红字确认单UUID（类型 varchar，长度 32，必填 是）
     */
    private String uuid;
    /**
     * 红字发票信息确认单编号（类型 varchar，长度 20，必填 是）
     */
    private String hzfpxxqrdbh;
    /**
     * 录入方身份（类型 varchar，长度 2，必填 是，0：销方 1：购方）
     */
    private String lrfsf;
    /**
     * （销售方）统一社会信用代码/纳税人识别号/身份证件号码（类型 varchar，长度 20，必填 否）
     */
    private String xsfnsrsbh;
    /**
     * （销售方）名称（类型 varchar，长度 300，必填 是）
     */
    private String xsfmc;
    /**
     * （购买方）统一社会信用代码/纳税人识别号/身份证件号码（类型 varchar，长度 20，必填 是）
     */
    private String gmfnsrsbh;
    /**
     * （购买方）名称（类型 varchar，长度 300，必填 是）
     */
    private String gmfmc;
    /**
     * 蓝字发票代码（类型 varchar，长度 12，必填 否）
     */
    private String lzfpdm;
    /**
     * 蓝字发票号码（类型 varchar，长度 20，必填 否）
     */
    private String lzfphm;
    /**
     * 是否纸质发票标志（类型 varchar，长度 1，必填 是，Y=纸质发票，N=电子发票）
     */
    private String sfzzfpbz;
    /**
     * 蓝字发票开票日期（类型 datetime，必填 否，yyyy-MM-dd HH:mm:ss）
     */
    private String lzkprq;
    /**
     * 蓝字发票合计金额（类型 number，长度 18,2，必填 否）
     */
    private BigDecimal lzhjje;
    /**
     * 蓝字发票合计税额（类型 number，长度 18,2，必填 否）
     */
    private BigDecimal lzhjse;
    /**
     * 蓝字发票票种代码（类型 varchar，长度 2，必填 否，01: 增值税专用发票02: 普通发票03: 机动车统一销售发票04: 二手车统一销售发票）
     */
    private String lzfppzDm;
    /**
     * 蓝字发票特定要素类型代码（类型 varchar，长度 2，必填 是，05：不动产销售服务发票）
     */
    private String lzfpTdyslxDm;
    /**
     * 红字冲销金额（类型 number，长度 18,2，必填 是）
     */
    private BigDecimal hzcxje;
    /**
     * 红字冲销税额（类型 number，长度 18,2，必填 是）
     */
    private BigDecimal hzcxse;
    /**
     * 红字确认单明细数量（类型 number，长度 10，必填 是）
     */
    private BigDecimal hzqrdmxsl;
    /**
     * 红字发票冲红原因代码（类型 varchar，长度 2，必填 是，01：开票有误02：销货退回03：服务中止04：销售折让）
     */
    private String chyyDm;
    /**
     * 红字确认信息状态代码（类型 varchar，长度 2，必填 是）
     */
    private String hzqrxxztDm;
    /**
     * 确认日期（类型 datetime，必填 否，yyyy-MM-dd HH:mm:ss）
     */
    private String qrrq;
    /**
     * 已开具红字发票标志（类型 varchar，长度 1，必填 否，Y：已开具N：未开具）
     */
    private String ykjhzfpbz;
    /**
     * 红字发票号码（类型 varchar，长度 20，必填 否）
     */
    private String hzfphm;
    /**
     * 红字开票日期（类型 datetime，必填 是，yyyy-MM-dd HH:mm:ss）
     */
    private String hzkprq;
    /**
     * 有效标志（类型 varchar，长度 1，必填 是，Y：有效N：无效）
     */
    private String yxbz;
    /**
     * 录入日期（类型 datetime，必填 是，yyyy-MM-dd HH:mm:ss）
     */
    private String lrrq;
}
