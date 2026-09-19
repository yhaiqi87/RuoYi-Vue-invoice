package com.yhq.invoice.common.enums;

import com.yhq.invoice.common.exception.InvoiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 建筑服务（乐企能力编码 202044）接口清单。
 *
 * <p>服务编码（serviceCode）取自《乐企数字化电子发票（建筑服务）开票能力说明文档-V4.002》的“接口清单”，
 * 对应请求头 {@code fwbm} 的取值；interfaceName 为对应“接口名称”。
 * 建筑服务专属：查询建筑服务信息、跨区域涉税数据单笔/批量查询；另含原差额征税业务授权信息查询。
 */
@Getter
@AllArgsConstructor
public enum BuildInterfaceCode {
    GET_BATCH_PRE_CODE("QDFPPLFM", "获取发票批量预赋码信息"),
    QUERY_QUOTA("CXSXED", "查询授信额度"),
    DOWNLOAD_OR_RETURN_QUOTA("XZTHSXED", "下载/退回授信额度"),
    ADJUST_QUOTA_VALIDITY("TZSXEDYXQ", "调整授信额度有效期"),
    QUERY_TAXPAYER_RISK("CXNSRFXXX", "查询纳税人风险信息"),
    QUERY_TAXPAYER_BASIC("CXNSRJBXX", "查询纳税人基本信息"),
    QUERY_TAX_RATE("CXKYSL", "查询可用税率信息"),
    QUERY_TAX_CATEGORY("CXSSFLBM", "查询税收分类编码信息"),
    BATCH_DOWNLOAD_APPLY("PLFPXZSQ", "批量发票下载申请"),
    QUERY_INVOICE_USAGE("FPZTXXCX", "发票用途状态信息查询"),
    QUERY_RED_CONFIRM_DETAIL("CXQDHZQRDMX", "查询建筑服务红字确认单明细信息"),
    UPLOAD_INVOICE("QDFPSC_JZFW", "建筑服务发票上传"),
    QUERY_RED_CONFIRM_LIST("CXQDHZQRDLB", "查询建筑服务红字确认单列表信息"),
    QUERY_UPLOAD_RESULT("CXQDFPSCJG", "查询建筑服务发票上传结果"),
    APPLY_RED_CONFIRM("QDHZQRDSQ", "建筑服务红字确认单申请"),
    CONFIRM_RED_CONFIRM("QDHZQRDQR", "建筑服务红字确认单确认"),
    QUERY_JZFW_INFO("CXJZFWXX", "查询建筑服务信息"),
    UPLOAD_SUMMARY_CONFIRM("SCFPHZQRXX", "上传发票汇总确认信息"),
    QUERY_SUMMARY_CONFIRM("CXFPHZQRXX", "查询发票汇总确认信息"),
    CROSS_REGION_SINGLE("KQYSSSJDBCX", "跨区域涉税数据单笔查询"),
    CROSS_REGION_BATCH("KQYSSSJPLCX", "跨区域涉税数据批量查询"),
    QUERY_DEDUCTION_VOUCHER("KCPZQDXGFPSJXXCX", "扣除凭证清单相关发票数据信息查询"),
    QUERY_DIFF_TAX_AUTH("YCEZSYWSQXXCX", "原差额征税业务授权信息查询");

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
    public static BuildInterfaceCode fromServiceCode(String serviceCode) {
        for (BuildInterfaceCode code : values()) {
            if (code.serviceCode.equals(serviceCode)) {
                return code;
            }
        }
        throw new InvoiceException("未知的建筑服务接口服务编码: " + serviceCode);
    }
}
