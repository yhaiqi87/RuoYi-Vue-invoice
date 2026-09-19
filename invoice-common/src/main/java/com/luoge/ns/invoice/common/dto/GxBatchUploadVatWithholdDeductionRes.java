package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：批量上传增值税代扣代缴完税凭证抵扣勾选（PLSCZZSDKDJWSPZDKGX）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 12「批量上传增值税代扣代缴完税凭证抵扣勾选」返回参数补全。
 */
@Getter
@Setter
public class GxBatchUploadVatWithholdDeductionRes extends GxResponse {

    /**
     * 扣缴义务人识别号（String，30，必填）。
     */
    private String kjywrsbh;

    /**
     * 勾选类型代码（String，2，必填）。
     */
    private String gxlxDm;

    /**
     * 税款所属期（String，6，否）。
     */
    private String skssq;

    /**
     * dkdjmx明细（List<GxBatchUploadVatWithholdDeductionResDkdjmx>）。
     */
    private java.util.List<GxBatchUploadVatWithholdDeductionResDkdjmx> dkdjmx;
}
