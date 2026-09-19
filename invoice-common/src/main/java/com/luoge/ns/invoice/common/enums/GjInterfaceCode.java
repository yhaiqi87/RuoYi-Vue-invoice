package com.luoge.ns.invoice.common.enums;

import com.luoge.ns.invoice.common.exception.InvoiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 乐企归集能力（能力编码 203067）接口清单。
 *
 * <p>服务编码（serviceCode）取自《乐企归集能力说明文档-V2.021》的“接口清单”，对应请求头 {@code fwbm} 的取值；
 * interfaceName 为对应“接口名称”。共 16 个接口，覆盖发票批量下载、增量下载、风险/用途查询，
 * 以及出口转内销、增值税/消费税代扣代缴完税凭证、海关缴款书的下载申请与反馈。
 */
@Getter
@AllArgsConstructor
public enum GjInterfaceCode {
    BATCH_INVOICE_DOWNLOAD_APPLY("PLFPXZSQ", "批量发票下载申请"),
    BATCH_INVOICE_DOWNLOAD_APPLY_FEEDBACK("PLFPXZSQFK", "批量发票下载申请反馈"),
    QUERY_INCREMENTAL_INVOICE_DOWNLOAD("CXZLXZFPXX", "查询增量下载发票信息"),
    QUERY_INVOICE_RISK_INFO("FPTSTXXXCX", "发票风险信息查询"),
    QUERY_INVOICE_USAGE_STATUS("FPZTXXCX", "发票用途状态信息查询"),
    QUERY_BATCH_EXPORT_DOMESTIC_INVOICE("PLCKZNXFPXXCX", "批量出口转内销发票信息查询"),
    QUERY_BATCH_EXPORT_DOMESTIC_CUSTOMS("PLCKZNXHGJKSXXCX", "批量出口转内销海关缴款书信息查询"),
    BATCH_VAT_WITHHOLDING_CERT_DOWNLOAD_APPLY("PLZZSDKDJWSPZXZSQ", "批量增值税代扣代缴完税凭证下载申请"),
    BATCH_VAT_WITHHOLDING_CERT_DOWNLOAD_APPLY_FEEDBACK("PLZZSDKDJWSPZXZSQFK", "批量增值税代扣代缴完税凭证下载申请反馈"),
    QUERY_INCREMENTAL_VAT_WITHHOLDING_CERT("CXZLXZZZSDKDJWSPZXX", "查询增量下载增值税代扣代缴完税凭证信息"),
    BATCH_CONSUMPTION_WITHHOLDING_CERT_DOWNLOAD_APPLY("PLXFSDKDJWSPZXZSQ", "批量消费税代扣代缴完税凭证下载申请"),
    BATCH_CONSUMPTION_WITHHOLDING_CERT_DOWNLOAD_APPLY_FEEDBACK("PLXFSDKDJWSPZXZSQFK", "批量消费税代扣代缴完税凭证下载申请反馈"),
    QUERY_INCREMENTAL_CONSUMPTION_WITHHOLDING_CERT("CXZLXZXFSDKDJWSPZXX", "查询增量下载消费税代扣代缴完税凭证信息"),
    BATCH_CUSTOMS_PAYMENT_BOOK_DOWNLOAD_APPLY("PLHGJKSXZSQ", "批量海关缴款书下载申请"),
    BATCH_CUSTOMS_PAYMENT_BOOK_DOWNLOAD_APPLY_FEEDBACK("PLHGJKSXZSQFK", "批量海关缴款书下载申请反馈"),
    QUERY_INCREMENTAL_CUSTOMS_PAYMENT_BOOK("CXZLXZHGJKSXX", "查询增量下载海关缴款书信息");

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
    public static GjInterfaceCode fromServiceCode(String serviceCode) {
        for (GjInterfaceCode code : values()) {
            if (code.serviceCode.equals(serviceCode)) {
                return code;
            }
        }
        throw new InvoiceException("未知的归集接口服务编码: " + serviceCode);
    }
}
