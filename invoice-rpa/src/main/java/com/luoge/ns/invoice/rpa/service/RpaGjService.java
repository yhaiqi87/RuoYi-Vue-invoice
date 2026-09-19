package com.luoge.ns.invoice.rpa.service;

import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.Channel;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import com.luoge.ns.invoice.common.service.GjCapabilityService;
import org.springframework.stereotype.Service;

/**
 * rpa 渠道归集实现：门面按 Channel=RPA 选中本服务。
 *
 * <p>按需求，rpa 渠道暂不对归集能力做任何实现，全部方法统一抛出 UnsupportedOperationException 占位，
 * 待后续接入 RPA 流程时再逐接口实现。本类与 {@code RpaInvoiceService}（开票能力）并列，
 * 不影响开票能力的 rpa 占位实现。
 */
@Service
public class RpaGjService implements GjCapabilityService {

    @Override
    public Channel channel() {
        return Channel.RPA;
    }

    /**
     * 批量发票下载申请（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjBatchInvoiceDownloadApplyRes> batchInvoiceDownloadApply(GjBatchInvoiceDownloadApplyReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 批量发票下载申请反馈（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjBatchInvoiceDownloadApplyFeedbackRes> batchInvoiceDownloadApplyFeedback(GjBatchInvoiceDownloadApplyFeedbackReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 查询增量下载发票信息（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjQueryIncrementalInvoiceDownloadRes> queryIncrementalInvoiceDownload(GjQueryIncrementalInvoiceDownloadReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 发票风险信息查询（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjQueryInvoiceRiskInfoRes> queryInvoiceRiskInfo(GjQueryInvoiceRiskInfoReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 发票用途状态信息查询（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjQueryInvoiceUsageStatusRes> queryInvoiceUsageStatus(GjQueryInvoiceUsageStatusReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 批量出口转内销发票信息查询（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjQueryBatchExportDomesticInvoiceRes> queryBatchExportDomesticInvoice(GjQueryBatchExportDomesticInvoiceReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 批量出口转内销海关缴款书信息查询（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjQueryBatchExportDomesticCustomsRes> queryBatchExportDomesticCustoms(GjQueryBatchExportDomesticCustomsReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 批量增值税代扣代缴完税凭证下载申请（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjBatchVatWithholdingCertDownloadApplyRes> batchVatWithholdingCertDownloadApply(GjBatchVatWithholdingCertDownloadApplyReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 批量增值税代扣代缴完税凭证下载申请反馈（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjBatchVatWithholdingCertDownloadApplyFeedbackRes> batchVatWithholdingCertDownloadApplyFeedback(GjBatchVatWithholdingCertDownloadApplyFeedbackReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 查询增量下载增值税代扣代缴完税凭证信息（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjQueryIncrementalVatWithholdingCertRes> queryIncrementalVatWithholdingCert(GjQueryIncrementalVatWithholdingCertReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 批量消费税代扣代缴完税凭证下载申请（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjBatchConsumptionWithholdingCertDownloadApplyRes> batchConsumptionWithholdingCertDownloadApply(GjBatchConsumptionWithholdingCertDownloadApplyReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 批量消费税代扣代缴完税凭证下载申请反馈（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjBatchConsumptionWithholdingCertDownloadApplyFeedbackRes> batchConsumptionWithholdingCertDownloadApplyFeedback(GjBatchConsumptionWithholdingCertDownloadApplyFeedbackReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 查询增量下载消费税代扣代缴完税凭证信息（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjQueryIncrementalConsumptionWithholdingCertRes> queryIncrementalConsumptionWithholdingCert(GjQueryIncrementalConsumptionWithholdingCertReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 批量海关缴款书下载申请（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjBatchCustomsPaymentBookDownloadApplyRes> batchCustomsPaymentBookDownloadApply(GjBatchCustomsPaymentBookDownloadApplyReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 批量海关缴款书下载申请反馈（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjBatchCustomsPaymentBookDownloadApplyFeedbackRes> batchCustomsPaymentBookDownloadApplyFeedback(GjBatchCustomsPaymentBookDownloadApplyFeedbackReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }

    /**
     * 查询增量下载海关缴款书信息（RPA）：渠道尚未实现归集能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GjQueryIncrementalCustomsPaymentBookRes> queryIncrementalCustomsPaymentBook(GjQueryIncrementalCustomsPaymentBookReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现归集能力");
    }
}
