package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 归集响应：批量消费税代扣代缴完税凭证下载申请反馈（PLXFSDKDJWSPZXZSQFK）。
 * 字段依据《乐企归集能力说明文档-V2.021》接口 12「批量消费税代扣代缴完税凭证下载申请反馈」返回参数补全。
 */
@Getter
@Setter
public class GjBatchConsumptionWithholdingCertDownloadApplyFeedbackRes extends GjResponse {
    /**
     * 总包数（String，5，是）
     */
    private String packagecount;
    /**
     * 被扣缴义务人统一社会信用代码/纳税人识别号（String，30，是）
     */
    private String bkjnsrsbh;
    /**
     * 处理结果（String，1，是，1：成功 2：不存在符合条件的数据 3：失败）
     */
    private String cljg;
    /**
     * 数据流（String，否，压缩包文件流 gzip+base64）
     */
    private String sjl;
}
