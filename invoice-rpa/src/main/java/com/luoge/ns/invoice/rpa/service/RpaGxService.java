package com.luoge.ns.invoice.rpa.service;

import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.Channel;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import com.luoge.ns.invoice.common.service.GxCapabilityService;
import org.springframework.stereotype.Service;

/**
 * rpa 渠道抵扣勾选实现：门面按 Channel=RPA 选中本服务。
 *
 * <p>按需求，rpa 渠道暂不对抵扣勾选能力做任何实现，全部方法统一抛出 UnsupportedOperationException 占位，
 * 待后续接入 RPA 流程时再逐接口实现。本类与 {@code RpaInvoiceService}（开票能力）并列，
 * 不影响开票能力的 rpa 占位实现。
 */
@Service
public class RpaGxService implements GxCapabilityService {

    @Override
    public Channel channel() {
        return Channel.RPA;
    }

    /**
     * 获取当前税款所属期与当期税款所属期统计状态（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxGetCurPeriodAndStatRes> getCurPeriodAndStat(GxGetCurPeriodAndStatReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 批量上传抵扣发票（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxBatchUploadDeductionInvoiceRes> batchUploadDeductionInvoice(GxBatchUploadDeductionInvoiceReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 查询发票抵扣勾选处理结果（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxQueryInvoiceDeductionResultRes> queryInvoiceDeductionResult(GxQueryInvoiceDeductionResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 批量上传海关缴款书抵扣勾选（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxBatchUploadCustomsDeductionRes> batchUploadCustomsDeduction(GxBatchUploadCustomsDeductionReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 查询海关缴款书抵扣勾选处理结果（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxQueryCustomsDeductionResultRes> queryCustomsDeductionResult(GxQueryCustomsDeductionResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 申请抵扣统计（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxApplyDeductionStatRes> applyDeductionStat(GxApplyDeductionStatReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 查询申请统计处理结果（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxQueryStatProcessResultRes> queryStatProcessResult(GxQueryStatProcessResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 申请确认抵扣统计（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxApplyConfirmDeductionStatRes> applyConfirmDeductionStat(GxApplyConfirmDeductionStatReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 查询确认统计处理结果（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxQueryConfirmStatResultRes> queryConfirmStatResult(GxQueryConfirmStatResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 获取税款所属期发票抵扣勾选处理结果（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxGetPeriodInvoiceDeductionResultRes> getPeriodInvoiceDeductionResult(GxGetPeriodInvoiceDeductionResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 获取税款所属期海关缴款书抵扣勾选处理结果（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxGetPeriodCustomsDeductionResultRes> getPeriodCustomsDeductionResult(GxGetPeriodCustomsDeductionResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 批量上传增值税代扣代缴完税凭证抵扣勾选（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxBatchUploadVatWithholdDeductionRes> batchUploadVatWithholdDeduction(GxBatchUploadVatWithholdDeductionReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 查询增值税代扣代缴完税凭证抵扣勾选处理结果（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxQueryVatWithholdDeductionResultRes> queryVatWithholdDeductionResult(GxQueryVatWithholdDeductionResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 获取税款所属期代扣代缴完税凭证抵扣勾选处理结果（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxGetPeriodWithholdDeductionResultRes> getPeriodWithholdDeductionResult(GxGetPeriodWithholdDeductionResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 未勾选数据初始化清单下载申请（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxUnselectedDataInitDownloadApplyRes> unselectedDataInitDownloadApply(GxUnselectedDataInitDownloadApplyReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 未勾选数据初始化清单下载申请反馈（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxUnselectedDataInitDownloadApplyFeedbackRes> unselectedDataInitDownloadApplyFeedback(GxUnselectedDataInitDownloadApplyFeedbackReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 批量上传待处理农产品发票确认清单（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxBatchUploadAgriInvoiceConfirmListRes> batchUploadAgriInvoiceConfirmList(GxBatchUploadAgriInvoiceConfirmListReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 查询批量上传待处理农产品发票确认清单处理结果（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxQueryAgriInvoiceConfirmListResultRes> queryAgriInvoiceConfirmListResult(GxQueryAgriInvoiceConfirmListResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 批量上传税务机关代开农产品发票补录信息（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxBatchUploadTaxAuthAgriInvoiceSupplementRes> batchUploadTaxAuthAgriInvoiceSupplement(GxBatchUploadTaxAuthAgriInvoiceSupplementReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 查询批量上传税务机关代开农产品发票补录信息处理结果（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxQueryTaxAuthAgriInvoiceSupplementResultRes> queryTaxAuthAgriInvoiceSupplementResult(GxQueryTaxAuthAgriInvoiceSupplementResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 查询发票农产品商品编码列表（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxQueryInvoiceAgriGoodsCodeListRes> queryInvoiceAgriGoodsCodeList(GxQueryInvoiceAgriGoodsCodeListReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 汇总纳税人机构列表查询（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxQuerySummaryTaxpayerOrgListRes> querySummaryTaxpayerOrgList(GxQuerySummaryTaxpayerOrgListReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 总分机构汇总勾选确认（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxBranchSummaryDeductionConfirmRes> branchSummaryDeductionConfirm(GxBranchSummaryDeductionConfirmReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 申请税款所属期变更（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxApplyPeriodChangeRes> applyPeriodChange(GxApplyPeriodChangeReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 申请注销勾选（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxApplyCancelDeductionRes> applyCancelDeduction(GxApplyCancelDeductionReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 刷新税款所属期（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxRefreshTaxPeriodRes> refreshTaxPeriod(GxRefreshTaxPeriodReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

    /**
     * 获取追溯期税款所属期与追溯期税款所属期统计状态（RPA）：渠道尚未实现抵扣勾选能力，抛 UnsupportedOperationException。
     */
    @Override
    public ChannelResponse<GxGetTracePeriodAndStatRes> getTracePeriodAndStat(GxGetTracePeriodAndStatReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现抵扣勾选能力");
    }

}
