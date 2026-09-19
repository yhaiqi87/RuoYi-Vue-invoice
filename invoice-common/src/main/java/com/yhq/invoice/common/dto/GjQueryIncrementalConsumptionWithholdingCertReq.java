package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 归集请求：查询增量下载消费税代扣代缴完税凭证信息（CXZLXZXFSDKDJWSPZXX）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 13「查询增量下载消费税代扣代缴完税凭证信息」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GjQueryIncrementalConsumptionWithholdingCertReq extends GjRequest {

    public GjQueryIncrementalConsumptionWithholdingCertReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 归集日期（String，8，必填，YYYYMMDD）
     */
    @NotBlank(message = "归集日期(gjrq)为必填项")
    @Size(max = 8, message = "归集日期(gjrq)长度不能超过 8")
    private String gjrq;
    /**
     * 被扣缴纳税人识别号（String，30，必填）
     */
    @NotBlank(message = "被扣缴纳税人识别号(bkjnsrsbh)为必填项")
    @Size(max = 30, message = "被扣缴纳税人识别号(bkjnsrsbh)长度不能超过 30")
    private String bkjnsrsbh;
    /**
     * 包号（String，5，否，初始包号 1）
     */
    @Size(max = 5, message = "包号(packageno)长度不能超过 5")
    private String packageno;
}
