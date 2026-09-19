package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 建筑服务请求：CrossRegionSingleQuery（跨区域涉税数据单笔查询，接口 KQYSSSJDBCX）。
 * 字段依据《乐企数字化电子发票（建筑服务）开票能力说明文档-V4.002》补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class CrossRegionSingleReq extends InvoiceRequest {

    public CrossRegionSingleReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 跨区域涉税事项报验管理编号（varchar，50，是）
     */
    @NotBlank(message = "跨区域涉税事项报验管理编号(kqysssxbyglbh)为必填项")
    @Size(max = 50, message = "跨区域涉税事项报验管理编号(kqysssxbyglbh)长度不能超过 50")
    private String kqysssxbyglbh;
}
