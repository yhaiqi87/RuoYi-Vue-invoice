package com.yhq.invoice.app.controller;

import com.yhq.invoice.app.facade.GjFacade;
import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.model.ChannelResponse;
import org.springframework.web.bind.annotation.*;

/**
 * 归集（乐企能力 203067）REST 端点，与 {@link InvoiceController}（开票能力）并列、互不影响。
 * 路径形如 POST /gj/{method}/{channel}，由 {@link GjFacade} 按渠道路由到 leqi / rpa 实现。
 */
@RestController
@RequestMapping("/gj")
public class GjController {

    private final GjFacade facade;

    public GjController(GjFacade facade) {
        this.facade = facade;
    }

    /**
     * 批量发票下载申请：POST /gj/batch-invoice-download-apply/{channel}。
     */
    @PostMapping("/batch-invoice-download-apply/{channel}")
    public ChannelResponse<GjBatchInvoiceDownloadApplyRes> batchInvoiceDownloadApply(@PathVariable Channel channel,
                                                                                     @RequestBody GjBatchInvoiceDownloadApplyReq req) {
        return facade.batchInvoiceDownloadApply(channel, req);
    }

    /**
     * 批量发票下载申请反馈：POST /gj/batch-invoice-download-apply-feedback/{channel}。
     */
    @PostMapping("/batch-invoice-download-apply-feedback/{channel}")
    public ChannelResponse<GjBatchInvoiceDownloadApplyFeedbackRes> batchInvoiceDownloadApplyFeedback(@PathVariable Channel channel,
                                                                                                     @RequestBody GjBatchInvoiceDownloadApplyFeedbackReq req) {
        return facade.batchInvoiceDownloadApplyFeedback(channel, req);
    }

    /**
     * 查询增量下载发票信息：POST /gj/query-incremental-invoice-download/{channel}。
     */
    @PostMapping("/query-incremental-invoice-download/{channel}")
    public ChannelResponse<GjQueryIncrementalInvoiceDownloadRes> queryIncrementalInvoiceDownload(@PathVariable Channel channel,
                                                                                                 @RequestBody GjQueryIncrementalInvoiceDownloadReq req) {
        return facade.queryIncrementalInvoiceDownload(channel, req);
    }

    /**
     * 发票风险信息查询：POST /gj/query-invoice-risk-info/{channel}。
     */
    @PostMapping("/query-invoice-risk-info/{channel}")
    public ChannelResponse<GjQueryInvoiceRiskInfoRes> queryInvoiceRiskInfo(@PathVariable Channel channel,
                                                                           @RequestBody GjQueryInvoiceRiskInfoReq req) {
        return facade.queryInvoiceRiskInfo(channel, req);
    }

    /**
     * 发票用途状态信息查询：POST /gj/query-invoice-usage-status/{channel}。
     */
    @PostMapping("/query-invoice-usage-status/{channel}")
    public ChannelResponse<GjQueryInvoiceUsageStatusRes> queryInvoiceUsageStatus(@PathVariable Channel channel,
                                                                                 @RequestBody GjQueryInvoiceUsageStatusReq req) {
        return facade.queryInvoiceUsageStatus(channel, req);
    }

    /**
     * 批量出口转内销发票信息查询：POST /gj/query-batch-export-domestic-invoice/{channel}。
     */
    @PostMapping("/query-batch-export-domestic-invoice/{channel}")
    public ChannelResponse<GjQueryBatchExportDomesticInvoiceRes> queryBatchExportDomesticInvoice(@PathVariable Channel channel,
                                                                                                 @RequestBody GjQueryBatchExportDomesticInvoiceReq req) {
        return facade.queryBatchExportDomesticInvoice(channel, req);
    }

    /**
     * 批量出口转内销海关缴款书信息查询：POST /gj/query-batch-export-domestic-customs/{channel}。
     */
    @PostMapping("/query-batch-export-domestic-customs/{channel}")
    public ChannelResponse<GjQueryBatchExportDomesticCustomsRes> queryBatchExportDomesticCustoms(@PathVariable Channel channel,
                                                                                                 @RequestBody GjQueryBatchExportDomesticCustomsReq req) {
        return facade.queryBatchExportDomesticCustoms(channel, req);
    }

    /**
     * 批量增值税代扣代缴完税凭证下载申请：POST /gj/batch-vat-withholding-cert-download-apply/{channel}。
     */
    @PostMapping("/batch-vat-withholding-cert-download-apply/{channel}")
    public ChannelResponse<GjBatchVatWithholdingCertDownloadApplyRes> batchVatWithholdingCertDownloadApply(@PathVariable Channel channel,
                                                                                                           @RequestBody GjBatchVatWithholdingCertDownloadApplyReq req) {
        return facade.batchVatWithholdingCertDownloadApply(channel, req);
    }

    /**
     * 批量增值税代扣代缴完税凭证下载申请反馈：POST /gj/batch-vat-withholding-cert-download-apply-feedback/{channel}。
     */
    @PostMapping("/batch-vat-withholding-cert-download-apply-feedback/{channel}")
    public ChannelResponse<GjBatchVatWithholdingCertDownloadApplyFeedbackRes> batchVatWithholdingCertDownloadApplyFeedback(@PathVariable Channel channel,
                                                                                                                           @RequestBody GjBatchVatWithholdingCertDownloadApplyFeedbackReq req) {
        return facade.batchVatWithholdingCertDownloadApplyFeedback(channel, req);
    }

    /**
     * 查询增量下载增值税代扣代缴完税凭证信息：POST /gj/query-incremental-vat-withholding-cert/{channel}。
     */
    @PostMapping("/query-incremental-vat-withholding-cert/{channel}")
    public ChannelResponse<GjQueryIncrementalVatWithholdingCertRes> queryIncrementalVatWithholdingCert(@PathVariable Channel channel,
                                                                                                       @RequestBody GjQueryIncrementalVatWithholdingCertReq req) {
        return facade.queryIncrementalVatWithholdingCert(channel, req);
    }

    /**
     * 批量消费税代扣代缴完税凭证下载申请：POST /gj/batch-consumption-withholding-cert-download-apply/{channel}。
     */
    @PostMapping("/batch-consumption-withholding-cert-download-apply/{channel}")
    public ChannelResponse<GjBatchConsumptionWithholdingCertDownloadApplyRes> batchConsumptionWithholdingCertDownloadApply(@PathVariable Channel channel,
                                                                                                                           @RequestBody GjBatchConsumptionWithholdingCertDownloadApplyReq req) {
        return facade.batchConsumptionWithholdingCertDownloadApply(channel, req);
    }

    /**
     * 批量消费税代扣代缴完税凭证下载申请反馈：POST /gj/batch-consumption-withholding-cert-download-apply-feedback/{channel}。
     */
    @PostMapping("/batch-consumption-withholding-cert-download-apply-feedback/{channel}")
    public ChannelResponse<GjBatchConsumptionWithholdingCertDownloadApplyFeedbackRes> batchConsumptionWithholdingCertDownloadApplyFeedback(@PathVariable Channel channel,
                                                                                                                                           @RequestBody GjBatchConsumptionWithholdingCertDownloadApplyFeedbackReq req) {
        return facade.batchConsumptionWithholdingCertDownloadApplyFeedback(channel, req);
    }

    /**
     * 查询增量下载消费税代扣代缴完税凭证信息：POST /gj/query-incremental-consumption-withholding-cert/{channel}。
     */
    @PostMapping("/query-incremental-consumption-withholding-cert/{channel}")
    public ChannelResponse<GjQueryIncrementalConsumptionWithholdingCertRes> queryIncrementalConsumptionWithholdingCert(@PathVariable Channel channel,
                                                                                                                       @RequestBody GjQueryIncrementalConsumptionWithholdingCertReq req) {
        return facade.queryIncrementalConsumptionWithholdingCert(channel, req);
    }

    /**
     * 批量海关缴款书下载申请：POST /gj/batch-customs-payment-book-download-apply/{channel}。
     */
    @PostMapping("/batch-customs-payment-book-download-apply/{channel}")
    public ChannelResponse<GjBatchCustomsPaymentBookDownloadApplyRes> batchCustomsPaymentBookDownloadApply(@PathVariable Channel channel,
                                                                                                           @RequestBody GjBatchCustomsPaymentBookDownloadApplyReq req) {
        return facade.batchCustomsPaymentBookDownloadApply(channel, req);
    }

    /**
     * 批量海关缴款书下载申请反馈：POST /gj/batch-customs-payment-book-download-apply-feedback/{channel}。
     */
    @PostMapping("/batch-customs-payment-book-download-apply-feedback/{channel}")
    public ChannelResponse<GjBatchCustomsPaymentBookDownloadApplyFeedbackRes> batchCustomsPaymentBookDownloadApplyFeedback(@PathVariable Channel channel,
                                                                                                                           @RequestBody GjBatchCustomsPaymentBookDownloadApplyFeedbackReq req) {
        return facade.batchCustomsPaymentBookDownloadApplyFeedback(channel, req);
    }

    /**
     * 查询增量下载海关缴款书信息：POST /gj/query-incremental-customs-payment-book/{channel}。
     */
    @PostMapping("/query-incremental-customs-payment-book/{channel}")
    public ChannelResponse<GjQueryIncrementalCustomsPaymentBookRes> queryIncrementalCustomsPaymentBook(@PathVariable Channel channel,
                                                                                                       @RequestBody GjQueryIncrementalCustomsPaymentBookReq req) {
        return facade.queryIncrementalCustomsPaymentBook(channel, req);
    }
}
