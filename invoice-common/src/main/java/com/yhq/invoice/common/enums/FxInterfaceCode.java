package com.yhq.invoice.common.enums;

import com.yhq.invoice.common.exception.InvoiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 反向开票通用（乐企能力编码 202083）接口清单。
 *
 * <p>服务编码（serviceCode）取自《乐企数字化电子发票（反向开票通用）开票能力说明文档-V1.002》的“接口清单”，
 * 对应请求头 {@code fwbm} 的取值；interfaceName 为对应“接口名称”。
 * FX 复用数学化电子发票通用概念 16 项，并独有「查询自然人开票信息」（CXFXKPTYZRRXX）。
 */
@Getter
@AllArgsConstructor
public enum FxInterfaceCode {
    GET_BATCH_PRE_CODE("QDFPPLFM", "获取数电票批量预赋码信息"),
    QUERY_QUOTA("CXSXED", "查询赋额额度"),
    DOWNLOAD_OR_RETURN_QUOTA("XZTHSXED", "下载/退回赋额额度"),
    ADJUST_QUOTA_VALIDITY("TZSXEDYXQ", "调整赋额额度有效期"),
    QUERY_TAXPAYER_RISK("CXNSRFXXX", "查询纳税人风险信息"),
    QUERY_TAXPAYER_BASIC("CXNSRJBXX", "查询纳税人基本信息"),
    QUERY_TAX_RATE("CXKYSL", "查询可用税率信息"),
    QUERY_TAX_CATEGORY("CXSSFLBM", "查询税收分类编码信息"),
    QUERY_NATURAL_PERSON_INVOICING("CXFXKPTYZRRXX", "查询自然人开票信息"),
    UPLOAD_INVOICE("FXKPTYFPSC", "反向开票通用发票上传"),
    QUERY_UPLOAD_RESULT("CXQDFPSCJG", "查询发票上传结果"),
    APPLY_RED_CONFIRM("QDHZQRDSQ", "数字化电子发票红字确认单申请"),
    CONFIRM_RED_CONFIRM("QDHZQRDQR", "数字化电子发票红字确认单确认"),
    QUERY_RED_CONFIRM_LIST("CXQDHZQRDLB", "查询数字化电子发票红字确认单列表"),
    QUERY_RED_CONFIRM_DETAIL("CXQDHZQRDMX", "查询数字化电子发票红字确认单明细"),
    UPLOAD_SUMMARY_CONFIRM("SCFPHZQRXX", "上传发票汇总确认信息"),
    QUERY_SUMMARY_CONFIRM("CXFPHZQRXX", "查询发票汇总确认信息");

    /**
     * 服务编码（请求头 fwbm 取值）。
     */
    private final String serviceCode;

    /**
     * 接口名称。
     */
    private final String interfaceName;

    /**
     * 按服务编码反查接口。
     */
    public static FxInterfaceCode fromServiceCode(String serviceCode) {
        for (FxInterfaceCode code : values()) {
            if (code.serviceCode.equals(serviceCode)) {
                return code;
            }
        }
        throw new InvoiceException("未知的反向开票通用接口服务编码: " + serviceCode);
    }
}
