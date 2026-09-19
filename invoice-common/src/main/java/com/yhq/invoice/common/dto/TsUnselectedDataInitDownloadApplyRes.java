package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 退税勾选响应：未勾选数据初始化清单下载申请（WGXSJCSHQDXZSQTSGX）。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 5「未勾选数据初始化清单下载申请」返回参数补全。
 *
 * <p>注：文档示例成功响应外层包有 {@code Response/Data} 信封（Data 内为业务字段），当前按 Gx 的统一约定，
 * 业务字段直接映射，信封解析与解密由 {@code LeqiHttpUtil} 在真实接入时统一处理（当前为占位桩链路）。
 */
@Getter
@Setter
public class TsUnselectedDataInitDownloadApplyRes extends TsResponse {

    /**
     * 批次流水号（String，32，否，返回码非 00 时不返回）。
     */
    private String pclsh;
}
