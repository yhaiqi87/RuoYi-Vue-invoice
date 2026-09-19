package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：申请注销勾选（SQZXGX）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 25「申请注销勾选」返回参数补全。
 */
@Getter
@Setter
public class GxApplyCancelDeductionRes extends GxResponse {

    /**
     * 购买方纳税人识别号/统一社会信用代码（String，30，必填）。
     */
    private String gfsbh;

    /**
     * 申请标志（String，1，必填）。
     */
    private String sqbz;
}
