package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 发票查验响应：发票查验（单张发票实时查询下载接口，FPCY_NEW）。
 * 字段依据《乐企发票查验能力说明文档-V1.025》返回参数补全。
 *
 * <p>returncode / returnmsg / requestId 由 {@link InvoiceResponse} 提供，结果代码语义：
 * 00 成功、01 失败、02 查无数据、03 查验不一致、04 此发票非本单位开具或取得。
 */
@Getter
@Setter
public class CyVerifyRes extends CyResponse {
    /**
     * 查验结果信息（String，20000，否）：压缩包文件流（gzip+base64），原始数据为 json 报文。
     * Json 报文格式详见《乐企发票查验能力说明文档》附件「发票票面信息数据说明」。
     */
    private String cyjgxx;
}
