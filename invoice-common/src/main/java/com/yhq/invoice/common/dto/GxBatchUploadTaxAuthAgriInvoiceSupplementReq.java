package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：批量上传税务机关代开农产品发票补录信息（PLSCSWJGDKNCPFPBLXX）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 19「批量上传税务机关代开农产品发票补录信息」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxBatchUploadTaxAuthAgriInvoiceSupplementReq extends GxRequest {

    public GxBatchUploadTaxAuthAgriInvoiceSupplementReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购方纳税人识别号/统一社会信用代码（String，30，必填）。
     */
    @NotBlank(message = "购方纳税人识别号/统一社会信用代码(gfsbh)为必填项")
    @Size(max = 30, message = "购方纳税人识别号/统一社会信用代码(gfsbh)长度不能超过 30")
    private String gfsbh;

    /**
     * fpmx明细（List<GxBatchUploadTaxAuthAgriInvoiceSupplementReqFpmx>）。
     */
    private java.util.List<GxBatchUploadTaxAuthAgriInvoiceSupplementReqFpmx> fpmx;
}
