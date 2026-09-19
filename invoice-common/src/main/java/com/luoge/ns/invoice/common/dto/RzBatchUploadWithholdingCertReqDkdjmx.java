package com.luoge.ns.invoice.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 发票入账Req明细：批量上传增值税入账代扣代缴完税凭证（PLSCRZDKDJWSPZ）的「dkdjmx」明细数据。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 3「批量上传增值税入账代扣代缴完税凭证」明细小节补全。
 */
@Getter
@Setter
public class RzBatchUploadWithholdingCertReqDkdjmx {

    /**
     * 代扣代缴完税凭证号（String，22，必填）
     */
    @NotBlank(message = "代扣代缴完税凭证号(dkdjwspzh)为必填项")
    @Size(max = 22, message = "代扣代缴完税凭证号(dkdjwspzh)长度不能超过 22")
    private String dkdjwspzh;

    /**
     * 被扣缴纳税人识别号（String，30，必填）
     */
    @NotBlank(message = "被扣缴纳税人识别号(bkjnsrsbh)为必填项")
    @Size(max = 30, message = "被扣缴纳税人识别号(bkjnsrsbh)长度不能超过 30")
    private String bkjnsrsbh;

    /**
     * 填发日期（String，8，必填，YYYYMMDD）
     */
    @NotBlank(message = "填发日期(tfrq)为必填项")
    @Size(max = 8, message = "填发日期(tfrq)长度不能超过 8")
    private String tfrq;

    /**
     * 入账属期（String，6，否，格式 YYYYMM；申请勾选类型为 02/03/04/05 时必填，06 无需填写）
     */
    @Size(max = 6, message = "入账属期(skssq)长度不能超过 6")
    private String skssq;

    /**
     * 申请勾选类型（String，1，必填）：02 入账(企业所得税税前扣除) / 03 入账(企业所得税不扣除) /
     * 04 入账(个人所得税经营所得税前扣除) / 05 入账(个人所得税经营所得不扣除) / 06 入账撤销
     */
    @NotBlank(message = "申请勾选类型(sqgxlx)为必填项")
    @Size(max = 1, message = "申请勾选类型(sqgxlx)长度不能超过 1")
    private String sqgxlx;
}
