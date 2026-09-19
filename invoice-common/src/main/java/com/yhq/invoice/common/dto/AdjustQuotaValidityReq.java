package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import com.yhq.invoice.common.validation.DatePattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：AdjustQuotaValidity。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class AdjustQuotaValidityReq extends InvoiceRequest {

    public AdjustQuotaValidityReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 统一社会信用代码/纳税人识别号/身份证件号码（类型 varchar，长度 20，必填 是）
     */
    @NotBlank(message = "统一社会信用代码/纳税人识别号/身份证件号码(xsfnsrsbh)为必填项")
    @Size(max = 20, message = "统一社会信用代码/纳税人识别号/身份证件号码(xsfnsrsbh)长度不能超过 20")
    private String xsfnsrsbh;
    /**
     * 授信额度属期（类型 varchar，长度 7，必填 是，格式：yyyy-MM）
     */
    @NotBlank(message = "授信额度属期(sxedsq)为必填项")
    @Size(max = 7, message = "授信额度属期(sxedsq)长度不能超过 7")
    @DatePattern(pattern = "yyyy-MM", message = "授信额度属期(sxedsq)格式必须为 yyyy-MM")
    private String sxedsq;
}
