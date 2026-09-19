package com.yhq.invoice.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yhq.invoice.common.enums.CapabilityCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 统一请求基类：所有概念请求的公共字段放这里，能力差异用扩展子类承载。 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class InvoiceRequest {
    /**
     * 自建路由字段：标识走哪套开票能力（BASE / SALE / LEASE），仅用于内部路由，不作为乐企报文上送。
     */
    @JsonIgnore
    private CapabilityCode capabilityCode;
}
