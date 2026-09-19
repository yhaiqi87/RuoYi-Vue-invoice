package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：queryDiffTaxCode（查询差额征税编码 / 原差额征税业务授权信息查询）。
 *
 * <p>该能力在不同渠道映射不同底层接口，请求参数不同：
 * <ul>
 *     <li>货物运输（HWYS）→ {@code CXCEZSBM}（查询差额征税编码，见
 *     《乐企数字化电子发票（货物运输）开票能力说明文档-V3.008》§19.3，请求参数为空 {@code {}}）。</li>
 *     <li>基础版 / 成品油 / 建筑服务 → {@code YCEZSYWSQXXCX}（原差额征税业务授权信息查询，
 *     见基础版 V6.006 文档 §20.2，请求必填 {@code nsrsbh}）。</li>
 * </ul>
 *
 * <p>为满足两套契约，统一携带 {@code nsrsbh}；货物运输渠道调用时该字段对 CXCEZSBM 无影响。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryDiffTaxCodeReq extends InvoiceRequest {

    public QueryDiffTaxCodeReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 纳税人识别号/统一社会信用代码（类型 varchar，长度 20，必填 是，基础版/成品油/建筑服务渠道调用 YCEZSYWSQXXCX 时必填）
     */
    @NotBlank(message = "纳税人识别号/统一社会信用代码(nsrsbh)为必填项")
    @Size(max = 20, message = "纳税人识别号/统一社会信用代码(nsrsbh)长度不能超过 20")
    private String nsrsbh;
}
