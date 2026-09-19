package com.yhq.invoice.app.facade;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.exception.InvoiceException;
import com.yhq.invoice.common.model.ChannelResponse;
import com.yhq.invoice.common.service.RzCapabilityService;
import com.yhq.invoice.leqi.service.LeqiRzService;
import com.yhq.invoice.rpa.service.RpaRzService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * app 发票入账门面层：按 Channel 枚举参数选择对应渠道实现（leqi / rpa）。
 *
 * <p>与 {@link GjFacade}（归集能力）并列、互不影响：仅做「选渠道」这一层判断，发票入账能力的 6 个接口
 * 全部在此委托给 {@link RzCapabilityService} 实现。门面直接持有 RpaRzService 与 LeqiRzService 两个渠道实现，
 * 按 channel() 建立路由表。发票入账能力（乐企能力 203057）不复用开票能力的 {@code CapabilityCode} 二次路由。
 */
@Service
public class RzFacade {

    private final Map<Channel, RzCapabilityService> services;

    /**
     * 注入两个渠道实现并构建 Channel -> 渠道服务的路由表。
     */
    public RzFacade(RpaRzService rpaRzService, LeqiRzService leqiRzService) {
        this.services = Map.of(
                rpaRzService.channel(), rpaRzService,
                leqiRzService.channel(), leqiRzService);
    }

    /**
     * 按 Channel 选出对应的渠道服务；无匹配实现时抛异常。
     */
    private RzCapabilityService select(Channel channel) {
        RzCapabilityService svc = services.get(channel);
        if (svc == null) {
            throw new InvoiceException("无对应渠道实现: " + channel);
        }
        return svc;
    }

    /**
     * 批量上传入账发票：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<RzBatchUploadInvoiceRes> batchUploadInvoice(Channel channel, RzBatchUploadInvoiceReq req) {
        return select(channel).batchUploadInvoice(req);
    }

    /**
     * 查询发票入账处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<RzQueryInvoiceRzResultRes> queryInvoiceRzResult(Channel channel, RzQueryInvoiceRzResultReq req) {
        return select(channel).queryInvoiceRzResult(req);
    }

    /**
     * 批量上传增值税入账代扣代缴完税凭证：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<RzBatchUploadWithholdingCertRes> batchUploadWithholdingCert(Channel channel, RzBatchUploadWithholdingCertReq req) {
        return select(channel).batchUploadWithholdingCert(req);
    }

    /**
     * 查询增值税代扣代缴完税凭证入账处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<RzQueryWithholdingCertRzResultRes> queryWithholdingCertRzResult(Channel channel, RzQueryWithholdingCertRzResultReq req) {
        return select(channel).queryWithholdingCertRzResult(req);
    }

    /**
     * 批量上传入账海关缴款书：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<RzBatchUploadCustomsPaymentBookRes> batchUploadCustomsPaymentBook(Channel channel, RzBatchUploadCustomsPaymentBookReq req) {
        return select(channel).batchUploadCustomsPaymentBook(req);
    }

    /**
     * 查询海关缴款书入账处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<RzQueryCustomsPaymentBookRzResultRes> queryCustomsPaymentBookRzResult(Channel channel, RzQueryCustomsPaymentBookRzResultReq req) {
        return select(channel).queryCustomsPaymentBookRzResult(req);
    }
}
