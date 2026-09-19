package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：未勾选数据初始化清单下载申请（WGXSJCSHQDXZSQDKGX）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 15「未勾选数据初始化清单下载申请」返回参数补全。
 */
@Getter
@Setter
public class GxUnselectedDataInitDownloadApplyRes extends GxResponse {

    /**
     * 购方纳税人识别号/统一社会信用代码（String，30，必填）。
     */
    private String gfsbh;

    /**
     * 业务类型（String，2，必填）。
     */
    private String ywlx;

    /**
     * 凭证种类（String，2，必填）。
     */
    private String pzzl;

    /**
     * 起始开票月份（String，6，否）。
     */
    private String kpyf;

    /**
     * 结束开票月份（String，6，否）。
     */
    private String jskpyf;
}
