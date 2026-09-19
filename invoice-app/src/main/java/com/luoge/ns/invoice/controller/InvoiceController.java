package com.luoge.ns.invoice.controller;

import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.Channel;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import com.luoge.ns.invoice.facade.InvoiceFacade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceFacade facade;

    public InvoiceController(InvoiceFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/batch-pre-code/{channel}")
    public ChannelResponse<GetBatchPreCodeRes> batchPreCode(@PathVariable Channel channel,
                                          @RequestBody GetBatchPreCodeReq req) {
        return facade.getBatchPreCode(channel, req);
    }

    @PostMapping("/query-quota/{channel}")
    public ChannelResponse<QueryQuotaRes> queryQuota(@PathVariable Channel channel,
                                    @RequestBody QueryQuotaReq req) {
        return facade.queryQuota(channel, req);
    }

    @PostMapping("/download-or-return-quota/{channel}")
    public ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(@PathVariable Channel channel,
                                                                           @RequestBody DownloadOrReturnQuotaReq req) {
        return facade.downloadOrReturnQuota(channel, req);
    }

    @PostMapping("/adjust-quota-validity/{channel}")
    public ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(@PathVariable Channel channel,
                                                                       @RequestBody AdjustQuotaValidityReq req) {
        return facade.adjustQuotaValidity(channel, req);
    }

    @PostMapping("/query-taxpayer-risk/{channel}")
    public ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(@PathVariable Channel channel,
                                                                   @RequestBody QueryTaxpayerRiskReq req) {
        return facade.queryTaxpayerRisk(channel, req);
    }

    @PostMapping("/query-taxpayer-basic/{channel}")
    public ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(@PathVariable Channel channel,
                                                                     @RequestBody QueryTaxpayerBasicReq req) {
        return facade.queryTaxpayerBasic(channel, req);
    }

    @PostMapping("/query-tax-rate/{channel}")
    public ChannelResponse<QueryTaxRateRes> queryTaxRate(@PathVariable Channel channel,
                                                         @RequestBody QueryTaxRateReq req) {
        return facade.queryTaxRate(channel, req);
    }

    @PostMapping("/query-tax-category/{channel}")
    public ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(@PathVariable Channel channel,
                                                                 @RequestBody QueryTaxCategoryReq req) {
        return facade.queryTaxCategory(channel, req);
    }

    @PostMapping("/query-red-confirm-detail/{channel}")
    public ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(@PathVariable Channel channel,
                                                                           @RequestBody QueryRedConfirmDetailReq req) {
        return facade.queryRedConfirmDetail(channel, req);
    }

    @PostMapping("/upload-invoice/{channel}")
    public ChannelResponse<UploadInvoiceRes> uploadInvoice(@PathVariable Channel channel,
                                                           @RequestBody UploadInvoiceReq req) {
        return facade.uploadInvoice(channel, req);
    }

    @PostMapping("/query-red-confirm-list/{channel}")
    public ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(@PathVariable Channel channel,
                                                                       @RequestBody QueryRedConfirmListReq req) {
        return facade.queryRedConfirmList(channel, req);
    }

    @PostMapping("/query-upload-result/{channel}")
    public ChannelResponse<QueryUploadResultRes> queryUploadResult(@PathVariable Channel channel,
                                                                   @RequestBody QueryUploadResultReq req) {
        return facade.queryUploadResult(channel, req);
    }

    @PostMapping("/apply-red-confirm/{channel}")
    public ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(@PathVariable Channel channel,
                                                               @RequestBody ApplyRedConfirmReq req) {
        return facade.applyRedConfirm(channel, req);
    }

    @PostMapping("/confirm-red-confirm/{channel}")
    public ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(@PathVariable Channel channel,
                                                                   @RequestBody ConfirmRedConfirmReq req) {
        return facade.confirmRedConfirm(channel, req);
    }

    @PostMapping("/upload-summary-confirm/{channel}")
    public ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(@PathVariable Channel channel,
                                                                         @RequestBody UploadSummaryConfirmReq req) {
        return facade.uploadSummaryConfirm(channel, req);
    }

    @PostMapping("/query-summary-confirm/{channel}")
    public ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(@PathVariable Channel channel,
                                                                       @RequestBody QuerySummaryConfirmReq req) {
        return facade.querySummaryConfirm(channel, req);
    }

    @PostMapping("/query-invoice-usage/{channel}")
    public ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(@PathVariable Channel channel,
                                                                   @RequestBody QueryInvoiceUsageReq req) {
        return facade.queryInvoiceUsage(channel, req);
    }

    @PostMapping("/query-deduction-voucher/{channel}")
    public ChannelResponse<QueryDeductionVoucherRes> queryDeductionVoucher(@PathVariable Channel channel,
                                                                           @RequestBody QueryDeductionVoucherReq req) {
        return facade.queryDeductionVoucher(channel, req);
    }

    @PostMapping("/query-tax-pro-service/{channel}")
    public ChannelResponse<QueryTaxProServiceRes> queryTaxProService(@PathVariable Channel channel,
                                                                     @RequestBody QueryTaxProServiceReq req) {
        return facade.queryTaxProService(channel, req);
    }

    @PostMapping("/query-diff-tax-auth/{channel}")
    public ChannelResponse<QueryDiffTaxAuthRes> queryDiffTaxAuth(@PathVariable Channel channel,
                                                                 @RequestBody QueryDiffTaxAuthReq req) {
        return facade.queryDiffTaxAuth(channel, req);
    }

    @PostMapping("/batch-download-apply/{channel}")
    public ChannelResponse<BatchDownloadApplyRes> batchDownloadApply(@PathVariable Channel channel,
                                                                     @RequestBody BatchDownloadApplyReq req) {
        return facade.batchDownloadApply(channel, req);
    }

    @PostMapping("/query-house-source/{channel}")
    public ChannelResponse<QueryHouseSourceRes> queryHouseSource(@PathVariable Channel channel,
                                                                 @RequestBody QueryHouseSourceReq req) {
        return facade.queryHouseSource(channel, req);
    }

    @PostMapping("/query-jzfw-info/{channel}")
    public ChannelResponse<QueryJzfwInfoRes> queryJzfwInfo(@PathVariable Channel channel,
                                                           @RequestBody QueryJzfwInfoReq req) {
        return facade.queryJzfwInfo(channel, req);
    }

    @PostMapping("/cross-region-single-query/{channel}")
    public ChannelResponse<CrossRegionSingleRes> crossRegionSingleQuery(@PathVariable Channel channel,
                                                                        @RequestBody CrossRegionSingleReq req) {
        return facade.crossRegionSingleQuery(channel, req);
    }

    @PostMapping("/cross-region-batch-query/{channel}")
    public ChannelResponse<CrossRegionBatchRes> crossRegionBatchQuery(@PathVariable Channel channel,
                                                                      @RequestBody CrossRegionBatchReq req) {
        return facade.crossRegionBatchQuery(channel, req);
    }

    @PostMapping("/query-diff-tax-code/{channel}")
    public ChannelResponse<QueryDiffTaxCodeRes> queryDiffTaxCode(@PathVariable Channel channel,
                                                                 @RequestBody QueryDiffTaxCodeReq req) {
        return facade.queryDiffTaxCode(channel, req);
    }

    @PostMapping("/query-seller-block/{channel}")
    public ChannelResponse<QuerySellerBlockRes> querySellerBlock(@PathVariable Channel channel,
                                                                 @RequestBody QuerySellerBlockReq req) {
        return facade.querySellerBlock(channel, req);
    }

    @PostMapping("/query-esc-exchangeable-batch/{channel}")
    public ChannelResponse<QueryEscExchangeableBatchRes> queryEscExchangeableBatch(@PathVariable Channel channel,
                                                                                   @RequestBody QueryEscExchangeableBatchReq req) {
        return facade.queryEscExchangeableBatch(channel, req);
    }

    @PostMapping("/query-esc-exchange-info/{channel}")
    public ChannelResponse<QueryEscExchangeInfoRes> queryEscExchangeInfo(@PathVariable Channel channel,
                                                                         @RequestBody QueryEscExchangeInfoReq req) {
        return facade.queryEscExchangeInfo(channel, req);
    }

    @PostMapping("/upload-esc-invoice/{channel}")
    public ChannelResponse<UploadEscInvoiceRes> uploadEscInvoice(@PathVariable Channel channel,
                                                                 @RequestBody EscUploadInvoiceReq req) {
        return facade.uploadEscInvoice(channel, req);
    }

    @PostMapping("/query-natural-person-invoicing/{channel}")
    public ChannelResponse<QueryNaturalPersonInvoicingRes> queryNaturalPersonInvoicing(@PathVariable Channel channel,
                                                                                       @RequestBody QueryNaturalPersonInvoicingReq req) {
        return facade.queryNaturalPersonInvoicing(channel, req);
    }

    @PostMapping("/query-cpy-tax-category/{channel}")
    public ChannelResponse<QueryTaxCategoryRes> queryCpyTaxCategory(@PathVariable Channel channel,
                                                                    @RequestBody CpyQueryTaxCategoryReq req) {
        return facade.queryCpyTaxCategory(channel, req);
    }

    @PostMapping("/query-cpy-inventory/{channel}")
    public ChannelResponse<CpyQueryInventoryRes> queryCpyInventory(@PathVariable Channel channel,
                                                                   @RequestBody CpyQueryInventoryReq req) {
        return facade.queryCpyInventory(channel, req);
    }

    @PostMapping("/download-or-return-cpy-inventory/{channel}")
    public ChannelResponse<CpyDownloadOrReturnInventoryRes> downloadOrReturnCpyInventory(@PathVariable Channel channel,
                                                                                         @RequestBody CpyDownloadOrReturnInventoryReq req) {
        return facade.downloadOrReturnCpyInventory(channel, req);
    }
}
