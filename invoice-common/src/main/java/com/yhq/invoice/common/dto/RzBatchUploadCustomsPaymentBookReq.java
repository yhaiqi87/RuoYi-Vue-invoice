package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 发票入账请求：批量上传入账海关缴款书（PLSCRZHGJKS）。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 5「批量上传入账海关缴款书」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class RzBatchUploadCustomsPaymentBookReq extends RzRequest {

    public RzBatchUploadCustomsPaymentBookReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 缴款单位人纳税人识别号（String，20，必填）
     */
    @NotBlank(message = "缴款单位人纳税人识别号(jkdwrnsrsbh)为必填项")
    @Size(max = 20, message = "缴款单位人纳税人识别号(jkdwrnsrsbh)长度不能超过 20")
    private String jkdwrnsrsbh;

    /**
     * hgjksmx明细（List<RzBatchUploadCustomsPaymentBookReqHgjksmx>）
     */
    @Valid
    private List<RzBatchUploadCustomsPaymentBookReqHgjksmx> hgjksmx;
}
