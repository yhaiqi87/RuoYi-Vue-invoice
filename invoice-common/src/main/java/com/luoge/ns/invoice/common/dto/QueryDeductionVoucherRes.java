package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企响应：QueryDeductionVoucher。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class QueryDeductionVoucherRes extends InvoiceResponse {
    /**
     * 发票号码（类型 VARCHAR，长度 20，必填 是）
     */
    private String fphm;
    /**
     * 发票代码（类型 VARCHAR，长度 12，必填 否）
     */
    private String fpdm;
    /**
     * 特定要素类型代码（类型 CHAR，长度 2，必填 否，01：成品油发票02：稀土发票03：建筑服务发票04：货物运输服务发票05：不动产销售服务发票06：不动产租赁服务发票07：代收车船税08：通行费09：旅客运输服务发票10：医疗服务（住院）发票11：医疗服务（门诊）发票12：自产农产品销售发票13拖拉机和联合收割机发票14：机动车15：二手车16：农产品收购发票17：光伏收购发票18：卷烟发票20：农产品发票21：铁路客票电子发票22：航空运输电子客票行程单23：白酒24：报废产品收购25：反向开票（通用）26：金银首饰批发27：金银首饰零售28：金融商品转让29：客运场站服务31：二手车*32：电子烟33：黄金交易结算（交易所购入）34：黄金交易结算（交易所销售）35：会员单位投资性黄金36：会员单位非投资性黄金37：客户标准黄金51：正常开具（针对二手车销售统一发票）52：反向开具（针对二手车销售统一发票））
     */
    private String tdyslxDm;
    /**
     * 价税合计（类型 NUMBER，长度 18,2，必填 是）
     */
    private BigDecimal jshj;
    /**
     * 发票票种代码（类型 CHAR，长度 2，必填 是，01：专票02：普票03：机动车销售统一发票04：二手车销售统一发票05：铁路电子客票06：航空电子行程单）
     */
    private String fppzDm;
    /**
     * 是否已用途确认（类型 CHAR，长度 1，必填 否，专票、机动车销售统一、航空、铁路发票必须返回“值”Y：是N：否）
     */
    private String sfyytqr;
}
