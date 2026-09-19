package com.yhq.invoice.leqi.service;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.enums.GxInterfaceCode;
import com.yhq.invoice.common.model.ChannelResponse;
import com.yhq.invoice.common.service.GxCapabilityService;
import com.yhq.invoice.leqi.annotation.DocVersion;
import com.yhq.invoice.leqi.annotation.ValidateReq;
import com.yhq.invoice.leqi.client.LeqiHttpUtil;
import org.springframework.stereotype.Service;

/**
 * 乐企（LEQI）增值税抵扣勾选能力服务：实现抵扣勾选统一契约 {@link GxCapabilityService} 的全部 27 个接口。
 *
 * <p>与开票能力完全隔离：本类<b>不</b>实现 {@code LeqiCapabilityService}，故不会进入 {@code LeqiInvoiceService}
 * 的开票能力路由表，也不会污染 BASE/SALE/LEASE 等开票实现。每个方法直接走通桩链路
 * {@code httpUtil.call(serviceCode, req, Res.class)}，后续校验/字段处理加在对应方法内，不影响其他能力。
 */
@Service
@ValidateReq
@DocVersion(doc = "乐企增值税抵扣勾选能力说明文档", value = "V3.025")
public class LeqiGxService implements GxCapabilityService {

    private final LeqiHttpUtil httpUtil;

    public LeqiGxService(LeqiHttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Override
    public Channel channel() {
        return Channel.LEQI;
    }

    /**
     * 获取当前税款所属期与当期税款所属期统计状态（HQDQSKSSQYDQSKSSQTJZT）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxGetCurPeriodAndStatRes> getCurPeriodAndStat(GxGetCurPeriodAndStatReq req) {
        return httpUtil.call(GxInterfaceCode.GET_CUR_PERIOD_AND_STAT.getServiceCode(), req, GxGetCurPeriodAndStatRes.class);
    }

    /**
     * 批量上传抵扣发票（PLFPDKGX）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxBatchUploadDeductionInvoiceRes> batchUploadDeductionInvoice(GxBatchUploadDeductionInvoiceReq req) {
        return httpUtil.call(GxInterfaceCode.BATCH_UPLOAD_DEDUCTION_INVOICE.getServiceCode(), req, GxBatchUploadDeductionInvoiceRes.class);
    }

    /**
     * 查询发票抵扣勾选处理结果（CXFPDKGXCLJG）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxQueryInvoiceDeductionResultRes> queryInvoiceDeductionResult(GxQueryInvoiceDeductionResultReq req) {
        return httpUtil.call(GxInterfaceCode.QUERY_INVOICE_DEDUCTION_RESULT.getServiceCode(), req, GxQueryInvoiceDeductionResultRes.class);
    }

    /**
     * 批量上传海关缴款书抵扣勾选（PLHGJKSDKGX）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxBatchUploadCustomsDeductionRes> batchUploadCustomsDeduction(GxBatchUploadCustomsDeductionReq req) {
        return httpUtil.call(GxInterfaceCode.BATCH_UPLOAD_CUSTOMS_DEDUCTION.getServiceCode(), req, GxBatchUploadCustomsDeductionRes.class);
    }

    /**
     * 查询海关缴款书抵扣勾选处理结果（CXHGJKSDKGXCLJG）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxQueryCustomsDeductionResultRes> queryCustomsDeductionResult(GxQueryCustomsDeductionResultReq req) {
        return httpUtil.call(GxInterfaceCode.QUERY_CUSTOMS_DEDUCTION_RESULT.getServiceCode(), req, GxQueryCustomsDeductionResultRes.class);
    }

    /**
     * 申请抵扣统计（SQCXDKTJ）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxApplyDeductionStatRes> applyDeductionStat(GxApplyDeductionStatReq req) {
        return httpUtil.call(GxInterfaceCode.APPLY_DEDUCTION_STAT.getServiceCode(), req, GxApplyDeductionStatRes.class);
    }

    /**
     * 查询申请统计处理结果（CXTJCLJG）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxQueryStatProcessResultRes> queryStatProcessResult(GxQueryStatProcessResultReq req) {
        return httpUtil.call(GxInterfaceCode.QUERY_STAT_PROCESS_RESULT.getServiceCode(), req, GxQueryStatProcessResultRes.class);
    }

    /**
     * 申请确认抵扣统计（SQQRDKTJ）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxApplyConfirmDeductionStatRes> applyConfirmDeductionStat(GxApplyConfirmDeductionStatReq req) {
        return httpUtil.call(GxInterfaceCode.APPLY_CONFIRM_DEDUCTION_STAT.getServiceCode(), req, GxApplyConfirmDeductionStatRes.class);
    }

    /**
     * 查询确认统计处理结果（CXQRTJCLJG）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxQueryConfirmStatResultRes> queryConfirmStatResult(GxQueryConfirmStatResultReq req) {
        return httpUtil.call(GxInterfaceCode.QUERY_CONFIRM_STAT_RESULT.getServiceCode(), req, GxQueryConfirmStatResultRes.class);
    }

    /**
     * 获取税款所属期发票抵扣勾选处理结果（HQDQSKSSQFPDKGXCLJG）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxGetPeriodInvoiceDeductionResultRes> getPeriodInvoiceDeductionResult(GxGetPeriodInvoiceDeductionResultReq req) {
        return httpUtil.call(GxInterfaceCode.GET_PERIOD_INVOICE_DEDUCTION_RESULT.getServiceCode(), req, GxGetPeriodInvoiceDeductionResultRes.class);
    }

    /**
     * 获取税款所属期海关缴款书抵扣勾选处理结果（HQDQSKSSQHGJKSDKGXCLJG）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxGetPeriodCustomsDeductionResultRes> getPeriodCustomsDeductionResult(GxGetPeriodCustomsDeductionResultReq req) {
        return httpUtil.call(GxInterfaceCode.GET_PERIOD_CUSTOMS_DEDUCTION_RESULT.getServiceCode(), req, GxGetPeriodCustomsDeductionResultRes.class);
    }

    /**
     * 批量上传增值税代扣代缴完税凭证抵扣勾选（PLSCZZSDKDJWSPZDKGX）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxBatchUploadVatWithholdDeductionRes> batchUploadVatWithholdDeduction(GxBatchUploadVatWithholdDeductionReq req) {
        return httpUtil.call(GxInterfaceCode.BATCH_UPLOAD_VAT_WITHHOLD_DEDUCTION.getServiceCode(), req, GxBatchUploadVatWithholdDeductionRes.class);
    }

    /**
     * 查询增值税代扣代缴完税凭证抵扣勾选处理结果（CXZZSDKDJWSPZDKGXCLJG）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxQueryVatWithholdDeductionResultRes> queryVatWithholdDeductionResult(GxQueryVatWithholdDeductionResultReq req) {
        return httpUtil.call(GxInterfaceCode.QUERY_VAT_WITHHOLD_DEDUCTION_RESULT.getServiceCode(), req, GxQueryVatWithholdDeductionResultRes.class);
    }

    /**
     * 获取税款所属期代扣代缴完税凭证抵扣勾选处理结果（HQDQSKSSQDKDJWSPZDKGXCLJG）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxGetPeriodWithholdDeductionResultRes> getPeriodWithholdDeductionResult(GxGetPeriodWithholdDeductionResultReq req) {
        return httpUtil.call(GxInterfaceCode.GET_PERIOD_WITHHOLD_DEDUCTION_RESULT.getServiceCode(), req, GxGetPeriodWithholdDeductionResultRes.class);
    }

    /**
     * 未勾选数据初始化清单下载申请（WGXSJCSHQDXZSQDKGX）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxUnselectedDataInitDownloadApplyRes> unselectedDataInitDownloadApply(GxUnselectedDataInitDownloadApplyReq req) {
        return httpUtil.call(GxInterfaceCode.UNSELECTED_DATA_INIT_DOWNLOAD_APPLY.getServiceCode(), req, GxUnselectedDataInitDownloadApplyRes.class);
    }

    /**
     * 未勾选数据初始化清单下载申请反馈（WGXSJCSHQDXZSQFKDKGX）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxUnselectedDataInitDownloadApplyFeedbackRes> unselectedDataInitDownloadApplyFeedback(GxUnselectedDataInitDownloadApplyFeedbackReq req) {
        return httpUtil.call(GxInterfaceCode.UNSELECTED_DATA_INIT_DOWNLOAD_APPLY_FEEDBACK.getServiceCode(), req, GxUnselectedDataInitDownloadApplyFeedbackRes.class);
    }

    /**
     * 批量上传待处理农产品发票确认清单（PLSCDCLNCPFPQRQD）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxBatchUploadAgriInvoiceConfirmListRes> batchUploadAgriInvoiceConfirmList(GxBatchUploadAgriInvoiceConfirmListReq req) {
        return httpUtil.call(GxInterfaceCode.BATCH_UPLOAD_AGRI_INVOICE_CONFIRM_LIST.getServiceCode(), req, GxBatchUploadAgriInvoiceConfirmListRes.class);
    }

    /**
     * 查询批量上传待处理农产品发票确认清单处理结果（CXPLSCDCLNCPFPQRQDCLJG）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxQueryAgriInvoiceConfirmListResultRes> queryAgriInvoiceConfirmListResult(GxQueryAgriInvoiceConfirmListResultReq req) {
        return httpUtil.call(GxInterfaceCode.QUERY_AGRI_INVOICE_CONFIRM_LIST_RESULT.getServiceCode(), req, GxQueryAgriInvoiceConfirmListResultRes.class);
    }

    /**
     * 批量上传税务机关代开农产品发票补录信息（PLSCSWJGDKNCPFPBLXX）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxBatchUploadTaxAuthAgriInvoiceSupplementRes> batchUploadTaxAuthAgriInvoiceSupplement(GxBatchUploadTaxAuthAgriInvoiceSupplementReq req) {
        return httpUtil.call(GxInterfaceCode.BATCH_UPLOAD_TAX_AUTH_AGRI_INVOICE_SUPPLEMENT.getServiceCode(), req, GxBatchUploadTaxAuthAgriInvoiceSupplementRes.class);
    }

    /**
     * 查询批量上传税务机关代开农产品发票补录信息处理结果（CXPLSCSWJGDKNCPFPBLXXCLJG）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxQueryTaxAuthAgriInvoiceSupplementResultRes> queryTaxAuthAgriInvoiceSupplementResult(GxQueryTaxAuthAgriInvoiceSupplementResultReq req) {
        return httpUtil.call(GxInterfaceCode.QUERY_TAX_AUTH_AGRI_INVOICE_SUPPLEMENT_RESULT.getServiceCode(), req, GxQueryTaxAuthAgriInvoiceSupplementResultRes.class);
    }

    /**
     * 查询发票农产品商品编码列表（CXFPNCPSPBMLB）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxQueryInvoiceAgriGoodsCodeListRes> queryInvoiceAgriGoodsCodeList(GxQueryInvoiceAgriGoodsCodeListReq req) {
        return httpUtil.call(GxInterfaceCode.QUERY_INVOICE_AGRI_GOODS_CODE_LIST.getServiceCode(), req, GxQueryInvoiceAgriGoodsCodeListRes.class);
    }

    /**
     * 汇总纳税人机构列表查询（HZNSRJGLBCX）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxQuerySummaryTaxpayerOrgListRes> querySummaryTaxpayerOrgList(GxQuerySummaryTaxpayerOrgListReq req) {
        return httpUtil.call(GxInterfaceCode.QUERY_SUMMARY_TAXPAYER_ORG_LIST.getServiceCode(), req, GxQuerySummaryTaxpayerOrgListRes.class);
    }

    /**
     * 总分机构汇总勾选确认（ZFJGHZGXQR）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxBranchSummaryDeductionConfirmRes> branchSummaryDeductionConfirm(GxBranchSummaryDeductionConfirmReq req) {
        return httpUtil.call(GxInterfaceCode.BRANCH_SUMMARY_DEDUCTION_CONFIRM.getServiceCode(), req, GxBranchSummaryDeductionConfirmRes.class);
    }

    /**
     * 申请税款所属期变更（SQSKSSQBG）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxApplyPeriodChangeRes> applyPeriodChange(GxApplyPeriodChangeReq req) {
        return httpUtil.call(GxInterfaceCode.APPLY_PERIOD_CHANGE.getServiceCode(), req, GxApplyPeriodChangeRes.class);
    }

    /**
     * 申请注销勾选（SQZXGX）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxApplyCancelDeductionRes> applyCancelDeduction(GxApplyCancelDeductionReq req) {
        return httpUtil.call(GxInterfaceCode.APPLY_CANCEL_DEDUCTION.getServiceCode(), req, GxApplyCancelDeductionRes.class);
    }

    /**
     * 刷新税款所属期（SXSKSSQ）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxRefreshTaxPeriodRes> refreshTaxPeriod(GxRefreshTaxPeriodReq req) {
        return httpUtil.call(GxInterfaceCode.REFRESH_TAX_PERIOD.getServiceCode(), req, GxRefreshTaxPeriodRes.class);
    }

    /**
     * 获取追溯期税款所属期与追溯期税款所属期统计状态（HQZSSKSSQYZSSKSSQTJZT）：调用乐企抵扣勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GxGetTracePeriodAndStatRes> getTracePeriodAndStat(GxGetTracePeriodAndStatReq req) {
        return httpUtil.call(GxInterfaceCode.GET_TRACE_PERIOD_AND_STAT.getServiceCode(), req, GxGetTracePeriodAndStatRes.class);
    }

}
