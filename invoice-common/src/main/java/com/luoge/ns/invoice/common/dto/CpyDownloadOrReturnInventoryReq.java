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
 * 乐企请求：下载或退回成品油库存（XZHTHCPYKC）。字段依据《乐企数字化电子发票（成品油）开票能力说明文档-V2.009》。
 *
 * <p>成品油库存下载/退回操作单次仅支持一个成品油商品编码的库存下载/退回处理。
 */
@Getter
@Setter
@NoArgsConstructor
public class CpyDownloadOrReturnInventoryReq extends InvoiceRequest {

    public CpyDownloadOrReturnInventoryReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 纳税人识别号/统一社会信用代码（类型 varchar，长度 20，必填 是）
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
    private String sqlx;
    /**
     * 业务流水号（类型 varchar，长度 100，必填 是，由“使用单位id”+“直连单位id”+“32位随机生成码”组成）
     */
    @NotBlank(message = "业务流水号(ywlsh)为必填项")
    @Size(max = 100, message = "业务流水号(ywlsh)长度不能超过 100")
    private String ywlsh;
    /**
     * 商品编码（类型 varchar，长度 19，必填 是）
     */
    @NotBlank(message = "商品编码(spbm)为必填项")
    @Size(max = 19, message = "商品编码(spbm)长度不能超过 19")
    private String spbm;
    /**
     * 数量（类型 number，长度 16,8，必填 是）
     */
    @NotNull(message = "数量(sl)为必填项")
    private BigDecimal sl;
}
