package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import com.yhq.invoice.common.enums.DownloadOrReturnType;
import com.yhq.invoice.common.validation.EnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企请求：DownloadOrReturnQuota。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class DownloadOrReturnQuotaReq extends InvoiceRequest {

    public DownloadOrReturnQuotaReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 纳税人识别号/统一社会信用代码（类型 varchar，长度 20，必填 是，申领发票的纳税人识别号或统一社会信用代码）
     */
    @NotBlank(message = "纳税人识别号/统一社会信用代码(nsrsbh)为必填项")
    @Size(max = 20, message = "纳税人识别号/统一社会信用代码(nsrsbh)长度不能超过 20")
    private String nsrsbh;
    /**
     * 平台编号（类型 varchar，长度 20，必填 是）
     */
    @NotBlank(message = "平台编号(ptbh)为必填项")
    @Size(max = 20, message = "平台编号(ptbh)长度不能超过 20")
    private String ptbh;
    /**
     * 申请类型（类型 varchar，长度 2，必填 是，0：下载 1：退回）
     */
    @NotBlank(message = "申请类型(sqlx)为必填项")
    @Size(max = 2, message = "申请类型(sqlx)长度不能超过 2")
    @EnumValue(enumClass = DownloadOrReturnType.class, message = "申请类型(sqlx)超出枚举范围，仅支持 0(下载)/1(退回)")
    private String sqlx;
    /**
     * 申请额度（类型 number，长度 20,2，必填 是）
     */
    @NotNull(message = "申请额度(sqed)为必填项")
    private BigDecimal sqed;
    /**
     * 业务流水号（类型 varchar，长度 100，必填 是，“使用单位id”+“直连单位id”+“32位随机生成数”）
     */
    @NotBlank(message = "业务流水号(ywlsh)为必填项")
    @Size(max = 100, message = "业务流水号(ywlsh)长度不能超过 100")
    private String ywlsh;
}
