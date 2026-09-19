package com.yhq.invoice.common.enums;

import com.yhq.invoice.common.exception.InvoiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 乐企增值税退税勾选能力（能力编码 203064）接口清单。
 *
 * <p>服务编码（serviceCode）取自《乐企增值税退税勾选能力说明文档-V2.013》的“接口清单”，对应请求头 {@code fwbm} 的取值；
 * interfaceName 为对应“接口名称”。共 6 个接口，覆盖退税发票/海关缴款书的批量上传与处理结果查询、
 * 以及未勾选数据初始化清单下载申请与反馈等场景。
 */
@Getter
@AllArgsConstructor
public enum TsInterfaceCode {
    BATCH_UPLOAD_REFUND_INVOICE("PLFPTSGXQR", "批量上传退税发票"),
    QUERY_INVOICE_REFUND_RESULT("CXFPTSGXQRCLJG", "查询发票退税勾选处理结果"),
    BATCH_UPLOAD_REFUND_CUSTOMS("PLHGJKSTSGXQR", "批量上传退税海关缴款书"),
    QUERY_CUSTOMS_REFUND_RESULT("CXHGJKSTSGXQRCLJG", "查询海关缴款书退税勾选处理结果"),
    UNSELECTED_DATA_INIT_DOWNLOAD_APPLY("WGXSJCSHQDXZSQTSGX", "未勾选数据初始化清单下载申请"),
    UNSELECTED_DATA_INIT_DOWNLOAD_APPLY_FEEDBACK("WGXSJCSHQDXZSQFKTSGX", "未勾选数据初始化清单下载申请反馈");

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
    public static TsInterfaceCode fromServiceCode(String serviceCode) {
        for (TsInterfaceCode code : values()) {
            if (code.serviceCode.equals(serviceCode)) {
                return code;
            }
        }
        throw new InvoiceException("未知的退税勾选接口服务编码: " + serviceCode);
    }
}
