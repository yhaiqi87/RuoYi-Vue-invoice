package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 发票入账Res明细：查询发票入账处理结果（CXFPRZCLJG）的「fpmx」明细数据。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 2「查询发票入账处理结果」返回参数明细小节补全。
 */
@Getter
@Setter
public class RzQueryInvoiceRzResultResFpmx {

    /**
     * 发票类型（String，2，是）
     */
    private String fplx;

    /**
     * 开票日期（String，8，是，YYYYMMDD）
     */
    private String kprq;

    /**
     * 发票代码（String，12，否）
     */
    private String fpdm;

    /**
     * 发票号码（String，20，是）
     */
    private String fphm;

    /**
     * 处理结果（String，1，是）：1 入账(撤销)成功 / 2 查无此票 / 3 该票异常无法入账 / 4 重复入账 / 5 其他
     */
    private String cljg;

    /**
     * 入账时间（String，8，否，撤销入账时此字段为空，YYYYMMDD）
     */
    private String rzsj;

    /**
     * 发票状态（String，2，否，处理结果为 3 时反馈）：0 正常 / 2 作废 / 3 已红冲 / 7 已部分红冲 / 8 已全额红冲
     */
    private String fpzt;

    /**
     * 已入账属期（String，6，否，处理结果为 4 时反馈，格式 YYYYMM）
     */
    private String yrzskssq;

    /**
     * 成品油异常标识（String，1，否，处理结果为 3 时反馈）：9 正常 / 1 成品油单价异常 / 2 成品油超库存异常 / 8 正常，需全额红冲
     */
    private String cpyycbs;
}
