package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 归集响应：批量出口转内销海关缴款书信息查询（PLCKZNXHGJKSXXCX）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 7「批量出口转内销海关缴款书信息查询」返回参数补全。
 */
@Getter
@Setter
public class GjQueryBatchExportDomesticCustomsRes extends GjResponse {
    /**
     * 总数量（String，5，是）
     */
    private String count;
    /**
     * 明细数据（mxxx）
     */
    private List<ExportDomesticCustomsItem> mxxx;

    /**
     * 出口转内销海关缴款书明细项。
     */
    @Getter
    @Setter
    public static class ExportDomesticCustomsItem {
        /**
         * 海关缴款书号码（String，20，是）
         */
        private String hgjkshm;
        /**
         * 填发日期（String，19，是，YYYY-MM-DD HH:mm:ss）
         */
        private String tfrq;
        /**
         * 出口转内销证明编号（String，50，是）
         */
        private String ckznxzmbh;
        /**
         * 缴款单位（人）纳税人识别号（String，20，是）
         */
        private String jkdwrnsrsbh;
        /**
         * 税款金额（String，18,2，是）
         */
        private String skje;
    }
}
