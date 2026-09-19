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
 * 发票入账请求：批量上传入账发票（PLSCRZFP）。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 1「批量上传入账发票」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class RzBatchUploadInvoiceReq extends RzRequest {

    public RzBatchUploadInvoiceReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，必填）
     */
    @NotBlank(message = "购买方统一社会信用代码/纳税人识别号(gfsbh)为必填项")
    @Size(max = 30, message = "购买方统一社会信用代码/纳税人识别号(gfsbh)长度不能超过 30")
    private String gfsbh;

    /**
     * fpmx明细（List<RzBatchUploadInvoiceReqFpmx>）
     */
    @Valid
    private List<RzBatchUploadInvoiceReqFpmx> fpmx;
}
