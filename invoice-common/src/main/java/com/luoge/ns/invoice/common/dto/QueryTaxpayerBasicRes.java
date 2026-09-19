package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 乐企响应：QueryTaxpayerBasic。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class QueryTaxpayerBasicRes extends InvoiceResponse {
    /**
     * 纳税人识别号/统一社会信用代码（类型 varchar，长度 20，必填 是）
     */
    private String nsrsbh;
    /**
     * 登记序号（类型 varchar，长度 20，必填 否）
     */
    private String djxh;
    /**
     * 税收档案编号（类型 varchar，长度 20，必填 否）
     */
    private String ssdabh;
    /**
     * 纳税人名称（类型 varchar，长度 300，必填 是）
     */
    private String nsrmc;
    /**
     * 纳税人类型标签（类型 varchar，长度 300，必填 是，1：一般纳税人2：小规模纳税人3：转登记小规模纳税人4：辅导期一般纳税人5：自然人）
     */
    private String nsrlx;
    /**
     * 生产经营地址（类型 varchar，长度 300，必填 是）
     */
    private String scjydz;
    /**
     * 注册地邮政编码（类型 varchar，长度 6，必填 是）
     */
    private String zcdyzbm;
    /**
     * 注册地联系电话（类型 varchar，长度 60，必填 否）
     */
    private String zcdlxdh;
    /**
     * 法人名称(法定代表人姓名（类型 varchar，长度 150，必填 是）
     */
    private String fddbrxm;
    /**
     * 登记注册类型（国标）（类型 varchar，长度 3，必填 否）
     */
    private String djzclxdm;
    /**
     * 行业代码（类型 varchar，长度 4，必填 是，主行业代码特定征收部门可空）
     */
    private String hydm;
    /**
     * 区县级税务机关代码（类型 varchar，长度 11，必填 是）
     */
    private String qxjswjgdm;
    /**
     * 税务机关代码（类型 varchar，长度 11，必填 是，主管税务所科分局）
     */
    private String zgswskfjdm;
    /**
     * 税务机关名称（类型 varchar，长度 300，必填 是，主管税务所科分局）
     */
    private String zgswskfjmc;
    /**
     * 财务负责人姓名（类型 varchar，长度 150，必填 是）
     */
    private String cwfzrxm;
    /**
     * 账户名称（类型 varchar，长度 300，必填 是）
     */
    private String khhmc;
    /**
     * 账号（类型 varchar，长度 50，必填 否，首选账户账号）
     */
    private String yhzh;
    /**
     * 企业办税人员姓名（类型 varchar，长度 150，必填 否）
     */
    private String bsrxm;
    /**
     * 数字化电子票试点企业标识（类型 varchar，长度 20，必填 是，Y：数字化电子票试点企业N：不是数字化电子票试点企业）
     */
    private String xdpsdqybz;
    /**
     * 出口企业分类（类型 varchar，长度 20，必填 是，空：非出口企业01：一类出口企业02：二类出口企业03：三类出口企业04：四类出口企业）
     */
    private String ckqyfldm;
    /**
     * 总分机构类型（类型 varchar，长度 20，必填 是，0：非总分机构 1：总机构2：分支机构 3：分总机构）
     */
    private String zfjglxdm;
    /**
     * 银行营业网点（类型 varchar，长度 13，必填 否）
     */
    private String yhyywddm;
    /**
     * 开票方纳税人状态（类型 varchar，长度 2，必填 是，01：受理02：筹建期03：正常04：停业05：非正常06：清算07：注销08：非正常户注销09：报验10：核销报验11：中断缴费12：恢复缴费13：简易注销无异议99：其他）
     */
    private String nsrztdm;
    /**
     * 纳税人类型标签有效期起（类型 date，必填 否，格式：yyyy-MM-dd）
     */
    private String yxqq;
    /**
     * 纳税人类型标签有效期止（类型 date，必填 否，格式：yyyy-MM-dd）
     */
    private String yxqz;
    /**
     * 企业行业性质（类型 varchar，长度 300，必填 否，01：成品油生产企业 02：成品油经销企业 03：国内机动车生产企业 04：进口机动车生产企业驻我国办事机构或总授权代理机构 05：机动车授权经销企业 06：其他机动车贸易商 07：二手车市场 08：二手车经销企业09：二手车拍卖企业 10：稀土矿产企业 11：稀土冶炼分离企业 12：稀土其他企业13：稀土矿产企业、稀土冶炼分离企业、稀土其他企业14：稀土矿产企业、稀土冶炼分离企业 15：稀土矿产企业、稀土其他企业 16：稀土冶炼分离企业、稀土其他企业 17：卷烟生产企业18：卷烟批发企业19：卷烟出口企业 20：生产型出口企业 21：外贸型出口企业22：外贸综合服务企业23：自贸区试点企业24：特殊监管区内企业 25：增值税一般纳税人资格试点26：市场采购试点27：退税商店28：电子商务出口企业29：享受出口退税政策的融资租赁企业 30：增值税零税率应税服务提供者31：房地产开发企业 32：农产品深加工企业33：外购石脑油、燃料油用于消费税退税企业 34：从事机动车进口的其他贸易商35：自产农产品者 36：农产品收购企业 37：光伏收购企业 38：住房租赁企业 39：冬奥会退税企业）
     */
    private String qyhyxzdm;
    /**
     * 消费税纳税人类型（类型 varchar，长度 20，必填 否，01：卷烟生产消费税纳税人 02：卷烟批发消费税纳税人 03：白酒生产消费税纳税人 04：黄酒生产消费税纳税人 05：啤酒生产消费税纳税人 06：其他酒生产消费税纳税人 07：小汽车生产消费税纳税人 08：超豪华小汽车消费税纳税人 09：汽油生产消费税纳税人 10：石脑油生产消费税纳税人 11：溶剂油生产消费税纳税人 12：润滑油生产消费税纳税人 13：柴油生产消费税纳税人 14：航空煤油生产消费税纳税人 15：燃料油生产消费税纳税人16：电池生产消费税纳税人 17：涂料生产消费税纳税人 18：高档化妆品生产消费税纳税人19：金银首饰铂金首饰和钻石及钻石饰品零售消费税纳税人20：其他贵重首饰和珠宝玉石生产消费税纳税人21：鞭炮焰火生产消费税纳税人 22：摩托车生产消费税纳税人 23：高尔夫球及球具生产消费税纳税人 24：高档手表生产消费税纳税人 25：游艇生产消费税纳税人 26：木制一次性筷子生产消费税纳税人 27：实木地板生产消费税纳税人 28：雪茄烟生产消费税纳税人 29：烟丝生产消费税纳税人）
     */
    private String xfsnsrlxdm;
}
