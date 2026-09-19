package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：申请确认抵扣统计（SQQRDKTJ）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 8「申请确认抵扣统计」返回参数补全。
 */
@Getter
@Setter
public class GxApplyConfirmDeductionStatRes extends GxResponse {

    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，必填）。
     */
    private String gfsbh;

    /**
     * 申请标志（String，1，必填）。
     */
    private String sqbz;

    /**
     * 税款所属期（String，6，否）。
     */
    private String skssq;
}
