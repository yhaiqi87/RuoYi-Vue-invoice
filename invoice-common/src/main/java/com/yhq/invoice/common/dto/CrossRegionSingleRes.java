package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 建筑服务响应：CrossRegionSingleQuery（跨区域涉税数据单笔查询，接口 KQYSSSJDBCX）。
 * 字段依据《乐企数字化电子发票（建筑服务）开票能力说明文档-V4.002》补全。
 */
@Getter
@Setter
public class CrossRegionSingleRes extends InvoiceResponse {
    private String kqysssxbyglbh;
    private String gcxmmc;
    private String kqyjyxzqh;
    private String kqyjyxzjd;
    private String kqyjydz;
    private String kqysssxyxqq;
    private String kqysssxyxqz;
}
