package com.yhq.invoice.rpa.service;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.model.ChannelResponse;
import com.yhq.invoice.common.service.TsCapabilityService;
import org.springframework.stereotype.Service;

/**
 * rpa 渠道退税勾选实现：门面按 Channel=RPA 选中本服务。
 *
 * <p>按需求，rpa 渠道暂不对退税勾选能力做任何实现，全部方法统一抛出 UnsupportedOperationException 占位，
 * 待后续接入 RPA 流程时再逐接口实现。本类与 {@code RpaInvoiceService}（开票能力）、
 * {@code RpaGxService}（抵扣勾选能力）并列，不影响其它能力的 rpa 占位实现。
 */
@Service
public class RpaTsService implements TsCapabilityService {

    @Override
    public Channel channel() {
        return Channel.RPA;
    }

    /**
     * 批量上传退税发票（RPA）：渠道尚未实现退税勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<TsBatchUploadRefundInvoiceRes> batchUploadRefundInvoice(TsBatchUploadRefundInvoiceReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现退税勾选能力");
    }

    /**
     * 查询发票退税勾选处理结果（RPA）：渠道尚未实现退税勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<TsQueryInvoiceRefundResultRes> queryInvoiceRefundResult(TsQueryInvoiceRefundResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现退税勾选能力");
    }

    /**
     * 批量上传退税海关缴款书（RPA）：渠道尚未实现退税勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<TsBatchUploadRefundCustomsRes> batchUploadRefundCustoms(TsBatchUploadRefundCustomsReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现退税勾选能力");
    }

    /**
     * 查询海关缴款书退税勾选处理结果（RPA）：渠道尚未实现退税勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<TsQueryCustomsRefundResultRes> queryCustomsRefundResult(TsQueryCustomsRefundResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现退税勾选能力");
    }

    /**
     * 未勾选数据初始化清单下载申请（RPA）：渠道尚未实现退税勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<TsUnselectedDataInitDownloadApplyRes> unselectedDataInitDownloadApply(TsUnselectedDataInitDownloadApplyReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现退税勾选能力");
    }

    /**
     * 未勾选数据初始化清单下载申请反馈（RPA）：渠道尚未实现退税勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<TsUnselectedDataInitDownloadApplyFeedbackRes> unselectedDataInitDownloadApplyFeedback(TsUnselectedDataInitDownloadApplyFeedbackReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现退税勾选能力");
    }

}
