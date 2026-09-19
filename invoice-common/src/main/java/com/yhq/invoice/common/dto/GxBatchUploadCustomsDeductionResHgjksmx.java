package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选Res明细：批量上传海关缴款书抵扣勾选（PLHGJKSDKGX）的「hgjksmx」明细数据。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 4「批量上传海关缴款书抵扣勾选」明细小节补全。
 */
@Getter
@Setter
public class GxBatchUploadCustomsDeductionResHgjksmx {

    /**
     * 缴款书号码（String，22，必填）。
     */
    private String jkshm;

    /**
     * 出口转内销证明编号（String，30，否）。
     */
    private String ckznxzmbh;

    /**
     * 填发日期（String，8，必填）。
     */
    private String tfrq;

    /**
     * 不抵扣类型（String，2，否）。
     */
    private String bdklx;

    /**
     * 不抵扣原因（String，300，否）。
     */
    private String bdkyy;
}
