package com.yhq.invoice.app.controller;

import com.yhq.invoice.app.facade.RzFacade;
import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.model.ChannelResponse;
import org.springframework.web.bind.annotation.*;

/**
 * 发票入账（乐企能力 203057）REST 端点，与 {@link GjController}（归集能力）并列、互不影响。
 * 路径形如 POST /rz/{method}/{channel}，由 {@link RzFacade} 按渠道路由到 leqi / rpa 实现。
 */
@RestController
@RequestMapping("/rz")
public class RzController {

    private final RzFacade facade;

    public RzController(RzFacade facade) {
        this.facade = facade;
    }

    /**
     * 批量上传入账发票：POST /rz/batch-upload-invoice/{channel}。
     */
    @PostMapping("/batch-upload-invoice/{channel}")
    public ChannelResponse<RzBatchUploadInvoiceRes> batchUploadInvoice(@PathVariable Channel channel,
                                                                       @RequestBody RzBatchUploadInvoiceReq req) {
        return facade.batchUploadInvoice(channel, req);
    }

    /**
     * 查询发票入账处理结果：POST /rz/query-invoice-rz-result/{channel}。
     */
    @PostMapping("/query-invoice-rz-result/{channel}")
    public ChannelResponse<RzQueryInvoiceRzResultRes> queryInvoiceRzResult(@PathVariable Channel channel,
                                                                           @RequestBody RzQueryInvoiceRzResultReq req) {
        return facade.queryInvoiceRzResult(channel, req);
    }

    /**
     * 批量上传增值税入账代扣代缴完税凭证：POST /rz/batch-upload-withholding-cert/{channel}。
     */
    @PostMapping("/batch-upload-withholding-cert/{channel}")
    public ChannelResponse<RzBatchUploadWithholdingCertRes> batchUploadWithholdingCert(@PathVariable Channel channel,
                                                                                       @RequestBody RzBatchUploadWithholdingCertReq req) {
        return facade.batchUploadWithholdingCert(channel, req);
    }

    /**
     * 查询增值税代扣代缴完税凭证入账处理结果：POST /rz/query-withholding-cert-rz-result/{channel}。
     */
    @PostMapping("/query-withholding-cert-rz-result/{channel}")
    public ChannelResponse<RzQueryWithholdingCertRzResultRes> queryWithholdingCertRzResult(@PathVariable Channel channel,
                                                                                           @RequestBody RzQueryWithholdingCertRzResultReq req) {
        return facade.queryWithholdingCertRzResult(channel, req);
    }

    /**
     * 批量上传入账海关缴款书：POST /rz/batch-upload-customs-payment-book/{channel}。
     */
    @PostMapping("/batch-upload-customs-payment-book/{channel}")
    public ChannelResponse<RzBatchUploadCustomsPaymentBookRes> batchUploadCustomsPaymentBook(@PathVariable Channel channel,
                                                                                             @RequestBody RzBatchUploadCustomsPaymentBookReq req) {
        return facade.batchUploadCustomsPaymentBook(channel, req);
    }

    /**
     * 查询海关缴款书入账处理结果：POST /rz/query-customs-payment-book-rz-result/{channel}。
     */
    @PostMapping("/query-customs-payment-book-rz-result/{channel}")
    public ChannelResponse<RzQueryCustomsPaymentBookRzResultRes> queryCustomsPaymentBookRzResult(@PathVariable Channel channel,
                                                                                                 @RequestBody RzQueryCustomsPaymentBookRzResultReq req) {
        return facade.queryCustomsPaymentBookRzResult(channel, req);
    }
}
