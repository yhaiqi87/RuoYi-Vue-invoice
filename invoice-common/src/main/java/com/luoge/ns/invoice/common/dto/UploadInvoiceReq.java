package com.luoge.ns.invoice.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.luoge.ns.invoice.common.enums.CapabilityCode;
import com.luoge.ns.invoice.common.validation.DatePattern;
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
 * 乐企发票上传请求（最外层 / 超集）：直接继承 {@link InvoiceRequest}，汇总基础版 / 不动产销售 /
 * 不动产经营租赁 / 建筑服务 / 货物运输五套「发票上传」报文的<b>全部字段</b>（公共 + 各能力专属），
 * 成为包含所有字段的并集。原公共基类 {@code AbstractUploadInvoiceReq} 已移除，公共字段上提至本类。
 *
 * <p>本类是门面与契约层（Controller → Facade → 渠道服务）的上送参数类型，
 * 由具体能力服务在 {@code uploadInvoice} 中通过对应特定 req 的 {@code from()} 转为能力专属 req 再上送。
 * 类级 {@link JsonInclude#NON_NULL} 使序列化仅输出非 null 字段。
 *
 * <p>字段归属（来自 {@code doc/请求参数/} 各能力示例 JSON）：
 * <ul>
 *     <li>公共字段：见下方「公共标量 / 公共嵌套数组 / 公共 item 类」；</li>
 *     <li>基础版专属：xmDm、dcxfsywlxbq、mtpzxx、sszyfwxybh、sfkjsszyfwfppm、zwhzyqtbs、
 *         jbrgjhdqDm、jbrnsrsbh、jbrsfzjzlDm；</li>
 *     <li>不动产销售专属：xmDm（与基础版共有）、dfgtgmbz、bdcxstdys、gtgmfList；</li>
 *     <li>不动产经营租赁专属：jbrgjhdqDm、jbrnsrsbh、jbrsfzjzlDm（与基础版共有）、bdcjyzltdys；</li>
 *     <li>建筑服务专属：xmDm（与基础版共有）、jzfwtdys；行级差异字段 jzfwfsd/jzxmmc 已合并进 {@link FpmxItem}；</li>
 *     <li>货物运输专属：行级差异字段 ysmxxh/qyd/ddd/ysgjzl/ysgjph/yshwmc 已合并进 {@link FpmxItem}，
 *         差额扣除差异字段 fpdm/pzlx 已合并进 {@link CekcItem}。</li>
 * </ul>
 *
 * <p>所有 item 类（FpmxItem/CekcItem/FjysItem/ZfxxItem 与各能力专属 BdcxsTdysItem/GtgmfItem/
 * BdcjyzlTdysItem/JzfwTdysItem）统一定义于本类，供本类及能力专属 req 引用。行级 / 差额扣除差异字段已
 * 上提进超集的 {@link FpmxItem}/{@link CekcItem}（并集）；能力专属 req 各自再定义自己的 {@code FpmxItem}/{@code CekcItem}
 * 承载本能力字段，{@code from()} 借助 BeanUtils 只拷贝目标类型声明的属性，自然丢弃其它能力专属字段。
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class UploadInvoiceReq extends InvoiceRequest {

    public UploadInvoiceReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    // ===== 公共标量（五能力共有） =====

    /**
     * 备注（varchar，450，否）
     */
    private String bz;
    /**
     * 差额征税类型代码（varchar，2，否）
     */
    private String cezslxDm;
    /**
     * 出口业务适用政策代码（varchar，2，否）
     */
    private String ckywsyzcDm;
    /**
     * CPU序列号（char，20，否）
     */
    private String cpuid;
    /**
     * 对应蓝字发票号码（varchar，20，否，红票开具时必传）
     */
    private String dylzfphm;
    /**
     * 复核人姓名（varchar，75，否）
     */
    private String fhrxm;
    /**
     * 发票号码（varchar，20，是）
     */
    @NotBlank(message = "发票号码(fphm)为必填项")
    @Size(max = 20, message = "发票号码(fphm)长度不能超过 20")
    private String fphm;
    /**
     * 发票开具方式代码（varchar，1，是，4：第三方平台开票 5：自建平台开票）
     */
    @NotBlank(message = "发票开具方式代码(fpkjfsDm)为必填项")
    @Size(max = 1, message = "发票开具方式代码(fpkjfsDm)长度不能超过 1")
    private String fpkjfsDm;
    /**
     * 发票票种（varchar，2，是，01：数字化电子专 02：数字化电子普）
     */
    @NotBlank(message = "发票票种(fppz)为必填项")
    @Size(max = 2, message = "发票票种(fppz)长度不能超过 2")
    private String fppz;
    /**
     * 购买方电话（varchar，60，否）
     */
    private String gmfdh;
    /**
     * 购买方地址（varchar，300，否）
     */
    private String gmfdz;
    /**
     * 购买方经办人（varchar，150，否）
     */
    private String gmfjbr;
    /**
     * 购买方经办人联系电话（varchar，60，否）
     */
    private String gmfjbrlxdh;
    /**
     * 购买方开户行名称（varchar，120，否）
     */
    private String gmfkhh;
    /**
     * （购买方）名称（varchar，300，是）
     */
    @NotBlank(message = "名称(gmfmc)为必填项")
    @Size(max = 300, message = "名称(gmfmc)长度不能超过 300")
    private String gmfmc;
    /**
     * （购买方）统一社会信用代码/纳税人识别号/身份证件号码（varchar，20，否，专票时必填）
     */
    private String gmfnsrsbh;
    /**
     * 购买方开户行银行账号（varchar，50，否）
     */
    private String gmfzh;
    /**
     * 购买方自然人标志（varchar，1，否，Y：是 N：否）
     */
    private String gmfzrrbz;
    /**
     * 合计金额（number，18,2，是）
     */
    @NotNull(message = "合计金额(hjje)为必填项")
    private BigDecimal hjje;
    /**
     * 合计税额（number，18,2，是）
     */
    @NotNull(message = "合计税额(hjse)为必填项")
    private BigDecimal hjse;
    /**
     * 红字确认单uuid（varchar，32，否）
     */
    private String hzqrduuid;
    /**
     * 红字确认信息单编号（varchar，20，否）
     */
    private String hzqrxxdbh;
    /**
     * 服务器地址（varchar，20，是，需自行采集开票申请设备）
     */
    @NotBlank(message = "服务器地址(ip)为必填项")
    @Size(max = 20, message = "服务器地址(ip)长度不能超过 20")
    private String ip;
    /**
     * 购买方经办人身份证件号码（varchar，30，否）
     */
    private String jbrsfzjhm;
    /**
     * 结算方式（varchar，2，否）
     */
    private String jsfs;
    /**
     * 价税合计（number，18,2，是）
     */
    @NotNull(message = "价税合计(jshj)为必填项")
    private BigDecimal jshj;
    /**
     * 交易信息来源渠道（varchar，2，否）
     */
    private String jyxxlyqdDm;
    /**
     * 开票人（varchar，300，是）
     */
    @NotBlank(message = "开票人(kpr)为必填项")
    @Size(max = 300, message = "开票人(kpr)长度不能超过 300")
    private String kpr;
    /**
     * 开票日期（datetime，是，yyyy-MM-dd HH:mm:ss）
     */
    @NotBlank(message = "开票日期(kprq)为必填项")
    @DatePattern(pattern = "yyyy-MM-dd HH:mm:ss", message = "开票日期(kprq)格式必须为 yyyy-MM-dd HH:mm:ss")
    private String kprq;
    /**
     * 开票人证件号码（varchar，30，否）
     */
    private String kprzjhm;
    /**
     * 开票人证件类型（varchar，4，否）
     */
    private String kprzjlx;
    /**
     * 乐企开票模式代码（varchar，2，否）
     */
    private String lqkpmsDm;
    /**
     * 蓝字发票标志（varchar，1，是，0：蓝字 1：红字）
     */
    @NotBlank(message = "蓝字发票标志(lzfpbz)为必填项")
    @Size(max = 1, message = "蓝字发票标志(lzfpbz)长度不能超过 1")
    private String lzfpbz;
    /**
     * mac地址（char，20，是，需自行采集开票申请设备）
     */
    @NotBlank(message = "mac地址(macdz)为必填项")
    @Size(max = 20, message = "mac地址(macdz)长度不能超过 20")
    private String macdz;
    /**
     * 平台编号（varchar，20，是，直连单位ID）
     */
    @NotBlank(message = "平台编号(ptbh)为必填项")
    @Size(max = 20, message = "平台编号(ptbh)长度不能超过 20")
    private String ptbh;
    /**
     * 区域代码（varchar，20，是）
     */
    @NotBlank(message = "区域代码(qyDm)为必填项")
    @Size(max = 20, message = "区域代码(qyDm)长度不能超过 20")
    private String qyDm;
    /**
     * 是否展示购买方地址电话（varchar，1，否）
     */
    private String sfzsgmfdzdh;
    /**
     * 是否展示购买方银行账号标签（varchar，1，否）
     */
    private String sfzsgmfyhzhbq;
    /**
     * 是否展示销售方地址电话（varchar，1，否）
     */
    private String sfzsxsfdzdh;
    /**
     * 是否展示销售方银行账号标签（varchar，1，否）
     */
    private String sfzsxsfyhzhbq;
    /**
     * 收购发票类型代码（varchar，2，否）
     */
    private String sgfplxDm;
    /**
     * 收款人姓名（varchar，150，否）
     */
    private String skrxm;
    /**
     * 收款银行名称（varchar，120，否）
     */
    private String skyhmc;
    /**
     * 收款银行账号（varchar，100，否）
     */
    private String skyhzh;
    /**
     * 特定要素（varchar，2，是，05：不动产销售服务发票 等）
     */
    @NotBlank(message = "特定要素(tdys)为必填项")
    @Size(max = 2, message = "特定要素(tdys)长度不能超过 2")
    private String tdys;
    /**
     * 销售方电话（varchar，60，否）
     */
    private String xsfdh;
    /**
     * 销售方地址（varchar，300，否）
     */
    private String xsfdz;
    /**
     * 销售方开户行（varchar，120，否）
     */
    private String xsfkhh;
    /**
     * （销售方）名称（varchar，300，是）
     */
    @NotBlank(message = "名称(xsfmc)为必填项")
    @Size(max = 300, message = "名称(xsfmc)长度不能超过 300")
    private String xsfmc;
    /**
     * （销售方）统一社会信用代码/纳税人识别号/身份证件号码（varchar，20，是）
     */
    @NotBlank(message = "统一社会信用代码/纳税人识别号/身份证件号码(xsfnsrsbh)为必填项")
    @Size(max = 20, message = "统一社会信用代码/纳税人识别号/身份证件号码(xsfnsrsbh)长度不能超过 20")
    private String xsfnsrsbh;
    /**
     * 销售方账号（varchar，50，否）
     */
    private String xsfzh;
    /**
     * 应税行为发生地（varchar，11，否）
     */
    private String ysxwfsd;
    /**
     * 主板序列号（varchar，20，否）
     */
    private String zbxlh;
    /**
     * （购买方）自然人国籍代码（varchar，3，否）
     */
    private String zrrgjDm;
    /**
     * （购买方）自然人证件号码（varchar，30，否）
     */
    private String zrrzjhm;
    /**
     * （购买方）自然人证件类型（varchar，3，否）
     */
    private String zrrzjlxDm;
    /**
     * 增值税即征即退代码（varchar，2，否）
     */
    private String zzsjzjtDm;

    // ===== 基础版专属标量 =====

    /**
     * 项目代码（varchar，6，否，基础版 / 不动产销售共有）
     */
    private String xmDm;
    /**
     * 电池消费税业务类型标签（char，2，否，仅基础版）
     */
    private String dcxfsywlxbq;
    /**
     * 煤炭品质信息（varchar，50，否，仅基础版）
     */
    private String mtpzxx;
    /** 涉税专业服务协议编号（varchar，20，否，仅基础版） */
    private String sszyfwxybh;
    /**
     * 是否开具涉税专业服务发票品目（varchar，1，否，仅基础版）
     */
    private String sfkjsszyfwfppm;
    /**
     * 中外合作油气田标识（char，1，否，仅基础版）
     */
    private String zwhzyqtbs;
    /**
     * 经办人国家或地区代码（varchar，3，否，基础版 / 不动产经营租赁共有）
     */
    private String jbrgjhdqDm;
    /**
     * 经办人纳税人识别号（varchar，20，否，基础版 / 不动产经营租赁共有）
     */
    private String jbrnsrsbh;
    /**
     * 购买方经办人证件种类代码（varchar，3，否，基础版 / 不动产经营租赁共有）
     */
    private String jbrsfzjzlDm;

    // ===== 二手车专属标量（仅二手车类特定要素发票上传 ESCLTDYSFPSC 复用） =====

    /**
     * 减按征税类型代码（varchar，2，否，仅二手车类特定要素发票上传）
     */
    private String jazslxDm;
    /**
     * 二手车销售统一发票号码（varchar，20，否，仅二手车类特定要素发票上传）
     */
    private String escxstyfphm;
    /**
     * 二手车销售统一纸质发票号码（varchar，20，否，仅二手车类特定要素发票上传）
     */
    private String escxstyzzfphm;
    /**
     * 二手车销售统一纸质发票代码（varchar，12，否，仅二手车类特定要素发票上传）
     */
    private String escxstyzzfpDm;

    // ===== 不动产销售专属标量 / 数组 =====

    /**
     * 多方共同购买标志（varchar，1，否，Y：多方共同购买N：非多方共同购买，仅不动产销售）
     */
    private String dfgtgmbz;

    /**
     * 不动产销售特定要素列表（仅不动产销售）
     */
    private List<BdcxsTdysItem> bdcxstdys;

    /**
     * 共同购买方列表（仅不动产销售）
     */
    private List<GtgmfItem> gtgmfList;

    // ===== 不动产经营租赁专属数组 =====

    /**
     * 不动产经营租赁特定要素列表（仅不动产经营租赁）
     */
    private List<BdcjyzlTdysItem> bdcjyzltdys;

    // ===== 建筑服务专属（顶层特定要素对象） =====

    /**
     * 建筑服务特定要素对象（仅建筑服务，顶层 jzfwtdys）
     */
    private JzfwTdysItem jzfwtdys;

    // ===== 公共嵌套数组（五能力共有） =====

    /**
     * 发票明细列表
     */
    @Valid
    private List<FpmxItem> fpmxList;
    /**
     * 差额扣除列表
     */
    @Valid
    private List<CekcItem> cekcList;
    /**
     * 附加要素列表
     */
    @Valid
    private List<FjysItem> fjysList;
    /**
     * 支付信息列表
     */
    @Valid
    private List<ZfxxItem> zfxxList;

    // ===== item 类（公共 + 各能力专属，统一定义于超集，供本类及子类引用） =====

    /**
     * 发票明细行项目（fpmxList 数组项）。
     */
    @Getter
    @Setter
    public static class FpmxItem {
        /**
         * 单价（varchar，25，否）
         */
        private String dj;
        /**
         * 单位（varchar，300，是）
         */
        @NotBlank(message = "单位(dw)为必填项")
        @Size(max = 300, message = "单位(dw)长度不能超过 300")
        private String dw;
        /**
         * 对应蓝字发票明细序号（number，8，否，红票必传）
         */
        private BigDecimal dylzfpmxxh;
        /**
         * 发票行性质（varchar，2，是）
         */
        @NotBlank(message = "发票行性质(fphxz)为必填项")
        @Size(max = 2, message = "发票行性质(fphxz)长度不能超过 2")
        private String fphxz;
        /**
         * 规格型号（varchar，150，否）
         */
        private String ggxh;
        /**
         * 含税金额（number，18,2，是）
         */
        @NotNull(message = "含税金额(hsje)为必填项")
        private BigDecimal hsje;
        /**
         * 货物或应税劳务、服务名称（varchar，300，是）
         */
        @NotBlank(message = "货物或应税劳务、服务名称(hwhyslwfwmc)为必填项")
        @Size(max = 300, message = "货物或应税劳务、服务名称(hwhyslwfwmc)长度不能超过 300")
        private String hwhyslwfwmc;
        /**
         * 金额（number，18,2，是）
         */
        @NotNull(message = "金额(je)为必填项")
        private BigDecimal je;
        /**
         * 扣除额（number，18,2，否）
         */
        private BigDecimal kce;
        /**
         * 煤炭种类（varchar，4，否）
         */
        private String mtzl;
        /**
         * 明细序号（number，8，是）
         */
        @NotNull(message = "明细序号(mxxh)为必填项")
        private BigDecimal mxxh;
        /**
         * 税额（number，18,2，是）
         */
        @NotNull(message = "税额(se)为必填项")
        private BigDecimal se;
        /**
         * 数量（varchar，25，否）
         */
        private String sl;
        /**
         * 增值税税率/征收率（number，16,6，是）
         */
        @NotNull(message = "增值税税率/征收率(slv)为必填项")
        private BigDecimal slv;
        /**
         * 商品服务简称（varchar，120，是）
         */
        @NotBlank(message = "商品服务简称(spfwjc)为必填项")
        @Size(max = 120, message = "商品服务简称(spfwjc)长度不能超过 120")
        private String spfwjc;
        /**
         * 商品和服务税收分类合并编码（varchar，19，是）
         */
        @NotBlank(message = "商品和服务税收分类合并编码(sphfwssflhbbm)为必填项")
        @Size(max = 19, message = "商品和服务税收分类合并编码(sphfwssflhbbm)长度不能超过 19")
        private String sphfwssflhbbm;
        /**
         * 项目名称（varchar，600，是）
         */
        @NotBlank(message = "项目名称(xmmc)为必填项")
        @Size(max = 600, message = "项目名称(xmmc)长度不能超过 600")
        private String xmmc;
        /**
         * 优惠政策标识（varchar，2，否）
         */
        private String yhzcbs;

        // ===== 各能力专属行级差异字段（已合并进超集 FpmxItem） =====

        /**
         * 建筑服务发生地（varchar，11，否，仅建筑服务）
         */
        private String jzfwfsd;
        /**
         * 建筑项目名称（varchar，300，否，仅建筑服务）
         */
        private String jzxmmc;
        /**
         * 运输明细序号（number，8，否，仅货物运输）
         */
        private BigDecimal ysmxxh;
        /**
         * 起运地（varchar，120，否，仅货物运输）
         */
        private String qyd;
        /**
         * 到达地（varchar，120，否，仅货物运输）
         */
        private String ddd;
        /**
         * 运输工具种类（varchar，15，否，仅货物运输）
         */
        private String ysgjzl;
        /**
         * 运输工具牌号（varchar，64，否，仅货物运输）
         */
        private String ysgjph;
        /**
         * 运输货物名称（varchar，300，否，仅货物运输）
         */
        private String yshwmc;
    }

    /**
     * 差额扣除（cekcList 数组项）。
     */
    @Getter
    @Setter
    public static class CekcItem {
        /**
         * 本次扣除金额（number，18,2，否）
         */
        private BigDecimal bckcje;
        /**
         * 备注（varchar，450，否）
         */
        private String bz;
        /**
         * 差额凭证代码（varchar，20，否）
         */
        private String cepzdm;
        /**
         * 差额凭证号码（varchar，40，否）
         */
        private String cepzhm;
        /**
         * 差额凭证类型代码（varchar，2，否）
         */
        private String cepzlxDm;
        /**
         * 开具日期（date，否，yyyy-MM-dd）
         */
        @DatePattern(pattern = "yyyy-MM-dd", message = "开具日期(kjrq)格式必须为 yyyy-MM-dd")
        private String kjrq;
        /**
         * 凭证合计金额（number，18,2，否）
         */
        private BigDecimal pzhjje;
        /**
         * 序号（number，8，否）
         */
        private BigDecimal xh;
        /**
         * 已扣除金额（number，18,2，否）
         */
        private BigDecimal ykcje;

        // ===== 各能力专属差额扣除差异字段（已合并进超集 CekcItem） =====

        /**
         * 发票代码（varchar，20，否，仅货物运输）
         */
        private String fpdm;
        /**
         * 凭证类型（varchar，2，否，仅货物运输）
         */
        private String pzlx;
    }

    /**
     * 附加要素（fjysList 数组项）。
     */
    @Getter
    @Setter
    public static class FjysItem {
        /**
         * 附加要素类型（varchar，200，否）
         */
        private String fjyslx;
        /**
         * 附加要素名称（varchar，200，否）
         */
        private String fjysmc;
        /**
         * 附加要素值（varchar，200，否）
         */
        private String fjysz;
    }

    /**
     * 支付信息（zfxxList 数组项）。
     */
    @Getter
    @Setter
    public static class ZfxxItem {
        /**
         * 交易（支付）单号（varchar，40，否）
         */
        private String jydh;
        /**
         * 交易流水号（varchar，50，否）
         */
        private String jylsh;
        /**
         * 支付渠道（varchar，3，否）
         */
        private String zfqdDm;
    }

    /**
     * 不动产销售特定要素（bdcxstdys 数组项，仅不动产销售）。
     */
    @Getter
    @Setter
    public static class BdcxsTdysItem {
        /**
         * 不动产单位代码（varchar，28，否，与网签合同备案编号二选一）
         */
        private String bdcdwdm;
        /**
         * 不动产坐落地址（省）（varchar，20，是）
         */
        @NotBlank(message = "不动产坐落地址(bdczldzS)为必填项")
        @Size(max = 20, message = "不动产坐落地址(bdczldzS)长度不能超过 20")
        private String bdczldzS;
        /**
         * 不动产坐落地址（市）（varchar，20，否）
         */
        private String bdczldzS1;
        /**
         * 不动产坐落地址（详细地址）（varchar，80，是）
         */
        @NotBlank(message = "不动产坐落地址(bdczldzXxdz)为必填项")
        @Size(max = 80, message = "不动产坐落地址(bdczldzXxdz)长度不能超过 80")
        private String bdczldzXxdz;
        /**
         * 产权证书/不动产权证号（varchar，40，否）
         */
        private String cqzsbh;
        /**
         * 核定计税价格（number，18,2，否）
         */
        private BigDecimal hdjsjg;
        /**
         * 跨地（市）标志（varchar，1，是，Y是N否）
         */
        @NotBlank(message = "跨地(kdsbz)为必填项")
        @Size(max = 1, message = "跨地(kdsbz)长度不能超过 1")
        private String kdsbz;
        /**
         * 实际成交含税金额（number，18,2，否，按核定计税价格征税时为必填）
         */
        private BigDecimal sjcjhsje;
        /**
         * 土地增值税项目编号（varchar，16，否）
         */
        private String tdzzsxmbh;
        /**
         * 网签合同备案编号（varchar，28，否，与不动产单位代码二选一）
         */
        private String wqhtbabh;
        /**
         * 序号（number，8，否）
         */
        private BigDecimal xh;
    }

    /**
     * 共同购买方（gtgmfList 数组项，仅不动产销售）。
     */
    @Getter
    @Setter
    public static class GtgmfItem {
        /**
         * 共同购买方（varchar，100，否）
         */
        private String gtgmf;
        /**
         * 证件号码（varchar，20，否）
         */
        private String zjhm;
        /**
         * 证件类型（varchar，3，否）
         */
        private String zjlx;
    }

    /**
     * 不动产经营租赁特定要素（bdcjyzltdys 数组项，仅不动产经营租赁）。
     */
    @Getter
    @Setter
    public static class BdcjyzlTdysItem {
        /**
         * 不动产坐落地址（省）（varchar，20，否）
         */
        private String bdczldzS;
        /**
         * 不动产坐落地址（市）（varchar，20，否）
         */
        private String bdczldzS1;
        /**
         * 不动产坐落地址（详细地址）（varchar，80，否）
         */
        private String bdczldzXxdz;
        /**
         * 车牌号列表（array，否）
         */
        private List<String> cph;
        /**
         * 产权证书/不动产权证号（varchar，40，否）
         */
        private String cqzsbh;
        /**
         * 跨地（市）标志（varchar，1，是，Y是N否）
         */
        @NotBlank(message = "跨地(kdsbz)为必填项")
        @Size(max = 1, message = "跨地(kdsbz)长度不能超过 1")
        private String kdsbz;
        /**
         * 序号（number，8，否）
         */
        private BigDecimal xh;
        /**
         * 租赁期起止（varchar，否）
         */
        private String zlqqz;
    }

    /**
     * 建筑服务特定要素（jzfwtdys 对象项，仅建筑服务）。
     */
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class JzfwTdysItem {
        /**
         * 土地增值税项目编号（varchar，16，否）
         */
        private String tdzzsxmbh;
        /**
         * 跨地市标志（char，1，是，Y：是 N：否）
         */
        private String kdsbz;
        /**
         * 跨区域涉税事项报验管理编号（varchar，50，否）
         */
        private String kqysssxbyglbh;
    }

}
