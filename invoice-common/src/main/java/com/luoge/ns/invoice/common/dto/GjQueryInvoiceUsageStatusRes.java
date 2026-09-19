package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 归集响应：发票用途状态信息查询（FPZTXXCX）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 5「发票用途状态信息查询」返回参数补全。
 */
@Getter
@Setter
public class GjQueryInvoiceUsageStatusRes extends GjResponse {
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
     * 发票状态（String，1，否）
     */
    private String fpzt;
    /**
     * 异常凭证状态（String，1，否）
     */
    private String ycpzzt;
    /**
     * 红字锁定标识（String，1，否，Y：锁定 N：未锁定）
     */
    private String hzsdbs;
    /**
     * 发票用途（String，2，否）
     */
    private String fpyt;
    /**
     * 入账状态（String，2，否）
     */
    private String rzzt;
    /**
     * 成品油异常标识（String，1，否）
     */
    private String cpyycbs;
    /**
     * 出口退税类代码（String，2，否）
     */
    private String cktsldm;
    /**
     * 增值税优惠用途（农产品加计扣除）（String，2，否）
     */
    private String zzsyhyt;
    /**
     * 消费税用途（String，2，否）
     */
    private String xfsyt;
    /**
     * 机动车异常标识（String，1，否）
     */
    private String jdcycbs;
    /**
     * 税款所属期（String，6，否，yyyymm）
     */
    private String skssq;
    /**
     * 小规模期间取得发票符合勾选条件标识（String，1，否）
     */
    private String xgmqjqdfpfhgxtjbs;
    /**
     * 差额扣除异常标识（String，1，否）
     */
    private String cekcycbs;
}
