package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import com.luoge.ns.invoice.common.validation.BigDecimalRange;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企请求：QueryTaxProService。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryTaxProServiceReq extends InvoiceRequest {

    public QueryTaxProServiceReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 委托人统一社会信用代码（类型 varchar，长度 20，必填 是，购买方纳税人识别号）
     */
    @NotBlank(message = "委托人统一社会信用代码(wtrtyshxydm)为必填项")
    @Size(max = 20, message = "委托人统一社会信用代码(wtrtyshxydm)长度不能超过 20")
    private String wtrtyshxydm;
    /**
     * 涉税专业服务协议页码（类型 number，长度 8，必填 是）
     */
    @NotNull(message = "涉税专业服务协议页码(pageNumber)为必填项")
    private BigDecimal pageNumber;
    /**
     * 涉税专业服务协议每页数量（类型 number，长度 8，必填 是，最大不超过50）
     */
    @NotNull(message = "涉税专业服务协议每页数量(pageSize)为必填项")
    @BigDecimalRange(max = "50", message = "涉税专业服务协议每页数量(pageSize)最大不超过50")
    private BigDecimal pageSize;
}
