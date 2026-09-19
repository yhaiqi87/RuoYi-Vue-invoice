package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：QueryTaxRate（CXKYSL，查询可用税率信息）。
 * 见基础版 V6.006 文档 §7.2 请求参数：无。请求报文体为空对象 {@code {}}。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryTaxRateReq extends InvoiceRequest {

    public QueryTaxRateReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }
}
