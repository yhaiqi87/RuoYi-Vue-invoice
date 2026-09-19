package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 发票入账请求：查询海关缴款书入账处理结果（CXHGJKSRZCLJG）。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 6「查询海关缴款书入账处理结果」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class RzQueryCustomsPaymentBookRzResultReq extends RzRequest {

    public RzQueryCustomsPaymentBookRzResultReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 批次流水号（String，32，必填）
     */
    @NotBlank(message = "批次流水号(pclsh)为必填项")
    @Size(max = 32, message = "批次流水号(pclsh)长度不能超过 32")
    private String pclsh;
}
