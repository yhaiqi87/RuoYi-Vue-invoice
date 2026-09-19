package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 归集请求：发票风险信息查询（FPTSTXXXCX）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 4「发票风险信息查询」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GjQueryInvoiceRiskInfoReq extends GjRequest {

    public GjQueryInvoiceRiskInfoReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 统一社会信用代码/纳税人识别号（String，30，必填）
     */
    @NotBlank(message = "纳税人识别号(nsrsbh)为必填项")
    @Size(max = 30, message = "纳税人识别号(nsrsbh)长度不能超过 30")
    private String nsrsbh;
    /**
     * 发票类型（String，2，必填）
     */
    @NotBlank(message = "发票类型(fplx)为必填项")
    @Size(max = 2, message = "发票类型(fplx)长度不能超过 2")
    private String fplx;
    /**
     * 起始日期（String，8，必填，YYYYMMDD，起止不能跨月）
     */
    @NotBlank(message = "起始日期(qsrq)为必填项")
    @Size(max = 8, message = "起始日期(qsrq)长度不能超过 8")
    private String qsrq;
    /**
     * 终止日期（String，8，必填，YYYYMMDD，起止不能跨月）
     */
    @NotBlank(message = "终止日期(zzrq)为必填项")
    @Size(max = 8, message = "终止日期(zzrq)长度不能超过 8")
    private String zzrq;
    /**
     * 每页数量（String，10，必填，不超过 2000）
     */
    @NotBlank(message = "每页数量(pagesize)为必填项")
    @Size(max = 10, message = "每页数量(pagesize)长度不能超过 10")
    private String pagesize;
    /**
     * 游标（String，200，否，初始可为空）
     */
    @Size(max = 200, message = "游标(scrollId)长度不能超过 200")
    private String scrollId;
}
