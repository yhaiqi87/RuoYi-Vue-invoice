package com.luoge.ns.invoice.leqi.service;

import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.BaseInterfaceCode;
import com.luoge.ns.invoice.common.enums.CapabilityCode;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import com.luoge.ns.invoice.leqi.annotation.DocVersion;
import com.luoge.ns.invoice.leqi.annotation.ValidateReq;
import com.luoge.ns.invoice.leqi.client.LeqiHttpUtil;
import com.luoge.ns.invoice.leqi.util.ReqValidation;
import org.springframework.stereotype.Service;

/**
 * 乐企基础版（BASE）能力服务：承载基础版支持的全部 22 个概念方法。
 * 仅实现 leqi 内部契约 LeqiCapabilityService，不实现统一门面契约 InvoiceCapabilityService。
 * 各方法直接走通桩链路，后续校验逻辑加在对应方法内，不影响 SALE/LEASE。
 */
@Service
@ValidateReq
@DocVersion(doc = "乐企数字化电子发票（基础版）开票能力说明文档", value = "V6.006")
public class LeqiInvoiceBaseService implements LeqiCapabilityService {

    private final LeqiHttpUtil httpUtil;

    public LeqiInvoiceBaseService(LeqiHttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Override
    public CapabilityCode capability() {
        return CapabilityCode.BASE;
    }

    /** 获取发票批量预赋码信息（基础版）。真实实现：调用乐企接口 QDFPPLFM，当前走通桩链路。 */
    @Override
    public ChannelResponse<GetBatchPreCodeRes> getBatchPreCode(GetBatchPreCodeReq req) {
        return httpUtil.call(BaseInterfaceCode.GET_BATCH_PRE_CODE.getServiceCode(), req, GetBatchPreCodeRes.class);
    }

    /** 查询发票额度（基础版）。真实实现：调用乐企接口 CXSXED，当前走通桩链路。 */
    @Override
    public ChannelResponse<QueryQuotaRes> queryQuota(QueryQuotaReq req) {
        return httpUtil.call(BaseInterfaceCode.QUERY_QUOTA.getServiceCode(), req, QueryQuotaRes.class);
    }

    /**
     * 下载/退回发票额度（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(DownloadOrReturnQuotaReq req) {
        return httpUtil.call(BaseInterfaceCode.DOWNLOAD_OR_RETURN_QUOTA.getServiceCode(), req, DownloadOrReturnQuotaRes.class);
    }

    /** 调整发票额度有效期（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(AdjustQuotaValidityReq req) {
        return httpUtil.call(BaseInterfaceCode.ADJUST_QUOTA_VALIDITY.getServiceCode(), req, AdjustQuotaValidityRes.class);
    }

    /** 查询纳税人风险信息（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(QueryTaxpayerRiskReq req) {
        return httpUtil.call(BaseInterfaceCode.QUERY_TAXPAYER_RISK.getServiceCode(), req, QueryTaxpayerRiskRes.class);
    }

    /** 查询纳税人基本信息（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(QueryTaxpayerBasicReq req) {
        return httpUtil.call(BaseInterfaceCode.QUERY_TAXPAYER_BASIC.getServiceCode(), req, QueryTaxpayerBasicRes.class);
    }

    /** 查询可用税率信息（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryTaxRateRes> queryTaxRate(QueryTaxRateReq req) {
        return httpUtil.call(BaseInterfaceCode.QUERY_TAX_RATE.getServiceCode(), req, QueryTaxRateRes.class);
    }

    /** 查询税收分类编码信息（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(QueryTaxCategoryReq req) {
        return httpUtil.call(BaseInterfaceCode.QUERY_TAX_CATEGORY.getServiceCode(), req, QueryTaxCategoryRes.class);
    }

    /** 查询红字确认单明细信息（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(QueryRedConfirmDetailReq req) {
        return httpUtil.call(BaseInterfaceCode.QUERY_RED_CONFIRM_DETAIL.getServiceCode(), req, QueryRedConfirmDetailRes.class);
    }

    /**
     * 发票上传（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。先转为基础版特定 req 再上送。
     */
    @Override
    public ChannelResponse<UploadInvoiceRes> uploadInvoice(UploadInvoiceReq req) {
        BaseUploadInvoiceReq sreq = BaseUploadInvoiceReq.from(req);
        ReqValidation.requireValid(sreq);
        return httpUtil.call(BaseInterfaceCode.UPLOAD_INVOICE.getServiceCode(), sreq, UploadInvoiceRes.class);
    }

    /** 查询红字确认单列表信息（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(QueryRedConfirmListReq req) {
        return httpUtil.call(BaseInterfaceCode.QUERY_RED_CONFIRM_LIST.getServiceCode(), req, QueryRedConfirmListRes.class);
    }

    /** 查询发票上传结果（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryUploadResultRes> queryUploadResult(QueryUploadResultReq req) {
        return httpUtil.call(BaseInterfaceCode.QUERY_UPLOAD_RESULT.getServiceCode(), req, QueryUploadResultRes.class);
    }

    /** 红字确认单申请（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(ApplyRedConfirmReq req) {
        return httpUtil.call(BaseInterfaceCode.APPLY_RED_CONFIRM.getServiceCode(), req, ApplyRedConfirmRes.class);
    }

    /** 红字确认单确认（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(ConfirmRedConfirmReq req) {
        return httpUtil.call(BaseInterfaceCode.CONFIRM_RED_CONFIRM.getServiceCode(), req, ConfirmRedConfirmRes.class);
    }

    /** 上传发票汇总确认信息（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(UploadSummaryConfirmReq req) {
        return httpUtil.call(BaseInterfaceCode.UPLOAD_SUMMARY_CONFIRM.getServiceCode(), req, UploadSummaryConfirmRes.class);
    }

    /** 查询发票汇总确认信息（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(QuerySummaryConfirmReq req) {
        return httpUtil.call(BaseInterfaceCode.QUERY_SUMMARY_CONFIRM.getServiceCode(), req, QuerySummaryConfirmRes.class);
    }

    /** 发票用途状态信息查询（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(QueryInvoiceUsageReq req) {
        return httpUtil.call(BaseInterfaceCode.QUERY_INVOICE_USAGE.getServiceCode(), req, QueryInvoiceUsageRes.class);
    }

    /** 扣除凭证清单相关发票数据信息查询（基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryDeductionVoucherRes> queryDeductionVoucher(QueryDeductionVoucherReq req) {
        return httpUtil.call(BaseInterfaceCode.QUERY_DEDUCTION_VOUCHER.getServiceCode(), req, QueryDeductionVoucherRes.class);
    }

    /** 查询涉税专业服务相关信息（仅基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryTaxProServiceRes> queryTaxProService(QueryTaxProServiceReq req) {
        return httpUtil.call(BaseInterfaceCode.QUERY_TAX_PRO_SERVICE.getServiceCode(), req, QueryTaxProServiceRes.class);
    }

    /** 原差额征税业务授权信息查询（仅基础版）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryDiffTaxAuthRes> queryDiffTaxAuth(QueryDiffTaxAuthReq req) {
        return httpUtil.call(BaseInterfaceCode.QUERY_DIFF_TAX_AUTH.getServiceCode(), req, QueryDiffTaxAuthRes.class);
    }

}
