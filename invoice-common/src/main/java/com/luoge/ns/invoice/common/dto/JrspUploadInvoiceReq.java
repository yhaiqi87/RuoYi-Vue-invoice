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
import java.util.stream.Collectors;

/**
 * 金融商品转让（JRSP）发票上传特定请求：直接继承 {@link InvoiceRequest}，<b>冗余声明</b>公共标量字段
 * （原公共基类 {@code AbstractUploadInvoiceReq} 已移除）。
 *
 * <p>嵌套 item 类处理，以避免带入其它能力的差异化字段：
 * <ul>
 *   <li>{@link FpmxItem}：本能力自定义，仅声明超集 fpmxList 的<b>公共行级字段</b>
 *       （裁剪掉建筑服务 / 货物运输等能力的差异化字段；行级 {@code kce} 扣除额仍保留）；</li>
 *   <li>{@code fjysList} / {@code zfxxList}：复用超集 {@link UploadInvoiceReq} 的 item 类（其无能力差异化字段）。</li>
 * </ul>
 * <p>金融商品转让发票上传<b>不含</b>差额扣除数组（cekcList）：差额征税通过 fpmxList 行内字段 {@code kce}（扣除额）
 * 体现，差额扣除异常标识（cekcycbs）仅出现在响应中。
 *
 * <p>由 {@link #from(UploadInvoiceReq)} 从最外层超集 req 投影得到：标量字段由 BeanUtils 拷贝（仅拷贝目标类型声明、
 * 自动丢弃其它能力专属字段）；fpmxList 逐元素投影为本能力 item（裁剪差异化字段）；
 * fjysList / zfxxList 因类型一致由 BeanUtils 直接拷贝。类级 {@code @JsonInclude(NON_NULL)} 使空字段不输出，
 * 序列化后严格对应金融商品转让报文。
 *
 * <p>金融商品转让发票上传的合规约束（来自《乐企数字化电子发票（金融商品转让）开票能力说明文档V1.005》）：
 * 特定要素必须为 28（金融商品转让）、差额征税类型代码必须为 02（差额开票）、发票票种必须为 02，
 * 且仅允许单张单行、不允许折扣行。相关取值约束由业务规则层保证，本 DTO 仅承载公共字段。
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class JrspUploadInvoiceReq extends InvoiceRequest {

    public JrspUploadInvoiceReq(CapabilityCode capabilityCode) {
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
    private String jbrsfzjzlDm;
    private String jbrgjhdqDm;
    private String jbrnsrsbh;
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

    // ===== 公共嵌套数组（FpmxItem 为本能力自定义 item 类，避免带入其它能力的差异化字段；fjys/zfxx 复用超集 item 类） =====

    @Valid
    private List<FpmxItem> fpmxList;
    @Valid
    private List<UploadInvoiceReq.FjysItem> fjysList;
    @Valid
    private List<UploadInvoiceReq.ZfxxItem> zfxxList;

    /**
     * 发票明细行项目（fpmxList 数组项）：公共行级字段（金融商品转让无行级差异化字段）。
     * 仅声明超集 FpmxItem 的公共部分，裁剪掉建筑服务/货物运输等其它能力的差异化字段
     * （jzfwfsd/jzxmmc/ysmxxh/qyd/ddd/ysgjzl/ysgjph/yshwmc/mtzl）。
     */
    @Getter
    @Setter
    public static class FpmxItem {
        private String dj;
        @NotBlank(message = "单位(dw)为必填项")
        @Size(max = 300, message = "单位(dw)长度不能超过 300")
        private String dw;
        private BigDecimal dylzfpmxxh;
        @NotBlank(message = "发票行性质(fphxz)为必填项")
        @Size(max = 2, message = "发票行性质(fphxz)长度不能超过 2")
        private String fphxz;
        private String ggxh;
        @NotNull(message = "含税金额(hsje)为必填项")
        private BigDecimal hsje;
        @NotBlank(message = "货物或应税劳务、服务名称(hwhyslwfwmc)为必填项")
        @Size(max = 300, message = "货物或应税劳务、服务名称(hwhyslwfwmc)长度不能超过 300")
        private String hwhyslwfwmc;
        @NotNull(message = "金额(je)为必填项")
        private BigDecimal je;
        private BigDecimal kce;
        @NotNull(message = "明细序号(mxxh)为必填项")
        private BigDecimal mxxh;
        @NotNull(message = "税额(se)为必填项")
        private BigDecimal se;
        private String sl;
        @NotNull(message = "增值税税率/征收率(slv)为必填项")
        private BigDecimal slv;
        @NotBlank(message = "商品服务简称(spfwjc)为必填项")
        @Size(max = 120, message = "商品服务简称(spfwjc)长度不能超过 120")
        private String spfwjc;
        @NotBlank(message = "商品和服务税收分类合并编码(sphfwssflhbbm)为必填项")
        @Size(max = 19, message = "商品和服务税收分类合并编码(sphfwssflhbbm)长度不能超过 19")
        private String sphfwssflhbbm;
        @NotBlank(message = "项目名称(xmmc)为必填项")
        @Size(max = 600, message = "项目名称(xmmc)长度不能超过 600")
        private String xmmc;
        private String yhzcbs;
    }

    /**
     * 从最外层超集 req 转为金融商品转让特定 req：标量字段由 BeanUtils 拷贝；
     * fpmxList 逐元素投影为本能力 item（仅保留公共字段，裁剪其它能力差异化字段），
     * fjysList / zfxxList 复用超集 item 类（其无能力差异化字段）。
     */
    public static JrspUploadInvoiceReq from(UploadInvoiceReq req) {
        if (req == null) {
            return null;
        }
        JrspUploadInvoiceReq target = new JrspUploadInvoiceReq();
        BeanUtils.copyProperties(req, target);
        if (req.getFpmxList() != null) {
            target.setFpmxList(req.getFpmxList().stream()
                    .map(JrspUploadInvoiceReq::toFpmxItem).collect(Collectors.toList()));
        }
        return target;
    }

    private static FpmxItem toFpmxItem(UploadInvoiceReq.FpmxItem src) {
        FpmxItem t = new FpmxItem();
        BeanUtils.copyProperties(src, t);
        return t;
    }
}
