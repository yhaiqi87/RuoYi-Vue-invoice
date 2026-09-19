package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 乐企响应：自然人卖方限制名单阻断查询（ZRRMFXZMDZDCX）。
 * 字段依据《乐企数字化电子发票（二手车）开票能力说明文档-V1.003》第 9 节。
 */
@Getter
@Setter
public class QuerySellerBlockRes extends InvoiceResponse {
    /**
     * 阻断标志（varchar，1，否，Y：阻断开票 N：不阻断开票）
     */
    private String zdbz;
}
