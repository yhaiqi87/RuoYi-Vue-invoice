package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选Res明细：批量上传增值税代扣代缴完税凭证抵扣勾选（PLSCZZSDKDJWSPZDKGX）的「dkdjmx」明细数据。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 12「批量上传增值税代扣代缴完税凭证抵扣勾选」明细小节补全。
 */
@Getter
@Setter
public class GxBatchUploadVatWithholdDeductionResDkdjmx {

    /**
     * 代扣代缴完税凭证号（String，22，必填）。
     */
    private String dkdjwspzh;

    /**
     * 填发日期（String，8，必填）。
     */
    private String tfrq;

    /**
     * 被扣缴义务人统一社会信用代码/纳税人识别号（String，30，必填）。
     */
    private String bkjnsrsbh;

    /**
     * 不抵扣类型（String，2，否）。
     */
    private String bdklx;

    /**
     * 不抵扣原因（String，300，否）。
     */
    private String bdkyy;
}
