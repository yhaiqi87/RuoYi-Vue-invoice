package com.yhq.invoice.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 发票入账Req明细：批量上传入账发票（PLSCRZFP）的「fpmx」明细数据。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 1「批量上传入账发票」明细小节补全。
 */
@Getter
@Setter
public class RzBatchUploadInvoiceReqFpmx {

    /**
     * 开票日期（String，8，必填，YYYYMMDD）
     */
    @NotBlank(message = "开票日期(kprq)为必填项")
    @Size(max = 8, message = "开票日期(kprq)长度不能超过 8")
    private String kprq;

    /**
     * 发票代码（String，12，否，数字化电子发票代码可为空）
     */
    @Size(max = 12, message = "发票代码(fpdm)长度不能超过 12")
    private String fpdm;

    /**
     * 发票号码（String，20，必填，数字化电子发票号码 20 位）
     */
    @NotBlank(message = "发票号码(fphm)为必填项")
    @Size(max = 20, message = "发票号码(fphm)长度不能超过 20")
    private String fphm;

    /**
     * 入账属期（String，6，否，格式 YYYYMM；申请勾选类型为 02/03/04/05 时必填，06 无需填写）
     */
    @Size(max = 6, message = "入账属期(skssq)长度不能超过 6")
    private String skssq;

    /**
     * 发票类型（String，2，必填）：01 增值税专用发票 / 02 货物运输业增值税专用发票 / 03 机动车销售统一发票 /
     * 04 增值税普通发票 / 08 增值税电子专用发票 / 10 增值税电子普通发票 / 11 卷式发票 / 14 通行费发票 /
     * 15 二手车销售统一发票 / 81 电子发票(增值税专用发票) / 82 电子发票(普通发票) / 83 机动车销售电子统一发票 /
     * 84 二手车销售电子统一发票 / 85 纸质发票(增值税专用发票) / 86 纸质发票(普通发票) /
     * 87 纸质发票(机动车销售统一发票) / 88 纸质发票(二手车销售统一发票) / 61 电子发票(航空运输客票电子行程单) /
     * 51 电子发票(铁路电子客票)
     */
    @NotBlank(message = "发票类型(fplx)为必填项")
    @Size(max = 2, message = "发票类型(fplx)长度不能超过 2")
    private String fplx;

    /**
     * 申请勾选类型（String，1，必填）：02 入账(企业所得税税前扣除) / 03 入账(企业所得税不扣除) /
     * 04 入账(个人所得税经营所得税前扣除) / 05 入账(个人所得税经营所得不扣除) / 06 入账撤销
     */
    @NotBlank(message = "申请勾选类型(sqgxlx)为必填项")
    @Size(max = 1, message = "申请勾选类型(sqgxlx)长度不能超过 1")
    private String sqgxlx;
}
