package com.luoge.ns.invoice.common.enums;

import com.luoge.ns.invoice.common.exception.InvoiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 开票能力维度：leqi/rpa 内部据此路由到 base / sale / lease 处理类。
 *
 * <p>乐企能力编码（leqiCode）取自乐企能力开放平台说明文档，对应请求头 {@code nlbm} 的取值；
 * 能力名称（leqiName）取自同一文档的“能力名称”字段。
 * 来源：
 * <ul>
 *   <li>乐企数字化电子发票（基础版）开票能力说明文档-V6.006 → 202007</li>
 *   <li>乐企数字化电子发票（不动产销售）开票能力说明文档-V3.004 → 202046</li>
 *   <li>乐企数字化电子发票（不动产经营租赁）开票能力说明文档-V3.021 → 202038</li>
 *   <li>乐企数字化电子发票（建筑服务）开票能力说明文档-V4.002 → 202044</li>
 *   <li>乐企数字化电子发票（货物运输）开票能力说明文档-V3.008 → 202026</li>
 *   <li>乐企数字化电子发票（金融商品转让）开票能力说明文档-V1.005 → 202086</li>
 *   <li>乐企数字化电子发票（二手车）开票能力说明文档-V1.003 → 202082</li>
 *   <li>乐企数字化电子发票（反向开票通用）开票能力说明文档-V1.002 → 202083</li>
 *   <li>乐企数字化电子发票（商品条码）开票能力说明文档 → 202099（占位编码，待真实接入时替换）</li>
 *   <li>乐企数字化电子发票（成品油）开票能力说明文档-V2.009 → 202055</li>
 * </ul>
 */
@Getter
@AllArgsConstructor
public enum CapabilityCode {
    BASE("202007", "乐企自用基础版开票能力"),
    SALE("202046", "乐企不动产销售开票能力"),
    LEASE("202038", "乐企不动产经营租赁开票能力"),
    BUILD("202044", "乐企建筑服务开票能力"),
    FREIGHT("202026", "乐企货物运输开票能力"),
    JRSP("202086", "乐企金融商品转让开票能力"),
    ESC("202082", "乐企二手车开票能力"),
    FX("202083", "乐企反向开票通用开票能力"),
    BARCODE("202080", "乐企商品条码开票能力"),
    CPY("202055", "乐企成品油开票能力");

    /** 乐企能力编码（请求头 nlbm 取值）。 */
    private final String leqiCode;

    /** 乐企能力名称。 */
    private final String leqiName;

    /** 按乐企能力编码反查能力维度。 */
    public static CapabilityCode fromLeqiCode(String leqiCode) {
        for (CapabilityCode code : values()) {
            if (code.leqiCode.equals(leqiCode)) {
                return code;
            }
        }
        throw new InvoiceException("未知的乐企能力编码: " + leqiCode);
    }
}
