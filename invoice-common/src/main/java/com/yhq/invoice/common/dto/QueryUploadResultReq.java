package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：QueryUploadResult。字段依据乐企开票能力说明文档（基础版 V6.006，接口 CXQDFPSCJG）补全。
 * 注：文档请求参数仅 sllsh 为必填；其余字段非文档请求项，未做必填校验。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryUploadResultReq extends InvoiceRequest {

    public QueryUploadResultReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 受理流水号（类型 String，长度 40，必填 是）
     */
    @NotBlank(message = "受理流水号(sllsh)为必填项")
    @Size(max = 40, message = "受理流水号(sllsh)长度不能超过 40")
    private String sllsh;
}
