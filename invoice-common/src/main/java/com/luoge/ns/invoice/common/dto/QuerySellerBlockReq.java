package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：自然人卖方限制名单阻断查询（ZRRMFXZMDZDCX）。
 * 字段依据《乐企数字化电子发票（二手车）开票能力说明文档-V1.003》第 9 节。
 */
@Getter
@Setter
@NoArgsConstructor
public class QuerySellerBlockReq extends InvoiceRequest {

    public QuerySellerBlockReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 销售方名称（varchar，300，是）
     */
    @NotBlank(message = "销售方名称(xsfmc)为必填项")
    @Size(max = 300, message = "销售方名称(xsfmc)长度不能超过 300")
    private String xsfmc;
    /**
     * 销售方纳税人识别号（varchar，20，是）
     */
    @NotBlank(message = "销售方纳税人识别号(xsfnsrsbh)为必填项")
    @Size(max = 20, message = "销售方纳税人识别号(xsfnsrsbh)长度不能超过 20")
    private String xsfnsrsbh;
}
