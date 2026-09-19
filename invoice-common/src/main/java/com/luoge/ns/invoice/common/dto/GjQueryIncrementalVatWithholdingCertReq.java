package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 归集请求：查询增量下载增值税代扣代缴完税凭证信息（CXZLXZZZSDKDJWSPZXX）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 10「查询增量下载增值税代扣代缴完税凭证信息」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GjQueryIncrementalVatWithholdingCertReq extends GjRequest {

    public GjQueryIncrementalVatWithholdingCertReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 归集日期（String，8，必填，YYYYMMDD）
     */
    @NotBlank(message = "归集日期(gjrq)为必填项")
    @Size(max = 8, message = "归集日期(gjrq)长度不能超过 8")
    private String gjrq;
    /**
     * 扣缴义务人识别号（String，30，必填）
     */
    @NotBlank(message = "扣缴义务人识别号(kjywrsbh)为必填项")
    @Size(max = 30, message = "扣缴义务人识别号(kjywrsbh)长度不能超过 30")
    private String kjywrsbh;
    /**
     * 包号（String，5，否，初始包号 1）
     */
    @Size(max = 5, message = "包号(packageno)长度不能超过 5")
    private String packageno;
}
