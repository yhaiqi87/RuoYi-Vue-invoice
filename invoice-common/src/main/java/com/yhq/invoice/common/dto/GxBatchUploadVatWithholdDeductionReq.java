package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：批量上传增值税代扣代缴完税凭证抵扣勾选（PLSCZZSDKDJWSPZDKGX）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 12「批量上传增值税代扣代缴完税凭证抵扣勾选」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxBatchUploadVatWithholdDeductionReq extends GxRequest {

    public GxBatchUploadVatWithholdDeductionReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 扣缴义务人识别号（String，30，必填）。
     */
    @NotBlank(message = "扣缴义务人识别号(kjywrsbh)为必填项")
    @Size(max = 30, message = "扣缴义务人识别号(kjywrsbh)长度不能超过 30")
    private String kjywrsbh;

    /**
     * 勾选类型代码（String，2，必填）。
     */
    @NotBlank(message = "勾选类型代码(gxlxDm)为必填项")
    @Size(max = 2, message = "勾选类型代码(gxlxDm)长度不能超过 2")
    private String gxlxDm;

    /**
     * 税款所属期（String，6，否）。
     */
    @Size(max = 6, message = "税款所属期(skssq)长度不能超过 6")
    private String skssq;

    /**
     * dkdjmx明细（List<GxBatchUploadVatWithholdDeductionReqDkdjmx>）。
     */
    private java.util.List<GxBatchUploadVatWithholdDeductionReqDkdjmx> dkdjmx;
}
