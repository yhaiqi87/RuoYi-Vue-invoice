package com.luoge.ns.invoice.leqi.service;

import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.Channel;
import com.luoge.ns.invoice.common.enums.RzInterfaceCode;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import com.luoge.ns.invoice.common.service.RzCapabilityService;
import com.luoge.ns.invoice.leqi.annotation.DocVersion;
import com.luoge.ns.invoice.leqi.annotation.ValidateReq;
import com.luoge.ns.invoice.leqi.client.LeqiHttpUtil;
import org.springframework.stereotype.Service;

/**
 * 乐企（LEQI）发票入账能力服务：实现发票入账统一契约 {@link RzCapabilityService} 的全部 6 个接口。
 *
 * <p>与开票能力及归集/勾选/退税能力完全隔离：本类<b>不</b>实现 {@code LeqiCapabilityService}，故不会进入
 * {@code LeqiInvoiceService} 的开票能力路由表，也不会污染 BASE/SALE/LEASE 等开票实现。每个方法直接走通桩链路
 * {@code httpUtil.call(serviceCode, req, Res.class)}，后续校验/字段处理加在对应方法内，不影响其他能力。
 */
@Service
@ValidateReq
@DocVersion(doc = "乐企发票入账能力说明文档", value = "V1.004")
public class LeqiRzService implements RzCapabilityService {

    private final LeqiHttpUtil httpUtil;

    public LeqiRzService(LeqiHttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Override
    public Channel channel() {
        return Channel.LEQI;
    }

    /**
     * 批量上传入账发票（PLSCRZFP）：提交入账发票批量上传，返回批次流水号，当前走通桩链路。
     */
    @Override
    public ChannelResponse<RzBatchUploadInvoiceRes> batchUploadInvoice(RzBatchUploadInvoiceReq req) {
        return httpUtil.call(RzInterfaceCode.BATCH_UPLOAD_INVOICE.getServiceCode(), req, RzBatchUploadInvoiceRes.class);
    }

    /**
     * 查询发票入账处理结果（CXFPRZCLJG）：按批次流水号查询入账发票处理明细，当前走通桩链路。
     */
    @Override
    public ChannelResponse<RzQueryInvoiceRzResultRes> queryInvoiceRzResult(RzQueryInvoiceRzResultReq req) {
        return httpUtil.call(RzInterfaceCode.QUERY_INVOICE_RZ_RESULT.getServiceCode(), req, RzQueryInvoiceRzResultRes.class);
    }

    /**
     * 批量上传增值税入账代扣代缴完税凭证（PLSCRZDKDJWSPZ）：提交完税凭证批量上传，返回批次流水号，当前走通桩链路。
     */
    @Override
    public ChannelResponse<RzBatchUploadWithholdingCertRes> batchUploadWithholdingCert(RzBatchUploadWithholdingCertReq req) {
        return httpUtil.call(RzInterfaceCode.BATCH_UPLOAD_WITHHOLDING_CERT.getServiceCode(), req, RzBatchUploadWithholdingCertRes.class);
    }

    /**
     * 查询增值税代扣代缴完税凭证入账处理结果（CXDKDJWSPZRZCLJG）：按批次流水号查询凭证入账明细，当前走通桩链路。
     */
    @Override
    public ChannelResponse<RzQueryWithholdingCertRzResultRes> queryWithholdingCertRzResult(RzQueryWithholdingCertRzResultReq req) {
        return httpUtil.call(RzInterfaceCode.QUERY_WITHHOLDING_CERT_RZ_RESULT.getServiceCode(), req, RzQueryWithholdingCertRzResultRes.class);
    }

    /**
     * 批量上传入账海关缴款书（PLSCRZHGJKS）：提交海关缴款书批量上传，返回批次流水号，当前走通桩链路。
     */
    @Override
    public ChannelResponse<RzBatchUploadCustomsPaymentBookRes> batchUploadCustomsPaymentBook(RzBatchUploadCustomsPaymentBookReq req) {
        return httpUtil.call(RzInterfaceCode.BATCH_UPLOAD_CUSTOMS_PAYMENT_BOOK.getServiceCode(), req, RzBatchUploadCustomsPaymentBookRes.class);
    }

    /**
     * 查询海关缴款书入账处理结果（CXHGJKSRZCLJG）：按批次流水号查询缴款书入账明细，当前走通桩链路。
     */
    @Override
    public ChannelResponse<RzQueryCustomsPaymentBookRzResultRes> queryCustomsPaymentBookRzResult(RzQueryCustomsPaymentBookRzResultReq req) {
        return httpUtil.call(RzInterfaceCode.QUERY_CUSTOMS_PAYMENT_BOOK_RZ_RESULT.getServiceCode(), req, RzQueryCustomsPaymentBookRzResultRes.class);
    }
}
