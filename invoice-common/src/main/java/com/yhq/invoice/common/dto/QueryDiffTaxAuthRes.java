package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 乐企响应：QueryDiffTaxAuth。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class QueryDiffTaxAuthRes extends InvoiceResponse {
    /**
     * 授权时间（类型 DATE，长度 -，必填 否，yyyy-MM-dd HH:mm:ss）
     */
    private String sqsj;
    /**
     * 授权失效时间（类型 DATE，长度 -，必填 否，yyyy-MM-dd HH:mm:ss）
     */
    private String sqsxsj;
}
