package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：查询申请统计处理结果（CXTJCLJG）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 7「查询申请统计处理结果」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxQueryStatProcessResultReq extends GxRequest {

    public GxQueryStatProcessResultReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 批次流水号（String，32，必填）。
     */
    @NotBlank(message = "批次流水号(pclsh)为必填项")
    @Size(max = 32, message = "批次流水号(pclsh)长度不能超过 32")
    private String pclsh;
}
