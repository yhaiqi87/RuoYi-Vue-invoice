package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 乐企响应：QueryRedConfirmDetail。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class QueryRedConfirmDetailRes extends InvoiceResponse {
    /**
     * 红字确认单UUID（类型 varchar，长度 32，必填 是）
     */
    private String uuid;
    /**
     * 红字发票信息确认单编号（类型 varchar，长度 20，必填 是）
     */
    private String hzfpxxqrdbh;
    /**
     * 录入方身份（类型 varchar，长度 1，必填 是，0：销方1：购方）
     */
    private String lrfsf;
    /**
     * （销售方）统一社会信用代码/纳税人识别号/身份证件号码（类型 varchar，长度 20，必填 是）
     */
    private String xsfnsrsbh;
    /**
     * （销售方）名称（类型 varchar，长度 300，必填 是）
     */
    private String xsfmc;
    /**
     * （购买方）统一社会信用代码/纳税人识别号/身份证件号码（类型 varchar，长度 20，必填 否）
     */
    private String gmfnsrsbh;
    /**
     * （购买方）名称（类型 varchar，长度 300，必填 是）
     */
    private String gmfmc;
    /**
     * 蓝字发票代码（类型 number，长度 12，必填 否）
     */
    private BigDecimal lzfpdm;
    /**
     * 蓝字发票号码（类型 number，长度 20，必填 否）
     */
    private BigDecimal lzfphm;
    /**
     * 是否纸质发票标志（类型 varchar，长度 1，必填 是，Y：纸质发票N：电子发票）
     */
    private String sfzzfpbz;
    /**
     * 蓝字发票开票日期（类型 datetime，必填 否，yyyy-MM-dd HH:mm:ss）
     */
    private String lzkprq;
    /**
     * 蓝字发票合计金额（类型 number，长度 18,2，必填 否）
     */
    private BigDecimal lzhjje;
    /**
     * 蓝字发票合计税额（类型 number，长度 18,2，必填 否）
     */
    private BigDecimal lzhjse;
    /**
     * 蓝字发票票种代码（类型 varchar，长度 2，必填 否，01: 增值税专用发票02: 普通发票03: 机动车统一销售发票04: 二手车统一销售发票）
     */
    private String lzfppzDm;
    /**
     * 蓝字发票特定要素类型代码（类型 varchar，长度 2，必填 是，05：不动产销售服务发票）
     */
    private String lzfpTdyslxDm;
    /**
     * 红字冲销金额（类型 number，长度 18,2，必填 是）
     */
    private BigDecimal hzcxje;
    /**
     * 红字冲销税额（类型 number，长度 18,2，必填 是）
     */
    private BigDecimal hzcxse;
    /**
     * 红字发票冲红原因代码（类型 varchar，长度 2，必填 是，01：开票有误02：销货退回03：服务中止04：销售折让）
     */
    private String chyyDm;
    /**
     * 红字确认单状态（类型 varchar，长度 2，必填 是，01：无需确认02：销方录入待购方确认03：购方录入待销方确认04：购销双方已确认05：作废（销方录入购方否认）06：作废（购方录入销方否认）07：作废（超72小时未确认）08：（发起方撤销）09：作废（确认后撤销）10：作废（异常凭证）11：作废（纳税人状态异常阻断）12：作废（自然人拒收）13：作废（已开具退税申请单））
     */
    private String hzqrxxztDm;
    /**
     * 确认日期（类型 datetime，必填 否，yyyy-MM-dd HH:mm:ss）
     */
    private String qrrq;
    /**
     * 已开具红字发票标志（类型 varchar，长度 1，必填 否，Y：已开具N：未开具）
     */
    private String ykjhzfpbz;
    /**
     * 红字发票号码（类型 String，长度 20，必填 否）
     */
    private String hzfphm;
    /**
     * 红字开票日期（类型 datetime，必填 是，yyyy-MM-dd HH:mm:ss）
     */
    private String hzkprq;
    /**
     * 有效标志（类型 varchar，长度 1，必填 是，Y：有效N：无效）
     */
    private String yxbz;
    /**
     * 差额征税类型代码（类型 varchar，长度 2，必填 否，空：非差额发票01：全额开票02：差额开票）
     */
    private String cezslxDm;
    /**
     * 录入日期（类型 datetime，必填 是，yyyy-MM-dd HH:mm:ss）
     */
    private String lrrq;
    /**
     * 红字确认单明细列表（对应乐企报文数组项 hzqrdmxList）。
     */
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
        private BigDecimal lzmxxh;
        /**
         * 序号（类型 number，长度 8，必填 是）
         */
        private BigDecimal xh;
        /**
         * 商品和服务税收分类合并编码（类型 varchar，长度 19，必填 是）
         */
        private String sphfwssflhbbm;
        /**
         * 货物或应税劳务、服务名称（类型 varchar，长度 300，必填 是）
         */
        private String hwhyslwfwmc;
        /**
         * 商品服务简称（类型 varchar，长度 120，必填 是）
         */
        private String spfwjc;
        /**
         * 项目名称（类型 varchar，长度 600，必填 是）
         */
        private String xmmc;
        /**
         * 规格型号（类型 varchar，长度 150，必填 否）
         */
        private String ggxh;
        /**
         * 单位（类型 varchar，长度 300，必填 否）
         */
        private String dw;
        /**
         * 单价（类型 varchar，长度 25，必填 否，如“数量”栏次非空，则本栏次必须非空）
         */
        private String fpspdj;
        /**
         * 数量（类型 varchar，长度 25，必填 否，如“单价”栏次非空，则本栏次必须非空）
         */
        private String fpspsl;
        /**
         * 金额（类型 number，长度 18,2，必填 是）
         */
        private BigDecimal je;
        /**
         * 税率（类型 number，长度 16,6，必填 是）
         */
        private BigDecimal sl1;
        /**
         * 税额（类型 number，长度 18,2，必填 是）
         */
        private BigDecimal se;
    }
}
