package com.luoge.ns.invoice.controller;

import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.Channel;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import com.luoge.ns.invoice.facade.TsFacade;
import org.springframework.web.bind.annotation.*;

/**
 * 增值税退税勾选（乐企能力 203064）REST 端点，与 {@link InvoiceController}（开票能力）、
 * {@code GxController}（抵扣勾选能力）、{@code GjController}（归集能力）并列、互不影响。
 * 路径形如 POST /ts/{method}/{channel}，由 {@link TsFacade} 按渠道路由到 leqi / rpa 实现。
 */
@RestController
@RequestMapping("/ts")
public class TsController {

    private final TsFacade facade;

    public TsController(TsFacade facade) {
        this.facade = facade;
    }

    /**
     * 批量上传退税发票：POST /ts/batch-upload-refund-invoice/{channel}。
     */
    @PostMapping("/batch-upload-refund-invoice/{channel}")
    public ChannelResponse<TsBatchUploadRefundInvoiceRes> batchUploadRefundInvoice(@PathVariable Channel channel,
                                                                                   @RequestBody TsBatchUploadRefundInvoiceReq req) {
        return facade.batchUploadRefundInvoice(channel, req);
    }

    /**
     * 查询发票退税勾选处理结果：POST /ts/query-invoice-refund-result/{channel}。
     */
    @PostMapping("/query-invoice-refund-result/{channel}")
    public ChannelResponse<TsQueryInvoiceRefundResultRes> queryInvoiceRefundResult(@PathVariable Channel channel,
                                                                                   @RequestBody TsQueryInvoiceRefundResultReq req) {
        return facade.queryInvoiceRefundResult(channel, req);
    }

    /**
     * 批量上传退税海关缴款书：POST /ts/batch-upload-refund-customs/{channel}。
     */
    @PostMapping("/batch-upload-refund-customs/{channel}")
    public ChannelResponse<TsBatchUploadRefundCustomsRes> batchUploadRefundCustoms(@PathVariable Channel channel,
                                                                                   @RequestBody TsBatchUploadRefundCustomsReq req) {
        return facade.batchUploadRefundCustoms(channel, req);
    }

    /**
     * 查询海关缴款书退税勾选处理结果：POST /ts/query-customs-refund-result/{channel}。
     */
    @PostMapping("/query-customs-refund-result/{channel}")
    public ChannelResponse<TsQueryCustomsRefundResultRes> queryCustomsRefundResult(@PathVariable Channel channel,
                                                                                   @RequestBody TsQueryCustomsRefundResultReq req) {
        return facade.queryCustomsRefundResult(channel, req);
    }

    /**
     * 未勾选数据初始化清单下载申请：POST /ts/unselected-data-init-download-apply/{channel}。
     */
    @PostMapping("/unselected-data-init-download-apply/{channel}")
    public ChannelResponse<TsUnselectedDataInitDownloadApplyRes> unselectedDataInitDownloadApply(@PathVariable Channel channel,
                                                                                                 @RequestBody TsUnselectedDataInitDownloadApplyReq req) {
        return facade.unselectedDataInitDownloadApply(channel, req);
    }

    /**
     * 未勾选数据初始化清单下载申请反馈：POST /ts/unselected-data-init-download-apply-feedback/{channel}。
     */
    @PostMapping("/unselected-data-init-download-apply-feedback/{channel}")
    public ChannelResponse<TsUnselectedDataInitDownloadApplyFeedbackRes> unselectedDataInitDownloadApplyFeedback(@PathVariable Channel channel,
                                                                                                                 @RequestBody TsUnselectedDataInitDownloadApplyFeedbackReq req) {
        return facade.unselectedDataInitDownloadApplyFeedback(channel, req);
    }

}
