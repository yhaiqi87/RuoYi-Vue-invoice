package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 发票查验（乐企能力 203059）请求基类。
 *
 * <p>沿用统一请求基类 {@link InvoiceRequest}，使其 {@code capabilityCode}（@JsonIgnore，不影响报文）字段与
 * 现有 {@code @ValidateReq} + {@code RequestValidateAspect} 校验链路保持兼容；查验自身不使用该路由字段。
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class CyRequest extends InvoiceRequest {

    public CyRequest(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

}
