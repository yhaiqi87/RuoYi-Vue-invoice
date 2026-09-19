package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 归集响应：批量发票下载申请反馈（PLFPXZSQFK）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 2「批量发票下载申请反馈」返回参数补全。
 */
@Getter
@Setter
public class GjBatchInvoiceDownloadApplyFeedbackRes extends GjResponse {
    /**
     * 总包数（String，5，是）
     */
    private String packagecount;
    /**
     * 统一社会信用代码/纳税人识别号（String，30，是）
     */
    private String nsrsbh;
    /**
     * 发票类型（String，2，是）
     */
    private String fplx;
}
