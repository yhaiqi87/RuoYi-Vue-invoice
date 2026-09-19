package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 乐企响应：可换开二手车销售统一发票批量查询（KHKESCXSTYFPPLCX）。
 * 字段依据《乐企数字化电子发票（二手车）开票能力说明文档-V1.003》第 10 节。
 */
@Getter
@Setter
public class QueryEscExchangeableBatchRes extends InvoiceResponse {
    /**
     * 总数量（varchar，4，是）
     */
    private String total;
    /**
     * 页码（Number，4，是）
     */
    private BigDecimal pageNumber;
    /**
     * 二手车销售统一发票列表
     */
    private List<EscExchangeableItem> escxstyfpList;

    /**
     * 可换开二手车销售统一发票列表项（escxstyfpList 数组项）。
     */
    @Getter
    @Setter
    public static class EscExchangeableItem {
        /**
         * 车价合计（number，18,2，否）
         */
        private BigDecimal cjhj;
        /**
         * 二手车发票换开状态代码（varchar，1，否，1:统一发票已开具 2:专票换开预申请 3:专票已换开成功 4:统一发票红票已开具 5:统一发票已作废）
         */
        private String escfphkztDm;
        /**
         * 二手车销售统一发票号码（varchar，20，否）
         */
        private String escxstyfphm;
        /**
         * 二手车销售统一发票开票企业行业性质代码（varchar，2，否，07：二手车市场 08：二手经销企业 09：二手车拍卖企业）
         */
        private String escxstyfpkpqyhyxzDm;
        /**
         * 二手车销售统一发票开票日期（datetime，否）
         */
        private String escxstyfpkprq;
        /**
         * 二手车销售统一发票正向开票标识（varchar，1，否，Y：正向开票 N：反向开票）
         */
        private String escxstyfpzxkpbz;
        /**
         * 二手车销售统一纸质发票代码（varchar，12，否）
         */
        private String escxstyzzfpDm;
        /**
         * 二手车销售统一纸质发票号码（varchar，20，否）
         */
        private String escxstyzzfphm;
        /**
         * 车辆识别代号（varchar，23，否）
         */
        private String clsbdh;
    }
}
