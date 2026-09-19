package com.yhq.invoice.leqi.service;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.BuildInterfaceCode;
import com.yhq.invoice.common.enums.CapabilityCode;
import com.yhq.invoice.common.model.ChannelResponse;
import com.yhq.invoice.leqi.annotation.DocVersion;
import com.yhq.invoice.leqi.annotation.ValidateReq;
import com.yhq.invoice.leqi.client.LeqiHttpUtil;
import com.yhq.invoice.leqi.util.ReqValidation;
import org.springframework.stereotype.Service;

/**
 * 建筑服务（BUILD）能力服务：承载建筑服务支持的全部概念方法。
 * 仅实现 leqi 内部契约 LeqiCapabilityService，不实现统一门面契约 InvoiceCapabilityService。
 * 真实接口使用 Build* 扩展 DTO / 接口编码枚举，走通桩链路。
 *
 * <p>能力矩阵（依据《乐企数字化电子发票（建筑服务）开票能力说明文档-V4.002》）：
 * <ul>
 *   <li>支持：除 queryTaxProService、queryHouseSource 外的全部 22 标准概念；</li>
 *   <li>专属：queryJzfwInfo / crossRegionSingleQuery / crossRegionBatchQuery；</li>
 *   <li>不支持：queryTaxProService、queryHouseSource、queryDiffTaxCode（后者为货物运输专属）。</li>
 * </ul>
 * <p>
 * 类级 {@code @ValidateReq} 与 BASE/SALE 一致，对全部入参执行 jakarta.validation 校验。
 */
@Service
@ValidateReq
@DocVersion(doc = "乐企数字化电子发票（建筑服务）开票能力说明文档", value = "V4.002")
public class LeqiInvoiceJzfwService implements LeqiCapabilityService {

    private final LeqiHttpUtil httpUtil;

    public LeqiInvoiceJzfwService(LeqiHttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Override
    public CapabilityCode capability() {
        return CapabilityCode.BUILD;
    }

    /**
     * 获取发票批量预赋码信息（建筑服务）。真实实现：调用乐企接口 QDFPPLFM，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GetBatchPreCodeRes> getBatchPreCode(GetBatchPreCodeReq req) {
        return httpUtil.call(BuildInterfaceCode.GET_BATCH_PRE_CODE.getServiceCode(), req, GetBatchPreCodeRes.class);
    }

    /**
     * 查询授信额度（建筑服务）。真实实现：调用乐企接口 CXSXED，当前走通桩链路。
     */
    @Override
    public ChannelResponse<QueryQuotaRes> queryQuota(QueryQuotaReq req) {
        return httpUtil.call(BuildInterfaceCode.QUERY_QUOTA.getServiceCode(), req, QueryQuotaRes.class);
    }

    /**
     * 下载/退回授信额度（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(DownloadOrReturnQuotaReq req) {
        return httpUtil.call(BuildInterfaceCode.DOWNLOAD_OR_RETURN_QUOTA.getServiceCode(), req, DownloadOrReturnQuotaRes.class);
    }

    /**
     * 调整授信额度有效期（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(AdjustQuotaValidityReq req) {
        return httpUtil.call(BuildInterfaceCode.ADJUST_QUOTA_VALIDITY.getServiceCode(), req, AdjustQuotaValidityRes.class);
    }

    /**
     * 查询纳税人风险信息（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(QueryTaxpayerRiskReq req) {
        return httpUtil.call(BuildInterfaceCode.QUERY_TAXPAYER_RISK.getServiceCode(), req, QueryTaxpayerRiskRes.class);
    }

    /**
     * 查询纳税人基本信息（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(QueryTaxpayerBasicReq req) {
        return httpUtil.call(BuildInterfaceCode.QUERY_TAXPAYER_BASIC.getServiceCode(), req, QueryTaxpayerBasicRes.class);
    }

    /**
     * 查询可用税率信息（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxRateRes> queryTaxRate(QueryTaxRateReq req) {
        return httpUtil.call(BuildInterfaceCode.QUERY_TAX_RATE.getServiceCode(), req, QueryTaxRateRes.class);
    }

    /**
     * 查询税收分类编码信息（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(QueryTaxCategoryReq req) {
        return httpUtil.call(BuildInterfaceCode.QUERY_TAX_CATEGORY.getServiceCode(), req, QueryTaxCategoryRes.class);
    }

    /**
     * 查询红字确认单明细信息（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(QueryRedConfirmDetailReq req) {
        return httpUtil.call(BuildInterfaceCode.QUERY_RED_CONFIRM_DETAIL.getServiceCode(), req, QueryRedConfirmDetailRes.class);
    }

    /**
     * 发票上传（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。先转为建筑服务特定 req 再上送。
     */
    @Override
    public ChannelResponse<UploadInvoiceRes> uploadInvoice(UploadInvoiceReq req) {
        BuildUploadInvoiceReq sreq = BuildUploadInvoiceReq.from(req);
        ReqValidation.requireValid(sreq);
        return httpUtil.call(BuildInterfaceCode.UPLOAD_INVOICE.getServiceCode(), sreq, UploadInvoiceRes.class);
    }

    /**
     * 查询红字确认单列表信息（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(QueryRedConfirmListReq req) {
        return httpUtil.call(BuildInterfaceCode.QUERY_RED_CONFIRM_LIST.getServiceCode(), req, QueryRedConfirmListRes.class);
    }

    /**
     * 查询发票上传结果（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryUploadResultRes> queryUploadResult(QueryUploadResultReq req) {
        return httpUtil.call(BuildInterfaceCode.QUERY_UPLOAD_RESULT.getServiceCode(), req, QueryUploadResultRes.class);
    }

    /**
     * 红字确认单申请（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(ApplyRedConfirmReq req) {
        return httpUtil.call(BuildInterfaceCode.APPLY_RED_CONFIRM.getServiceCode(), req, ApplyRedConfirmRes.class);
    }

    /**
     * 红字确认单确认（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(ConfirmRedConfirmReq req) {
        return httpUtil.call(BuildInterfaceCode.CONFIRM_RED_CONFIRM.getServiceCode(), req, ConfirmRedConfirmRes.class);
    }

    /**
     * 上传发票汇总确认信息（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(UploadSummaryConfirmReq req) {
        return httpUtil.call(BuildInterfaceCode.UPLOAD_SUMMARY_CONFIRM.getServiceCode(), req, UploadSummaryConfirmRes.class);
    }

    /**
     * 查询发票汇总确认信息（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(QuerySummaryConfirmReq req) {
        return httpUtil.call(BuildInterfaceCode.QUERY_SUMMARY_CONFIRM.getServiceCode(), req, QuerySummaryConfirmRes.class);
    }

    /**
     * 发票用途状态信息查询（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(QueryInvoiceUsageReq req) {
        return httpUtil.call(BuildInterfaceCode.QUERY_INVOICE_USAGE.getServiceCode(), req, QueryInvoiceUsageRes.class);
    }

    /**
     * 扣除凭证清单相关发票数据信息查询（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryDeductionVoucherRes> queryDeductionVoucher(QueryDeductionVoucherReq req) {
        return httpUtil.call(BuildInterfaceCode.QUERY_DEDUCTION_VOUCHER.getServiceCode(), req, QueryDeductionVoucherRes.class);
    }


    /**
     * 原差额征税业务授权信息查询（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryDiffTaxAuthRes> queryDiffTaxAuth(QueryDiffTaxAuthReq req) {
        return httpUtil.call(BuildInterfaceCode.QUERY_DIFF_TAX_AUTH.getServiceCode(), req, QueryDiffTaxAuthRes.class);
    }

    /**
     * 批量发票下载申请（建筑服务）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<BatchDownloadApplyRes> batchDownloadApply(BatchDownloadApplyReq req) {
        return httpUtil.call(BuildInterfaceCode.BATCH_DOWNLOAD_APPLY.getServiceCode(), req, BatchDownloadApplyRes.class);
    }


    /**
     * 查询建筑服务信息（建筑服务）。真实实现：调用乐企对应接口 CXJZFWXX。
     */
    @Override
    public ChannelResponse<QueryJzfwInfoRes> queryJzfwInfo(QueryJzfwInfoReq req) {
        return httpUtil.call(BuildInterfaceCode.QUERY_JZFW_INFO.getServiceCode(), req, QueryJzfwInfoRes.class);
    }

    /**
     * 跨区域涉税数据单笔查询（建筑服务）。真实实现：调用乐企对应接口 KQYSSSJDBCX。
     */
    @Override
    public ChannelResponse<CrossRegionSingleRes> crossRegionSingleQuery(CrossRegionSingleReq req) {
        return httpUtil.call(BuildInterfaceCode.CROSS_REGION_SINGLE.getServiceCode(), req, CrossRegionSingleRes.class);
    }

    /**
     * 跨区域涉税数据批量查询（建筑服务）。真实实现：调用乐企对应接口 KQYSSSJPLCX。
     */
    @Override
    public ChannelResponse<CrossRegionBatchRes> crossRegionBatchQuery(CrossRegionBatchReq req) {
        return httpUtil.call(BuildInterfaceCode.CROSS_REGION_BATCH.getServiceCode(), req, CrossRegionBatchRes.class);
    }

}
