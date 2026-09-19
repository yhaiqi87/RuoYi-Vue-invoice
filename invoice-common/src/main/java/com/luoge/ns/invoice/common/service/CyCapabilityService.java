package com.luoge.ns.invoice.common.service;

import com.luoge.ns.invoice.common.dto.CyVerifyReq;
import com.luoge.ns.invoice.common.dto.CyVerifyRes;
import com.luoge.ns.invoice.common.enums.Channel;
import com.luoge.ns.invoice.common.model.ChannelResponse;

/**
 * 发票查验（乐企能力 203059）统一能力接口（契约层，定义在 common）。
 *
 * <p>与 {@link InvoiceCapabilityService}（开票能力）、{@code GjCapabilityService}/{@code GxCapabilityService}/
 * {@code TsCapabilityService}（勾选/归集能力）并列、互不影响：发票查验是独立的业务域，不复用开票能力路由。
 * 共 1 个接口（FPCY_NEW 单张发票实时查询下载）。leqi 与 rpa 各自实现本接口，
 * app 门面 {@code CyFacade} 按 Channel 枚举选择实现。
 */
public interface CyCapabilityService {

    /**
     * 返回本实现所属渠道（LEQI / RPA）。
     */
    Channel channel();

    /**
     * 发票查验（单张发票实时查询下载接口，FPCY_NEW）：对单张发票发起查验申请，反馈查验结果信息。
     */
    ChannelResponse<CyVerifyRes> verifyInvoice(CyVerifyReq req);
}
