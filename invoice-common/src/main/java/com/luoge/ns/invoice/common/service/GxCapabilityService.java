package com.luoge.ns.invoice.common.service;

import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.Channel;
import com.luoge.ns.invoice.common.model.ChannelResponse;

/**
 * 增值税抵扣勾选（乐企能力 203065）统一能力接口（契约层，定义在 common）。
 *
 * <p>与 {@link InvoiceCapabilityService}（开票能力）、{@code GjCapabilityService}（归集能力）并列、互不影响：
 * 抵扣勾选是独立的业务域，不复用开票/归集能力路由。共 27 个接口，覆盖税款所属期与统计状态查询、
 * 发票/海关缴款书/代扣代缴完税凭证的抵扣勾选上传与结果查询、统计申请与确认、未勾选数据初始化、
 * 农产品发票确认与补录、机构汇总勾选、属期变更与注销等。leqi 与 rpa 各自实现本接口，
 * app 门面 {@code GxFacade} 按 Channel 枚举选择实现。
 */
public interface GxCapabilityService {

    /**
     * 返回本实现所属渠道（LEQI / RPA）。
     */
    Channel channel();

    /**
     * 1. 获取当前税款所属期与当期税款所属期统计状态（HQDQSKSSQYDQSKSSQTJZT）。
     */
    ChannelResponse<GxGetCurPeriodAndStatRes> getCurPeriodAndStat(GxGetCurPeriodAndStatReq req);

    /**
     * 2. 批量上传抵扣发票（PLFPDKGX）。
     */
    ChannelResponse<GxBatchUploadDeductionInvoiceRes> batchUploadDeductionInvoice(GxBatchUploadDeductionInvoiceReq req);

    /**
     * 3. 查询发票抵扣勾选处理结果（CXFPDKGXCLJG）。
     */
    ChannelResponse<GxQueryInvoiceDeductionResultRes> queryInvoiceDeductionResult(GxQueryInvoiceDeductionResultReq req);

    /**
     * 4. 批量上传海关缴款书抵扣勾选（PLHGJKSDKGX）。
     */
    ChannelResponse<GxBatchUploadCustomsDeductionRes> batchUploadCustomsDeduction(GxBatchUploadCustomsDeductionReq req);

    /**
     * 5. 查询海关缴款书抵扣勾选处理结果（CXHGJKSDKGXCLJG）。
     */
    ChannelResponse<GxQueryCustomsDeductionResultRes> queryCustomsDeductionResult(GxQueryCustomsDeductionResultReq req);

    /**
     * 6. 申请抵扣统计（SQCXDKTJ）。
     */
    ChannelResponse<GxApplyDeductionStatRes> applyDeductionStat(GxApplyDeductionStatReq req);

    /**
     * 7. 查询申请统计处理结果（CXTJCLJG）。
     */
    ChannelResponse<GxQueryStatProcessResultRes> queryStatProcessResult(GxQueryStatProcessResultReq req);

    /**
     * 8. 申请确认抵扣统计（SQQRDKTJ）。
     */
    ChannelResponse<GxApplyConfirmDeductionStatRes> applyConfirmDeductionStat(GxApplyConfirmDeductionStatReq req);

    /**
     * 9. 查询确认统计处理结果（CXQRTJCLJG）。
     */
    ChannelResponse<GxQueryConfirmStatResultRes> queryConfirmStatResult(GxQueryConfirmStatResultReq req);

    /**
     * 10. 获取税款所属期发票抵扣勾选处理结果（HQDQSKSSQFPDKGXCLJG）。
     */
    ChannelResponse<GxGetPeriodInvoiceDeductionResultRes> getPeriodInvoiceDeductionResult(GxGetPeriodInvoiceDeductionResultReq req);

    /**
     * 11. 获取税款所属期海关缴款书抵扣勾选处理结果（HQDQSKSSQHGJKSDKGXCLJG）。
     */
    ChannelResponse<GxGetPeriodCustomsDeductionResultRes> getPeriodCustomsDeductionResult(GxGetPeriodCustomsDeductionResultReq req);

    /**
     * 12. 批量上传增值税代扣代缴完税凭证抵扣勾选（PLSCZZSDKDJWSPZDKGX）。
     */
    ChannelResponse<GxBatchUploadVatWithholdDeductionRes> batchUploadVatWithholdDeduction(GxBatchUploadVatWithholdDeductionReq req);

    /**
     * 13. 查询增值税代扣代缴完税凭证抵扣勾选处理结果（CXZZSDKDJWSPZDKGXCLJG）。
     */
    ChannelResponse<GxQueryVatWithholdDeductionResultRes> queryVatWithholdDeductionResult(GxQueryVatWithholdDeductionResultReq req);

    /**
     * 14. 获取税款所属期代扣代缴完税凭证抵扣勾选处理结果（HQDQSKSSQDKDJWSPZDKGXCLJG）。
     */
    ChannelResponse<GxGetPeriodWithholdDeductionResultRes> getPeriodWithholdDeductionResult(GxGetPeriodWithholdDeductionResultReq req);

    /**
     * 15. 未勾选数据初始化清单下载申请（WGXSJCSHQDXZSQDKGX）。
     */
    ChannelResponse<GxUnselectedDataInitDownloadApplyRes> unselectedDataInitDownloadApply(GxUnselectedDataInitDownloadApplyReq req);

    /**
     * 16. 未勾选数据初始化清单下载申请反馈（WGXSJCSHQDXZSQFKDKGX）。
     */
    ChannelResponse<GxUnselectedDataInitDownloadApplyFeedbackRes> unselectedDataInitDownloadApplyFeedback(GxUnselectedDataInitDownloadApplyFeedbackReq req);

    /**
     * 17. 批量上传待处理农产品发票确认清单（PLSCDCLNCPFPQRQD）。
     */
    ChannelResponse<GxBatchUploadAgriInvoiceConfirmListRes> batchUploadAgriInvoiceConfirmList(GxBatchUploadAgriInvoiceConfirmListReq req);

    /**
     * 18. 查询批量上传待处理农产品发票确认清单处理结果（CXPLSCDCLNCPFPQRQDCLJG）。
     */
    ChannelResponse<GxQueryAgriInvoiceConfirmListResultRes> queryAgriInvoiceConfirmListResult(GxQueryAgriInvoiceConfirmListResultReq req);

    /**
     * 19. 批量上传税务机关代开农产品发票补录信息（PLSCSWJGDKNCPFPBLXX）。
     */
    ChannelResponse<GxBatchUploadTaxAuthAgriInvoiceSupplementRes> batchUploadTaxAuthAgriInvoiceSupplement(GxBatchUploadTaxAuthAgriInvoiceSupplementReq req);

    /**
     * 20. 查询批量上传税务机关代开农产品发票补录信息处理结果（CXPLSCSWJGDKNCPFPBLXXCLJG）。
     */
    ChannelResponse<GxQueryTaxAuthAgriInvoiceSupplementResultRes> queryTaxAuthAgriInvoiceSupplementResult(GxQueryTaxAuthAgriInvoiceSupplementResultReq req);

    /**
     * 21. 查询发票农产品商品编码列表（CXFPNCPSPBMLB）。
     */
    ChannelResponse<GxQueryInvoiceAgriGoodsCodeListRes> queryInvoiceAgriGoodsCodeList(GxQueryInvoiceAgriGoodsCodeListReq req);

    /**
     * 22. 汇总纳税人机构列表查询（HZNSRJGLBCX）。
     */
    ChannelResponse<GxQuerySummaryTaxpayerOrgListRes> querySummaryTaxpayerOrgList(GxQuerySummaryTaxpayerOrgListReq req);

    /**
     * 23. 总分机构汇总勾选确认（ZFJGHZGXQR）。
     */
    ChannelResponse<GxBranchSummaryDeductionConfirmRes> branchSummaryDeductionConfirm(GxBranchSummaryDeductionConfirmReq req);

    /**
     * 24. 申请税款所属期变更（SQSKSSQBG）。
     */
    ChannelResponse<GxApplyPeriodChangeRes> applyPeriodChange(GxApplyPeriodChangeReq req);

    /**
     * 25. 申请注销勾选（SQZXGX）。
     */
    ChannelResponse<GxApplyCancelDeductionRes> applyCancelDeduction(GxApplyCancelDeductionReq req);

    /**
     * 26. 刷新税款所属期（SXSKSSQ）。
     */
    ChannelResponse<GxRefreshTaxPeriodRes> refreshTaxPeriod(GxRefreshTaxPeriodReq req);

    /**
     * 27. 获取追溯期税款所属期与追溯期税款所属期统计状态（HQZSSKSSQYZSSKSSQTJZT）。
     */
    ChannelResponse<GxGetTracePeriodAndStatRes> getTracePeriodAndStat(GxGetTracePeriodAndStatReq req);

}
