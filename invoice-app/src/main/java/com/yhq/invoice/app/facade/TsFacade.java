package com.yhq.invoice.app.facade;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.exception.InvoiceException;
import com.yhq.invoice.common.model.ChannelResponse;
import com.yhq.invoice.common.service.TsCapabilityService;
import com.yhq.invoice.leqi.service.LeqiTsService;
import com.yhq.invoice.rpa.service.RpaTsService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * app 退税勾选门面层：按 Channel 枚举参数选择对应渠道实现（leqi / rpa）。
 *
 * <p>与 {@link InvoiceFacade}（开票能力）、{@code GxFacade}（抵扣勾选能力）、{@code GjFacade}（归集能力）并列、互不影响：
 * 仅做「选渠道」这一层判断，退税勾选能力的 6 个接口全部在此委托给 {@link TsCapabilityService} 实现。
 * 门面直接持有 RpaTsService 与 LeqiTsService 两个渠道实现，按 channel() 建立路由表。
 */
@Service
public class TsFacade {

    private final Map<Channel, TsCapabilityService> services;

    /**
     * 注入两个渠道实现并构建 Channel -> 渠道服务的路由表。
     */
    public TsFacade(RpaTsService rpaTsService, LeqiTsService leqiTsService) {
        this.services = Map.of(
                rpaTsService.channel(), rpaTsService,
                leqiTsService.channel(), leqiTsService);
    }

    /**
     * 按 Channel 选出对应的渠道服务；无匹配实现时抛异常。
     */
    private TsCapabilityService select(Channel channel) {
        TsCapabilityService svc = services.get(channel);
        if (svc == null) {
            throw new InvoiceException("无对应渠道实现: " + channel);
        }
        return svc;
    }

    /**
     * 批量上传退税发票：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<TsBatchUploadRefundInvoiceRes> batchUploadRefundInvoice(Channel channel, TsBatchUploadRefundInvoiceReq req) {
        return select(channel).batchUploadRefundInvoice(req);
    }

    /**
     * 查询发票退税勾选处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<TsQueryInvoiceRefundResultRes> queryInvoiceRefundResult(Channel channel, TsQueryInvoiceRefundResultReq req) {
        return select(channel).queryInvoiceRefundResult(req);
    }

    /**
     * 批量上传退税海关缴款书：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<TsBatchUploadRefundCustomsRes> batchUploadRefundCustoms(Channel channel, TsBatchUploadRefundCustomsReq req) {
        return select(channel).batchUploadRefundCustoms(req);
    }

    /**
     * 查询海关缴款书退税勾选处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<TsQueryCustomsRefundResultRes> queryCustomsRefundResult(Channel channel, TsQueryCustomsRefundResultReq req) {
        return select(channel).queryCustomsRefundResult(req);
    }

    /**
     * 未勾选数据初始化清单下载申请：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<TsUnselectedDataInitDownloadApplyRes> unselectedDataInitDownloadApply(Channel channel, TsUnselectedDataInitDownloadApplyReq req) {
        return select(channel).unselectedDataInitDownloadApply(req);
    }

    /**
     * 未勾选数据初始化清单下载申请反馈：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<TsUnselectedDataInitDownloadApplyFeedbackRes> unselectedDataInitDownloadApplyFeedback(Channel channel, TsUnselectedDataInitDownloadApplyFeedbackReq req) {
        return select(channel).unselectedDataInitDownloadApplyFeedback(req);
    }

}
