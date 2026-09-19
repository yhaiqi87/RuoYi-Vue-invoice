package com.yhq.invoice.common.enums;

import com.yhq.invoice.common.exception.InvoiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 货物运输（乐企能力编码 202026）接口清单。
 *
 * <p>服务编码（serviceCode）取自《乐企数字化电子发票（货物运输）开票能力说明文档-V3.008》的“接口清单”，
 * 对应请求头 {@code fwbm} 的取值；interfaceName 为对应“接口名称”。
 * 货物运输不支持批量发票下载申请、发票用途状态信息查询、涉税专业服务、房源信息查询、原差额征税业务授权信息查询；
 * 其专属接口为查询差额征税编码（CXCEZSBM）。
 */
@Getter
@AllArgsConstructor
public enum FreightInterfaceCode {
    GET_BATCH_PRE_CODE("QDFPPLFM", "获取发票批量预赋码信息"),
    QUERY_QUOTA("CXSXED", "查询授信额度"),
    DOWNLOAD_OR_RETURN_QUOTA("XZTHSXED", "下载/退回授信额度"),
    ADJUST_QUOTA_VALIDITY("TZSXEDYXQ", "调整授信额度有效期"),
    QUERY_TAXPAYER_RISK("CXNSRFXXX", "查询纳税人风险信息"),
    QUERY_TAXPAYER_BASIC("CXNSRJBXX", "查询纳税人基本信息"),
    QUERY_TAX_RATE("CXKYSL", "查询可用税率信息"),
    QUERY_TAX_CATEGORY("CXSSFLBM", "查询税收分类编码信息"),
    QUERY_RED_CONFIRM_DETAIL("CXQDHZQRDMX", "查询货物运输服务红字确认单明细信息"),
    UPLOAD_INVOICE("HWYSFPSC", "货物运输服务发票上传"),
    QUERY_RED_CONFIRM_LIST("CXQDHZQRDLB", "查询货物运输服务红字确认单列表信息"),
    QUERY_UPLOAD_RESULT("CXQDFPSCJG", "查询货物运输服务发票上传结果"),
    APPLY_RED_CONFIRM("QDHZQRDSQ", "货物运输服务红字确认单申请"),
    CONFIRM_RED_CONFIRM("QDHZQRDQR", "货物运输服务红字确认单确认"),
    UPLOAD_SUMMARY_CONFIRM("SCFPHZQRXX", "上传发票汇总确认信息"),
    QUERY_SUMMARY_CONFIRM("CXFPHZQRXX", "查询发票汇总确认信息"),
    QUERY_DEDUCTION_VOUCHER("KCPZQDXGFPSJXXCX", "扣除凭证清单相关发票数据信息查询"),
    QUERY_DIFF_TAX_CODE("CXCEZSBM", "查询差额征税编码");

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
    public static FreightInterfaceCode fromServiceCode(String serviceCode) {
        for (FreightInterfaceCode code : values()) {
            if (code.serviceCode.equals(serviceCode)) {
                return code;
            }
        }
        throw new InvoiceException("未知的货物运输入口服务编码: " + serviceCode);
    }
}
