package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 建筑服务响应：CrossRegionBatchQuery（跨区域涉税数据批量查询，接口 KQYSSSJPLCX）。
 * 字段依据《乐企数字化电子发票（建筑服务）开票能力说明文档-V4.002》补全。
 */
@Getter
@Setter
public class CrossRegionBatchRes extends InvoiceResponse {
    private String total;
    private String pageNumber;
    /**
     * 跨区域涉税数据列表
     */
    private List<KqyssItem> kqyssList;

    /**
     * 跨区域涉税数据列表项。
     */
    @Getter
    @Setter
    public static class KqyssItem {
        private String kqysssxbyglbh;
        private String gcxmmc;
        private String kqyjyxzqh;
        private String kqyjyxzjd;
        private String kqyjydz;
        private String kqysssxyxqq;
        private String kqysssxyxqz;
    }
}
