package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：刷新税款所属期（SXSKSSQ）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 26「刷新税款所属期」返回参数补全。
 */
@Getter
@Setter
public class GxRefreshTaxPeriodRes extends GxResponse {

    /**
     * 购方识别号（String，30，必填）。
     */
    private String gfsbh;

    /**
     * 追溯期标志（String，1，否）。
     */
    private String zsqbz;
}
