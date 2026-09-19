package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 货物运输响应：QueryDiffTaxCode（查询差额征税编码，接口 CXCEZSBM）。
 * 字段依据《乐企数字化电子发票（货物运输）开票能力说明文档-V3.008》补全。
 */
@Getter
@Setter
public class QueryDiffTaxCodeRes extends InvoiceResponse {
    /**
     * 总数量（number，10，是）
     */
    private String count;
    /**
     * 差额征税编码列表
     */
    private List<CezzbmItem> cezzbmList;

    /**
     * 差额征税编码列表项。
     */
    @Getter
    @Setter
    public static class CezzbmItem {
        /**
         * 商品和服务税收分类合并编码（varchar，19）
         */
        private String sphfwssflhbbm;
        /**
         * 商品和服务名称（varchar，150）
         */
        private String sphfwmc;
        /**
         * 商品和服务分类简称（varchar，120）
         */
        private String sphfwfljc;
        /**
         * 是否可以差额开票（varchar，1，Y：是）
         */
        private String sfkycekp;
        /**
         * 差额开票是否可以开专票（varchar，1，Y：是）
         */
        private String cekpsfkykzp;
        /**
         * 全额开票是否可以开专票（varchar，1，Y：是）
         */
        private String qekpsfkykzp;
        /**
         * 一般纳税人税率（varchar，50）
         */
        private String ybnsrsl;
        /**
         * 一般纳税人征收率（varchar，50）
         */
        private String ybnsrzsl;
        /**
         * 小规模纳税人征收率（varchar，50）
         */
        private String xgmnsrzsl;
    }
}
