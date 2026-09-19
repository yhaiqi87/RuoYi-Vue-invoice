package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 退税勾选请求：批量上传退税发票（PLFPTSGXQR）。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 1「批量上传退税发票」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class TsBatchUploadRefundInvoiceReq extends TsRequest {

    public TsBatchUploadRefundInvoiceReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，否）。
     */
    @Size(max = 30, message = "购买方统一社会信用代码/纳税人识别号(gfsbh)长度不能超过 30")
    private String gfsbh;

    /**
     * fpmx明细（List<TsBatchUploadRefundInvoiceReqFpmx>）。
     */
    private List<TsBatchUploadRefundInvoiceReqFpmx> fpmx;
}
