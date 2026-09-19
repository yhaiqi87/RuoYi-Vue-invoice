package com.yhq.invoice.common.enums;

import com.yhq.invoice.common.exception.InvoiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 乐企发票入账能力（能力编码 203057）接口清单。
 *
 * <p>服务编码（serviceCode）取自《乐企发票入账能力说明文档-V1.004》的“接口清单”，对应请求头 {@code fwbm} 的取值；
 * interfaceName 为对应“接口名称”。共 6 个接口，覆盖批量上传入账发票、查询发票入账处理结果，
 * 以及增值税代扣代缴完税凭证、海关缴款书的批量上传与处理结果查询。
 */
@Getter
@AllArgsConstructor
public enum RzInterfaceCode {
    BATCH_UPLOAD_INVOICE("PLSCRZFP", "批量上传入账发票"),
    QUERY_INVOICE_RZ_RESULT("CXFPRZCLJG", "查询发票入账处理结果"),
    BATCH_UPLOAD_WITHHOLDING_CERT("PLSCRZDKDJWSPZ", "批量上传增值税入账代扣代缴完税凭证"),
    QUERY_WITHHOLDING_CERT_RZ_RESULT("CXDKDJWSPZRZCLJG", "查询增值税代扣代缴完税凭证入账处理结果"),
    BATCH_UPLOAD_CUSTOMS_PAYMENT_BOOK("PLSCRZHGJKS", "批量上传入账海关缴款书"),
    QUERY_CUSTOMS_PAYMENT_BOOK_RZ_RESULT("CXHGJKSRZCLJG", "查询海关缴款书入账处理结果");

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
    public static RzInterfaceCode fromServiceCode(String serviceCode) {
        for (RzInterfaceCode code : values()) {
            if (code.serviceCode.equals(serviceCode)) {
                return code;
            }
        }
        throw new InvoiceException("未知的发票入账接口服务编码: " + serviceCode);
    }
}
