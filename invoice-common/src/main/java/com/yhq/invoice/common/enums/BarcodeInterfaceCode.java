package com.yhq.invoice.common.enums;

import com.yhq.invoice.common.exception.InvoiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品条码（乐企能力编码 202099，占位）接口清单。
 *
 * <p>服务编码（serviceCode）对应请求头 {@code fwbm} 的取值。商品条码能力暂无乐企能力文档，
 * 本枚举暂<b>借用基础版（数电票）接口编码作为占位</b>，接口名称沿用基础版口径；
 * 真实接入时按《乐企数字化电子发票（商品条码）开票能力说明文档》的接口清单替换 serviceCode 与 interfaceName。
 * 支持范围与基础版对齐：覆盖通用概念，不含不动产 / 建筑 / 货物运输 / 二手车 / 反向开票专属接口。
 */
@Getter
@AllArgsConstructor
public enum BarcodeInterfaceCode {
    GET_BATCH_PRE_CODE("QDFPPLFM", "获取数电票批量预赋码信息"),
    QUERY_QUOTA("CXSXED", "查询发票额度"),
    DOWNLOAD_OR_RETURN_QUOTA("XZTHSXED", "下载/退回发票额度"),
    ADJUST_QUOTA_VALIDITY("TZSXEDYXQ", "调整发票额度有效期"),
    QUERY_TAXPAYER_RISK("CXNSRFXXX", "查询纳税人风险信息"),
    QUERY_TAXPAYER_BASIC("CXNSRJBXX", "查询纳税人基本信息"),
    QUERY_TAX_RATE("CXKYSL", "查询可用税率信息"),
    QUERY_TAX_CATEGORY("CXSSFLBM", "查询税收分类编码信息"),
    QUERY_RED_CONFIRM_DETAIL("CXQDHZQRDMX", "查询数电红字确认单明细信息"),
    UPLOAD_INVOICE("QDFPSC", "数电票上传"),
    QUERY_RED_CONFIRM_LIST("CXQDHZQRDLB", "查询数电红字确认单列表信息"),
    QUERY_UPLOAD_RESULT("CXQDFPSCJG", "查询数电票上传结果"),
    APPLY_RED_CONFIRM("QDHZQRDSQ", "数电红字确认单申请"),
    CONFIRM_RED_CONFIRM("QDHZQRDQR", "数电红字确认单确认"),
    UPLOAD_SUMMARY_CONFIRM("SCFPHZQRXX", "上传发票汇总确认信息"),
    QUERY_SUMMARY_CONFIRM("CXFPHZQRXX", "查询发票汇总确认信息"),
    QUERY_INVOICE_USAGE("FPZTXXCX", "发票用途状态信息查询"),
    QUERY_DEDUCTION_VOUCHER("KCPZQDXGFPSJXXCX", "扣除凭证清单相关发票数据信息查询"),
    QUERY_TAX_PRO_SERVICE("CXSSZYFWXGXX", "查询涉税专业服务相关信息"),
    QUERY_DIFF_TAX_AUTH("YCEZSYWSQXXCX", "原差额征税业务授权信息查询");

    /**
     * 服务编码（请求头 fwbm 取值，当前为占位值）。
     */
    private final String serviceCode;
    /**
     * 接口名称。
     */
    private final String interfaceName;

    /**
     * 按服务编码反查接口。
     */
    public static BarcodeInterfaceCode fromServiceCode(String serviceCode) {
        for (BarcodeInterfaceCode code : values()) {
            if (code.serviceCode.equals(serviceCode)) {
                return code;
            }
        }
        throw new InvoiceException("未知的商品条码接口服务编码: " + serviceCode);
    }
}
