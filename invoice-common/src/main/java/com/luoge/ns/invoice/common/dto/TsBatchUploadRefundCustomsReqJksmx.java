package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 退税勾选Req明细：批量上传退税海关缴款书（PLHGJKSTSGXQR）的「jksmx」明细数据。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 3「批量上传退税海关缴款书」明细小节补全。
 */
@Getter
@Setter
public class TsBatchUploadRefundCustomsReqJksmx {

    /**
     * 填发日期（String，8，必填），格式 YYYYMMDD。
     */
    private String tfrq;

    /**
     * 缴款书号码（String，50，必填）。
     */
    private String jkshm;
}
