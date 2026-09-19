package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 归集请求：批量发票下载申请（PLFPXZSQ）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 1「批量发票下载申请」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GjBatchInvoiceDownloadApplyReq extends GjRequest {

    public GjBatchInvoiceDownloadApplyReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 统一社会信用代码/纳税人识别号（String，30，必填）
     */
    @NotBlank(message = "纳税人识别号(nsrsbh)为必填项")
    @Size(max = 30, message = "纳税人识别号(nsrsbh)长度不能超过 30")
    private String nsrsbh;
    /**
     * 开票日期起（String，8，必填，YYYYMMDD）
     */
    @NotBlank(message = "开票日期起(kprqq)为必填项")
    @Size(max = 8, message = "开票日期起(kprqq)长度不能超过 8")
    private String kprqq;
    /**
     * 开票日期止（String，8，必填，YYYYMMDD）
     */
    @NotBlank(message = "开票日期止(kprqz)为必填项")
    @Size(max = 8, message = "开票日期止(kprqz)长度不能超过 8")
    private String kprqz;
    /**
     * 发票类型（String，2，必填）
     */
    @NotBlank(message = "发票类型(fplx)为必填项")
    @Size(max = 2, message = "发票类型(fplx)长度不能超过 2")
    private String fplx;
    /**
     * 数据类型（String，1，必填，1：进项票 2：销项票 3：自然人推送）
     */
    @NotBlank(message = "数据类型(sjlx)为必填项")
    @Size(max = 1, message = "数据类型(sjlx)长度不能超过 1")
    private String sjlx;
}
