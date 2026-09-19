package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：汇总纳税人机构列表查询（HZNSRJGLBCX）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 22「汇总纳税人机构列表查询」返回参数补全。
 */
@Getter
@Setter
public class GxQuerySummaryTaxpayerOrgListRes extends GxResponse {

    /**
     * 购买方纳税人识别号/统一社会信用代码（String，30，必填）。
     */
    private String gfsbh;
}
