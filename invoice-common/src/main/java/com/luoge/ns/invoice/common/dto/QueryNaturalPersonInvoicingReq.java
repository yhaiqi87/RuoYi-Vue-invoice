package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 查询自然人开票信息请求（接口 CXFXKPTYZRRXX，仅反向开票通用）。
 * 提供查询自然人出售者开票信息，用于开票前的校验规则处理。
 * 字段依据《乐企数字化电子发票（反向开票通用）开票能力说明文档-V1.002》。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryNaturalPersonInvoicingReq extends InvoiceRequest {

    public QueryNaturalPersonInvoicingReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 自然人姓名（varchar，100，是）
     */
    @NotBlank(message = "自然人姓名(zrrxm)为必填项")
    @Size(max = 100, message = "自然人姓名(zrrxm)长度不能超过 100")
    private String zrrxm;
    /**
     * 身份证件类型（char，3，是，201：居民身份证）
     */
    @NotBlank(message = "身份证件类型(sfzjlx)为必填项")
    @Size(max = 3, message = "身份证件类型(sfzjlx)长度不能超过 3")
    private String sfzjlx;
    /**
     * 身份证件号码（varchar，20，是）
     */
    @NotBlank(message = "身份证件号码(sfzjhm)为必填项")
    @Size(max = 20, message = "身份证件号码(sfzjhm)长度不能超过 20")
    private String sfzjhm;
}
