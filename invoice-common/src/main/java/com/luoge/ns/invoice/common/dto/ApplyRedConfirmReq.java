package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.*;
import com.luoge.ns.invoice.common.validation.DatePattern;
import com.luoge.ns.invoice.common.validation.EnumValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 乐企请求：ApplyRedConfirm。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class ApplyRedConfirmReq extends InvoiceRequest {

    public ApplyRedConfirmReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 录入方身份（类型 varchar，长度 2，必填 是，0：销方1：购方）
     */
    @NotBlank(message = "录入方身份(lrfsf)为必填项")
    @Size(max = 2, message = "录入方身份(lrfsf)长度不能超过 2")
    @EnumValue(enumClass = Lrfsf.class, message = "录入方身份(lrfsf)取值必须为 0(销方) 或 1(购方)")
    private String lrfsf;
    /**
     * 销售方纳税人识别号（类型 varchar，长度 20，必填 是）
     */
    @NotBlank(message = "销售方纳税人识别号(xsfnsrsbh)为必填项")
    @Size(max = 20, message = "销售方纳税人识别号(xsfnsrsbh)长度不能超过 20")
    private String xsfnsrsbh;
    /**
     * 销售方名称（类型 varchar，长度 300，必填 是）
     */
    @NotBlank(message = "销售方名称(xsfmc)为必填项")
    @Size(max = 300, message = "销售方名称(xsfmc)长度不能超过 300")
    private String xsfmc;
    /**
     * 购买方纳税人识别号（类型 varchar，长度 20，必填 否）
     */
    private String gmfnsrsbh;
    /**
     * 购买方名称（类型 varchar，长度 300，必填 是）
     */
    @NotBlank(message = "购买方名称(gmfmc)为必填项")
    @Size(max = 300, message = "购买方名称(gmfmc)长度不能超过 300")
    private String gmfmc;
    /**
     * 蓝字发票代码（类型 varchar，长度 12，必填 否）
     */
    private String lzfpdm;
    /**
     * 蓝字发票号码（类型 varchar，长度 20，必填 是）
     */
    @NotBlank(message = "蓝字发票号码(lzfphm)为必填项")
    @Size(max = 20, message = "蓝字发票号码(lzfphm)长度不能超过 20")
    private String lzfphm;
    /**
     * 是否纸质发票标志（类型 varchar，长度 1，必填 是，Y：纸质发票N：电子发票）
     */
    @NotBlank(message = "是否纸质发票标志(sfzzfpbz)为必填项")
    @Size(max = 1, message = "是否纸质发票标志(sfzzfpbz)长度不能超过 1")
    @EnumValue(enumClass = Sfzzfpbz.class, message = "是否纸质发票标志(sfzzfpbz)取值必须为 Y(纸质发票) 或 N(电子发票)")
    private String sfzzfpbz;
    /**
     * 蓝字发票开票日期（类型 datetime，必填 是，yyyy-MM-dd HH:mm:ss）
     */
    @NotBlank(message = "蓝字发票开票日期(lzkprq)为必填项")
    @DatePattern(pattern = "yyyy-MM-dd HH:mm:ss", message = "蓝字发票开票日期(lzkprq)格式必须为 yyyy-MM-dd HH:mm:ss")
    private String lzkprq;
    /**
     * 蓝字发票合计金额（类型 number，长度 18,2，必填 是）
     */
    @NotNull(message = "蓝字发票合计金额(lzhjje)为必填项")
    private BigDecimal lzhjje;
    /**
     * 蓝字发票合计税额（类型 number，长度 18,2，必填 是）
     */
    @NotNull(message = "蓝字发票合计税额(lzhjse)为必填项")
    private BigDecimal lzhjse;
    /**
     * 蓝字发票票种代码（类型 varchar，长度 2，必填 是，01: 增值税专用发票02: 普通发票03: 机动车统一销售发票04: 二手车统一销售发票）
     */
    @NotBlank(message = "蓝字发票票种代码(lzfppzDm)为必填项")
    @Size(max = 2, message = "蓝字发票票种代码(lzfppzDm)长度不能超过 2")
    @EnumValue(enumClass = LzfppzDm.class, message = "蓝字发票票种代码(lzfppzDm)取值必须为 01/02/03/04")
    private String lzfppzDm;
    /**
     * 蓝字发票特定要素类型代码（类型 varchar，长度 2，选填，取值范围 01~18、20，仅 05=不动产销售服务发票有单独名称）
     */
    @Size(max = 2, message = "蓝字发票特定要素类型代码(lzfpTdyslxDm)长度不能超过 2")
    @EnumValue(enumClass = LzfpTdyslxDm.class, message = "蓝字发票特定要素类型代码(lzfpTdyslxDm)取值必须为 01~18 或 20")
    private String lzfpTdyslxDm;
    /**
     * 红字冲销金额（类型 number，长度 18,2，必填 是）
     */
    @NotNull(message = "红字冲销金额(hzcxje)为必填项")
    private BigDecimal hzcxje;
    /**
     * 红字冲销税额（类型 number，长度 18,2，必填 是）
     */
    @NotNull(message = "红字冲销税额(hzcxse)为必填项")
    private BigDecimal hzcxse;
    /**
     * 红字发票冲红原因代码（类型 varchar，长度 2，必填 是，01：开票有误02：销货退回03：服务中止04：销售折让）
     */
    @NotBlank(message = "红字发票冲红原因代码(chyyDm)为必填项")
    @Size(max = 2, message = "红字发票冲红原因代码(chyyDm)长度不能超过 2")
    @EnumValue(enumClass = ChyyDm.class, message = "红字发票冲红原因代码(chyyDm)取值必须为 01/02/03/04")
    private String chyyDm;
    /**
     * 红字确认单明细列表（乐企报文数组项，对应请求字段 hzqrdmxList，必填）。
     */
    @Valid
    @NotNull(message = "红字确认单明细列表(hzqrdmxList)为必填项")
    private List<HzqrdmxItem> hzqrdmxList;

    /**
     * 红字确认单明细（乐企报文数组项）。
     */
    @Getter
    @Setter
    public static class HzqrdmxItem {
        /**
         * 蓝字发票明细序号（类型 number，长度 8，必填 是）
         */
        @NotNull(message = "蓝字发票明细序号(lzmxxh)为必填项")
        private BigDecimal lzmxxh;
        /**
         * 序号（类型 number，长度 8，必填 是）
         */
        @NotNull(message = "序号(xh)为必填项")
        private BigDecimal xh;
        /**
         * 商品和服务税收分类合并编码（类型 String，长度 19，必填 是）
         */
        @NotBlank(message = "商品和服务税收分类合并编码(sphfwssflhbbm)为必填项")
        @Size(max = 19, message = "商品和服务税收分类合并编码(sphfwssflhbbm)长度不能超过 19")
        private String sphfwssflhbbm;
        /**
         * 货物或应税劳务、服务名称（类型 varchar，长度 300，必填 是）
         */
        @NotBlank(message = "货物或应税劳务、服务名称(hwhyslwfwmc)为必填项")
        @Size(max = 300, message = "货物或应税劳务、服务名称(hwhyslwfwmc)长度不能超过 300")
        private String hwhyslwfwmc;
        /**
         * 商品服务简称（类型 String，长度 120，必填 是）
         */
        @NotBlank(message = "商品服务简称(spfwjc)为必填项")
        @Size(max = 120, message = "商品服务简称(spfwjc)长度不能超过 120")
        private String spfwjc;
        /**
         * 项目名称（类型 varchar，长度 600，必填 是）
         */
        @NotBlank(message = "项目名称(xmmc)为必填项")
        @Size(max = 600, message = "项目名称(xmmc)长度不能超过 600")
        private String xmmc;
        /**
         * 规格型号（类型 varchar，长度 150，必填 否）
         */
        private String ggxh;
        /**
         * 单位（类型 varchar，长度 300，必填 是，必须是平方米、亩、㎡、平方千米、公顷、h㎡、k㎡等。必须与蓝票“单位”保持一致。）
         */
        @NotBlank(message = "单位(dw)为必填项")
        @Size(max = 300, message = "单位(dw)长度不能超过 300")
        private String dw;
        /**
         * 单价（类型 varchar，长度 25，必填 否）
         */
        private String fpspdj;
        /**
         * 数量（类型 varchar，长度 25，必填 否）
         */
        private String fpspsl;
        /**
         * 金额（类型 number，长度 18,2，必填 是）
         */
        @NotNull(message = "金额(je)为必填项")
        private BigDecimal je;
        /**
         * 税率（类型 number，长度 16,6，必填 是）
         */
        @NotNull(message = "税率(sl1)为必填项")
        private BigDecimal sl1;
        /**
         * 税额（类型 number，长度 18,2，必填 是）
         */
        @NotNull(message = "税额(se)为必填项")
        private BigDecimal se;
    }
}
