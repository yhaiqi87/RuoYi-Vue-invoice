package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 归集请求：查询增量下载海关缴款书信息（CXZLXZHGJKSXX）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 16「查询增量下载海关缴款书信息」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GjQueryIncrementalCustomsPaymentBookReq extends GjRequest {

    public GjQueryIncrementalCustomsPaymentBookReq(CapabilityCode capabilityCode) {
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
     * 海关缴款书类型（String，1，必填，1：增值税海关缴款书 2：消费税海关缴款书）
     */
    @NotBlank(message = "海关缴款书类型(hgjkslx)为必填项")
    @Size(max = 1, message = "海关缴款书类型(hgjkslx)长度不能超过 1")
    private String hgjkslx;
    /**
     * 包号（String，5，否，初始包号 1）
     */
    @Size(max = 5, message = "包号(packageno)长度不能超过 5")
    private String packageno;
}
