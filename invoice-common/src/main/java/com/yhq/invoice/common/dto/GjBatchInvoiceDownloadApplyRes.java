package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 归集响应：批量发票下载申请（PLFPXZSQ）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 1「批量发票下载申请」返回参数补全。
 */
@Getter
@Setter
public class GjBatchInvoiceDownloadApplyRes extends GjResponse {
    /**
     * 流水号（String，32，否，返回码非 00 时不返回）
     */
    private String lsh;
}
