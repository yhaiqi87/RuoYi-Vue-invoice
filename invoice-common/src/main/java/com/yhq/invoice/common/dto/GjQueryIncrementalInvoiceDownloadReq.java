package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 归集请求：查询增量下载发票信息（CXZLXZFPXX）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 3「查询增量下载发票信息」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GjQueryIncrementalInvoiceDownloadReq extends GjRequest {

    public GjQueryIncrementalInvoiceDownloadReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 归集日期（String，8，必填，YYYYMMDD）
     */
    @NotBlank(message = "归集日期(gjrq)为必填项")
    @Size(max = 8, message = "归集日期(gjrq)长度不能超过 8")
    private String gjrq;
    /**
     * 统一社会信用代码/纳税人识别号（String，30，必填）
     */
    @NotBlank(message = "纳税人识别号(nsrsbh)为必填项")
    @Size(max = 30, message = "纳税人识别号(nsrsbh)长度不能超过 30")
    private String nsrsbh;
    /**
     * 数据类型（String，1，必填，1：进项票 3：自然人推送）
     */
    @NotBlank(message = "数据类型(sjlx)为必填项")
    @Size(max = 1, message = "数据类型(sjlx)长度不能超过 1")
    private String sjlx;
    /**
     * 发票类型（String，2，必填）
     */
    @NotBlank(message = "发票类型(fplx)为必填项")
    @Size(max = 2, message = "发票类型(fplx)长度不能超过 2")
    private String fplx;
    /**
     * 包号（String，5，否，初始包号 1）
     */
    @Size(max = 5, message = "包号(packageno)长度不能超过 5")
    private String packageno;
}
