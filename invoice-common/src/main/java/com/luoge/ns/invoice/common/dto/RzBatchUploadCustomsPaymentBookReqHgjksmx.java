package com.luoge.ns.invoice.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 发票入账Req明细：批量上传入账海关缴款书（PLSCRZHGJKS）的「hgjksmx」明细数据。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 5「批量上传入账海关缴款书」明细小节补全。
 */
@Getter
@Setter
public class RzBatchUploadCustomsPaymentBookReqHgjksmx {

    /**
     * 海关缴款书号码（String，75，必填）
     */
    @NotBlank(message = "海关缴款书号码(hgjkshm)为必填项")
    @Size(max = 75, message = "海关缴款书号码(hgjkshm)长度不能超过 75")
    private String hgjkshm;

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
