package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：未勾选数据初始化清单下载申请反馈（WGXSJCSHQDXZSQFKDKGX）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 16「未勾选数据初始化清单下载申请反馈」返回参数补全。
 */
@Getter
@Setter
public class GxUnselectedDataInitDownloadApplyFeedbackRes extends GxResponse {

    /**
     * 批次流水号（String，32，必填）。
     */
    private String pclsh;

    /**
     * 包号（String，5，必填）。
     */
    private String packageno;
}
