package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.*;
import com.yhq.invoice.common.validation.DatePattern;
import com.yhq.invoice.common.validation.EnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企请求：QueryRedConfirmList。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryRedConfirmListReq extends InvoiceRequest {

    public QueryRedConfirmListReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 用户角色类型（类型 varchar，长度 2，必填 是，0：销方1：购方）
     */
    @NotBlank(message = "用户角色类型(yhjslx)为必填项")
    @Size(max = 2, message = "用户角色类型(yhjslx)长度不能超过 2")
    @EnumValue(enumClass = Yhjslx.class, message = "用户角色类型(yhjslx)取值必须为 0(销方) 或 1(购方)")
    private String yhjslx;
    /**
     * （销售方）统一社会信用代码/纳税人识别号/身份证件号码（类型 varchar，长度 20，必填 否，条件必录，作为销方，必录）
     */
    private String xsfnsrsbh;
    /**
     * （销售方）名称（类型 varchar，长度 300，必填 否，条件必录，作为销方，必录）
     */
    private String xsfmc;
    /**
     * （购买方）统一社会信用代码/纳税人识别号/身份证件号码（类型 varchar，长度 20，必填 否，条件必录，作为购方，必录）
     */
    private String gmfnsrsbh;
    /**
     * （购买方）名称（类型 varchar，长度 300，必填 否，条件必录，作为购方，必录）
     */
    private String gmfmc;
    /**
     * 录入方身份（类型 varchar，长度 2，必填 否，0：销方1：购方）
     */
    @EnumValue(enumClass = Lrfsf.class, message = "录入方身份(lrfsf)取值必须为 0(销方) 或 1(购方)")
    private String lrfsf;
    /**
     * 录入日期起（类型 date，长度 20，必填 否，yyyy-MM-dd）
     */
    @DatePattern(pattern = "yyyy-MM-dd", message = "录入日期起(lrrqq)格式必须为 yyyy-MM-dd")
    private String lrrqq;
    /**
     * 录入日期止（类型 date，长度 20，必填 否，yyyy-MM-dd）
     */
    @DatePattern(pattern = "yyyy-MM-dd", message = "录入日期止(lrrqz)格式必须为 yyyy-MM-dd")
    private String lrrqz;
    /**
     * 蓝字发票代码（类型 varchar，长度 12，必填 否）
     */
    private String lzfpdm;
    /**
     * 蓝字发票号码（类型 varchar，长度 20，必填 否）
     */
    private String lzfphm;
    /**
     * 红字确认单编号（类型 varchar，长度 20，必填 否）
     */
    private String hzfpxxqrdbh;
    /**
     * 红字确认信息状态代码（类型 varchar，长度 2，必填 否，01：无需确认02：销方录入待购方确认03：购方录入待销方确认04：购销双方已确认05：作废（销方录入购方否认）06：作废（购方录入销方否认）07：作废（超72小时未确认）08：（发起方撤销）09：作废（确认后撤销）10：作废（异常凭证）11：作废（纳税人状态异常阻断）12：作废（自然人拒收）13：作废（已开具退税申请单））
     */
    @EnumValue(enumClass = HzqrxxztDm.class, message = "红字确认信息状态代码(hzqrxxztDm)取值必须为 01~13")
    private String hzqrxxztDm;
    /**
     * 发票票种代码（类型 varchar，长度 2，必填 否，01: 增值税专用发票02: 普通发票03: 机动车统一销售发票04: 二手车统一销售发票05：铁路客票电子发票06：航空运输电子客票行程单）
     */
    @EnumValue(enumClass = FppzDm.class, message = "发票票种代码(fppzDm)取值必须为 01~06")
    private String fppzDm;
    /**
     * 页码（类型 number，长度 10，必填 否）
     */
    private BigDecimal pageNumber;
    /**
     * 每页数量（类型 number，长度 10，必填 否）
     */
    private BigDecimal pageSize;
}
