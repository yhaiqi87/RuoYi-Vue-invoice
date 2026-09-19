package com.luoge.ns.invoice.facade;

import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.Channel;
import com.luoge.ns.invoice.common.exception.InvoiceException;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import com.luoge.ns.invoice.common.service.GjCapabilityService;
import com.luoge.ns.invoice.leqi.service.LeqiGjService;
import com.luoge.ns.invoice.rpa.service.RpaGjService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * app 归集门面层：按 Channel 枚举参数选择对应渠道实现（leqi / rpa）。
 *
 * <p>与 {@link InvoiceFacade}（开票能力）并列、互不影响：仅做「选渠道」这一层判断，归集能力的 16 个接口
 * 全部在此委托给 {@link GjCapabilityService} 实现。门面直接持有 RpaGjService 与 LeqiGjService 两个渠道实现，
 * 按 channel() 建立路由表。
 */
@Service
public class GjFacade {

    private final Map<Channel, GjCapabilityService> services;

    /**
     * 注入两个渠道实现并构建 Channel -> 渠道服务的路由表。
     */
    public GjFacade(RpaGjService rpaGjService, LeqiGjService leqiGjService) {
        this.services = Map.of(
                rpaGjService.channel(), rpaGjService,
                leqiGjService.channel(), leqiGjService);
    }

    /**
     * 按 Channel 选出对应的渠道服务；无匹配实现时抛异常。
     */
    private GjCapabilityService select(Channel channel) {
        GjCapabilityService svc = services.get(channel);
        if (svc == null) {
            throw new InvoiceException("无对应渠道实现: " + channel);
        }
        return svc;
    }

    /**
     * 批量发票下载申请：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjBatchInvoiceDownloadApplyRes> batchInvoiceDownloadApply(Channel channel, GjBatchInvoiceDownloadApplyReq req) {
        return select(channel).batchInvoiceDownloadApply(req);
    }

    /**
     * 批量发票下载申请反馈：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjBatchInvoiceDownloadApplyFeedbackRes> batchInvoiceDownloadApplyFeedback(Channel channel, GjBatchInvoiceDownloadApplyFeedbackReq req) {
        return select(channel).batchInvoiceDownloadApplyFeedback(req);
    }

    /**
     * 查询增量下载发票信息：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjQueryIncrementalInvoiceDownloadRes> queryIncrementalInvoiceDownload(Channel channel, GjQueryIncrementalInvoiceDownloadReq req) {
        return select(channel).queryIncrementalInvoiceDownload(req);
    }

    /**
     * 发票风险信息查询：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjQueryInvoiceRiskInfoRes> queryInvoiceRiskInfo(Channel channel, GjQueryInvoiceRiskInfoReq req) {
        return select(channel).queryInvoiceRiskInfo(req);
    }

    /**
     * 发票用途状态信息查询：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjQueryInvoiceUsageStatusRes> queryInvoiceUsageStatus(Channel channel, GjQueryInvoiceUsageStatusReq req) {
        return select(channel).queryInvoiceUsageStatus(req);
    }

    /**
     * 批量出口转内销发票信息查询：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjQueryBatchExportDomesticInvoiceRes> queryBatchExportDomesticInvoice(Channel channel, GjQueryBatchExportDomesticInvoiceReq req) {
        return select(channel).queryBatchExportDomesticInvoice(req);
    }

    /**
     * 批量出口转内销海关缴款书信息查询：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjQueryBatchExportDomesticCustomsRes> queryBatchExportDomesticCustoms(Channel channel, GjQueryBatchExportDomesticCustomsReq req) {
        return select(channel).queryBatchExportDomesticCustoms(req);
    }

    /**
     * 批量增值税代扣代缴完税凭证下载申请：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjBatchVatWithholdingCertDownloadApplyRes> batchVatWithholdingCertDownloadApply(Channel channel, GjBatchVatWithholdingCertDownloadApplyReq req) {
        return select(channel).batchVatWithholdingCertDownloadApply(req);
    }

    /**
     * 批量增值税代扣代缴完税凭证下载申请反馈：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjBatchVatWithholdingCertDownloadApplyFeedbackRes> batchVatWithholdingCertDownloadApplyFeedback(Channel channel, GjBatchVatWithholdingCertDownloadApplyFeedbackReq req) {
        return select(channel).batchVatWithholdingCertDownloadApplyFeedback(req);
    }

    /**
     * 查询增量下载增值税代扣代缴完税凭证信息：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjQueryIncrementalVatWithholdingCertRes> queryIncrementalVatWithholdingCert(Channel channel, GjQueryIncrementalVatWithholdingCertReq req) {
        return select(channel).queryIncrementalVatWithholdingCert(req);
    }

    /**
     * 批量消费税代扣代缴完税凭证下载申请：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjBatchConsumptionWithholdingCertDownloadApplyRes> batchConsumptionWithholdingCertDownloadApply(Channel channel, GjBatchConsumptionWithholdingCertDownloadApplyReq req) {
        return select(channel).batchConsumptionWithholdingCertDownloadApply(req);
    }

    /**
     * 批量消费税代扣代缴完税凭证下载申请反馈：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjBatchConsumptionWithholdingCertDownloadApplyFeedbackRes> batchConsumptionWithholdingCertDownloadApplyFeedback(Channel channel, GjBatchConsumptionWithholdingCertDownloadApplyFeedbackReq req) {
        return select(channel).batchConsumptionWithholdingCertDownloadApplyFeedback(req);
    }

    /**
     * 查询增量下载消费税代扣代缴完税凭证信息：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjQueryIncrementalConsumptionWithholdingCertRes> queryIncrementalConsumptionWithholdingCert(Channel channel, GjQueryIncrementalConsumptionWithholdingCertReq req) {
        return select(channel).queryIncrementalConsumptionWithholdingCert(req);
    }

    /**
     * 批量海关缴款书下载申请：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjBatchCustomsPaymentBookDownloadApplyRes> batchCustomsPaymentBookDownloadApply(Channel channel, GjBatchCustomsPaymentBookDownloadApplyReq req) {
        return select(channel).batchCustomsPaymentBookDownloadApply(req);
    }

    /**
     * 批量海关缴款书下载申请反馈：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjBatchCustomsPaymentBookDownloadApplyFeedbackRes> batchCustomsPaymentBookDownloadApplyFeedback(Channel channel, GjBatchCustomsPaymentBookDownloadApplyFeedbackReq req) {
        return select(channel).batchCustomsPaymentBookDownloadApplyFeedback(req);
    }

    /**
     * 查询增量下载海关缴款书信息：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GjQueryIncrementalCustomsPaymentBookRes> queryIncrementalCustomsPaymentBook(Channel channel, GjQueryIncrementalCustomsPaymentBookReq req) {
        return select(channel).queryIncrementalCustomsPaymentBook(req);
    }
}
