package com.luoge.ns.invoice.leqi.service;

import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.Channel;
import com.luoge.ns.invoice.common.enums.GjInterfaceCode;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import com.luoge.ns.invoice.common.service.GjCapabilityService;
import com.luoge.ns.invoice.leqi.annotation.DocVersion;
import com.luoge.ns.invoice.leqi.annotation.ValidateReq;
import com.luoge.ns.invoice.leqi.client.LeqiHttpUtil;
import org.springframework.stereotype.Service;

/**
 * 乐企（LEQI）归集能力服务：实现归集统一契约 {@link GjCapabilityService} 的全部 16 个接口。
 *
 * <p>与开票能力完全隔离：本类<b>不</b>实现 {@code LeqiCapabilityService}，故不会进入 {@code LeqiInvoiceService}
 * 的开票能力路由表，也不会污染 BASE/SALE/LEASE 等开票实现。每个方法直接走通桩链路
 * {@code httpUtil.call(serviceCode, req, Res.class)}，后续校验/字段处理加在对应方法内，不影响其他能力。
 */
@Service
@ValidateReq
@DocVersion(doc = "乐企归集能力说明文档", value = "V2.021")
public class LeqiGjService implements GjCapabilityService {

    private final LeqiHttpUtil httpUtil;

    public LeqiGjService(LeqiHttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Override
    public Channel channel() {
        return Channel.LEQI;
    }

    /**
     * 批量发票下载申请（PLFPXZSQ）：调用乐企归集接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjBatchInvoiceDownloadApplyRes> batchInvoiceDownloadApply(GjBatchInvoiceDownloadApplyReq req) {
        return httpUtil.call(GjInterfaceCode.BATCH_INVOICE_DOWNLOAD_APPLY.getServiceCode(), req, GjBatchInvoiceDownloadApplyRes.class);
    }

    /**
     * 批量发票下载申请反馈（PLFPXZSQFK）：按流水号与包号批量下载全票面信息，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjBatchInvoiceDownloadApplyFeedbackRes> batchInvoiceDownloadApplyFeedback(GjBatchInvoiceDownloadApplyFeedbackReq req) {
        return httpUtil.call(GjInterfaceCode.BATCH_INVOICE_DOWNLOAD_APPLY_FEEDBACK.getServiceCode(), req, GjBatchInvoiceDownloadApplyFeedbackRes.class);
    }

    /**
     * 查询增量下载发票信息（CXZLXZFPXX）：按归集日期查询增量发票数据，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjQueryIncrementalInvoiceDownloadRes> queryIncrementalInvoiceDownload(GjQueryIncrementalInvoiceDownloadReq req) {
        return httpUtil.call(GjInterfaceCode.QUERY_INCREMENTAL_INVOICE_DOWNLOAD.getServiceCode(), req, GjQueryIncrementalInvoiceDownloadRes.class);
    }

    /**
     * 发票风险信息查询（FPTSTXXXCX）：查询纳税人发票风险信息，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjQueryInvoiceRiskInfoRes> queryInvoiceRiskInfo(GjQueryInvoiceRiskInfoReq req) {
        return httpUtil.call(GjInterfaceCode.QUERY_INVOICE_RISK_INFO.getServiceCode(), req, GjQueryInvoiceRiskInfoRes.class);
    }

    /**
     * 发票用途状态信息查询（FPZTXXCX）：查询发票用途状态，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjQueryInvoiceUsageStatusRes> queryInvoiceUsageStatus(GjQueryInvoiceUsageStatusReq req) {
        return httpUtil.call(GjInterfaceCode.QUERY_INVOICE_USAGE_STATUS.getServiceCode(), req, GjQueryInvoiceUsageStatusRes.class);
    }

    /**
     * 批量出口转内销发票信息查询（PLCKZNXFPXXCX）：查询出口转内销发票信息，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjQueryBatchExportDomesticInvoiceRes> queryBatchExportDomesticInvoice(GjQueryBatchExportDomesticInvoiceReq req) {
        return httpUtil.call(GjInterfaceCode.QUERY_BATCH_EXPORT_DOMESTIC_INVOICE.getServiceCode(), req, GjQueryBatchExportDomesticInvoiceRes.class);
    }

    /**
     * 批量出口转内销海关缴款书信息查询（PLCKZNXHGJKSXXCX）：查询出口转内销海关缴款书信息，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjQueryBatchExportDomesticCustomsRes> queryBatchExportDomesticCustoms(GjQueryBatchExportDomesticCustomsReq req) {
        return httpUtil.call(GjInterfaceCode.QUERY_BATCH_EXPORT_DOMESTIC_CUSTOMS.getServiceCode(), req, GjQueryBatchExportDomesticCustomsRes.class);
    }

    /**
     * 批量增值税代扣代缴完税凭证下载申请（PLZZSDKDJWSPZXZSQ）：提交下载申请，返回批次流水号，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjBatchVatWithholdingCertDownloadApplyRes> batchVatWithholdingCertDownloadApply(GjBatchVatWithholdingCertDownloadApplyReq req) {
        return httpUtil.call(GjInterfaceCode.BATCH_VAT_WITHHOLDING_CERT_DOWNLOAD_APPLY.getServiceCode(), req, GjBatchVatWithholdingCertDownloadApplyRes.class);
    }

    /**
     * 批量增值税代扣代缴完税凭证下载申请反馈（PLZZSDKDJWSPZXZSQFK）：按批次流水号与包号下载凭证，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjBatchVatWithholdingCertDownloadApplyFeedbackRes> batchVatWithholdingCertDownloadApplyFeedback(GjBatchVatWithholdingCertDownloadApplyFeedbackReq req) {
        return httpUtil.call(GjInterfaceCode.BATCH_VAT_WITHHOLDING_CERT_DOWNLOAD_APPLY_FEEDBACK.getServiceCode(), req, GjBatchVatWithholdingCertDownloadApplyFeedbackRes.class);
    }

    /**
     * 查询增量下载增值税代扣代缴完税凭证信息（CXZLXZZZSDKDJWSPZXX）：按归集日期查询增量凭证，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjQueryIncrementalVatWithholdingCertRes> queryIncrementalVatWithholdingCert(GjQueryIncrementalVatWithholdingCertReq req) {
        return httpUtil.call(GjInterfaceCode.QUERY_INCREMENTAL_VAT_WITHHOLDING_CERT.getServiceCode(), req, GjQueryIncrementalVatWithholdingCertRes.class);
    }

    /**
     * 批量消费税代扣代缴完税凭证下载申请（PLXFSDKDJWSPZXZSQ）：提交下载申请，返回批次流水号，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjBatchConsumptionWithholdingCertDownloadApplyRes> batchConsumptionWithholdingCertDownloadApply(GjBatchConsumptionWithholdingCertDownloadApplyReq req) {
        return httpUtil.call(GjInterfaceCode.BATCH_CONSUMPTION_WITHHOLDING_CERT_DOWNLOAD_APPLY.getServiceCode(), req, GjBatchConsumptionWithholdingCertDownloadApplyRes.class);
    }

    /**
     * 批量消费税代扣代缴完税凭证下载申请反馈（PLXFSDKDJWSPZXZSQFK）：按批次流水号与包号下载凭证，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjBatchConsumptionWithholdingCertDownloadApplyFeedbackRes> batchConsumptionWithholdingCertDownloadApplyFeedback(GjBatchConsumptionWithholdingCertDownloadApplyFeedbackReq req) {
        return httpUtil.call(GjInterfaceCode.BATCH_CONSUMPTION_WITHHOLDING_CERT_DOWNLOAD_APPLY_FEEDBACK.getServiceCode(), req, GjBatchConsumptionWithholdingCertDownloadApplyFeedbackRes.class);
    }

    /**
     * 查询增量下载消费税代扣代缴完税凭证信息（CXZLXZXFSDKDJWSPZXX）：按归集日期查询增量凭证，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjQueryIncrementalConsumptionWithholdingCertRes> queryIncrementalConsumptionWithholdingCert(GjQueryIncrementalConsumptionWithholdingCertReq req) {
        return httpUtil.call(GjInterfaceCode.QUERY_INCREMENTAL_CONSUMPTION_WITHHOLDING_CERT.getServiceCode(), req, GjQueryIncrementalConsumptionWithholdingCertRes.class);
    }

    /**
     * 批量海关缴款书下载申请（PLHGJKSXZSQ）：提交下载申请，返回批次流水号，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjBatchCustomsPaymentBookDownloadApplyRes> batchCustomsPaymentBookDownloadApply(GjBatchCustomsPaymentBookDownloadApplyReq req) {
        return httpUtil.call(GjInterfaceCode.BATCH_CUSTOMS_PAYMENT_BOOK_DOWNLOAD_APPLY.getServiceCode(), req, GjBatchCustomsPaymentBookDownloadApplyRes.class);
    }

    /**
     * 批量海关缴款书下载申请反馈（PLHGJKSXZSQFK）：按批次流水号与包号下载缴款书，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjBatchCustomsPaymentBookDownloadApplyFeedbackRes> batchCustomsPaymentBookDownloadApplyFeedback(GjBatchCustomsPaymentBookDownloadApplyFeedbackReq req) {
        return httpUtil.call(GjInterfaceCode.BATCH_CUSTOMS_PAYMENT_BOOK_DOWNLOAD_APPLY_FEEDBACK.getServiceCode(), req, GjBatchCustomsPaymentBookDownloadApplyFeedbackRes.class);
    }

    /**
     * 查询增量下载海关缴款书信息（CXZLXZHGJKSXX）：按归集日期查询增量缴款书，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GjQueryIncrementalCustomsPaymentBookRes> queryIncrementalCustomsPaymentBook(GjQueryIncrementalCustomsPaymentBookReq req) {
        return httpUtil.call(GjInterfaceCode.QUERY_INCREMENTAL_CUSTOMS_PAYMENT_BOOK.getServiceCode(), req, GjQueryIncrementalCustomsPaymentBookRes.class);
    }
}
