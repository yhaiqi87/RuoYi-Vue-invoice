package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：QueryRedConfirmDetail。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryRedConfirmDetailReq extends InvoiceRequest {

    public QueryRedConfirmDetailReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 红字确认单UUID（类型 varchar，长度 32，必填 是）
     */
    @NotBlank(message = "红字确认单UUID(uuid)为必填项")
    @Size(max = 32, message = "红字确认单UUID(uuid)长度不能超过 32")
    private String uuid;
    /**
     * （销售方）统一社会信用代码/纳税人识别号/身份证件号码（类型 varchar，长度 20，必填 是）
     */
    @NotBlank(message = "统一社会信用代码/纳税人识别号/身份证件号码(xsfnsrsbh)为必填项")
    @Size(max = 20, message = "统一社会信用代码/纳税人识别号/身份证件号码(xsfnsrsbh)长度不能超过 20")
    private String xsfnsrsbh;
}
