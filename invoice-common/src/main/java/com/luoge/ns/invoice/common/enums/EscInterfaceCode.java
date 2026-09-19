package com.luoge.ns.invoice.common.enums;

import com.luoge.ns.invoice.common.exception.InvoiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 二手车（乐企能力编码 202082）接口清单。
 *
 * <p>服务编码（serviceCode）取自《乐企数字化电子发票（二手车）开票能力说明文档-V1.003》的“接口清单”，
 * 对应请求头 {@code fwbm} 的取值；interfaceName 为对应“接口名称”。
 * 二手车复用 16 个数电票通用概念接口，并含 4 个专属接口：自然人卖方限制名单阻断查询、可换开二手车销售统一发票批量查询、
 * 二手车销售统一发票换开信息单笔查询、二手车销售统一发票上传；二手车类特定要素发票上传（ESCLTDYSFPSC）与数电票上传报文一致。
 */
@Getter
@AllArgsConstructor
public enum EscInterfaceCode {
    GET_BATCH_PRE_CODE("QDFPPLFM", "获取数电票批量预赋码信息"),
    QUERY_QUOTA("CXSXED", "查询发票额度"),
    DOWNLOAD_OR_RETURN_QUOTA("XZTHSXED", "下载/退回发票额度"),
    ADJUST_QUOTA_VALIDITY("TZSXEDYXQ", "调整发票额度有效期"),
    QUERY_TAXPAYER_RISK("CXNSRFXXX", "查询纳税人风险信息"),
    QUERY_TAXPAYER_BASIC("CXNSRJBXX", "查询纳税人基本信息"),
    QUERY_TAX_RATE("CXKYSL", "查询可用税率信息"),
    QUERY_TAX_CATEGORY("CXSSFLBM", "查询税收分类编码信息"),
    QUERY_SELLER_BLOCK("ZRRMFXZMDZDCX", "自然人卖方限制名单阻断查询"),
    QUERY_ESC_EXCHANGEABLE_BATCH("KHKESCXSTYFPPLCX", "可换开二手车销售统一发票批量查询"),
    QUERY_ESC_EXCHANGE_INFO("ESCXSTYFPHXXXDBCX", "二手车销售统一发票换开信息单笔查询"),
    UPLOAD_ESC_INVOICE("ESCXSTYFPSC", "二手车销售统一发票上传"),
    UPLOAD_SPECIFIC_ELEMENT_INVOICE("ESCLTDYSFPSC", "二手车类特定要素发票上传"),
    QUERY_UPLOAD_RESULT("CXQDFPSCJG", "查询二手车发票上传结果"),
    APPLY_RED_CONFIRM("QDHZQRDSQ", "数电红字确认单申请"),
    CONFIRM_RED_CONFIRM("QDHZQRDQR", "数电红字确认单确认"),
    QUERY_RED_CONFIRM_LIST("CXQDHZQRDLB", "查询数电红字确认单列表"),
    QUERY_RED_CONFIRM_DETAIL("CXQDHZQRDMX", "查询数电红字确认单明细"),
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
    public static EscInterfaceCode fromServiceCode(String serviceCode) {
        for (EscInterfaceCode code : values()) {
            if (code.serviceCode.equals(serviceCode)) {
                return code;
            }
        }
        throw new InvoiceException("未知的二手车接口服务编码: " + serviceCode);
    }
}
