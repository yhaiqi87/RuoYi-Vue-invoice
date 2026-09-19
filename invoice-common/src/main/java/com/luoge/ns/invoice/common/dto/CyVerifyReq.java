package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 发票查验请求：发票查验（单张发票实时查询下载接口，FPCY_NEW）。
 * 字段依据《乐企发票查验能力说明文档-V1.025》「发票查验（单张发票实时查询下载接口）」补全。
 *
 * <p>校验说明：fpdm / jym / kpje 是否必填随 fplx（发票类型）而定，详见文档 1.2 业务规则；
 * 此处仅对无条件必填项做 jakarta 校验，条件必填交由乐企端按 returncode 反馈（00 成功 / 02 查无数据 / 03 查验不一致等）。
 */
@Getter
@Setter
@NoArgsConstructor
public class CyVerifyReq extends CyRequest {

    public CyVerifyReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 发票类型（String，2，必填）
     * 01：增值税专用发票 02：货物运输业增值税专用发票 03：机动车销售统一发票 04：增值税普通发票
     * 08：增值税电子专用发票 10：增值税电子普通发票 11：卷式发票 14：通行费发票 15：二手车销售统一发票
     * 81：电子发票（增值税专用发票） 82：电子发票（普通发票） 85：纸质发票（增值税专用发票） 86：纸质发票（普通发票）
     * 51：电子发票（铁路电子客票） 61：电子发票（航空运输客票电子行程单）
     * 83：机动车销售电子统一发票 84：二手车销售电子统一发票 87：纸质发票（机动车销售统一发票） 88：纸质发票（二手车销售统一发票）
     */
    @NotBlank(message = "发票类型(fplx)为必填项")
    @Size(max = 2, message = "发票类型(fplx)长度不能超过 2")
    private String fplx;

    /**
     * 发票代码（String，12，否）
     * 发票类型为 01、02、03、04、08、10、11、14、15、85、86、87、88 传纸质发票代码。
     */
    @Size(max = 12, message = "发票代码(fpdm)长度不能超过 12")
    private String fpdm;

    /**
     * 发票号码（String，20，必填）
     * 纸质发票类型传纸质发票号码；81、82、51、61、83、84 传 20 位数电发票号码。
     */
    @NotBlank(message = "发票号码(fphm)为必填项")
    @Size(max = 20, message = "发票号码(fphm)长度不能超过 20")
    private String fphm;

    /**
     * 开票日期（String，8，必填，YYYYMMDD）
     */
    @NotBlank(message = "开票日期(kprq)为必填项")
    @Size(max = 8, message = "开票日期(kprq)长度不能超过 8")
    private String kprq;

    /**
     * 校验码（String，32，否），传校验码后六位。
     * 04、10、11、14、86（发票号码赋码后六位）类型必填。
     */
    @Size(max = 32, message = "校验码(jym)长度不能超过 32")
    private String jym;

    /**
     * 开票金额（String，18,2，否）
     * 08/01/85/02 传开具金额(不含税)；03/87 传不含税价；15/84/88 传车价合计；81/82/51/61/83 传价税合计。
     */
    @Size(max = 21, message = "开票金额(kpje)长度不能超过 21")
    private String kpje;

    /**
     * 纳税人识别号（String，20，必填）
     */
    @NotBlank(message = "纳税人识别号(nsrsbh)为必填项")
    @Size(max = 20, message = "纳税人识别号(nsrsbh)长度不能超过 20")
    private String nsrsbh;
}
