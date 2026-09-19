package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：查询发票农产品商品编码列表（CXFPNCPSPBMLB）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 21「查询发票农产品商品编码列表」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxQueryInvoiceAgriGoodsCodeListReq extends GxRequest {

    public GxQueryInvoiceAgriGoodsCodeListReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购买方纳税人识别号/统一社会信用代码（String，30，必填）。
     */
    @NotBlank(message = "购买方纳税人识别号/统一社会信用代码(gfsbh)为必填项")
    @Size(max = 30, message = "购买方纳税人识别号/统一社会信用代码(gfsbh)长度不能超过 30")
    private String gfsbh;
}
