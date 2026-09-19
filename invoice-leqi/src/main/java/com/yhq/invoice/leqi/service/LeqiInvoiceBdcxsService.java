package com.yhq.invoice.leqi.service;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.CapabilityCode;
import com.yhq.invoice.common.enums.SaleInterfaceCode;
import com.yhq.invoice.common.model.ChannelResponse;
import com.yhq.invoice.leqi.annotation.DocVersion;
import com.yhq.invoice.leqi.annotation.ValidateReq;
import com.yhq.invoice.leqi.client.LeqiHttpUtil;
import com.yhq.invoice.leqi.util.ReqValidation;
import org.springframework.stereotype.Service;

/**
 * 不动产销售（SALE）能力服务：承载不动产销售支持的全部 22 个概念方法。
 * 仅实现 leqi 内部契约 LeqiCapabilityService，不实现统一门面契约 InvoiceCapabilityService。
 * 真实接口使用 Sale* 扩展 DTO，预留不动产销售专属字段差异位；后续校验逻辑加在对应方法内，不影响 BASE/LEASE。
 * 类级 {@code @ValidateReq} 与 BASE 一致，对全部入参执行 jakarta.validation 校验（共享约束来自基类 DTO，
 * SALE 专属必填项写到 Sale* 子类即可，无需 validation group）。
 */
@Service
@ValidateReq
@DocVersion(doc = "乐企数字化电子发票（不动产销售）开票能力说明文档", value = "V3.004")
public class LeqiInvoiceBdcxsService implements LeqiCapabilityService {

    private final LeqiHttpUtil httpUtil;

    public LeqiInvoiceBdcxsService(LeqiHttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Override
    public CapabilityCode capability() {
        return CapabilityCode.SALE;
    }

    /** 获取发票批量预赋码信息（不动产销售）。真实实现：调用乐企接口 QDFPPLFM，当前走通桩链路。 */
    @Override
    public ChannelResponse<GetBatchPreCodeRes> getBatchPreCode(GetBatchPreCodeReq req) {
        return httpUtil.call(SaleInterfaceCode.GET_BATCH_PRE_CODE.getServiceCode(), req, GetBatchPreCodeRes.class);
    }

    /** 查询授信额度（不动产销售）。真实实现：调用乐企接口 CXSXED，当前走通桩链路。 */
    @Override
    public ChannelResponse<QueryQuotaRes> queryQuota(QueryQuotaReq req) {
        return httpUtil.call(SaleInterfaceCode.QUERY_QUOTA.getServiceCode(), req, QueryQuotaRes.class);
    }

    /**
     * 下载/退回授信额度（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(DownloadOrReturnQuotaReq req) {
        return httpUtil.call(SaleInterfaceCode.DOWNLOAD_OR_RETURN_QUOTA.getServiceCode(), req, DownloadOrReturnQuotaRes.class);
    }

    /** 调整授信额度有效期（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(AdjustQuotaValidityReq req) {
        return httpUtil.call(SaleInterfaceCode.ADJUST_QUOTA_VALIDITY.getServiceCode(), req, AdjustQuotaValidityRes.class);
    }

    /** 查询纳税人风险信息（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(QueryTaxpayerRiskReq req) {
        return httpUtil.call(SaleInterfaceCode.QUERY_TAXPAYER_RISK.getServiceCode(), req, QueryTaxpayerRiskRes.class);
    }

    /** 查询纳税人基本信息（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(QueryTaxpayerBasicReq req) {
        return httpUtil.call(SaleInterfaceCode.QUERY_TAXPAYER_BASIC.getServiceCode(), req, QueryTaxpayerBasicRes.class);
    }

    /** 查询可用税率信息（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryTaxRateRes> queryTaxRate(QueryTaxRateReq req) {
        return httpUtil.call(SaleInterfaceCode.QUERY_TAX_RATE.getServiceCode(), req, QueryTaxRateRes.class);
    }

    /** 查询税收分类编码信息（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(QueryTaxCategoryReq req) {
        return httpUtil.call(SaleInterfaceCode.QUERY_TAX_CATEGORY.getServiceCode(), req, QueryTaxCategoryRes.class);
    }

    /** 查询红字确认单明细信息（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(QueryRedConfirmDetailReq req) {
        return httpUtil.call(SaleInterfaceCode.QUERY_RED_CONFIRM_DETAIL.getServiceCode(), req, QueryRedConfirmDetailRes.class);
    }

    /**
     * 发票上传（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。先转为不动产销售特定 req 再上送。
     */
    @Override
    public ChannelResponse<UploadInvoiceRes> uploadInvoice(UploadInvoiceReq req) {
        SaleUploadInvoiceReq sreq = SaleUploadInvoiceReq.from(req);
        ReqValidation.requireValid(sreq);
        return httpUtil.call(SaleInterfaceCode.UPLOAD_INVOICE.getServiceCode(), sreq, UploadInvoiceRes.class);
    }

    /** 查询红字确认单列表信息（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(QueryRedConfirmListReq req) {
        return httpUtil.call(SaleInterfaceCode.QUERY_RED_CONFIRM_LIST.getServiceCode(), req, QueryRedConfirmListRes.class);
    }

    /** 查询发票上传结果（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryUploadResultRes> queryUploadResult(QueryUploadResultReq req) {
        return httpUtil.call(SaleInterfaceCode.QUERY_UPLOAD_RESULT.getServiceCode(), req, QueryUploadResultRes.class);
    }

    /** 红字确认单申请（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(ApplyRedConfirmReq req) {
        return httpUtil.call(SaleInterfaceCode.APPLY_RED_CONFIRM.getServiceCode(), req, ApplyRedConfirmRes.class);
    }

    /** 红字确认单确认（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(ConfirmRedConfirmReq req) {
        return httpUtil.call(SaleInterfaceCode.CONFIRM_RED_CONFIRM.getServiceCode(), req, ConfirmRedConfirmRes.class);
    }

    /** 上传发票汇总确认信息（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(UploadSummaryConfirmReq req) {
        return httpUtil.call(SaleInterfaceCode.UPLOAD_SUMMARY_CONFIRM.getServiceCode(), req, UploadSummaryConfirmRes.class);
    }

    /** 查询发票汇总确认信息（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(QuerySummaryConfirmReq req) {
        return httpUtil.call(SaleInterfaceCode.QUERY_SUMMARY_CONFIRM.getServiceCode(), req, QuerySummaryConfirmRes.class);
    }

    /** 发票用途状态信息查询（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(QueryInvoiceUsageReq req) {
        return httpUtil.call(SaleInterfaceCode.QUERY_INVOICE_USAGE.getServiceCode(), req, QueryInvoiceUsageRes.class);
    }

    /** 扣除凭证清单相关发票数据信息查询（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryDeductionVoucherRes> queryDeductionVoucher(QueryDeductionVoucherReq req) {
        return httpUtil.call(SaleInterfaceCode.QUERY_DEDUCTION_VOUCHER.getServiceCode(), req, QueryDeductionVoucherRes.class);
    }


    /** 批量发票下载申请（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<BatchDownloadApplyRes> batchDownloadApply(BatchDownloadApplyReq req) {
        return httpUtil.call(SaleInterfaceCode.BATCH_DOWNLOAD_APPLY.getServiceCode(), req, BatchDownloadApplyRes.class);
    }

    /** 查询房源信息（不动产销售）。真实实现：调用乐企对应接口（服务编码见枚举）。 */
    @Override
    public ChannelResponse<QueryHouseSourceRes> queryHouseSource(QueryHouseSourceReq req) {
        return httpUtil.call(SaleInterfaceCode.QUERY_HOUSE_SOURCE.getServiceCode(), req, QueryHouseSourceRes.class);
    }

}
