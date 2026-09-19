package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import com.yhq.invoice.common.validation.DatePattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：QuerySummaryConfirm（CXFPHZQRXX，查询发票汇总确认信息）。字段依据基础版 V6.006 文档 §16.2 请求参数。
 */
@Getter
@Setter
@NoArgsConstructor
public class QuerySummaryConfirmReq extends InvoiceRequest {

    public QuerySummaryConfirmReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 销售方纳税人识别号（类型 varchar，长度 20，必填 是）
     */
    @NotBlank(message = "销售方纳税人识别号(xsfnsrsbh)为必填项")
    @Size(max = 20, message = "销售方纳税人识别号(xsfnsrsbh)长度不能超过 20")
    private String xsfnsrsbh;
    /**
     * 销售方省级税务机关代码（类型 varchar，长度 11，必填 是）
     */
    @NotBlank(message = "销售方省级税务机关代码(xsfsjswjgdm)为必填项")
    @Size(max = 11, message = "销售方省级税务机关代码(xsfsjswjgdm)长度不能超过 11")
    private String xsfsjswjgdm;
    /**
     * 平台编号（类型 varchar，长度 20，必填 否，若为空，则查询纳税人在所有平台的开票汇总信息）
     */
    @Size(max = 20, message = "平台编号(ptbh)长度不能超过 20")
    private String ptbh;
    /**
     * 月份（类型 varchar，长度 7，必填 否，格式：yyyy-MM；若为空，则查询所有历史月份数据）
     */
    @Size(max = 7, message = "月份(yf)长度不能超过 7")
    @DatePattern(pattern = "yyyy-MM", message = "月份(yf)格式必须为 yyyy-MM")
    private String yf;
}
