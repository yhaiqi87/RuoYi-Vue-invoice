package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 归集响应：批量海关缴款书下载申请反馈（PLHGJKSXZSQFK）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 15「批量海关缴款书下载申请反馈」返回参数补全。
 */
@Getter
@Setter
public class GjBatchCustomsPaymentBookDownloadApplyFeedbackRes extends GjResponse {
    /**
     * 总包数（String，5，是）
     */
    private String packagecount;
    /**
     * 统一社会信用代码/纳税人识别号（String，30，是）
     */
    private String nsrsbh;
    /**
     * 处理结果（String，1，是，1：成功 2：不存在符合条件的数据 3：失败）
     */
    private String cljg;
    /**
     * 数据流（String，否，压缩包文件流 gzip+base64）
     */
    private String sjl;
}
