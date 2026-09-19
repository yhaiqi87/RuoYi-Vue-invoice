package com.yhq.invoice.leqi.service;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.CapabilityCode;
import com.yhq.invoice.common.enums.FxInterfaceCode;
import com.yhq.invoice.common.model.ChannelResponse;
import com.yhq.invoice.leqi.annotation.DocVersion;
import com.yhq.invoice.leqi.annotation.ValidateReq;
import com.yhq.invoice.leqi.client.LeqiHttpUtil;
import org.springframework.stereotype.Service;

/**
 * 反向开票通用（FX）能力服务：承载反向开票通用支持的全部概念方法。
 * 仅实现 leqi 内部契约 LeqiCapabilityService，不实现统一门面契约 InvoiceCapabilityService。
 * 真实接口使用 FxInterfaceCode 枚举，走通桩链路。
 *
 * <p>能力矩阵（依据《乐企数字化电子发票（反向开票通用）开票能力说明文档-V1.002》，能力编码 202083）：
 * <ul>
 *   <li>复用数电票通用概念 16 项：预赋码 / 赋额额度 / 风险 / 基本信息 / 税率 / 分类编码 / 红字确认单 /
 *       汇总确认 / 上传结果等；</li>
 *   <li>反向开票通用专属 1 项：queryNaturalPersonInvoicing（查询自然人开票信息，CXFXKPTYZRRXX）；</li>
 *   <li>uploadInvoice 映射到反向开票通用发票上传（FXKPTYFPSC），报文与数电票上传超集一致；</li>
 *   <li>不支持 14 项：queryInvoiceUsage / queryDeductionVoucher / queryTaxProService / queryDiffTaxAuth /
 *       batchDownloadApply / queryHouseSource / queryJzfwInfo / crossRegionSingleQuery / crossRegionBatchQuery /
 *       queryDiffTaxCode / querySellerBlock / queryEscExchangeableBatch / queryEscExchangeInfo / uploadEscInvoice。</li>
 * </ul>
 *
 * <p>类级 {@code @ValidateReq} 与 BASE/SALE 一致，对全部入参执行 jakarta.validation 校验。
 */
@Service
@ValidateReq
@DocVersion(doc = "乐企数字化电子发票（反向开票通用）开票能力说明文档", value = "V1.002")
public class LeqiInvoiceFxService implements LeqiCapabilityService {

    private final LeqiHttpUtil httpUtil;

    public LeqiInvoiceFxService(LeqiHttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Override
    public CapabilityCode capability() {
        return CapabilityCode.FX;
    }

    /**
     * 获取数电票批量预赋码信息（反向开票通用）。真实实现：调用乐企接口 QDFPPLFM，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GetBatchPreCodeRes> getBatchPreCode(GetBatchPreCodeReq req) {
        return httpUtil.call(FxInterfaceCode.GET_BATCH_PRE_CODE.getServiceCode(), req, GetBatchPreCodeRes.class);
    }

    /**
     * 查询赋额额度（反向开票通用）。真实实现：调用乐企接口 CXSXED，当前走通桩链路。
     */
    @Override
    public ChannelResponse<QueryQuotaRes> queryQuota(QueryQuotaReq req) {
        return httpUtil.call(FxInterfaceCode.QUERY_QUOTA.getServiceCode(), req, QueryQuotaRes.class);
    }

    /**
     * 下载/退回赋额额度（反向开票通用）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(DownloadOrReturnQuotaReq req) {
        return httpUtil.call(FxInterfaceCode.DOWNLOAD_OR_RETURN_QUOTA.getServiceCode(), req, DownloadOrReturnQuotaRes.class);
    }

    /**
     * 调整赋额额度有效期（反向开票通用）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(AdjustQuotaValidityReq req) {
        return httpUtil.call(FxInterfaceCode.ADJUST_QUOTA_VALIDITY.getServiceCode(), req, AdjustQuotaValidityRes.class);
    }

    /**
     * 查询纳税人风险信息（反向开票通用）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(QueryTaxpayerRiskReq req) {
        return httpUtil.call(FxInterfaceCode.QUERY_TAXPAYER_RISK.getServiceCode(), req, QueryTaxpayerRiskRes.class);
    }

    /**
     * 查询纳税人基本信息（反向开票通用）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(QueryTaxpayerBasicReq req) {
        return httpUtil.call(FxInterfaceCode.QUERY_TAXPAYER_BASIC.getServiceCode(), req, QueryTaxpayerBasicRes.class);
    }

    /**
     * 查询可用税率信息（反向开票通用）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxRateRes> queryTaxRate(QueryTaxRateReq req) {
        return httpUtil.call(FxInterfaceCode.QUERY_TAX_RATE.getServiceCode(), req, QueryTaxRateRes.class);
    }

    /**
     * 查询税收分类编码信息（反向开票通用）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(QueryTaxCategoryReq req) {
        return httpUtil.call(FxInterfaceCode.QUERY_TAX_CATEGORY.getServiceCode(), req, QueryTaxCategoryRes.class);
    }

    /**
     * 查询自然人开票信息（反向开票通用）。真实实现：调用乐企接口 CXFXKPTYZRRXX。
     */
    @Override
    public ChannelResponse<QueryNaturalPersonInvoicingRes> queryNaturalPersonInvoicing(QueryNaturalPersonInvoicingReq req) {
        return httpUtil.call(FxInterfaceCode.QUERY_NATURAL_PERSON_INVOICING.getServiceCode(), req, QueryNaturalPersonInvoicingRes.class);
    }

    /**
     * 反向开票通用发票上传（反向开票通用）。真实实现：调用乐企接口 FXKPTYFPSC，报文与数电票上传超集一致。
     */
    @Override
    public ChannelResponse<UploadInvoiceRes> uploadInvoice(UploadInvoiceReq req) {
        return httpUtil.call(FxInterfaceCode.UPLOAD_INVOICE.getServiceCode(), req, UploadInvoiceRes.class);
    }

    /**
     * 查询发票上传结果（反向开票通用）。真实实现：调用乐企对应接口 CXQDFPSCJG。
     */
    @Override
    public ChannelResponse<QueryUploadResultRes> queryUploadResult(QueryUploadResultReq req) {
        return httpUtil.call(FxInterfaceCode.QUERY_UPLOAD_RESULT.getServiceCode(), req, QueryUploadResultRes.class);
    }

    /**
     * 红字确认单申请（反向开票通用）。真实实现：调用乐企对应接口 QDHZQRDSQ。
     */
    @Override
    public ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(ApplyRedConfirmReq req) {
        return httpUtil.call(FxInterfaceCode.APPLY_RED_CONFIRM.getServiceCode(), req, ApplyRedConfirmRes.class);
    }

    /**
     * 红字确认单确认（反向开票通用）。真实实现：调用乐企对应接口 QDHZQRDQR。
     */
    @Override
    public ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(ConfirmRedConfirmReq req) {
        return httpUtil.call(FxInterfaceCode.CONFIRM_RED_CONFIRM.getServiceCode(), req, ConfirmRedConfirmRes.class);
    }

    /**
     * 查询红字确认单列表信息（反向开票通用）。真实实现：调用乐企对应接口 CXQDHZQRDLB。
     */
    @Override
    public ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(QueryRedConfirmListReq req) {
        return httpUtil.call(FxInterfaceCode.QUERY_RED_CONFIRM_LIST.getServiceCode(), req, QueryRedConfirmListRes.class);
    }

    /**
     * 查询红字确认单明细信息（反向开票通用）。真实实现：调用乐企对应接口 CXQDHZQRDMX。
     */
    @Override
    public ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(QueryRedConfirmDetailReq req) {
        return httpUtil.call(FxInterfaceCode.QUERY_RED_CONFIRM_DETAIL.getServiceCode(), req, QueryRedConfirmDetailRes.class);
    }

    /**
     * 上传发票汇总确认信息（反向开票通用）。真实实现：调用乐企对应接口 SCFPHZQRXX。
     */
    @Override
    public ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(UploadSummaryConfirmReq req) {
        return httpUtil.call(FxInterfaceCode.UPLOAD_SUMMARY_CONFIRM.getServiceCode(), req, UploadSummaryConfirmRes.class);
    }

    /**
     * 查询发票汇总确认信息（反向开票通用）。真实实现：调用乐企对应接口 CXFPHZQRXX。
     */
    @Override
    public ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(QuerySummaryConfirmReq req) {
        return httpUtil.call(FxInterfaceCode.QUERY_SUMMARY_CONFIRM.getServiceCode(), req, QuerySummaryConfirmRes.class);
    }

    /**
     * 发票用途状态信息查询（仅基础版/不动产，反向开票通用不涉及）。返回空响应占位。
     */
    @Override
    public ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(QueryInvoiceUsageReq req) {
        throw new UnsupportedOperationException("反向开票通用无此方法");
    }

}
