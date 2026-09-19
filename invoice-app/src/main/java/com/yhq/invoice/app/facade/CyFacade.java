package com.yhq.invoice.app.facade;

import com.yhq.invoice.common.dto.CyVerifyReq;
import com.yhq.invoice.common.dto.CyVerifyRes;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.exception.InvoiceException;
import com.yhq.invoice.common.model.ChannelResponse;
import com.yhq.invoice.common.service.CyCapabilityService;
import com.yhq.invoice.leqi.service.LeqiCyService;
import com.yhq.invoice.rpa.service.RpaCyService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * app 发票查验门面层：按 Channel 枚举参数选择对应渠道实现（leqi / rpa）。
 *
 * <p>与 {@link InvoiceFacade}（开票能力）、{@link GjFacade}（归集能力）等并列、互不影响：仅做「选渠道」这一层判断，
 * 发票查验能力的唯一接口委托给 {@link CyCapabilityService} 实现。门面直接持有 RpaCyService 与 LeqiCyService
 * 两个渠道实现，按 channel() 建立路由表。
 */
@Service
public class CyFacade {

    private final Map<Channel, CyCapabilityService> services;

    /**
     * 注入两个渠道实现并构建 Channel -> 渠道服务的路由表。
     */
    public CyFacade(RpaCyService rpaCyService, LeqiCyService leqiCyService) {
        this.services = Map.of(
                rpaCyService.channel(), rpaCyService,
                leqiCyService.channel(), leqiCyService);
    }

    /**
     * 按 Channel 选出对应的渠道服务；无匹配实现时抛异常。
     */
    private CyCapabilityService select(Channel channel) {
        CyCapabilityService svc = services.get(channel);
        if (svc == null) {
            throw new InvoiceException("无对应渠道实现: " + channel);
        }
        return svc;
    }

    /**
     * 发票查验（单张发票实时查询下载接口）：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<CyVerifyRes> verifyInvoice(Channel channel, CyVerifyReq req) {
        return select(channel).verifyInvoice(req);
    }
}
