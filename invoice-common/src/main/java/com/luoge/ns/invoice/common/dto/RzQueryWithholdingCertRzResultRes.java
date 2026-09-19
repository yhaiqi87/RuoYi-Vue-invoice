package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 发票入账响应：查询增值税代扣代缴完税凭证入账处理结果（CXDKDJWSPZRZCLJG）。
 * 字段依据《乐企发票入账能力说明文档-V1.004》接口 4「查询增值税代扣代缴完税凭证入账处理结果」返回参数补全。
 */
@Getter
@Setter
public class RzQueryWithholdingCertRzResultRes extends RzResponse {

    /**
     * 批次流水号（String，32，是）
     */
    private String pclsh;

    /**
     * 扣缴义务人识别号（String，30，是）
     */
    private String kjywrsbh;

    /**
     * dkdjmx明细（List<RzQueryWithholdingCertRzResultResDkdjmx>）
     */
    private List<RzQueryWithholdingCertRzResultResDkdjmx> dkdjmx;
}
