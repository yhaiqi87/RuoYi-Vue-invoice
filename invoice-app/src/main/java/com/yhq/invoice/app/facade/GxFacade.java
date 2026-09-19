package com.yhq.invoice.app.facade;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.exception.InvoiceException;
import com.yhq.invoice.common.model.ChannelResponse;
import com.yhq.invoice.common.service.GxCapabilityService;
import com.yhq.invoice.leqi.service.LeqiGxService;
import com.yhq.invoice.rpa.service.RpaGxService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * app 抵扣勾选门面层：按 Channel 枚举参数选择对应渠道实现（leqi / rpa）。
 *
 * <p>与 {@link InvoiceFacade}（开票能力）、{@code GjFacade}（归集能力）并列、互不影响：仅做「选渠道」这一层判断，
 * 抵扣勾选能力的 27 个接口全部在此委托给 {@link GxCapabilityService} 实现。门面直接持有 RpaGxService 与
 * LeqiGxService 两个渠道实现，按 channel() 建立路由表。
 */
@Service
public class GxFacade {

    private final Map<Channel, GxCapabilityService> services;

    /**
     * 注入两个渠道实现并构建 Channel -> 渠道服务的路由表。
     */
    public GxFacade(RpaGxService rpaGxService, LeqiGxService leqiGxService) {
        this.services = Map.of(
                rpaGxService.channel(), rpaGxService,
                leqiGxService.channel(), leqiGxService);
    }

    /**
     * 按 Channel 选出对应的渠道服务；无匹配实现时抛异常。
     */
    private GxCapabilityService select(Channel channel) {
        GxCapabilityService svc = services.get(channel);
        if (svc == null) {
            throw new InvoiceException("无对应渠道实现: " + channel);
        }
        return svc;
    }

    /**
     * 获取当前税款所属期与当期税款所属期统计状态：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxGetCurPeriodAndStatRes> getCurPeriodAndStat(Channel channel, GxGetCurPeriodAndStatReq req) {
        return select(channel).getCurPeriodAndStat(req);
    }

    /**
     * 批量上传抵扣发票：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxBatchUploadDeductionInvoiceRes> batchUploadDeductionInvoice(Channel channel, GxBatchUploadDeductionInvoiceReq req) {
        return select(channel).batchUploadDeductionInvoice(req);
    }

    /**
     * 查询发票抵扣勾选处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxQueryInvoiceDeductionResultRes> queryInvoiceDeductionResult(Channel channel, GxQueryInvoiceDeductionResultReq req) {
        return select(channel).queryInvoiceDeductionResult(req);
    }

    /**
     * 批量上传海关缴款书抵扣勾选：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxBatchUploadCustomsDeductionRes> batchUploadCustomsDeduction(Channel channel, GxBatchUploadCustomsDeductionReq req) {
        return select(channel).batchUploadCustomsDeduction(req);
    }

    /**
     * 查询海关缴款书抵扣勾选处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxQueryCustomsDeductionResultRes> queryCustomsDeductionResult(Channel channel, GxQueryCustomsDeductionResultReq req) {
        return select(channel).queryCustomsDeductionResult(req);
    }

    /**
     * 申请抵扣统计：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxApplyDeductionStatRes> applyDeductionStat(Channel channel, GxApplyDeductionStatReq req) {
        return select(channel).applyDeductionStat(req);
    }

    /**
     * 查询申请统计处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxQueryStatProcessResultRes> queryStatProcessResult(Channel channel, GxQueryStatProcessResultReq req) {
        return select(channel).queryStatProcessResult(req);
    }

    /**
     * 申请确认抵扣统计：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxApplyConfirmDeductionStatRes> applyConfirmDeductionStat(Channel channel, GxApplyConfirmDeductionStatReq req) {
        return select(channel).applyConfirmDeductionStat(req);
    }

    /**
     * 查询确认统计处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxQueryConfirmStatResultRes> queryConfirmStatResult(Channel channel, GxQueryConfirmStatResultReq req) {
        return select(channel).queryConfirmStatResult(req);
    }

    /**
     * 获取税款所属期发票抵扣勾选处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxGetPeriodInvoiceDeductionResultRes> getPeriodInvoiceDeductionResult(Channel channel, GxGetPeriodInvoiceDeductionResultReq req) {
        return select(channel).getPeriodInvoiceDeductionResult(req);
    }

    /**
     * 获取税款所属期海关缴款书抵扣勾选处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxGetPeriodCustomsDeductionResultRes> getPeriodCustomsDeductionResult(Channel channel, GxGetPeriodCustomsDeductionResultReq req) {
        return select(channel).getPeriodCustomsDeductionResult(req);
    }

    /**
     * 批量上传增值税代扣代缴完税凭证抵扣勾选：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxBatchUploadVatWithholdDeductionRes> batchUploadVatWithholdDeduction(Channel channel, GxBatchUploadVatWithholdDeductionReq req) {
        return select(channel).batchUploadVatWithholdDeduction(req);
    }

    /**
     * 查询增值税代扣代缴完税凭证抵扣勾选处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxQueryVatWithholdDeductionResultRes> queryVatWithholdDeductionResult(Channel channel, GxQueryVatWithholdDeductionResultReq req) {
        return select(channel).queryVatWithholdDeductionResult(req);
    }

    /**
     * 获取税款所属期代扣代缴完税凭证抵扣勾选处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxGetPeriodWithholdDeductionResultRes> getPeriodWithholdDeductionResult(Channel channel, GxGetPeriodWithholdDeductionResultReq req) {
        return select(channel).getPeriodWithholdDeductionResult(req);
    }

    /**
     * 未勾选数据初始化清单下载申请：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxUnselectedDataInitDownloadApplyRes> unselectedDataInitDownloadApply(Channel channel, GxUnselectedDataInitDownloadApplyReq req) {
        return select(channel).unselectedDataInitDownloadApply(req);
    }

    /**
     * 未勾选数据初始化清单下载申请反馈：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxUnselectedDataInitDownloadApplyFeedbackRes> unselectedDataInitDownloadApplyFeedback(Channel channel, GxUnselectedDataInitDownloadApplyFeedbackReq req) {
        return select(channel).unselectedDataInitDownloadApplyFeedback(req);
    }

    /**
     * 批量上传待处理农产品发票确认清单：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxBatchUploadAgriInvoiceConfirmListRes> batchUploadAgriInvoiceConfirmList(Channel channel, GxBatchUploadAgriInvoiceConfirmListReq req) {
        return select(channel).batchUploadAgriInvoiceConfirmList(req);
    }

    /**
     * 查询批量上传待处理农产品发票确认清单处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxQueryAgriInvoiceConfirmListResultRes> queryAgriInvoiceConfirmListResult(Channel channel, GxQueryAgriInvoiceConfirmListResultReq req) {
        return select(channel).queryAgriInvoiceConfirmListResult(req);
    }

    /**
     * 批量上传税务机关代开农产品发票补录信息：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxBatchUploadTaxAuthAgriInvoiceSupplementRes> batchUploadTaxAuthAgriInvoiceSupplement(Channel channel, GxBatchUploadTaxAuthAgriInvoiceSupplementReq req) {
        return select(channel).batchUploadTaxAuthAgriInvoiceSupplement(req);
    }

    /**
     * 查询批量上传税务机关代开农产品发票补录信息处理结果：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxQueryTaxAuthAgriInvoiceSupplementResultRes> queryTaxAuthAgriInvoiceSupplementResult(Channel channel, GxQueryTaxAuthAgriInvoiceSupplementResultReq req) {
        return select(channel).queryTaxAuthAgriInvoiceSupplementResult(req);
    }

    /**
     * 查询发票农产品商品编码列表：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxQueryInvoiceAgriGoodsCodeListRes> queryInvoiceAgriGoodsCodeList(Channel channel, GxQueryInvoiceAgriGoodsCodeListReq req) {
        return select(channel).queryInvoiceAgriGoodsCodeList(req);
    }

    /**
     * 汇总纳税人机构列表查询：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxQuerySummaryTaxpayerOrgListRes> querySummaryTaxpayerOrgList(Channel channel, GxQuerySummaryTaxpayerOrgListReq req) {
        return select(channel).querySummaryTaxpayerOrgList(req);
    }

    /**
     * 总分机构汇总勾选确认：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxBranchSummaryDeductionConfirmRes> branchSummaryDeductionConfirm(Channel channel, GxBranchSummaryDeductionConfirmReq req) {
        return select(channel).branchSummaryDeductionConfirm(req);
    }

    /**
     * 申请税款所属期变更：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxApplyPeriodChangeRes> applyPeriodChange(Channel channel, GxApplyPeriodChangeReq req) {
        return select(channel).applyPeriodChange(req);
    }

    /**
     * 申请注销勾选：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxApplyCancelDeductionRes> applyCancelDeduction(Channel channel, GxApplyCancelDeductionReq req) {
        return select(channel).applyCancelDeduction(req);
    }

    /**
     * 刷新税款所属期：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxRefreshTaxPeriodRes> refreshTaxPeriod(Channel channel, GxRefreshTaxPeriodReq req) {
        return select(channel).refreshTaxPeriod(req);
    }

    /**
     * 获取追溯期税款所属期与追溯期税款所属期统计状态：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<GxGetTracePeriodAndStatRes> getTracePeriodAndStat(Channel channel, GxGetTracePeriodAndStatReq req) {
        return select(channel).getTracePeriodAndStat(req);
    }

}
