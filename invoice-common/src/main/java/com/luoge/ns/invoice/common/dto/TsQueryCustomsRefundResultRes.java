package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 退税勾选响应：查询海关缴款书退税勾选处理结果（CXHGJKSTSGXQRCLJG）。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 4「查询海关缴款书退税勾选处理结果」返回参数补全。
 */
@Getter
@Setter
public class TsQueryCustomsRefundResultRes extends TsResponse {

    /**
     * 购买方统一社会信用代码/纳税人识别号（String，20，必填）。
     */
    private String gfsbh;

    /**
     * 批次流水号（String，32，必填）。
     */
    private String pclsh;

    /**
     * jksmx明细（List<TsQueryCustomsRefundResultResJksmx>）。
     */
    private List<TsQueryCustomsRefundResultResJksmx> jksmx;
}
