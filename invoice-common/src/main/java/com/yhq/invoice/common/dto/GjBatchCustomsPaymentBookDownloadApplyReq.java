package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 归集请求：批量海关缴款书下载申请（PLHGJKSXZSQ）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 14「批量海关缴款书下载申请」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GjBatchCustomsPaymentBookDownloadApplyReq extends GjRequest {

    public GjBatchCustomsPaymentBookDownloadApplyReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 统一社会信用代码/纳税人识别号（String，30，必填）
     */
    @NotBlank(message = "纳税人识别号(nsrsbh)为必填项")
    @Size(max = 30, message = "纳税人识别号(nsrsbh)长度不能超过 30")
    private String nsrsbh;
    /**
     * 海关缴款书类型（String，1，必填，1：增值税海关缴款书 2：消费税海关缴款书）
     */
    @NotBlank(message = "海关缴款书类型(hgjkslx)为必填项")
    @Size(max = 1, message = "海关缴款书类型(hgjkslx)长度不能超过 1")
    private String hgjkslx;
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
