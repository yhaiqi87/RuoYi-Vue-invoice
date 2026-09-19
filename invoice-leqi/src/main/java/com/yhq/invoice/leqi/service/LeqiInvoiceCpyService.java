package com.yhq.invoice.leqi.service;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.CapabilityCode;
import com.yhq.invoice.common.enums.CpyInterfaceCode;
import com.yhq.invoice.common.model.ChannelResponse;
import com.yhq.invoice.leqi.annotation.DocVersion;
import com.yhq.invoice.leqi.annotation.ValidateReq;
import com.yhq.invoice.leqi.client.LeqiHttpUtil;
import com.yhq.invoice.leqi.util.ReqValidation;
import org.springframework.stereotype.Service;

/**
 * 成品油（CPY）能力服务：承载成品油支持的全部概念方法。
 * 仅实现 leqi 内部契约 LeqiCapabilityService，不实现统一门面契约 InvoiceCapabilityService。
 * 真实接口使用 CpyInterfaceCode 枚举，走通桩链路。
 *
 * <p>能力矩阵（依据《乐企数字化电子发票（成品油）开票能力说明文档-V2.009》，能力编码 202055）：
 * <ul>
 *   <li>复用数电票通用概念：预赋码 / 额度 / 风险 / 基本信息 / 税率 / 分类编码 / 红字确认单 / 汇总确认 / 上传结果 /
 *       发票上传（复用通用 UploadInvoiceReq）/ 差额征税编码（成品油支持）；</li>
 *   <li>成品油专属 3 项：queryCpyTaxCategory（查询成品油可用税收分类编码信息，CXCPYKC）/
 *       queryCpyInventory（查询成品油库存，CXCPYKYSSFLBM）/ downloadOrReturnCpyInventory（下载或退回成品油库存，XZHTHCPYKC）；</li>
 *   <li>uploadInvoice 映射到成品油发票上传（QDFPSC_CPY），报文复用通用数电票上传超集；</li>
 *   <li>不支持 12 项：queryDeductionVoucher / queryTaxProService / queryDiffTaxAuth / queryHouseSource /
 *       queryJzfwInfo / crossRegionSingleQuery / crossRegionBatchQuery / querySellerBlock /
 *       queryEscExchangeableBatch / queryEscExchangeInfo / uploadEscInvoice / queryNaturalPersonInvoicing。</li>
 * </ul>
 *
 * <p>类级 {@code @ValidateReq} 与 BASE/SALE 一致，对全部入参执行 jakarta.validation 校验。
 */
@Service
@ValidateReq
@DocVersion(doc = "乐企数字化电子发票（成品油）开票能力说明文档", value = "V2.009")
public class LeqiInvoiceCpyService implements LeqiCapabilityService {

    private final LeqiHttpUtil httpUtil;

    public LeqiInvoiceCpyService(LeqiHttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Override
    public CapabilityCode capability() {
        return CapabilityCode.CPY;
    }

    /**
     * 获取发票批量预赋码信息（成品油）。真实实现：调用乐企接口 QDFPPLFM，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GetBatchPreCodeRes> getBatchPreCode(GetBatchPreCodeReq req) {
        return httpUtil.call(CpyInterfaceCode.GET_BATCH_PRE_CODE.getServiceCode(), req, GetBatchPreCodeRes.class);
    }

    /**
     * 查询授信额度（成品油）。真实实现：调用乐企接口 CXSXED，当前走通桩链路。
     */
    @Override
    public ChannelResponse<QueryQuotaRes> queryQuota(QueryQuotaReq req) {
        return httpUtil.call(CpyInterfaceCode.QUERY_QUOTA.getServiceCode(), req, QueryQuotaRes.class);
    }

    /**
     * 下载/退回授信额度（成品油）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(DownloadOrReturnQuotaReq req) {
        return httpUtil.call(CpyInterfaceCode.DOWNLOAD_OR_RETURN_QUOTA.getServiceCode(), req, DownloadOrReturnQuotaRes.class);
    }

    /**
     * 调整授信额度有效期（成品油）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(AdjustQuotaValidityReq req) {
        return httpUtil.call(CpyInterfaceCode.ADJUST_QUOTA_VALIDITY.getServiceCode(), req, AdjustQuotaValidityRes.class);
    }

    /**
     * 查询纳税人风险信息（成品油）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(QueryTaxpayerRiskReq req) {
        return httpUtil.call(CpyInterfaceCode.QUERY_TAXPAYER_RISK.getServiceCode(), req, QueryTaxpayerRiskRes.class);
    }

    /**
     * 查询纳税人基本信息（成品油）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(QueryTaxpayerBasicReq req) {
        return httpUtil.call(CpyInterfaceCode.QUERY_TAXPAYER_BASIC.getServiceCode(), req, QueryTaxpayerBasicRes.class);
    }

    /**
     * 查询可用税率信息（成品油）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxRateRes> queryTaxRate(QueryTaxRateReq req) {
        return httpUtil.call(CpyInterfaceCode.QUERY_TAX_RATE.getServiceCode(), req, QueryTaxRateRes.class);
    }

    /**
     * 查询税收分类编码信息（成品油）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(QueryTaxCategoryReq req) {
        return httpUtil.call(CpyInterfaceCode.QUERY_TAX_CATEGORY.getServiceCode(), req, QueryTaxCategoryRes.class);
    }

    /**
     * 查询红字确认单明细信息（成品油）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(QueryRedConfirmDetailReq req) {
        return httpUtil.call(CpyInterfaceCode.QUERY_RED_CONFIRM_DETAIL.getServiceCode(), req, QueryRedConfirmDetailRes.class);
    }

    /**
     * 成品油发票上传（成品油）。真实实现：调用乐企接口 QDFPSC_CPY，报文复用通用数电票上传超集。
     */
    @Override
    public ChannelResponse<UploadInvoiceRes> uploadInvoice(UploadInvoiceReq req) {
        ReqValidation.requireValid(req);
        return httpUtil.call(CpyInterfaceCode.UPLOAD_INVOICE.getServiceCode(), req, UploadInvoiceRes.class);
    }

    /**
     * 查询红字确认单列表信息（成品油）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(QueryRedConfirmListReq req) {
        return httpUtil.call(CpyInterfaceCode.QUERY_RED_CONFIRM_LIST.getServiceCode(), req, QueryRedConfirmListRes.class);
    }

    /**
     * 查询成品油发票上传结果（成品油）。真实实现：调用乐企对应接口 CXQDFPSCJG_CPY。
     */
    @Override
    public ChannelResponse<QueryUploadResultRes> queryUploadResult(QueryUploadResultReq req) {
        return httpUtil.call(CpyInterfaceCode.QUERY_UPLOAD_RESULT.getServiceCode(), req, QueryUploadResultRes.class);
    }

    /**
     * 成品油红字确认单申请（成品油）。真实实现：调用乐企对应接口 QDHZQRDSQ。
     */
    @Override
    public ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(ApplyRedConfirmReq req) {
        return httpUtil.call(CpyInterfaceCode.APPLY_RED_CONFIRM.getServiceCode(), req, ApplyRedConfirmRes.class);
    }

    /**
     * 成品油红字确认单确认（成品油）。真实实现：调用乐企对应接口 QDHZQRDQR。
     */
    @Override
    public ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(ConfirmRedConfirmReq req) {
        return httpUtil.call(CpyInterfaceCode.CONFIRM_RED_CONFIRM.getServiceCode(), req, ConfirmRedConfirmRes.class);
    }

    /**
     * 上传发票汇总确认信息（成品油）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(UploadSummaryConfirmReq req) {
        return httpUtil.call(CpyInterfaceCode.UPLOAD_SUMMARY_CONFIRM.getServiceCode(), req, UploadSummaryConfirmRes.class);
    }

    /**
     * 查询发票汇总确认信息（成品油）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(QuerySummaryConfirmReq req) {
        return httpUtil.call(CpyInterfaceCode.QUERY_SUMMARY_CONFIRM.getServiceCode(), req, QuerySummaryConfirmRes.class);
    }

    /**
     * 发票状态信息查询（成品油）。真实实现：调用乐企对应接口（服务编码见枚举，文档未给，复用通用占位 FPZTXXCX）。
     */
    @Override
    public ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(QueryInvoiceUsageReq req) {
        return httpUtil.call(CpyInterfaceCode.QUERY_INVOICE_USAGE.getServiceCode(), req, QueryInvoiceUsageRes.class);
    }

    /**
     * 批量发票下载申请（成品油，文档未给服务编码，复用通用占位 PLFPXZSQ）。返回空响应占位。
     */
    @Override
    public ChannelResponse<BatchDownloadApplyRes> batchDownloadApply(BatchDownloadApplyReq req) {
        return httpUtil.call(CpyInterfaceCode.BATCH_DOWNLOAD_APPLY.getServiceCode(), req, BatchDownloadApplyRes.class);
    }

    /**
     * 查询差额征税编码（成品油，文档列出服务编码 CXCEZSBM）。真实实现：调用乐企对应接口。
     */
    @Override
    public ChannelResponse<QueryDiffTaxCodeRes> queryDiffTaxCode(QueryDiffTaxCodeReq req) {
        return httpUtil.call(CpyInterfaceCode.QUERY_DIFF_TAX_CODE.getServiceCode(), req, QueryDiffTaxCodeRes.class);
    }

    /**
     * 查询成品油可用税收分类编码信息（成品油）。真实实现：调用乐企接口 CXCPYKC。
     */
    @Override
    public ChannelResponse<QueryTaxCategoryRes> queryCpyTaxCategory(CpyQueryTaxCategoryReq req) {
        return httpUtil.call(CpyInterfaceCode.QUERY_CPY_TAX_CATEGORY.getServiceCode(), req, QueryTaxCategoryRes.class);
    }

    /**
     * 查询成品油库存（成品油）。真实实现：调用乐企接口 CXCPYKYSSFLBM。
     */
    @Override
    public ChannelResponse<CpyQueryInventoryRes> queryCpyInventory(CpyQueryInventoryReq req) {
        return httpUtil.call(CpyInterfaceCode.QUERY_CPY_INVENTORY.getServiceCode(), req, CpyQueryInventoryRes.class);
    }

    /**
     * 下载或退回成品油库存（成品油）。真实实现：调用乐企接口 XZHTHCPYKC。
     */
    @Override
    public ChannelResponse<CpyDownloadOrReturnInventoryRes> downloadOrReturnCpyInventory(CpyDownloadOrReturnInventoryReq req) {
        ReqValidation.requireValid(req);
        return httpUtil.call(CpyInterfaceCode.DOWNLOAD_OR_RETURN_CPY_INVENTORY.getServiceCode(), req, CpyDownloadOrReturnInventoryRes.class);
    }

}
