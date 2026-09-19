package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 归集请求：批量出口转内销海关缴款书信息查询（PLCKZNXHGJKSXXCX）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 7「批量出口转内销海关缴款书信息查询」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GjQueryBatchExportDomesticCustomsReq extends GjRequest {

    public GjQueryBatchExportDomesticCustomsReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 纳税人识别号（String，30，必填）
     */
    @NotBlank(message = "纳税人识别号(nsrsbh)为必填项")
    @Size(max = 30, message = "纳税人识别号(nsrsbh)长度不能超过 30")
    private String nsrsbh;
    /**
     * 数据归集日期起（String，8，必填，YYYYMMDD，不能跨月）
     */
    @NotBlank(message = "数据归集日期起(sjgjrqq)为必填项")
    @Size(max = 8, message = "数据归集日期起(sjgjrqq)长度不能超过 8")
    private String sjgjrqq;
    /**
     * 数据归集日期止（String，8，必填，YYYYMMDD，不能跨月）
     */
    @NotBlank(message = "数据归集日期止(sjgjrqz)为必填项")
    @Size(max = 8, message = "数据归集日期止(sjgjrqz)长度不能超过 8")
    private String sjgjrqz;
}
