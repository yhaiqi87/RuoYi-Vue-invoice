package com.yhq.invoice.app.controller;

import com.yhq.invoice.app.facade.GxFacade;
import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.model.ChannelResponse;
import org.springframework.web.bind.annotation.*;

/**
 * 增值税抵扣勾选（乐企能力 203065）REST 端点，与 {@link InvoiceController}（开票能力）、
 * {@code GjController}（归集能力）并列、互不影响。
 * 路径形如 POST /gx/{method}/{channel}，由 {@link GxFacade} 按渠道路由到 leqi / rpa 实现。
 */
@RestController
@RequestMapping("/gx")
public class GxController {

    private final GxFacade facade;

    public GxController(GxFacade facade) {
        this.facade = facade;
    }

    /**
     * 获取当前税款所属期与当期税款所属期统计状态：POST /gx/get-cur-period-and-stat/{channel}。
     */
    @PostMapping("/get-cur-period-and-stat/{channel}")
    public ChannelResponse<GxGetCurPeriodAndStatRes> getCurPeriodAndStat(@PathVariable Channel channel,
                                                                         @RequestBody GxGetCurPeriodAndStatReq req) {
        return facade.getCurPeriodAndStat(channel, req);
    }

    /**
     * 批量上传抵扣发票：POST /gx/batch-upload-deduction-invoice/{channel}。
     */
    @PostMapping("/batch-upload-deduction-invoice/{channel}")
    public ChannelResponse<GxBatchUploadDeductionInvoiceRes> batchUploadDeductionInvoice(@PathVariable Channel channel,
                                                                                         @RequestBody GxBatchUploadDeductionInvoiceReq req) {
        return facade.batchUploadDeductionInvoice(channel, req);
    }

    /**
     * 查询发票抵扣勾选处理结果：POST /gx/query-invoice-deduction-result/{channel}。
     */
    @PostMapping("/query-invoice-deduction-result/{channel}")
    public ChannelResponse<GxQueryInvoiceDeductionResultRes> queryInvoiceDeductionResult(@PathVariable Channel channel,
                                                                                         @RequestBody GxQueryInvoiceDeductionResultReq req) {
        return facade.queryInvoiceDeductionResult(channel, req);
    }

    /**
     * 批量上传海关缴款书抵扣勾选：POST /gx/batch-upload-customs-deduction/{channel}。
     */
    @PostMapping("/batch-upload-customs-deduction/{channel}")
    public ChannelResponse<GxBatchUploadCustomsDeductionRes> batchUploadCustomsDeduction(@PathVariable Channel channel,
                                                                                         @RequestBody GxBatchUploadCustomsDeductionReq req) {
        return facade.batchUploadCustomsDeduction(channel, req);
    }

    /**
     * 查询海关缴款书抵扣勾选处理结果：POST /gx/query-customs-deduction-result/{channel}。
     */
    @PostMapping("/query-customs-deduction-result/{channel}")
    public ChannelResponse<GxQueryCustomsDeductionResultRes> queryCustomsDeductionResult(@PathVariable Channel channel,
                                                                                         @RequestBody GxQueryCustomsDeductionResultReq req) {
        return facade.queryCustomsDeductionResult(channel, req);
    }

    /**
     * 申请抵扣统计：POST /gx/apply-deduction-stat/{channel}。
     */
    @PostMapping("/apply-deduction-stat/{channel}")
    public ChannelResponse<GxApplyDeductionStatRes> applyDeductionStat(@PathVariable Channel channel,
                                                                       @RequestBody GxApplyDeductionStatReq req) {
        return facade.applyDeductionStat(channel, req);
    }

    /**
     * 查询申请统计处理结果：POST /gx/query-stat-process-result/{channel}。
     */
    @PostMapping("/query-stat-process-result/{channel}")
    public ChannelResponse<GxQueryStatProcessResultRes> queryStatProcessResult(@PathVariable Channel channel,
                                                                               @RequestBody GxQueryStatProcessResultReq req) {
        return facade.queryStatProcessResult(channel, req);
    }

    /**
     * 申请确认抵扣统计：POST /gx/apply-confirm-deduction-stat/{channel}。
     */
    @PostMapping("/apply-confirm-deduction-stat/{channel}")
    public ChannelResponse<GxApplyConfirmDeductionStatRes> applyConfirmDeductionStat(@PathVariable Channel channel,
                                                                                     @RequestBody GxApplyConfirmDeductionStatReq req) {
        return facade.applyConfirmDeductionStat(channel, req);
    }

    /**
     * 查询确认统计处理结果：POST /gx/query-confirm-stat-result/{channel}。
     */
    @PostMapping("/query-confirm-stat-result/{channel}")
    public ChannelResponse<GxQueryConfirmStatResultRes> queryConfirmStatResult(@PathVariable Channel channel,
                                                                               @RequestBody GxQueryConfirmStatResultReq req) {
        return facade.queryConfirmStatResult(channel, req);
    }

    /**
     * 获取税款所属期发票抵扣勾选处理结果：POST /gx/get-period-invoice-deduction-result/{channel}。
     */
    @PostMapping("/get-period-invoice-deduction-result/{channel}")
    public ChannelResponse<GxGetPeriodInvoiceDeductionResultRes> getPeriodInvoiceDeductionResult(@PathVariable Channel channel,
                                                                                                 @RequestBody GxGetPeriodInvoiceDeductionResultReq req) {
        return facade.getPeriodInvoiceDeductionResult(channel, req);
    }

    /**
     * 获取税款所属期海关缴款书抵扣勾选处理结果：POST /gx/get-period-customs-deduction-result/{channel}。
     */
    @PostMapping("/get-period-customs-deduction-result/{channel}")
    public ChannelResponse<GxGetPeriodCustomsDeductionResultRes> getPeriodCustomsDeductionResult(@PathVariable Channel channel,
                                                                                                 @RequestBody GxGetPeriodCustomsDeductionResultReq req) {
        return facade.getPeriodCustomsDeductionResult(channel, req);
    }

    /**
     * 批量上传增值税代扣代缴完税凭证抵扣勾选：POST /gx/batch-upload-vat-withhold-deduction/{channel}。
     */
    @PostMapping("/batch-upload-vat-withhold-deduction/{channel}")
    public ChannelResponse<GxBatchUploadVatWithholdDeductionRes> batchUploadVatWithholdDeduction(@PathVariable Channel channel,
                                                                                                 @RequestBody GxBatchUploadVatWithholdDeductionReq req) {
        return facade.batchUploadVatWithholdDeduction(channel, req);
    }

    /**
     * 查询增值税代扣代缴完税凭证抵扣勾选处理结果：POST /gx/query-vat-withhold-deduction-result/{channel}。
     */
    @PostMapping("/query-vat-withhold-deduction-result/{channel}")
    public ChannelResponse<GxQueryVatWithholdDeductionResultRes> queryVatWithholdDeductionResult(@PathVariable Channel channel,
                                                                                                 @RequestBody GxQueryVatWithholdDeductionResultReq req) {
        return facade.queryVatWithholdDeductionResult(channel, req);
    }

    /**
     * 获取税款所属期代扣代缴完税凭证抵扣勾选处理结果：POST /gx/get-period-withhold-deduction-result/{channel}。
     */
    @PostMapping("/get-period-withhold-deduction-result/{channel}")
    public ChannelResponse<GxGetPeriodWithholdDeductionResultRes> getPeriodWithholdDeductionResult(@PathVariable Channel channel,
                                                                                                   @RequestBody GxGetPeriodWithholdDeductionResultReq req) {
        return facade.getPeriodWithholdDeductionResult(channel, req);
    }

    /**
     * 未勾选数据初始化清单下载申请：POST /gx/unselected-data-init-download-apply/{channel}。
     */
    @PostMapping("/unselected-data-init-download-apply/{channel}")
    public ChannelResponse<GxUnselectedDataInitDownloadApplyRes> unselectedDataInitDownloadApply(@PathVariable Channel channel,
                                                                                                 @RequestBody GxUnselectedDataInitDownloadApplyReq req) {
        return facade.unselectedDataInitDownloadApply(channel, req);
    }

    /**
     * 未勾选数据初始化清单下载申请反馈：POST /gx/unselected-data-init-download-apply-feedback/{channel}。
     */
    @PostMapping("/unselected-data-init-download-apply-feedback/{channel}")
    public ChannelResponse<GxUnselectedDataInitDownloadApplyFeedbackRes> unselectedDataInitDownloadApplyFeedback(@PathVariable Channel channel,
                                                                                                                 @RequestBody GxUnselectedDataInitDownloadApplyFeedbackReq req) {
        return facade.unselectedDataInitDownloadApplyFeedback(channel, req);
    }

    /**
     * 批量上传待处理农产品发票确认清单：POST /gx/batch-upload-agri-invoice-confirm-list/{channel}。
     */
    @PostMapping("/batch-upload-agri-invoice-confirm-list/{channel}")
    public ChannelResponse<GxBatchUploadAgriInvoiceConfirmListRes> batchUploadAgriInvoiceConfirmList(@PathVariable Channel channel,
                                                                                                     @RequestBody GxBatchUploadAgriInvoiceConfirmListReq req) {
        return facade.batchUploadAgriInvoiceConfirmList(channel, req);
    }

    /**
     * 查询批量上传待处理农产品发票确认清单处理结果：POST /gx/query-agri-invoice-confirm-list-result/{channel}。
     */
    @PostMapping("/query-agri-invoice-confirm-list-result/{channel}")
    public ChannelResponse<GxQueryAgriInvoiceConfirmListResultRes> queryAgriInvoiceConfirmListResult(@PathVariable Channel channel,
                                                                                                     @RequestBody GxQueryAgriInvoiceConfirmListResultReq req) {
        return facade.queryAgriInvoiceConfirmListResult(channel, req);
    }

    /**
     * 批量上传税务机关代开农产品发票补录信息：POST /gx/batch-upload-tax-auth-agri-invoice-supplement/{channel}。
     */
    @PostMapping("/batch-upload-tax-auth-agri-invoice-supplement/{channel}")
    public ChannelResponse<GxBatchUploadTaxAuthAgriInvoiceSupplementRes> batchUploadTaxAuthAgriInvoiceSupplement(@PathVariable Channel channel,
                                                                                                                 @RequestBody GxBatchUploadTaxAuthAgriInvoiceSupplementReq req) {
        return facade.batchUploadTaxAuthAgriInvoiceSupplement(channel, req);
    }

    /**
     * 查询批量上传税务机关代开农产品发票补录信息处理结果：POST /gx/query-tax-auth-agri-invoice-supplement-result/{channel}。
     */
    @PostMapping("/query-tax-auth-agri-invoice-supplement-result/{channel}")
    public ChannelResponse<GxQueryTaxAuthAgriInvoiceSupplementResultRes> queryTaxAuthAgriInvoiceSupplementResult(@PathVariable Channel channel,
                                                                                                                 @RequestBody GxQueryTaxAuthAgriInvoiceSupplementResultReq req) {
        return facade.queryTaxAuthAgriInvoiceSupplementResult(channel, req);
    }

    /**
     * 查询发票农产品商品编码列表：POST /gx/query-invoice-agri-goods-code-list/{channel}。
     */
    @PostMapping("/query-invoice-agri-goods-code-list/{channel}")
    public ChannelResponse<GxQueryInvoiceAgriGoodsCodeListRes> queryInvoiceAgriGoodsCodeList(@PathVariable Channel channel,
                                                                                             @RequestBody GxQueryInvoiceAgriGoodsCodeListReq req) {
        return facade.queryInvoiceAgriGoodsCodeList(channel, req);
    }

    /**
     * 汇总纳税人机构列表查询：POST /gx/query-summary-taxpayer-org-list/{channel}。
     */
    @PostMapping("/query-summary-taxpayer-org-list/{channel}")
    public ChannelResponse<GxQuerySummaryTaxpayerOrgListRes> querySummaryTaxpayerOrgList(@PathVariable Channel channel,
                                                                                         @RequestBody GxQuerySummaryTaxpayerOrgListReq req) {
        return facade.querySummaryTaxpayerOrgList(channel, req);
    }

    /**
     * 总分机构汇总勾选确认：POST /gx/branch-summary-deduction-confirm/{channel}。
     */
    @PostMapping("/branch-summary-deduction-confirm/{channel}")
    public ChannelResponse<GxBranchSummaryDeductionConfirmRes> branchSummaryDeductionConfirm(@PathVariable Channel channel,
                                                                                             @RequestBody GxBranchSummaryDeductionConfirmReq req) {
        return facade.branchSummaryDeductionConfirm(channel, req);
    }

    /**
     * 申请税款所属期变更：POST /gx/apply-period-change/{channel}。
     */
    @PostMapping("/apply-period-change/{channel}")
    public ChannelResponse<GxApplyPeriodChangeRes> applyPeriodChange(@PathVariable Channel channel,
                                                                     @RequestBody GxApplyPeriodChangeReq req) {
        return facade.applyPeriodChange(channel, req);
    }

    /**
     * 申请注销勾选：POST /gx/apply-cancel-deduction/{channel}。
     */
    @PostMapping("/apply-cancel-deduction/{channel}")
    public ChannelResponse<GxApplyCancelDeductionRes> applyCancelDeduction(@PathVariable Channel channel,
                                                                           @RequestBody GxApplyCancelDeductionReq req) {
        return facade.applyCancelDeduction(channel, req);
    }

    /**
     * 刷新税款所属期：POST /gx/refresh-tax-period/{channel}。
     */
    @PostMapping("/refresh-tax-period/{channel}")
    public ChannelResponse<GxRefreshTaxPeriodRes> refreshTaxPeriod(@PathVariable Channel channel,
                                                                   @RequestBody GxRefreshTaxPeriodReq req) {
        return facade.refreshTaxPeriod(channel, req);
    }

    /**
     * 获取追溯期税款所属期与追溯期税款所属期统计状态：POST /gx/get-trace-period-and-stat/{channel}。
     */
    @PostMapping("/get-trace-period-and-stat/{channel}")
    public ChannelResponse<GxGetTracePeriodAndStatRes> getTracePeriodAndStat(@PathVariable Channel channel,
                                                                             @RequestBody GxGetTracePeriodAndStatReq req) {
        return facade.getTracePeriodAndStat(channel, req);
    }

}
