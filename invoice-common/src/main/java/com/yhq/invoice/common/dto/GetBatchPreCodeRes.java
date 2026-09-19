package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企响应：GetBatchPreCode。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class GetBatchPreCodeRes extends InvoiceResponse {
    /** 发票起始号码（类型 varchar，长度 20，必填 是） */
    private String fpqshm;
    /** 发票终止号码（类型 varchar，长度 20，必填 是） */
    private String fpzzhm;
    /**
     * 领用数量（类型 number，长度 10，必填 是）
     */
    private BigDecimal lysl;
}
