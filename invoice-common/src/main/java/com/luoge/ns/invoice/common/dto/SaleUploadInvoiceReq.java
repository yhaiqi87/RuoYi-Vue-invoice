package com.luoge.ns.invoice.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 不动产销售（SALE）发票上传特定请求：直接继承 {@link InvoiceRequest}，<b>冗余声明</b>公共字段 + 不动产销售专属字段，
 * 成为真正的能力子集（原公共基类 {@code AbstractUploadInvoiceReq} 已移除）。
 * 本能力单独定义行级 item 类 {@link FpmxItem}/{@link CekcItem}（公共行级字段，无差异化），
 * 附加要素 / 支付信息 item 类引用超集 {@link UploadInvoiceReq} 的嵌套定义。
 *
 * <p>由 {@link #from(UploadInvoiceReq)} 从最外层超集 req 拷贝字段得到： BeanUtils 仅拷贝目标类型声明的属性，
 * 因此自动丢弃基础版 / 不动产经营租赁 / 建筑服务 / 货物运输的专属字段，序列化后严格对应不动产销售报文。
 * 类级 {@code @JsonInclude(NON_NULL)} 使空字段不输出。
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class SaleUploadInvoiceReq extends InvoiceRequest {

    public SaleUploadInvoiceReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    // ===== 公共标量（五能力共有，冗余声明） =====

    private String bz;
    private String cezslxDm;
    private String ckywsyzcDm;
    private String cpuid;
    private String dylzfphm;
    private String fhrxm;
    @NotBlank(message = "发票号码(fphm)为必填项")
    @Size(max = 20, message = "发票号码(fphm)长度不能超过 20")
    private String fphm;
    @NotBlank(message = "发票开具方式代码(fpkjfsDm)为必填项")
    @Size(max = 1, message = "发票开具方式代码(fpkjfsDm)长度不能超过 1")
    private String fpkjfsDm;
    @NotBlank(message = "发票票种(fppz)为必填项")
    @Size(max = 2, message = "发票票种(fppz)长度不能超过 2")
    private String fppz;
    private String gmfdh;
    private String gmfdz;
    private String gmfjbr;
    private String gmfjbrlxdh;
    private String gmfkhh;
    @NotBlank(message = "名称(gmfmc)为必填项")
    @Size(max = 300, message = "名称(gmfmc)长度不能超过 300")
    private String gmfmc;
    private String gmfnsrsbh;
    private String gmfzh;
    private String gmfzrrbz;
    @NotNull(message = "合计金额(hjje)为必填项")
    private BigDecimal hjje;
    @NotNull(message = "合计税额(hjse)为必填项")
    private BigDecimal hjse;
    private String hzqrduuid;
    private String hzqrxxdbh;
    @NotBlank(message = "服务器地址(ip)为必填项")
    @Size(max = 20, message = "服务器地址(ip)长度不能超过 20")
    private String ip;
    private String jbrsfzjhm;
    private String jsfs;
    @NotNull(message = "价税合计(jshj)为必填项")
    private BigDecimal jshj;
    private String jyxxlyqdDm;
    @NotBlank(message = "开票人(kpr)为必填项")
    @Size(max = 300, message = "开票人(kpr)长度不能超过 300")
    private String kpr;
    @NotBlank(message = "开票日期(kprq)为必填项")
    private String kprq;
    private String kprzjhm;
    private String kprzjlx;
    private String lqkpmsDm;
    @NotBlank(message = "蓝字发票标志(lzfpbz)为必填项")
    @Size(max = 1, message = "蓝字发票标志(lzfpbz)长度不能超过 1")
    private String lzfpbz;
    @NotBlank(message = "mac地址(macdz)为必填项")
    @Size(max = 20, message = "mac地址(macdz)长度不能超过 20")
    private String macdz;
    @NotBlank(message = "平台编号(ptbh)为必填项")
    @Size(max = 20, message = "平台编号(ptbh)长度不能超过 20")
    private String ptbh;
    @NotBlank(message = "区域代码(qyDm)为必填项")
    @Size(max = 20, message = "区域代码(qyDm)长度不能超过 20")
    private String qyDm;
    private String sfzsgmfdzdh;
    private String sfzsgmfyhzhbq;
    private String sfzsxsfdzdh;
    private String sfzsxsfyhzhbq;
    private String sgfplxDm;
    private String skrxm;
    private String skyhmc;
    private String skyhzh;
    @NotBlank(message = "特定要素(tdys)为必填项")
    @Size(max = 2, message = "特定要素(tdys)长度不能超过 2")
    private String tdys;
    private String xsfdh;
    private String xsfdz;
    private String xsfkhh;
    @NotBlank(message = "名称(xsfmc)为必填项")
    @Size(max = 300, message = "名称(xsfmc)长度不能超过 300")
    private String xsfmc;
    @NotBlank(message = "统一社会信用代码/纳税人识别号/身份证件号码(xsfnsrsbh)为必填项")
    @Size(max = 20, message = "统一社会信用代码/纳税人识别号/身份证件号码(xsfnsrsbh)长度不能超过 20")
    private String xsfnsrsbh;
    private String xsfzh;
    private String ysxwfsd;
    private String zbxlh;
    private String zrrgjDm;
    private String zrrzjhm;
    private String zrrzjlxDm;
    private String zzsjzjtDm;

    // ===== 公共嵌套数组（本能力 item 类） =====

    @Valid
    private List<FpmxItem> fpmxList;
    @Valid
    private List<CekcItem> cekcList;
    @Valid
    private List<UploadInvoiceReq.FjysItem> fjysList;
    @Valid
    private List<UploadInvoiceReq.ZfxxItem> zfxxList;

    // ===== 不动产销售专属标量 / 数组 =====

    /**
     * 项目代码（varchar，6，否，基础版 / 不动产销售共有）
     */
    private String xmDm;
    /**
     * 多方共同购买标志（varchar，1，否，Y：多方共同购买N：非多方共同购买）
     */
    private String dfgtgmbz;
    /**
     * 不动产销售特定要素列表
     */
    private List<UploadInvoiceReq.BdcxsTdysItem> bdcxstdys;
    /**
     * 共同购买方列表
     */
    private List<UploadInvoiceReq.GtgmfItem> gtgmfList;

    /**
     * 发票明细行项目（fpmxList 数组项）：不动产销售公共行级字段（无差异化）。
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
    }

    /**
     * 差额扣除（cekcList 数组项）：不动产销售公共差额扣除字段（无差异化）。
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
         * 开具日期（date，否，yyyy-mm-dd）
         */
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
    }

    /**
     * 从最外层超集 req 转为不动产销售特定 req（仅保留公共 + 不动产销售专属字段）。
     */
    public static SaleUploadInvoiceReq from(UploadInvoiceReq req) {
        if (req == null) {
            return null;
        }
        SaleUploadInvoiceReq target = new SaleUploadInvoiceReq();
        BeanUtils.copyProperties(req, target);
        return target;
    }
}
