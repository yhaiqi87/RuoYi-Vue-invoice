package com.luoge.ns.invoice.common.enums;

import com.luoge.ns.invoice.common.exception.InvoiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 乐企增值税抵扣勾选能力（能力编码 203065）接口清单。
 *
 * <p>服务编码（serviceCode）取自《乐企增值税抵扣勾选能力说明文档-V3.025》的“接口清单”，对应请求头 {@code fwbm} 的取值；
 * interfaceName 为对应“接口名称”。共 27 个接口，覆盖税款所属期与统计状态查询、发票/海关缴款书/代扣代缴完税凭证的
 * 抵扣勾选上传与结果查询、统计申请与确认、未勾选数据初始化、农产品发票确认与补录、机构汇总勾选、属期变更/注销/刷新/追溯等场景。
 */
@Getter
@AllArgsConstructor
public enum GxInterfaceCode {
    GET_CUR_PERIOD_AND_STAT("HQDQSKSSQYDQSKSSQTJZT", "获取当前税款所属期与当期税款所属期统计状态"),
    BATCH_UPLOAD_DEDUCTION_INVOICE("PLFPDKGX", "批量上传抵扣发票"),
    QUERY_INVOICE_DEDUCTION_RESULT("CXFPDKGXCLJG", "查询发票抵扣勾选处理结果"),
    BATCH_UPLOAD_CUSTOMS_DEDUCTION("PLHGJKSDKGX", "批量上传海关缴款书抵扣勾选"),
    QUERY_CUSTOMS_DEDUCTION_RESULT("CXHGJKSDKGXCLJG", "查询海关缴款书抵扣勾选处理结果"),
    APPLY_DEDUCTION_STAT("SQCXDKTJ", "申请抵扣统计"),
    QUERY_STAT_PROCESS_RESULT("CXTJCLJG", "查询申请统计处理结果"),
    APPLY_CONFIRM_DEDUCTION_STAT("SQQRDKTJ", "申请确认抵扣统计"),
    QUERY_CONFIRM_STAT_RESULT("CXQRTJCLJG", "查询确认统计处理结果"),
    GET_PERIOD_INVOICE_DEDUCTION_RESULT("HQDQSKSSQFPDKGXCLJG", "获取税款所属期发票抵扣勾选处理结果"),
    GET_PERIOD_CUSTOMS_DEDUCTION_RESULT("HQDQSKSSQHGJKSDKGXCLJG", "获取税款所属期海关缴款书抵扣勾选处理结果"),
    BATCH_UPLOAD_VAT_WITHHOLD_DEDUCTION("PLSCZZSDKDJWSPZDKGX", "批量上传增值税代扣代缴完税凭证抵扣勾选"),
    QUERY_VAT_WITHHOLD_DEDUCTION_RESULT("CXZZSDKDJWSPZDKGXCLJG", "查询增值税代扣代缴完税凭证抵扣勾选处理结果"),
    GET_PERIOD_WITHHOLD_DEDUCTION_RESULT("HQDQSKSSQDKDJWSPZDKGXCLJG", "获取税款所属期代扣代缴完税凭证抵扣勾选处理结果"),
    UNSELECTED_DATA_INIT_DOWNLOAD_APPLY("WGXSJCSHQDXZSQDKGX", "未勾选数据初始化清单下载申请"),
    UNSELECTED_DATA_INIT_DOWNLOAD_APPLY_FEEDBACK("WGXSJCSHQDXZSQFKDKGX", "未勾选数据初始化清单下载申请反馈"),
    BATCH_UPLOAD_AGRI_INVOICE_CONFIRM_LIST("PLSCDCLNCPFPQRQD", "批量上传待处理农产品发票确认清单"),
    QUERY_AGRI_INVOICE_CONFIRM_LIST_RESULT("CXPLSCDCLNCPFPQRQDCLJG", "查询批量上传待处理农产品发票确认清单处理结果"),
    BATCH_UPLOAD_TAX_AUTH_AGRI_INVOICE_SUPPLEMENT("PLSCSWJGDKNCPFPBLXX", "批量上传税务机关代开农产品发票补录信息"),
    QUERY_TAX_AUTH_AGRI_INVOICE_SUPPLEMENT_RESULT("CXPLSCSWJGDKNCPFPBLXXCLJG", "查询批量上传税务机关代开农产品发票补录信息处理结果"),
    QUERY_INVOICE_AGRI_GOODS_CODE_LIST("CXFPNCPSPBMLB", "查询发票农产品商品编码列表"),
    QUERY_SUMMARY_TAXPAYER_ORG_LIST("HZNSRJGLBCX", "汇总纳税人机构列表查询"),
    BRANCH_SUMMARY_DEDUCTION_CONFIRM("ZFJGHZGXQR", "总分机构汇总勾选确认"),
    APPLY_PERIOD_CHANGE("SQSKSSQBG", "申请税款所属期变更"),
    APPLY_CANCEL_DEDUCTION("SQZXGX", "申请注销勾选"),
    REFRESH_TAX_PERIOD("SXSKSSQ", "刷新税款所属期"),
    GET_TRACE_PERIOD_AND_STAT("HQZSSKSSQYZSSKSSQTJZT", "获取追溯期税款所属期与追溯期税款所属期统计状态");

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
    public static GxInterfaceCode fromServiceCode(String serviceCode) {
        for (GxInterfaceCode code : values()) {
            if (code.serviceCode.equals(serviceCode)) {
                return code;
            }
        }
        throw new InvoiceException("未知的抵扣勾选接口服务编码: " + serviceCode);
    }
}
