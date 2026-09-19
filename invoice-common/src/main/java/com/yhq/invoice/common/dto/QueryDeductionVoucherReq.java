package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import com.yhq.invoice.common.validation.DatePattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：QueryDeductionVoucher。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryDeductionVoucherReq extends InvoiceRequest {

    public QueryDeductionVoucherReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 发票号码（类型 varchar，长度 20，必填 是）
     */
    @NotBlank(message = "发票号码(fphm)为必填项")
    @Size(max = 20, message = "发票号码(fphm)长度不能超过 20")
    private String fphm;
    /**
     * 发票代码（类型 varchar，长度 12，必填 否，税控票必传（数电纸票不传））
     */
    private String fpdm;
    /**
     * 购买方纳税人识别号（类型 varchar，长度 20，必填 是，必须为使用单位自身纳税人识别号）
     */
    @NotBlank(message = "购买方纳税人识别号(gmfnsrsbh)为必填项")
    @Size(max = 20, message = "购买方纳税人识别号(gmfnsrsbh)长度不能超过 20")
    private String gmfnsrsbh;
    /**
     * 开具日期（类型 date，长度 -，必填 是，yyyy-MM-dd）
     */
    @NotBlank(message = "开具日期(kjrq)为必填项")
    @DatePattern(pattern = "yyyy-MM-dd", message = "开具日期(kjrq)格式必须为 yyyy-MM-dd")
    private String kjrq;
}
