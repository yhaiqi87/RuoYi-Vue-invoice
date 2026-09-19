package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企请求：可换开二手车销售统一发票批量查询（KHKESCXSTYFPPLCX）。
 * 字段依据《乐企数字化电子发票（二手车）开票能力说明文档-V1.003》第 10 节。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryEscExchangeableBatchReq extends InvoiceRequest {

    public QueryEscExchangeableBatchReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 销售方身份证件号码（varchar，20，是）
     */
    @NotBlank(message = "销售方身份证件号码(xsfsfzjhm)为必填项")
    @Size(max = 20, message = "销售方身份证件号码(xsfsfzjhm)长度不能超过 20")
    private String xsfsfzjhm;
    /**
     * 购买方纳税人识别号（varchar，20，否）
     */
    @Size(max = 20, message = "购买方纳税人识别号(gmfnsrsbh)长度不能超过 20")
    private String gmfnsrsbh;
    /**
     * 特定要素类型代码（varchar，2，是，15：二手车）
     */
    @NotBlank(message = "特定要素类型代码(tdyslxDm)为必填项")
    @Size(max = 2, message = "特定要素类型代码(tdyslxDm)长度不能超过 2")
    private String tdyslxDm;
    /**
     * 开票起始日期（datetime，是，yyyy-MM-dd HH:mm:ss）
     */
    @NotBlank(message = "开票起始日期(kpqsrq)为必填项")
    private String kpqsrq;
    /**
     * 开票终止日期（datetime，是，yyyy-MM-dd HH:mm:ss）
     */
    @NotBlank(message = "开票终止日期(kpzzrq)为必填项")
    private String kpzzrq;
    /**
     * 页码（Number，4，是）
     */
    @NotNull(message = "页码(pageNumber)为必填项")
    private BigDecimal pageNumber;
    /**
     * 每页数量（Number，4，是）
     */
    @NotNull(message = "每页数量(pageSize)为必填项")
    private BigDecimal pageSize;
}
