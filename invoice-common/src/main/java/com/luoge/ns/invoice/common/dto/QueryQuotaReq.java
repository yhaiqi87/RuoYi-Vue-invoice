package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：QueryQuota。字段依据乐企开票能力说明文档（基础版 V6.006，接口 CXSXED）补全。
 * 必填项与长度约束采用 jakarta.validation 注解声明，由调用方统一通过 hutool ValidationUtil.validate() 触发校验。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryQuotaReq extends InvoiceRequest {

    public QueryQuotaReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /** 纳税人识别号/统一社会信用代码（类型 varchar，长度 20，必填 是，申领发票的纳税人识别号或统一社会信用代码） */
    @NotBlank(message = "纳税人识别号/统一社会信用代码(nsrsbh)为必填项")
    @Size(max = 20, message = "纳税人识别号/统一社会信用代码(nsrsbh)长度不能超过 20")
    private String nsrsbh;
}
