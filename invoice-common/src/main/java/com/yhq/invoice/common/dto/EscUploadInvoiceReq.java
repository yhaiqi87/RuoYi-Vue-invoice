package com.yhq.invoice.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 二手车销售统一发票上传请求（ESCXSTYFPSC）：独立继承 {@link InvoiceRequest} 的专属请求。
 *
 * <p>二手车销售统一发票报文结构与数电票上传超集（{@link UploadInvoiceReq}）完全不同——以车辆 / 买卖方自然人信息为主，
 * 无 fpmxList 等数电票明细数组，故不复用超集，单独建模。字段依据
 * 《乐企数字化电子发票（二手车）开票能力说明文档-V1.003》第 12 节「二手车销售统一发票上传」请求参数补全。
 * 类级 {@code @JsonInclude(NON_NULL)} 使空字段不输出。
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class EscUploadInvoiceReq extends InvoiceRequest {

    public EscUploadInvoiceReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 蓝字发票标志（varchar，1，是，0：蓝字发票 1：红字发票）
     */
    @NotBlank(message = "蓝字发票标志(lzfpbz)为必填项")
    @Size(max = 1, message = "蓝字发票标志(lzfpbz)长度不能超过 1")
    private String lzfpbz;
    /**
     * 发票号码（varchar，20，是）
     */
    @NotBlank(message = "发票号码(fphm)为必填项")
    @Size(max = 20, message = "发票号码(fphm)长度不能超过 20")
    private String fphm;
    /**
     * 平台编号（varchar，20，是，直连单位ID）
     */
    @NotBlank(message = "平台编号(ptbh)为必填项")
    @Size(max = 20, message = "平台编号(ptbh)长度不能超过 20")
    private String ptbh;
    /**
     * 发票票种（varchar，2，是，04:二手车销售统一发票）
     */
    @NotBlank(message = "发票票种(fppz)为必填项")
    @Size(max = 2, message = "发票票种(fppz)长度不能超过 2")
    private String fppz;
    /**
     * 开票日期（date，是，yyyy-MM-dd HH:mm:ss）
     */
    @NotBlank(message = "开票日期(kprq)为必填项")
    private String kprq;
    /**
     * 开票人（varchar，20，是）
     */
    @NotBlank(message = "开票人(kpr)为必填项")
    @Size(max = 20, message = "开票人(kpr)长度不能超过 20")
    private String kpr;
    /**
     * 开票人证件号码（varchar，30，否）
     */
    @Size(max = 30, message = "开票人证件号码(kprzjhm)长度不能超过 30")
    private String kprzjhm;
    /**
     * 开票人证件类型（varchar，4，否）
     */
    @Size(max = 4, message = "开票人证件类型(kprzjlx)长度不能超过 4")
    private String kprzjlx;
    /**
     * 区域代码（varchar，20，是）
     */
    @NotBlank(message = "区域代码(qyDm)为必填项")
    @Size(max = 20, message = "区域代码(qyDm)长度不能超过 20")
    private String qyDm;
    /**
     * 应税行为发生地（varchar，11，否）
     */
    @Size(max = 11, message = "应税行为发生地(ysxwfsd)长度不能超过 11")
    private String ysxwfsd;
    /**
     * 开票企业开票行业性质（char，2，是，07：二手车市场 08：二手车经销企业 09：二手车拍卖企业）
     */
    @NotBlank(message = "开票企业开票行业性质(kpqykphyxz)为必填项")
    @Size(max = 2, message = "开票企业开票行业性质(kpqykphyxz)长度不能超过 2")
    private String kpqykphyxz;
    /**
     * 开票企业纳税人识别号（varchar，20，是）
     */
    @NotBlank(message = "开票企业纳税人识别号(kpqynsrsbh)为必填项")
    @Size(max = 20, message = "开票企业纳税人识别号(kpqynsrsbh)长度不能超过 20")
    private String kpqynsrsbh;
    /**
     * 开票企业纳税人名称（varchar，80，是）
     */
    @NotBlank(message = "开票企业纳税人名称(kpqynsrmc)为必填项")
    @Size(max = 80, message = "开票企业纳税人名称(kpqynsrmc)长度不能超过 80")
    private String kpqynsrmc;
    /**
     * 开票企业联系地址（varchar，80，是）
     */
    @NotBlank(message = "开票企业联系地址(kpqylxdz)为必填项")
    @Size(max = 80, message = "开票企业联系地址(kpqylxdz)长度不能超过 80")
    private String kpqylxdz;
    /**
     * 开票企业开户行（varchar，120，是）
     */
    @NotBlank(message = "开票企业开户行(kpqykhh)为必填项")
    @Size(max = 120, message = "开票企业开户行(kpqykhh)长度不能超过 120")
    private String kpqykhh;
    /**
     * 开票企业银行账号（varchar，50，是）
     */
    @NotBlank(message = "开票企业银行账号(kpqyyhzh)为必填项")
    @Size(max = 50, message = "开票企业银行账号(kpqyyhzh)长度不能超过 50")
    private String kpqyyhzh;
    /**
     * 开票企业联系电话（varchar，20，是）
     */
    @NotBlank(message = "开票企业联系电话(kpqylxdh)为必填项")
    @Size(max = 20, message = "开票企业联系电话(kpqylxdh)长度不能超过 20")
    private String kpqylxdh;
    /**
     * 特定要素（char，2，是，51：正常开具 52：反向开具）
     */
    @NotBlank(message = "特定要素(tdys)为必填项")
    @Size(max = 2, message = "特定要素(tdys)长度不能超过 2")
    private String tdys;
    /**
     * 购买方自然人标志（char，1，是，Y：是 N：否）
     */
    @NotBlank(message = "购买方自然人标志(gmfzrrbz)为必填项")
    @Size(max = 1, message = "购买方自然人标志(gmfzrrbz)长度不能超过 1")
    private String gmfzrrbz;
    /**
     * 买方单位/个人名称（varchar，300，是）
     */
    @NotBlank(message = "买方单位/个人名称(gmfmc)为必填项")
    @Size(max = 300, message = "买方单位/个人名称(gmfmc)长度不能超过 300")
    private String gmfmc;
    /**
     * 购买方纳税人识别号（varchar，20，否）
     */
    @Size(max = 20, message = "购买方纳税人识别号(gmfnsrsbh)长度不能超过 20")
    private String gmfnsrsbh;
    /**
     * （购买方）自然人证件类型（varchar，3，否）
     */
    @Size(max = 3, message = "（购买方）自然人证件类型(zrrzjlxDm)长度不能超过 3")
    private String zrrzjlxDm;
    /**
     * （购买方）自然人证件号码（varchar，30，否）
     */
    @Size(max = 30, message = "（购买方）自然人证件号码(zrrzjhm)长度不能超过 30")
    private String zrrzjhm;
    /**
     * （购买方）自然人国籍代码（varchar，3，否）
     */
    @Size(max = 3, message = "（购买方）自然人国籍代码(zrrgjDm)长度不能超过 3")
    private String zrrgjDm;
    /**
     * 买方单位/个人住址（varchar，80，是）
     */
    @NotBlank(message = "买方单位/个人住址(gmfdz)为必填项")
    @Size(max = 80, message = "买方单位/个人住址(gmfdz)长度不能超过 80")
    private String gmfdz;
    /**
     * 买方单位/个人电话（varchar，20，是）
     */
    @NotBlank(message = "买方单位/个人电话(gmfdh)为必填项")
    @Size(max = 20, message = "买方单位/个人电话(gmfdh)长度不能超过 20")
    private String gmfdh;
    /**
     * 销售方自然人标志（char，1，是，Y：是 N：否）
     */
    @NotBlank(message = "销售方自然人标志(xsfzrrbz)为必填项")
    @Size(max = 1, message = "销售方自然人标志(xsfzrrbz)长度不能超过 1")
    private String xsfzrrbz;
    /**
     * 卖方（销售方）单位/个人名称（varchar，300，是）
     */
    @NotBlank(message = "卖方（销售方）单位/个人名称(xsfmc)为必填项")
    @Size(max = 300, message = "卖方（销售方）单位/个人名称(xsfmc)长度不能超过 300")
    private String xsfmc;
    /**
     * 销售方纳税人识别号（varchar，20，否）
     */
    @Size(max = 20, message = "销售方纳税人识别号(xsfnsrsbh)长度不能超过 20")
    private String xsfnsrsbh;
    /**
     * 销售方自然人证件类型（varchar，3，否）
     */
    @Size(max = 3, message = "销售方自然人证件类型(xsfzrrzjlxDm)长度不能超过 3")
    private String xsfzrrzjlxDm;
    /**
     * 销售方自然人证件号码（varchar，30，否）
     */
    @Size(max = 30, message = "销售方自然人证件号码(xsfzrrzjhm)长度不能超过 30")
    private String xsfzrrzjhm;
    /**
     * 销售方自然人国籍代码（varchar，3，否）
     */
    @Size(max = 3, message = "销售方自然人国籍代码(xsfzrrgjDm)长度不能超过 3")
    private String xsfzrrgjDm;
    /**
     * 卖方（销售方）单位/个人住址（varchar，80，是）
     */
    @NotBlank(message = "卖方（销售方）单位/个人住址(xsfdz)为必填项")
    @Size(max = 80, message = "卖方（销售方）单位/个人住址(xsfdz)长度不能超过 80")
    private String xsfdz;
    /**
     * 卖方（销售方）单位/个人电话（varchar，20，是）
     */
    @NotBlank(message = "卖方（销售方）单位/个人电话(xsfdh)为必填项")
    @Size(max = 20, message = "卖方（销售方）单位/个人电话(xsfdh)长度不能超过 20")
    private String xsfdh;
    /**
     * 发票开具方式代码（varchar，1，是，4：第三方平台开票 5：自建平台开票）
     */
    @NotBlank(message = "发票开具方式代码(fpkjfsDm)为必填项")
    @Size(max = 1, message = "发票开具方式代码(fpkjfsDm)长度不能超过 1")
    private String fpkjfsDm;
    /**
     * 车牌号码（varchar，20，是）
     */
    @NotBlank(message = "车牌号码(cphm)为必填项")
    @Size(max = 20, message = "车牌号码(cphm)长度不能超过 20")
    private String cphm;
    /**
     * 登记证号（varchar，20，是）
     */
    @NotBlank(message = "登记证号(djzh)为必填项")
    @Size(max = 20, message = "登记证号(djzh)长度不能超过 20")
    private String djzh;
    /**
     * 车辆类型代码（varchar，5，是）
     */
    @NotBlank(message = "车辆类型代码(cllxDm)为必填项")
    @Size(max = 5, message = "车辆类型代码(cllxDm)长度不能超过 5")
    private String cllxDm;
    /**
     * 商品和服务税收分类合并编码（varchar，19，是）
     */
    @NotBlank(message = "商品和服务税收分类合并编码(sphfwssflhbbm)为必填项")
    @Size(max = 19, message = "商品和服务税收分类合并编码(sphfwssflhbbm)长度不能超过 19")
    private String sphfwssflhbbm;
    /**
     * 商品服务简称（varchar，120，是）
     */
    @NotBlank(message = "商品服务简称(spfwjc)为必填项")
    @Size(max = 120, message = "商品服务简称(spfwjc)长度不能超过 120")
    private String spfwjc;
    /**
     * 项目名称（varchar，600，是）
     */
    @NotBlank(message = "项目名称(xmmc)为必填项")
    @Size(max = 600, message = "项目名称(xmmc)长度不能超过 600")
    private String xmmc;
    /**
     * 货物或应税劳务、服务名称（varchar，300，是）
     */
    @NotBlank(message = "货物或应税劳务、服务名称(hwhyslwfwmc)为必填项")
    @Size(max = 300, message = "货物或应税劳务、服务名称(hwhyslwfwmc)长度不能超过 300")
    private String hwhyslwfwmc;
    /**
     * 车架识别代号（varchar，23，是，车辆识别代号与车架号同时存在时，填写车辆识别代号）
     */
    @NotBlank(message = "车架识别代号(clsbdh)为必填项")
    @Size(max = 23, message = "车架识别代号(clsbdh)长度不能超过 23")
    private String clsbdh;
    /**
     * 厂牌型号（varchar，60，是）
     */
    @NotBlank(message = "厂牌型号(cpxh1)为必填项")
    @Size(max = 60, message = "厂牌型号(cpxh1)长度不能超过 60")
    private String cpxh1;
    /**
     * 转入地车辆管理所名称（varchar，80，是）
     */
    @NotBlank(message = "转入地车辆管理所名称(zrdclglsmc)为必填项")
    @Size(max = 80, message = "转入地车辆管理所名称(zrdclglsmc)长度不能超过 80")
    private String zrdclglsmc;
    /**
     * 车价合计小写（number，18,2，是）
     */
    @NotNull(message = "车价合计小写(cjhjxx)为必填项")
    private BigDecimal cjhjxx;
    /**
     * 备注（varchar，230，否，蓝票最长230位；红票最长162位）
     */
    @Size(max = 230, message = "备注(bz)长度不能超过 230")
    private String bz;
    /**
     * 对应蓝字发票号码（varchar，20，否，蓝票必须为空，红票必须有值）
     */
    @Size(max = 20, message = "对应蓝字发票号码(dylzfphm)长度不能超过 20")
    private String dylzfphm;
    /**
     * 红字确认信息单编号（varchar，20，否，蓝票必须为空，红票必须有值）
     */
    @Size(max = 20, message = "红字确认信息单编号(hzqrxxdbh)长度不能超过 20")
    private String hzqrxxdbh;
    /**
     * 红字确认单uuid（varchar，32，否，蓝票必须为空，红票必须有值）
     */
    @Size(max = 32, message = "红字确认单uuid(hzqrduuid)长度不能超过 32")
    private String hzqrduuid;
    /**
     * 服务器地址（varchar，20，是）
     */
    @NotBlank(message = "服务器地址(ip)为必填项")
    @Size(max = 20, message = "服务器地址(ip)长度不能超过 20")
    private String ip;
    /**
     * mac地址（char，20，是）
     */
    @NotBlank(message = "mac地址(macdz)为必填项")
    @Size(max = 20, message = "mac地址(macdz)长度不能超过 20")
    private String macdz;
    /**
     * CPU序列号（char，20，否）
     */
    @Size(max = 20, message = "CPU序列号(cpuid)长度不能超过 20")
    private String cpuid;
    /**
     * 主板序列号（varchar，20，否）
     */
    @Size(max = 20, message = "主板序列号(zbxlh)长度不能超过 20")
    private String zbxlh;
}
