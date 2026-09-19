package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：查询成品油库存（CXCPYKYSSFLBM）。字段依据《乐企数字化电子发票（成品油）开票能力说明文档-V2.009》。
 */
@Getter
@Setter
@NoArgsConstructor
public class CpyQueryInventoryReq extends InvoiceRequest {

    public CpyQueryInventoryReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 纳税人识别号/统一社会信用代码（类型 varchar，长度 20，必填 是，申领成品油库存的纳税人识别号或统一社会信用代码）
     */
    @NotBlank(message = "纳税人识别号/统一社会信用代码(nsrsbh)为必填项")
    @Size(max = 20, message = "纳税人识别号/统一社会信用代码(nsrsbh)长度不能超过 20")
    private String nsrsbh;
}
