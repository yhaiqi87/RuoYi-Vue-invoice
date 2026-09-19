package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 发票入账响应：查询发票入账处理结果（CXFPRZCLJG）。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 2「查询发票入账处理结果」返回参数补全。
 */
@Getter
@Setter
public class RzQueryInvoiceRzResultRes extends RzResponse {

    /**
     * 批次流水号（String，32，是）
     */
    private String pclsh;

    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，是）
     */
    private String gfsbh;

    /**
     * fpmx明细（List<RzQueryInvoiceRzResultResFpmx>）
     */
    private List<RzQueryInvoiceRzResultResFpmx> fpmx;
}
