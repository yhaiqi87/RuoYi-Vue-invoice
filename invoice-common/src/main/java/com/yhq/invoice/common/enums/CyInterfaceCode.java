package com.yhq.invoice.common.enums;

import com.yhq.invoice.common.exception.InvoiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 乐企发票查验能力（能力编码 203059）接口清单。
 *
 * <p>服务编码（serviceCode）取自《乐企发票查验能力说明文档-V1.025》的「发票查验（单张发票实时查询下载接口）」，
 * 对应请求头 {@code fwbm} 的取值；interfaceName 为对应“接口名称”。共 1 个接口。
 */
@Getter
@AllArgsConstructor
public enum CyInterfaceCode {
    FPCY_NEW("FPCY_NEW", "发票查验（单张发票实时查询下载接口）");

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
    public static CyInterfaceCode fromServiceCode(String serviceCode) {
        for (CyInterfaceCode code : values()) {
            if (code.serviceCode.equals(serviceCode)) {
                return code;
            }
        }
        throw new InvoiceException("未知的发票查验接口服务编码: " + serviceCode);
    }
}
