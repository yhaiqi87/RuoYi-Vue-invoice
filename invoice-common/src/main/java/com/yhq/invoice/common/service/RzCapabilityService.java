package com.yhq.invoice.common.service;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.model.ChannelResponse;

/**
 * 发票入账（乐企能力 203057）统一能力接口（契约层，定义在 common）。
 *
 * <p>与 {@link GjCapabilityService}（归集能力）、{@link InvoiceCapabilityService}
 * （开票能力）并列、互不影响：发票入账是独立的业务域，不复用开票能力路由，也不在 {@code LeqiCapabilityService} /
 * {@code LeqiInvoiceBaseService} 等开票体系中实现。共 6 个接口，覆盖批量上传入账发票、查询发票入账处理结果，
 * 以及增值税代扣代缴完税凭证、海关缴款书的批量上传与处理结果查询。leqi 与 rpa 各自实现本接口，
 * app 门面 {@code RzFacade} 按 Channel 枚举选择实现。
 */
public interface RzCapabilityService {

    /**
     * 返回本实现所属渠道（LEQI / RPA）。
     */
    Channel channel();

    /**
     * 1. 批量上传入账发票（PLSCRZFP）。
     */
    ChannelResponse<RzBatchUploadInvoiceRes> batchUploadInvoice(RzBatchUploadInvoiceReq req);

    /**
     * 2. 查询发票入账处理结果（CXFPRZCLJG）。
     */
    ChannelResponse<RzQueryInvoiceRzResultRes> queryInvoiceRzResult(RzQueryInvoiceRzResultReq req);

    /**
     * 3. 批量上传增值税入账代扣代缴完税凭证（PLSCRZDKDJWSPZ）。
     */
    ChannelResponse<RzBatchUploadWithholdingCertRes> batchUploadWithholdingCert(RzBatchUploadWithholdingCertReq req);

    /**
     * 4. 查询增值税代扣代缴完税凭证入账处理结果（CXDKDJWSPZRZCLJG）。
     */
    ChannelResponse<RzQueryWithholdingCertRzResultRes> queryWithholdingCertRzResult(RzQueryWithholdingCertRzResultReq req);

    /**
     * 5. 批量上传入账海关缴款书（PLSCRZHGJKS）。
     */
    ChannelResponse<RzBatchUploadCustomsPaymentBookRes> batchUploadCustomsPaymentBook(RzBatchUploadCustomsPaymentBookReq req);

    /**
     * 6. 查询海关缴款书入账处理结果（CXHGJKSRZCLJG）。
     */
    ChannelResponse<RzQueryCustomsPaymentBookRzResultRes> queryCustomsPaymentBookRzResult(RzQueryCustomsPaymentBookRzResultReq req);
}
