package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 建筑服务请求：QueryJzfwInfo（查询建筑服务信息，接口 CXJZFWXX）。
 * 字段依据《乐企数字化电子发票（建筑服务）开票能力说明文档-V4.002》补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryJzfwInfoReq extends InvoiceRequest {

    public QueryJzfwInfoReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 纳税人识别号（varchar，20，是，购买方纳税人识别号）
     */
    @NotBlank(message = "纳税人识别号(nsrsbh)为必填项")
    @Size(max = 20, message = "纳税人识别号(nsrsbh)长度不能超过 20")
    private String nsrsbh;
    /**
     * 省级税务机关代码（varchar，11，是，销售方省级税务机关代码）
     */
    @NotBlank(message = "省级税务机关代码(sjswjgdm)为必填项")
    @Size(max = 11, message = "省级税务机关代码(sjswjgdm)长度不能超过 11")
    private String sjswjgdm;
    /**
     * 项目编号（varchar，16，是）
     */
    @NotBlank(message = "项目编号(xmbh)为必填项")
    @Size(max = 16, message = "项目编号(xmbh)长度不能超过 16")
    private String xmbh;
}
