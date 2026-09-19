package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企响应：下载或退回成品油库存（XZHTHCPYKC）。字段依据《乐企数字化电子发票（成品油）开票能力说明文档-V2.009》。
 *
 * <p>返回字段为占位：真实结果字段（如受理流水号、处理数量等）待按文档返回参数表补全后调整。
 */
@Getter
@Setter
public class CpyDownloadOrReturnInventoryRes extends InvoiceResponse {
    /**
     * 受理流水号（占位字段，待文档核对）。
     */
    private String sllsh;
    /**
     * 处理数量（占位字段，待文档核对）。
     */
    private BigDecimal sl;
}
