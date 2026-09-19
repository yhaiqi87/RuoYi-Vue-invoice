package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：批量上传待处理农产品发票确认清单（PLSCDCLNCPFPQRQD）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 17「批量上传待处理农产品发票确认清单」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxBatchUploadAgriInvoiceConfirmListReq extends GxRequest {

    public GxBatchUploadAgriInvoiceConfirmListReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购方纳税人识别号/统一社会信用代码（String，30，必填）。
     */
    @NotBlank(message = "购方纳税人识别号/统一社会信用代码(gfsbh)为必填项")
    @Size(max = 30, message = "购方纳税人识别号/统一社会信用代码(gfsbh)长度不能超过 30")
    private String gfsbh;

    /**
     * 农产品类型（String，2，必填）。
     */
    @NotBlank(message = "农产品类型(ncplx)为必填项")
    @Size(max = 2, message = "农产品类型(ncplx)长度不能超过 2")
    private String ncplx;

    /**
     * 勾选类型（String，2，必填）。
     */
    @NotBlank(message = "勾选类型(gxlxDm)为必填项")
    @Size(max = 2, message = "勾选类型(gxlxDm)长度不能超过 2")
    private String gxlxDm;

    /**
     * fpmx明细（List<GxBatchUploadAgriInvoiceConfirmListReqFpmx>）。
     */
    private java.util.List<GxBatchUploadAgriInvoiceConfirmListReqFpmx> fpmx;
}
