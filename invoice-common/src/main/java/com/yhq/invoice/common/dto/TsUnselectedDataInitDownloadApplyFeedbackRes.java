package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 退税勾选响应：未勾选数据初始化清单下载申请反馈（WGXSJCSHQDXZSQFKTSGX）。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 6「未勾选数据初始化清单下载申请反馈」返回参数补全。
 *
 * <p>注：文档示例成功响应外层包有 {@code Response/Data} 信封（Data 内为业务字段），当前按 Gx 的统一约定，
 * 业务字段直接映射，信封解析与解密由 {@code LeqiHttpUtil} 在真实接入时统一处理（当前为占位桩链路）。
 * {@code sjl} 为 gzip+base64 数据流，原始数据为 json 报文（字段详见文档附件「未勾选数据初始化清单数据说明」）。
 */
@Getter
@Setter
public class TsUnselectedDataInitDownloadApplyFeedbackRes extends TsResponse {

    /**
     * 总包数（String，5，必填），当前流水号下的总包数。
     */
    private String packagecount;

    /**
     * 购方纳税人识别号/统一社会信用代码（String，30，必填）。
     */
    private String gfsbh;

    /**
     * 处理结果（String，1，必填）：1 成功 / 2 不存在符合条件的发票 / 3 失败。
     */
    private String cljg;

    /**
     * 数据流（String，否），压缩包文件流（gzip+base64），原始数据为 json 报文。
     */
    private String sjl;
}
