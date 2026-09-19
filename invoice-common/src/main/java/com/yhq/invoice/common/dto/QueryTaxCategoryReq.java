package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：QueryTaxCategory。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryTaxCategoryReq extends InvoiceRequest {

    public QueryTaxCategoryReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 纳税人识别号/统一社会信用代码（类型 varchar，长度 20，必填 是，开票纳税人识别号/统一社会信用代码）
     */
    @NotBlank(message = "纳税人识别号/统一社会信用代码(nsrsbh)为必填项")
    @Size(max = 20, message = "纳税人识别号/统一社会信用代码(nsrsbh)长度不能超过 20")
    private String nsrsbh;
    /**
     * 时间戳（类型 date，必填 否，格式：yyyyMMddHHmmss首次下载时为空；非首次下载时，传入上次下载返回的时间戳.格式：yyyyMMddHHmmss）
     */
    private String sjc;
    /**
     * 省级税务机关代码（类型 varchar，长度 11，必填 否）
     */
    private String sjswjgdm;
}
