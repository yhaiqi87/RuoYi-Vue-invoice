package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 归集请求：批量增值税代扣代缴完税凭证下载申请（PLZZSDKDJWSPZXZSQ）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 8「批量增值税代扣代缴完税凭证下载申请」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GjBatchVatWithholdingCertDownloadApplyReq extends GjRequest {

    public GjBatchVatWithholdingCertDownloadApplyReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 扣缴义务人识别号（String，30，必填）
     */
    @NotBlank(message = "扣缴义务人识别号(kjywrsbh)为必填项")
    @Size(max = 30, message = "扣缴义务人识别号(kjywrsbh)长度不能超过 30")
    private String kjywrsbh;
    /**
     * 填发日期起（String，8，必填，YYYYMMDD）
     */
    @NotBlank(message = "填发日期起(tfrqq)为必填项")
    @Size(max = 8, message = "填发日期起(tfrqq)长度不能超过 8")
    private String tfrqq;
    /**
     * 填发日期止（String，8，必填，YYYYMMDD）
     */
    @NotBlank(message = "填发日期止(tfrqz)为必填项")
    @Size(max = 8, message = "填发日期止(tfrqz)长度不能超过 8")
    private String tfrqz;
}
