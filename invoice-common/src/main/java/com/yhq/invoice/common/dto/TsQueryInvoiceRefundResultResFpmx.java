package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 退税勾选Res明细：查询发票退税勾选处理结果（CXFPTSGXQRCLJG）的「fpmx」明细数据。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 2「查询发票退税勾选处理结果」返回参数明细小节补全。
 */
@Getter
@Setter
public class TsQueryInvoiceRefundResultResFpmx {

    /**
     * 开票日期（String，8，必填），格式 YYYYMMDD。
     */
    private String kprq;

    /**
     * 发票代码（String，12，否）。
     */
    private String fpdm;

    /**
     * 发票号码（String，20，必填）。
     */
    private String fphm;

    /**
     * 税款所属期（String，6，否），格式 YYYYMM，退税成功时该字段不为空。
     */
    private String skssq;

    /**
     * 处理结果（String，1，否）：01 勾选成功 / 02 查无此票 / 03 该票异常无法勾选 / 04 该票已经逾期无法认证 /
     * 06 已勾选无法重复勾选 / 12 机动车异常发票 / 13 该票号码重复 / 31 待处理农产品发票未确认 /
     * 32 不得抵扣及退税发票 / 33 小规模期间取得不符合抵扣条件的发票 / 35 差额扣除凭证不能用于增值税抵扣勾选、增值税退税勾选、代办退税勾选 / 99 其它。
     */
    private String cljg;

    /**
     * 勾选时间（String，否）。
     */
    private String gxsj;

    /**
     * 发票状态（String，2，否）：0 正常 / 1 失控 / 2 作废 / 3 红字 / 7 已部分冲红 / 8 已全额红冲。
     */
    private String fpzt;

    /**
     * 是否异常凭证（String，2，否）：01 正常 / 02 异常凭证 / 03 疑点发票。
     */
    private String sfycpz;

    /**
     * 锁定状态（String，2，否）：01 未锁定 / 02 已锁定。
     */
    private String sdzt;

    /**
     * 当前勾选用途（String，2，否）：1 已申请抵扣 / 2 已申请退税 / 3 已申请代办退税 / 4 已勾选不抵扣(历史数据) /
     * 5 已申请代办退税(历史数据) / 6 已申请不抵扣 / 11 冬奥退税。
     */
    private String hxyt;

    /**
     * 成品油异常标识（String，1，否）：9 正常 / 1 成品油单价异常 / 2 成品油超库存异常 / 8 正常，需全额红冲。
     */
    private String cpyycbs;
}
