package com.luoge.ns.invoice.common.enums;

import com.luoge.ns.invoice.common.exception.InvoiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 金融商品转让（乐企能力编码 202086）接口清单。
 *
 * <p>服务编码（serviceCode）取自《乐企数字化电子发票（金融商品转让）开票能力说明文档V1.005》的“接口清单”，
 * 对应请求头 {@code fwbm} 的取值；interfaceName 为对应“接口名称”。
 * 金融商品转让支持其中 17 个概念接口，不含扣除凭证清单、涉税专业服务、差额征税授权、房源信息、
 * 建筑服务信息、跨区域涉税、差额征税编码、批量发票下载申请等其它能力专属接口。
 */
@Getter
@AllArgsConstructor
public enum JrspInterfaceCode {
    GET_BATCH_PRE_CODE("QDFPPLFM", "获取数电票批量预赋码信息"),
    QUERY_QUOTA("CXSXED", "查询发票额度"),
    DOWNLOAD_OR_RETURN_QUOTA("XZTHSXED", "下载/退回发票额度"),
    ADJUST_QUOTA_VALIDITY("TZSXEDYXQ", "调整发票额度有效期"),
    QUERY_TAXPAYER_RISK("CXNSRFXXX", "查询纳税人风险信息"),
    QUERY_TAXPAYER_BASIC("CXNSRJBXX", "查询纳税人基本信息"),
    QUERY_TAX_RATE("CXKYSL", "查询可用税率信息"),
    QUERY_TAX_CATEGORY("CXSSFLBM", "查询税收分类编码信息"),
    QUERY_RED_CONFIRM_LIST("CXQDHZQRDLB", "查询数电红字确认单列表信息"),
    QUERY_RED_CONFIRM_DETAIL("CXQDHZQRDMX", "查询数电红字确认单明细信息"),
    UPLOAD_INVOICE("JRSPZRFPSC", "金融商品转让发票上传"),
    QUERY_UPLOAD_RESULT("CXQDFPSCJG", "查询金融商品转让发票上传结果"),
    APPLY_RED_CONFIRM("QDHZQRDSQ", "数电红字确认单申请"),
    CONFIRM_RED_CONFIRM("QDHZQRDQR", "数电红字确认单确认"),
    UPLOAD_SUMMARY_CONFIRM("SCFPHZQRXX", "上传发票汇总确认信息"),
    QUERY_SUMMARY_CONFIRM("CXFPHZQRXX", "查询发票汇总确认信息"),
    QUERY_INVOICE_USAGE("FPZTXXCX", "发票用途状态信息查询");

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
    public static JrspInterfaceCode fromServiceCode(String serviceCode) {
        for (JrspInterfaceCode code : values()) {
            if (code.serviceCode.equals(serviceCode)) {
                return code;
            }
        }
        throw new InvoiceException("未知的金融商品转让接口服务编码: " + serviceCode);
    }
}
