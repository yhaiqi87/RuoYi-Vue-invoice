package com.yhq.invoice.leqi.service;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.CapabilityCode;
import com.yhq.invoice.common.enums.LeaseInterfaceCode;
import com.yhq.invoice.common.model.ChannelResponse;
import com.yhq.invoice.leqi.annotation.DocVersion;
import com.yhq.invoice.leqi.annotation.ValidateReq;
import com.yhq.invoice.leqi.client.LeqiHttpUtil;
import com.yhq.invoice.leqi.util.ReqValidation;
import org.springframework.stereotype.Service;

/**
 * 不动产经营租赁（LEASE）能力服务：承载不动产经营租赁支持的全部 22 个概念方法。
 * 仅实现 leqi 内部契约 LeqiCapabilityService，不实现统一门面契约 InvoiceCapabilityService。
 * 参考 {@link LeqiInvoiceBdcxsService}（不动产销售）的实现结构：各方法直接走通桩链路，
 * 服务编码取自 {@link LeaseInterfaceCode}（不动产经营租赁 V3.021 接口清单），能力编码为 202038。
 *
 * <p>LEASE 支持其中 18 个概念（见 LeaseInterfaceCode）；其余 4 个不在不动产经营租赁接口清单内，
 * 无法支持，统一抛出 UnsupportedOperationException：
 * 扣除凭证清单查询（queryDeductionVoucher）、涉税专业服务（queryTaxProService）、
 * 原差额征税授权（queryDiffTaxAuth）、查询房源信息（queryHouseSource，仅不动产销售支持）。
 *
 * <p>类级 {@code @ValidateReq} 与 BASE / SALE 一致，对全部入参执行 jakarta.validation 校验
 * （共享约束来自基类 DTO，LEASE 专属必填项可写到对应 Lease* 子类，无需 validation group）。
 */
@Service
@ValidateReq
@DocVersion(doc = "乐企数字化电子发票（不动产经营租赁）开票能力说明文档", value = "V3.021")
public class LeqiInvoiceBdczlService implements LeqiCapabilityService {

    private final LeqiHttpUtil httpUtil;

    public LeqiInvoiceBdczlService(LeqiHttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Override
    public CapabilityCode capability() {
        return CapabilityCode.LEASE;
    }

    /**
     * 获取发票批量预赋码信息（不动产经营租赁）。真实实现：调用乐企接口 QDFPPLFM，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GetBatchPreCodeRes> getBatchPreCode(GetBatchPreCodeReq req) {
        return httpUtil.call(LeaseInterfaceCode.GET_BATCH_PRE_CODE.getServiceCode(), req, GetBatchPreCodeRes.class);
    }

    /**
     * 查询授信额度（不动产经营租赁）。真实实现：调用乐企接口 CXSXED，当前走通桩链路。
     */
    @Override
    public ChannelResponse<QueryQuotaRes> queryQuota(QueryQuotaReq req) {
        return httpUtil.call(LeaseInterfaceCode.QUERY_QUOTA.getServiceCode(), req, QueryQuotaRes.class);
    }

    /**
     * 下载/退回授信额度（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(DownloadOrReturnQuotaReq req) {
        return httpUtil.call(LeaseInterfaceCode.DOWNLOAD_OR_RETURN_QUOTA.getServiceCode(), req, DownloadOrReturnQuotaRes.class);
    }

    /**
     * 调整授信额度有效期（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(AdjustQuotaValidityReq req) {
        return httpUtil.call(LeaseInterfaceCode.ADJUST_QUOTA_VALIDITY.getServiceCode(), req, AdjustQuotaValidityRes.class);
    }

    /**
     * 查询纳税人风险信息（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(QueryTaxpayerRiskReq req) {
        return httpUtil.call(LeaseInterfaceCode.QUERY_TAXPAYER_RISK.getServiceCode(), req, QueryTaxpayerRiskRes.class);
    }

    /**
     * 查询纳税人基本信息（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(QueryTaxpayerBasicReq req) {
        return httpUtil.call(LeaseInterfaceCode.QUERY_TAXPAYER_BASIC.getServiceCode(), req, QueryTaxpayerBasicRes.class);
    }

    /**
     * 查询可用税率信息（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxRateRes> queryTaxRate(QueryTaxRateReq req) {
        return httpUtil.call(LeaseInterfaceCode.QUERY_TAX_RATE.getServiceCode(), req, QueryTaxRateRes.class);
    }

    /**
     * 查询税收分类编码信息（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(QueryTaxCategoryReq req) {
        return httpUtil.call(LeaseInterfaceCode.QUERY_TAX_CATEGORY.getServiceCode(), req, QueryTaxCategoryRes.class);
    }

    /**
     * 查询红字确认单明细信息（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(QueryRedConfirmDetailReq req) {
        return httpUtil.call(LeaseInterfaceCode.QUERY_RED_CONFIRM_DETAIL.getServiceCode(), req, QueryRedConfirmDetailRes.class);
    }

    /**
     * 不动产经营租赁发票上传。真实实现：调用乐企对应接口（服务编码见枚举）。先转为不动产经营租赁特定 req 再上送。
     */
    @Override
    public ChannelResponse<UploadInvoiceRes> uploadInvoice(UploadInvoiceReq req) {
        LeaseUploadInvoiceReq sreq = LeaseUploadInvoiceReq.from(req);
        ReqValidation.requireValid(sreq);
        return httpUtil.call(LeaseInterfaceCode.UPLOAD_INVOICE.getServiceCode(), sreq, UploadInvoiceRes.class);
    }

    /**
     * 查询红字确认单列表信息（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(QueryRedConfirmListReq req) {
        return httpUtil.call(LeaseInterfaceCode.QUERY_RED_CONFIRM_LIST.getServiceCode(), req, QueryRedConfirmListRes.class);
    }

    /**
     * 查询发票上传结果（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryUploadResultRes> queryUploadResult(QueryUploadResultReq req) {
        return httpUtil.call(LeaseInterfaceCode.QUERY_UPLOAD_RESULT.getServiceCode(), req, QueryUploadResultRes.class);
    }

    /**
     * 红字确认单申请（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(ApplyRedConfirmReq req) {
        return httpUtil.call(LeaseInterfaceCode.APPLY_RED_CONFIRM.getServiceCode(), req, ApplyRedConfirmRes.class);
    }

    /**
     * 红字确认单确认（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(ConfirmRedConfirmReq req) {
        return httpUtil.call(LeaseInterfaceCode.CONFIRM_RED_CONFIRM.getServiceCode(), req, ConfirmRedConfirmRes.class);
    }

    /**
     * 上传发票汇总确认信息（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(UploadSummaryConfirmReq req) {
        return httpUtil.call(LeaseInterfaceCode.UPLOAD_SUMMARY_CONFIRM.getServiceCode(), req, UploadSummaryConfirmRes.class);
    }

    /**
     * 查询发票汇总确认信息（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(QuerySummaryConfirmReq req) {
        return httpUtil.call(LeaseInterfaceCode.QUERY_SUMMARY_CONFIRM.getServiceCode(), req, QuerySummaryConfirmRes.class);
    }

    /**
     * 发票用途状态信息查询（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(QueryInvoiceUsageReq req) {
        return httpUtil.call(LeaseInterfaceCode.QUERY_INVOICE_USAGE.getServiceCode(), req, QueryInvoiceUsageRes.class);
    }

    /**
     * 批量发票下载申请（不动产经营租赁）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<BatchDownloadApplyRes> batchDownloadApply(BatchDownloadApplyReq req) {
        return httpUtil.call(LeaseInterfaceCode.BATCH_DOWNLOAD_APPLY.getServiceCode(), req, BatchDownloadApplyRes.class);
    }

}
