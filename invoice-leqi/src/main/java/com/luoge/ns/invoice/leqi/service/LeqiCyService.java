package com.luoge.ns.invoice.leqi.service;

import com.luoge.ns.invoice.common.dto.CyVerifyReq;
import com.luoge.ns.invoice.common.dto.CyVerifyRes;
import com.luoge.ns.invoice.common.enums.Channel;
import com.luoge.ns.invoice.common.enums.CyInterfaceCode;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import com.luoge.ns.invoice.common.service.CyCapabilityService;
import com.luoge.ns.invoice.leqi.annotation.DocVersion;
import com.luoge.ns.invoice.leqi.annotation.ValidateReq;
import com.luoge.ns.invoice.leqi.client.LeqiHttpUtil;
import org.springframework.stereotype.Service;

/**
 * 乐企（LEQI）发票查验能力服务：实现查验统一契约 {@link CyCapabilityService} 的唯一接口。
 *
 * <p>与开票/归集/勾选能力完全隔离：本类<b>不</b>实现 {@code LeqiCapabilityService}，故不会进入
 * {@code LeqiInvoiceService} 的开票能力路由表。每个方法直接走通桩链路
 * {@code httpUtil.call(nlbm, fwbm, req, Res.class)}，后续校验/字段处理加在对应方法内，不影响其他能力。
 */
@Service
@ValidateReq
@DocVersion(doc = "乐企发票查验能力说明文档", value = "V1.025")
public class LeqiCyService implements CyCapabilityService {

    private final LeqiHttpUtil httpUtil;

    public LeqiCyService(LeqiHttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Override
    public Channel channel() {
        return Channel.LEQI;
    }

    /**
     * 发票查验（单张发票实时查询下载接口，FPCY_NEW）：调用乐企查验接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<CyVerifyRes> verifyInvoice(CyVerifyReq req) {
        return httpUtil.call(CyInterfaceCode.FPCY_NEW.getServiceCode(), req, CyVerifyRes.class);
    }
}
