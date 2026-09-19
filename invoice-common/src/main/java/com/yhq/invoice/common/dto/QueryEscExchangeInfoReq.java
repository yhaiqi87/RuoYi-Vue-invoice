package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：二手车销售统一发票换开信息单笔查询（ESCXSTYFPHXXXDBCX）。
 * 字段依据《乐企数字化电子发票（二手车）开票能力说明文档-V1.003》第 11 节。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryEscExchangeInfoReq extends InvoiceRequest {

    public QueryEscExchangeInfoReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 二手车销售统一发票号码（varchar，20，是）
     */
    @NotBlank(message = "二手车销售统一发票号码(escxstyfphm)为必填项")
    @Size(max = 20, message = "二手车销售统一发票号码(escxstyfphm)长度不能超过 20")
    private String escxstyfphm;
    /**
     * 特定要素类型代码（varchar，2，是，15：二手车）
     */
    @NotBlank(message = "特定要素类型代码(tdyslxDm)为必填项")
    @Size(max = 2, message = "特定要素类型代码(tdyslxDm)长度不能超过 2")
    private String tdyslxDm;
    /**
     * 销售方身份证件号码（varchar，20，是）
     */
    @NotBlank(message = "销售方身份证件号码(xsfsfzjhm)为必填项")
    @Size(max = 20, message = "销售方身份证件号码(xsfsfzjhm)长度不能超过 20")
    private String xsfsfzjhm;
}
