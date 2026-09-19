package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：批量上传海关缴款书抵扣勾选（PLHGJKSDKGX）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 4「批量上传海关缴款书抵扣勾选」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxBatchUploadCustomsDeductionReq extends GxRequest {

    public GxBatchUploadCustomsDeductionReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，必填）。
     */
    @NotBlank(message = "购买方统一社会信用代码/纳税人识别号(gfsbh)为必填项")
    @Size(max = 30, message = "购买方统一社会信用代码/纳税人识别号(gfsbh)长度不能超过 30")
    private String gfsbh;

    /**
     * 勾选类型（String，2，必填）。
     */
    @NotBlank(message = "勾选类型(gxlxDm)为必填项")
    @Size(max = 2, message = "勾选类型(gxlxDm)长度不能超过 2")
    private String gxlxDm;

    /**
     * 税款所属期（String，6，否）。
     */
    @Size(max = 6, message = "税款所属期(skssq)长度不能超过 6")
    private String skssq;

    /**
     * hgjksmx明细（List<GxBatchUploadCustomsDeductionReqHgjksmx>）。
     */
    private java.util.List<GxBatchUploadCustomsDeductionReqHgjksmx> hgjksmx;
}
