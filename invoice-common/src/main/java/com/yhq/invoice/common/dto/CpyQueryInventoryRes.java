package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企响应：查询成品油库存（CXCPYKYSSFLBM）。字段依据《乐企数字化电子发票（成品油）开票能力说明文档-V2.009》。
 *
 * <p>返回字段为占位：真实库存维度（可下载 / 已下载 / 已下载未使用 / 已退回成品油库存等）字段名与明细结构
 * 待按文档返回参数表补全后调整。
 */
@Getter
@Setter
public class CpyQueryInventoryRes extends InvoiceResponse {
    /**
     * 可下载成品油库存（占位字段，待文档核对）。
     */
    private BigDecimal kyxkc;
    /**
     * 已下载成品油库存（占位字段，待文档核对）。
     */
    private BigDecimal yxzkc;
    /**
     * 已下载未使用成品油库存（占位字段，待文档核对）。
     */
    private BigDecimal yxzwsykc;
    /**
     * 已退回成品油库存（占位字段，待文档核对）。
     */
    private BigDecimal ythkc;
}
