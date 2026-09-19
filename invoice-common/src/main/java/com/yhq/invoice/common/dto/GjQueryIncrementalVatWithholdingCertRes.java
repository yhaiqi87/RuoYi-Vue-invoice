package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 归集响应：查询增量下载增值税代扣代缴完税凭证信息（CXZLXZZZSDKDJWSPZXX）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 10「查询增量下载增值税代扣代缴完税凭证信息」返回参数补全。
 */
@Getter
@Setter
public class GjQueryIncrementalVatWithholdingCertRes extends GjResponse {
    /**
     * 总包数（String，5，是）
     */
    private String packagecount;
    /**
     * 扣缴义务人识别号（String，30，是）
     */
    private String kjywrsbh;
    /**
     * 归集日期（String，8，是，YYYYMMDD）
     */
    private String gjrq;
    /**
     * 数据流（String，否，压缩包文件流 gzip+base64）
     */
    private String sjl;
}
