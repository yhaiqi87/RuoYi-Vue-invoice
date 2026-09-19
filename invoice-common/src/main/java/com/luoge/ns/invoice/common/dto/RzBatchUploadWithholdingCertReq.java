package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 发票入账请求：批量上传增值税入账代扣代缴完税凭证（PLSCRZDKDJWSPZ）。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 3「批量上传增值税入账代扣代缴完税凭证」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class RzBatchUploadWithholdingCertReq extends RzRequest {

    public RzBatchUploadWithholdingCertReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 扣缴义务人识别号（String，30，必填）
     */
    @NotBlank(message = "扣缴义务人识别号(kjywrsbh)为必填项")
    @Size(max = 30, message = "扣缴义务人识别号(kjywrsbh)长度不能超过 30")
    private String kjywrsbh;

    /**
     * dkdjmx明细（List<RzBatchUploadWithholdingCertReqDkdjmx>）
     */
    @Valid
    private List<RzBatchUploadWithholdingCertReqDkdjmx> dkdjmx;
}
