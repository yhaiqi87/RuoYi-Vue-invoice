package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 发票入账响应：查询海关缴款书入账处理结果（CXHGJKSRZCLJG）。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 6「查询海关缴款书入账处理结果」返回参数补全。
 */
@Getter
@Setter
public class RzQueryCustomsPaymentBookRzResultRes extends RzResponse {

    /**
     * 批次流水号（String，32，是）
     */
    private String pclsh;

    /**
     * 缴款单位人纳税人识别号（String，30，是）
     */
    private String jkdwrnsrsbh;

    /**
     * hgjksmx明细（List<RzQueryCustomsPaymentBookRzResultResHgjksmx>）
     */
    private List<RzQueryCustomsPaymentBookRzResultResHgjksmx> hgjksmx;
}
