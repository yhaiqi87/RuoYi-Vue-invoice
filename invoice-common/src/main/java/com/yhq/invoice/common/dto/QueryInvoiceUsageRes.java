package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企响应：QueryInvoiceUsage。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class QueryInvoiceUsageRes extends InvoiceResponse {
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
    /**
     * 发票代码（类型 String，长度 12，必填 是，数字化电子发票发票代码可为空）
     */
    private String fpdm;
    /**
     * 发票号码（类型 String，长度 20，必填 是，数字化电子发票，发票号码20位）
     */
    private String fphm;
    /**
     * 开票日期（类型 String，长度 19，必填 是，YYYY-MM-DD HH:mm:ss）
     */
    private String kprq;
    /**
     * 发票状态（类型 String，长度 1，必填 否，0：正常2：作废3：已红冲7：部分红冲8：全额冲红）
     */
    private String fpzt;
    /**
     * 异常凭证状态（类型 String，长度 1，必填 否，01：正常02：异常凭证03：疑似异常凭证）
     */
    private String ycpzzt;
    /**
     * 红字锁定标识（类型 String，长度 1，必填 否，Y：锁定N：未锁定）
     */
    private String hzsdbs;
    /**
     * 发票用途（类型 String，长度 2，必填 否，0：未使用1：已申请抵扣2：已申请退税3：已申请代办退税4：已勾选不抵扣（历史数据）5：已申请代办退税（历史数据）6：已申请不抵扣7：内销转出口8：出口转内销9：准予退税10：不予退税11：冬奥退税）
     */
    private String fpyt;
    /**
     * 入账状态（类型 String，长度 2，必填 否，01：未入账02：已入账03：已入账撤销）
     */
    private String rzzt;
    /**
     * 成品油异常标识（类型 String，长度 1，必填 否，9：正常1：成品油单价异常2：成品油超库存异常8：正常，需全额红冲）
     */
    private String cpyycbs;
    /**
     * 出口退税类代码（类型 String，长度 2，必填 否，01：增值税专用发票可用于代办退税标签（仅用于代办退税））
     */
    private String cktsldm;
    /**
     * 增值税优惠用途（农产品加计扣除）（类型 String，长度 2，必填 否，0：未使用 1：全部项目加计 2：全部项目不加计 3：部分项目加计）
     */
    private String zzsyhyt;
    /**
     * 消费税用途（类型 String，长度 2，必填 否，00：未勾选库存01：已勾选库存）
     */
    private String xfsyt;
    /**
     * 机动车异常标识（类型 String，长度 1，必填 否，0：无风险1：低风险2：中风险3：高风险）
     */
    private String jdcycbs;
    /**
     * 税款所属期（类型 String，长度 6，必填 否，返回格式：yyyymm）
     */
    private String skssq;
    /**
     * 小规模期间取得发票符合勾选条件标识（类型 String，长度 1，必填 否，当为小规模期间取得发票时，返回是否符合勾选条件标识：0：符合1：不符合，登记为一般纳税人之前取得且不符合勾选抵扣条件的发票2：不符合，转登记为小规模纳税人期间取得且不符合勾选抵扣条件的发票当码值为1或2时不可勾选，为0时可勾选）
     */
    private String xgmqjqdfpfhgxtjbs;
    /**
     * 差额扣除异常标识（类型 String，长度 1，必填 否，当为差额扣除凭证的发票时，返回是否符合勾选条件标识：0：正常，可用于勾选1：异常，不能用于勾选）
     */
    private String cekcycbs;
}
