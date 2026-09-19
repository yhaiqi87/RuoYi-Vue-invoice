package com.yhq.invoice.rpa.service;

import com.yhq.invoice.common.dto.CyVerifyReq;
import com.yhq.invoice.common.dto.CyVerifyRes;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.model.ChannelResponse;
import com.yhq.invoice.common.service.CyCapabilityService;
import org.springframework.stereotype.Service;

/**
 * rpa 渠道发票查验实现：门面按 Channel=RPA 选中本服务。
 *
 * <p>按需求，rpa 渠道暂不对发票查验能力做任何实现，统一抛出 UnsupportedOperationException 占位，
 * 待后续接入 RPA 流程时再实现。本类与 {@code RpaInvoiceService}（开票能力）、{@code RpaGjService} 等并列，
 * 不影响其它能力的 rpa 占位实现。
 */
@Service
public class RpaCyService implements CyCapabilityService {

    @Override
    public Channel channel() {
        return Channel.RPA;
    }

    /**
     * 发票查验（RPA）：渠道尚未实现发票查验能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<CyVerifyRes> verifyInvoice(CyVerifyReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现发票查验能力");
    }
}
