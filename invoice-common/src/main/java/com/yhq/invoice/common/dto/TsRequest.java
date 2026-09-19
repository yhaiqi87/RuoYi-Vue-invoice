package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 退税勾选（乐企能力 203064）请求基类。
 *
 * <p>沿用统一请求基类 {@link InvoiceRequest}，使其 {@code capabilityCode}（@JsonIgnore，不影响报文）字段与
 * 现有 {@code @ValidateReq} + {@code RequestValidateAspect} 校验链路保持兼容；退税勾选自身不使用该路由字段。
 * 各退税勾选接口请求继承本类并补充专属字段。
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class TsRequest extends InvoiceRequest {

    public TsRequest(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

}
