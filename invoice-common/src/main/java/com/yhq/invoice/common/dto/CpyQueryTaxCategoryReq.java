package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：查询成品油可用税收分类编码信息（CXCPYKC）。字段依据《乐企数字化电子发票（成品油）开票能力说明文档-V2.009》。
 */
@Getter
@Setter
@NoArgsConstructor
public class CpyQueryTaxCategoryReq extends InvoiceRequest {

    public CpyQueryTaxCategoryReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 纳税人识别号/统一社会信用代码（类型 varchar，长度 20，必填 是）
     */
    @NotBlank(message = "纳税人识别号/统一社会信用代码(nsrsbh)为必填项")
    @Size(max = 20, message = "纳税人识别号/统一社会信用代码(nsrsbh)长度不能超过 20")
    private String nsrsbh;
    /**
     * 省级税务机关代码（类型 varchar，长度 11，必填 是）
     */
    @NotBlank(message = "省级税务机关代码(sjswjgdm)为必填项")
    @Size(max = 11, message = "省级税务机关代码(sjswjgdm)长度不能超过 11")
    private String sjswjgdm;
}
