package com.yhq.invoice.rpa.service;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.model.ChannelResponse;
import com.yhq.invoice.common.service.RzCapabilityService;
import org.springframework.stereotype.Service;

/**
 * rpa 渠道发票入账实现：门面按 Channel=RPA 选中本服务。
 *
 * <p>按需求，rpa 渠道暂不对发票入账能力做任何实现，全部方法统一抛出 UnsupportedOperationException 占位，
 * 待后续接入 RPA 流程时再逐接口实现。本类与 {@code RpaInvoiceService}（开票能力）、{@code RpaGjService}
 * （归集能力）并列，不影响其他能力的 rpa 占位实现。
 */
@Service
public class RpaRzService implements RzCapabilityService {

    @Override
    public Channel channel() {
        return Channel.RPA;
    }

    /**
     * 批量上传入账发票（RPA）：渠道尚未实现发票入账能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<RzBatchUploadInvoiceRes> batchUploadInvoice(RzBatchUploadInvoiceReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现发票入账能力");
    }

    /**
     * 查询发票入账处理结果（RPA）：渠道尚未实现发票入账能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<RzQueryInvoiceRzResultRes> queryInvoiceRzResult(RzQueryInvoiceRzResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现发票入账能力");
    }

    /**
     * 批量上传增值税入账代扣代缴完税凭证（RPA）：渠道尚未实现发票入账能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<RzBatchUploadWithholdingCertRes> batchUploadWithholdingCert(RzBatchUploadWithholdingCertReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现发票入账能力");
    }

    /**
     * 查询增值税代扣代缴完税凭证入账处理结果（RPA）：渠道尚未实现发票入账能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<RzQueryWithholdingCertRzResultRes> queryWithholdingCertRzResult(RzQueryWithholdingCertRzResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现发票入账能力");
    }

    /**
     * 批量上传入账海关缴款书（RPA）：渠道尚未实现发票入账能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<RzBatchUploadCustomsPaymentBookRes> batchUploadCustomsPaymentBook(RzBatchUploadCustomsPaymentBookReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现发票入账能力");
    }

    /**
     * 查询海关缴款书入账处理结果（RPA）：渠道尚未实现发票入账能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<RzQueryCustomsPaymentBookRzResultRes> queryCustomsPaymentBookRzResult(RzQueryCustomsPaymentBookRzResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现发票入账能力");
    }
}
