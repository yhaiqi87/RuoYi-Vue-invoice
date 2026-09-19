package com.yhq.invoice.common.service;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.model.ChannelResponse;

/**
 * 归集（乐企能力 203067）统一能力接口（契约层，定义在 common）。
 *
 * <p>与 {@link InvoiceCapabilityService}（开票能力）并列、互不影响：归集是独立的业务域，
 * 不复用开票能力路由，也不在 {@code LeqiCapabilityService} / {@code LeqiInvoiceBaseService} 等开票体系中实现。
 * 共 16 个接口，覆盖发票批量/增量下载、风险与用途查询，以及出口转内销、增值税/消费税代扣代缴完税凭证、
 * 海关缴款书的下载申请与反馈。leqi 与 rpa 各自实现本接口，app 门面 {@code GjFacade} 按 Channel 枚举选择实现。
 */
public interface GjCapabilityService {

    /**
     * 返回本实现所属渠道（LEQI / RPA）。
     */
    Channel channel();

    /**
     * 1. 批量发票下载申请（PLFPXZSQ）。
     */
    ChannelResponse<GjBatchInvoiceDownloadApplyRes> batchInvoiceDownloadApply(GjBatchInvoiceDownloadApplyReq req);

    /**
     * 2. 批量发票下载申请反馈（PLFPXZSQFK）。
     */
    ChannelResponse<GjBatchInvoiceDownloadApplyFeedbackRes> batchInvoiceDownloadApplyFeedback(GjBatchInvoiceDownloadApplyFeedbackReq req);

    /**
     * 3. 查询增量下载发票信息（CXZLXZFPXX）。
     */
    ChannelResponse<GjQueryIncrementalInvoiceDownloadRes> queryIncrementalInvoiceDownload(GjQueryIncrementalInvoiceDownloadReq req);

    /**
     * 4. 发票风险信息查询（FPTSTXXXCX）。
     */
    ChannelResponse<GjQueryInvoiceRiskInfoRes> queryInvoiceRiskInfo(GjQueryInvoiceRiskInfoReq req);

    /**
     * 5. 发票用途状态信息查询（FPZTXXCX）。
     */
    ChannelResponse<GjQueryInvoiceUsageStatusRes> queryInvoiceUsageStatus(GjQueryInvoiceUsageStatusReq req);

    /**
     * 6. 批量出口转内销发票信息查询（PLCKZNXFPXXCX）。
     */
    ChannelResponse<GjQueryBatchExportDomesticInvoiceRes> queryBatchExportDomesticInvoice(GjQueryBatchExportDomesticInvoiceReq req);

    /**
     * 7. 批量出口转内销海关缴款书信息查询（PLCKZNXHGJKSXXCX）。
     */
    ChannelResponse<GjQueryBatchExportDomesticCustomsRes> queryBatchExportDomesticCustoms(GjQueryBatchExportDomesticCustomsReq req);

    /**
     * 8. 批量增值税代扣代缴完税凭证下载申请（PLZZSDKDJWSPZXZSQ）。
     */
    ChannelResponse<GjBatchVatWithholdingCertDownloadApplyRes> batchVatWithholdingCertDownloadApply(GjBatchVatWithholdingCertDownloadApplyReq req);

    /**
     * 9. 批量增值税代扣代缴完税凭证下载申请反馈（PLZZSDKDJWSPZXZSQFK）。
     */
    ChannelResponse<GjBatchVatWithholdingCertDownloadApplyFeedbackRes> batchVatWithholdingCertDownloadApplyFeedback(GjBatchVatWithholdingCertDownloadApplyFeedbackReq req);

    /**
     * 10. 查询增量下载增值税代扣代缴完税凭证信息（CXZLXZZZSDKDJWSPZXX）。
     */
    ChannelResponse<GjQueryIncrementalVatWithholdingCertRes> queryIncrementalVatWithholdingCert(GjQueryIncrementalVatWithholdingCertReq req);

    /**
     * 11. 批量消费税代扣代缴完税凭证下载申请（PLXFSDKDJWSPZXZSQ）。
     */
    ChannelResponse<GjBatchConsumptionWithholdingCertDownloadApplyRes> batchConsumptionWithholdingCertDownloadApply(GjBatchConsumptionWithholdingCertDownloadApplyReq req);

    /**
     * 12. 批量消费税代扣代缴完税凭证下载申请反馈（PLXFSDKDJWSPZXZSQFK）。
     */
    ChannelResponse<GjBatchConsumptionWithholdingCertDownloadApplyFeedbackRes> batchConsumptionWithholdingCertDownloadApplyFeedback(GjBatchConsumptionWithholdingCertDownloadApplyFeedbackReq req);

    /**
     * 13. 查询增量下载消费税代扣代缴完税凭证信息（CXZLXZXFSDKDJWSPZXX）。
     */
    ChannelResponse<GjQueryIncrementalConsumptionWithholdingCertRes> queryIncrementalConsumptionWithholdingCert(GjQueryIncrementalConsumptionWithholdingCertReq req);

    /**
     * 14. 批量海关缴款书下载申请（PLHGJKSXZSQ）。
     */
    ChannelResponse<GjBatchCustomsPaymentBookDownloadApplyRes> batchCustomsPaymentBookDownloadApply(GjBatchCustomsPaymentBookDownloadApplyReq req);

    /**
     * 15. 批量海关缴款书下载申请反馈（PLHGJKSXZSQFK）。
     */
    ChannelResponse<GjBatchCustomsPaymentBookDownloadApplyFeedbackRes> batchCustomsPaymentBookDownloadApplyFeedback(GjBatchCustomsPaymentBookDownloadApplyFeedbackReq req);

    /**
     * 16. 查询增量下载海关缴款书信息（CXZLXZHGJKSXX）。
     */
    ChannelResponse<GjQueryIncrementalCustomsPaymentBookRes> queryIncrementalCustomsPaymentBook(GjQueryIncrementalCustomsPaymentBookReq req);
}
