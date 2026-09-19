package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 建筑服务响应：QueryJzfwInfo（查询建筑服务信息，接口 CXJZFWXX）。
 * 字段依据《乐企数字化电子发票（建筑服务）开票能力说明文档-V4.002》补全。
 */
@Getter
@Setter
public class QueryJzfwInfoRes extends InvoiceResponse {
    private String nsrsbh;
    private String jzfwfsd;
    private String jzxmmc;
    private String xmbh;
    /**
     * 土地增值税项目信息列表
     */
    private List<TdzzsxmxxItem> tdzzsxmxxList;

    /**
     * 土地增值税项目信息列表项。
     */
    @Getter
    @Setter
    public static class TdzzsxmxxItem {
        private String jzfwfsd;
        private String jzxmmc;
        private String xmbh;
    }
}
