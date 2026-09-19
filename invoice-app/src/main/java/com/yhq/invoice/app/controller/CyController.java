package com.yhq.invoice.app.controller;

import com.yhq.invoice.app.facade.CyFacade;
import com.yhq.invoice.common.dto.CyVerifyReq;
import com.yhq.invoice.common.dto.CyVerifyRes;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.model.ChannelResponse;
import org.springframework.web.bind.annotation.*;

/**
 * 发票查验（乐企能力 203059）REST 端点，与 {@link InvoiceController}（开票能力）、{@link GjController}（归集能力）等并列、互不影响。
 * 路径形如 POST /cy/{method}/{channel}，由 {@link CyFacade} 按渠道路由到 leqi / rpa 实现。
 */
@RestController
@RequestMapping("/cy")
public class CyController {

    private final CyFacade facade;

    public CyController(CyFacade facade) {
        this.facade = facade;
    }

    /**
     * 发票查验（单张发票实时查询下载接口）：POST /cy/verify-invoice/{channel}。
     */
    @PostMapping("/verify-invoice/{channel}")
    public ChannelResponse<CyVerifyRes> verifyInvoice(@PathVariable Channel channel,
                                                      @RequestBody CyVerifyReq req) {
        return facade.verifyInvoice(channel, req);
    }
}
