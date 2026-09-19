package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 乐企响应：QueryTaxCategory。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class QueryTaxCategoryRes extends InvoiceResponse {
    /**
     * 时间戳（类型 date，必填 是，格式：yyyyMMddHHmmss用于增量下载）
     */
    private String sjc;
    /**
     * 总数量（类型 number，长度 10，必填 是）
     */
    private BigDecimal count;
    /**
     * 父级编码（类型 varchar，长度 19，必填 是）
     */
    private String fjbm;
    /**
     * 特定要素标签归类（类型 varchar，长度 2，必填 否，01：成品油02：稀土03：建筑服务04：货物运输05：不动产销售06：不动产租赁07：代收车船使用税08：通行费09：旅客运输10：医疗服务11：农产品12：拖拉机和联合收割机13：机动车14：光伏收购15：卷烟17：报废产品）
     */
    private String tdyslxdm;
    /**
     * 差额征税（类型 varchar，长度 1，必填 否，Y：是N：否）
     */
    private String cezs;
    /**
     * 不征税（类型 varchar，长度 1，必填 否，Y：是N：否）
     */
    private String bzs;
    /**
     * 即征即退代码集合（类型 varchar，长度 150，必填 否，01：增值税即征即退-软件产品02：增值税即征即退-资源综合利用产品03：增值税即征即退-管道运输服务04：增值税即征即退-有形动产融资租赁服务05：增值税即征即退-有形动产融资性售后回租服务06：增值税即征即退-新型墙体材料07：增值税即征即退-风力发电产品08：增值税即征即退-光伏发电产品09：增值税即征即退-动漫软件产品10：增值税即征即退-飞机维修劳务11：增值税即征即退-黄金12：增值税即征即退-铂金）
     */
    private String jzjtdm;
    /**
     * 商品和服务名称（类型 varchar，长度 150，必填 是）
     */
    private String sphfwmc;
    /**
     * 商品和服务分类简称（类型 varchar，长度 120，必填 是）
     */
    private String sphfwfljc;
    /**
     * 说明（类型 varchar，必填 否）
     */
    private String sm;
    /**
     * 增值税税率（类型 varchar，长度 50，必填 否，取值返回是中文顿号连接的百分数。例如3%、5%）
     */
    private String zzssl;
    /**
     * 征收率（类型 varchar，长度 50，必填 否，取值返回是中文顿号连接的百分数。例如3%、5%）
     */
    private String zsl;
    /**
     * 增值税特殊管理（类型 varchar，长度 150，必填 否）
     */
    private String zzstsgl;
    /**
     * 增值税政策依据（类型 varchar，长度 300，必填 否）
     */
    private String zzszcyj;
    /**
     * 增值税特殊内容代码（类型 varchar，长度 300，必填 否）
     */
    private String zzstsnrdm;
    /**
     * 消费税管理（类型 varchar，长度 150，必填 否）
     */
    private String xfsgl;
    /**
     * 消费税政策依据（类型 varchar，长度 300，必填 否）
     */
    private String xfszcyj;
    /**
     * 消费税特殊内容代码（类型 varchar，长度 300，必填 否）
     */
    private String xfstsnrdm;
    /**
     * 关键字（类型 mediumtext，必填 否）
     */
    private String gjz;
    /**
     * 是否汇总项（类型 varchar，长度 1，必填 是，Y：是N：否）
     */
    private String sfhzx;
    /**
     * 对应统计局编码（2011年版）或国民行业代码（类型 mediumtext，必填 否）
     */
    private String dytjjbm;
    /**
     * 海关进出口商品品目（类型 mediumtext，必填 否）
     */
    private String hgjcksppm;
    /**
     * 启用日期（类型 date，必填 是，格式：yyyy-MM-dd）
     */
    private String qyrq;
    /**
     * 停用日期（类型 date，必填 否，格式：yyyy-MM-dd）
     */
    private String tyrq;
    /**
     * 行业代码（类型 varchar，长度 4，必填 否，主行业代码特定征收部门可空）
     */
    private String hydm;
    /**
     * 对应的行业（类型 varchar，长度 68，必填 否）
     */
    private String dydhy;
    /**
     * 行业对应的征收品目一（类型 varchar，长度 9，必填 否）
     */
    private String hydydzspm1;
    /**
     * 行业对应的征收名称一（类型 varchar，长度 210，必填 否）
     */
    private String hydydzsmc1;
    /**
     * 行业对应的征收品目二（类型 varchar，长度 9，必填 否）
     */
    private String hydydzspm2;
    /**
     * 行业对应的征收名称二（类型 varchar，长度 210，必填 否）
     */
    private String hydydzsmc2;
    /**
     * 行项目明细列表。
     */
    private List<DetailItem> detailList;

    /**
     * 行项目明细（乐企报文数组项）。
     */
    @Getter
    @Setter
    public static class DetailItem {
        /**
         * 商品和服务税收分类合并编码（类型 varchar，长度 19，必填 是）
         */
        private String sphfwssflhbbm;
    }
}
