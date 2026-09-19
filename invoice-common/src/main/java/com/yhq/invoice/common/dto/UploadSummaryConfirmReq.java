package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import com.yhq.invoice.common.enums.Ywlx;
import com.yhq.invoice.common.validation.DatePattern;
import com.yhq.invoice.common.validation.EnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企请求：UploadSummaryConfirm。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class UploadSummaryConfirmReq extends InvoiceRequest {

    public UploadSummaryConfirmReq(CapabilityCode capabilityCode) {
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
     * 平台编号（类型 varchar，长度 20，必填 是）
     */
    @NotBlank(message = "平台编号(ptbh)为必填项")
    @Size(max = 20, message = "平台编号(ptbh)长度不能超过 20")
    private String ptbh;
    /**
     * 月份（类型 varchar，长度 7，必填 是，格式：yyyy-MM）
     */
    @NotBlank(message = "月份(yf)为必填项")
    @Size(max = 7, message = "月份(yf)长度不能超过 7")
    @DatePattern(pattern = "yyyy-MM", message = "月份(yf)格式必须为 yyyy-MM")
    private String yf;
    /**
     * 业务类型（类型 varchar，长度 1，必填 是，0：确认1：取消）
     */
    @NotBlank(message = "业务类型(ywlx)为必填项")
    @Size(max = 1, message = "业务类型(ywlx)长度不能超过 1")
    @EnumValue(enumClass = Ywlx.class, message = "业务类型(ywlx)取值必须为 0(确认) 或 1(取消)")
    private String ywlx;
    /**
     * 蓝字发票数量（类型 number，长度 10，必填 否，业务类型为“0”时必填）
     */
    private BigDecimal lzfpsl;
    /**
     * 蓝字发票金额（类型 number，长度 18,2，必填 否，业务类型为“0”时必填）
     */
    private BigDecimal lzfpje;
    /**
     * 蓝字发票税额（类型 number，长度 18,2，必填 否，业务类型为“0”时必填）
     */
    private BigDecimal lzfpse;
    /**
     * 红字发票数量（类型 number，长度 10，必填 否，业务类型为“0”时必填）
     */
    private BigDecimal hzfpsl;
    /**
     * 红字发票金额（类型 number，长度 18,2，必填 否，业务类型为“0”时必填）
     */
    private BigDecimal hzfpje;
    /**
     * 红字发票税额（类型 number，长度 18,2，必填 否，业务类型为“0”时必填）
     */
    private BigDecimal hzfpse;
}
