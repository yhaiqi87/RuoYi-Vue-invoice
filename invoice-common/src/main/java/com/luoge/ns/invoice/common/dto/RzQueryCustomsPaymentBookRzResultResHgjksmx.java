package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 发票入账Res明细：查询海关缴款书入账处理结果（CXHGJKSRZCLJG）的「hgjksmx」明细数据。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 6「查询海关缴款书入账处理结果」返回参数明细小节补全。
 */
@Getter
@Setter
public class RzQueryCustomsPaymentBookRzResultResHgjksmx {

    /**
     * 填发日期（String，8，是，YYYYMMDD）
     */
    private String tfrq;

    /**
     * 海关缴款书号码（String，75，是）
     */
    private String hgjkshm;

    /**
     * 处理结果（String，1，是）：1 入账(撤销)成功 / 2 查无此票 / 3 该票异常无法入账 / 4 重复入账 / 5 其他
     */
    private String cljg;

    /**
     * 入账时间（String，8，否，撤销入账时此字段为空，YYYYMMDD）
     */
    private String rzsj;

    /**
     * 已入账属期（String，6，否，处理结果为 4 时反馈，格式 YYYYMM）
     */
    private String yrzskssq;
}
