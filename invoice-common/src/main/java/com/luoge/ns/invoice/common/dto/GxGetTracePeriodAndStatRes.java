package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：获取追溯期税款所属期与追溯期税款所属期统计状态（HQZSSKSSQYZSSKSSQTJZT）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 27「获取追溯期税款所属期与追溯期税款所属期统计状态」返回参数补全。
 */
@Getter
@Setter
public class GxGetTracePeriodAndStatRes extends GxResponse {

    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，必填）。
     */
    private String gfsbh;
}
