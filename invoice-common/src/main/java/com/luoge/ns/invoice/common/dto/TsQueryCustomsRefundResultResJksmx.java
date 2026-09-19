package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 退税勾选Res明细：查询海关缴款书退税勾选处理结果（CXHGJKSTSGXQRCLJG）的「jksmx」明细数据。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 4「查询海关缴款书退税勾选处理结果」返回参数明细小节补全。
 */
@Getter
@Setter
public class TsQueryCustomsRefundResultResJksmx {

    /**
     * 填发日期（String，8，必填），格式 YYYYMMDD。
     */
    private String tfrq;

    /**
     * 缴款书号码（String，50，否）。
     */
    private String jkshm;

    /**
     * 税款所属期（String，6，否），格式 YYYYMM，抵扣成功时该字段不为空。
     */
    private String skssq;

    /**
     * 处理结果（String，1，否）：01 勾选成功 / 02 查无此票 / 03 该票异常无法勾选 / 06 已勾选无法重复勾选 /
     * 10 非出口退税企业 / 13 该票号码重复 / 34 增值税海关缴款书不允许用于退税用途、不抵扣用途 /
     * 35 差额扣除凭证不能用于增值税抵扣勾选、增值税退税勾选、代办退税勾选 / 99 其它。
     */
    private String cljg;

    /**
     * 勾选时间（String，8，否）。
     */
    private String gxsj;

    /**
     * 当前勾选用途（String，2，否）：1 已申请抵扣 / 2 已申请退税 / 3 已申请代办退税 / 4 已勾选不抵扣(历史数据) /
     * 5 已申请代办退税(历史数据) / 6 已申请不抵扣 / 11 冬奥退税。
     */
    private String hxyt;
}
