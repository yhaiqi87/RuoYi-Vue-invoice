package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 乐企响应：QueryTaxProService（查询涉税专业服务相关信息）。
 * Data 信封内除使用单位标识、机构类别、分页信息外，另含涉税专业服务协议列表 sszyfwxyysList。
 */
@Getter
@Setter
public class QueryTaxProServiceRes extends InvoiceResponse {
    /**
     * 使用单位是否为涉税专业服务企业（类型 varchar，长度 1，必填 是，Y：是N：否）
     */
    private String sydwsfwsszyfwqy;
    /**
     * 涉税专业服务机构类别代码（类型 varchar，长度 2，必填 否，是否为涉税服务企业为“Y”时返回：01：税务师事务所02：会计师事务所03：律师事务所04：代理记账机构05：税务代理公司06：财税类咨询公司99：其他）
     */
    private String sszyfwjglbDm;
    /**
     * 涉税专业服务协议总数量（类型 number，长度 8，必填 否）
     */
    private BigDecimal total;
    /**
     * 涉税专业服务协议每页数量（类型 number，长度 8，必填 是）
     */
    private BigDecimal pageSize;
    /**
     * 涉税专业服务协议页码（类型 number，长度 8，必填 否）
     */
    private BigDecimal pageNumber;
    /**
     * 涉税专业服务协议列表
     */
    private List<Sszyfwxyys> sszyfwxyysList;

    /**
     * 涉税专业服务协议（列表项）。
     */
    @Getter
    @Setter
    public static class Sszyfwxyys {
        /**
         * 委托人统一社会信用代码（类型 varchar，长度 20，必填 否）
         */
        private String wtrtyshxydm;
        /**
         * 委托人名称（类型 varchar，长度 300，必填 否）
         */
        private String wtrmc;
        /**
         * 委托协议信息采集编号（类型 varchar，长度 20，必填 否）
         */
        private String wtxyxxcjbh;
        /**
         * 服务项目（类型 varchar，长度 200，必填 否，存在多个时用“，”隔开）
         */
        private String fwxm;
    }
}
