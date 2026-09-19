package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 退税勾选响应：批量上传退税海关缴款书（PLHGJKSTSGXQR）。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 3「批量上传退税海关缴款书」返回参数补全。
 */
@Getter
@Setter
public class TsBatchUploadRefundCustomsRes extends TsResponse {

    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，必填）。
     */
    private String gfsbh;

    /**
     * 批次流水号（String，32，否，返回码非 00 时不返回）。
     */
    private String pclsh;

    /**
     * jksmx明细（List<TsBatchUploadRefundCustomsResJksmx>）。
     */
    private List<TsBatchUploadRefundCustomsResJksmx> jksmx;
}
