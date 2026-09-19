package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 归集请求：批量出口转内销发票信息查询（PLCKZNXFPXXCX）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 6「批量出口转内销发票信息查询」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GjQueryBatchExportDomesticInvoiceReq extends GjRequest {

    public GjQueryBatchExportDomesticInvoiceReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 纳税人识别号（String，30，必填）
     */
    @NotBlank(message = "纳税人识别号(nsrsbh)为必填项")
    @Size(max = 30, message = "纳税人识别号(nsrsbh)长度不能超过 30")
    private String nsrsbh;
    /**
     * 数据归集日期起（String，8，必填，YYYYMMDD，不能跨月）
     */
    @NotBlank(message = "数据归集日期起(sjgjrqq)为必填项")
    @Size(max = 8, message = "数据归集日期起(sjgjrqq)长度不能超过 8")
    private String sjgjrqq;
    /**
     * 数据归集日期止（String，8，必填，YYYYMMDD，不能跨月）
     */
    @NotBlank(message = "数据归集日期止(sjgjrqz)为必填项")
    @Size(max = 8, message = "数据归集日期止(sjgjrqz)长度不能超过 8")
    private String sjgjrqz;
    /**
     * 发票类型（String，2，必填，01/08/81/85）
     */
    @NotBlank(message = "发票类型(fplx)为必填项")
    @Size(max = 2, message = "发票类型(fplx)长度不能超过 2")
    private String fplx;
}
