package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：申请税款所属期变更（SQSKSSQBG）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 24「申请税款所属期变更」返回参数补全。
 */
@Getter
@Setter
public class GxApplyPeriodChangeRes extends GxResponse {

    /**
     * 购方纳税人识别号/统一社会信用代码（String，30，必填）。
     */
    private String gfsbh;

    /**
     * 变更属期（String，6，必填）。
     */
    private String bgsq;
}
