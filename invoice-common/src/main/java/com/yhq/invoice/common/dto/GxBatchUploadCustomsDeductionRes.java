package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：批量上传海关缴款书抵扣勾选（PLHGJKSDKGX）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 4「批量上传海关缴款书抵扣勾选」返回参数补全。
 */
@Getter
@Setter
public class GxBatchUploadCustomsDeductionRes extends GxResponse {

    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，必填）。
     */
    private String gfsbh;

    /**
     * 勾选类型（String，2，必填）。
     */
    private String gxlxDm;

    /**
     * 税款所属期（String，6，否）。
     */
    private String skssq;

    /**
     * hgjksmx明细（List<GxBatchUploadCustomsDeductionResHgjksmx>）。
     */
    private java.util.List<GxBatchUploadCustomsDeductionResHgjksmx> hgjksmx;
}
