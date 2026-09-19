package com.luoge.ns.invoice.leqi.service;

import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.CapabilityCode;
import com.luoge.ns.invoice.common.enums.FreightInterfaceCode;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import com.luoge.ns.invoice.leqi.annotation.DocVersion;
import com.luoge.ns.invoice.leqi.annotation.ValidateReq;
import com.luoge.ns.invoice.leqi.client.LeqiHttpUtil;
import com.luoge.ns.invoice.leqi.util.ReqValidation;
import org.springframework.stereotype.Service;

/**
 * 货物运输（FREIGHT）能力服务：承载货物运输支持的全部概念方法。
 * 仅实现 leqi 内部契约 LeqiCapabilityService，不实现统一门面契约 InvoiceCapabilityService。
 * 真实接口使用 Freight* 扩展 DTO / 接口编码枚举，走通桩链路。
 *
 * <p>能力矩阵（依据《乐企数字化电子发票（货物运输）开票能力说明文档-V3.008》）：
 * <ul>
 *   <li>支持：除 queryInvoiceUsage、queryTaxProService、queryHouseSource、queryDiffTaxAuth、batchDownloadApply 外的全部 22 标准概念；</li>
 *   <li>专属：queryDiffTaxCode（查询差额征税编码，替代原差额征税业务授权信息查询）；</li>
 *   <li>不支持：queryInvoiceUsage、queryTaxProService、queryHouseSource、queryDiffTaxAuth、batchDownloadApply、
 *        queryJzfwInfo、crossRegionSingleQuery、crossRegionBatchQuery（后三者为建筑服务专属）。</li>
 * </ul>
 * <p>
 * 类级 {@code @ValidateReq} 与 BASE/SALE 一致，对全部入参执行 jakarta.validation 校验。
 */
@Service
@ValidateReq
@DocVersion(doc = "乐企数字化电子发票（货物运输）开票能力说明文档", value = "V3.008")
public class LeqiInvoiceHwysService implements LeqiCapabilityService {

    private final LeqiHttpUtil httpUtil;

    public LeqiInvoiceHwysService(LeqiHttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Override
    public CapabilityCode capability() {
        return CapabilityCode.FREIGHT;
    }

    /**
     * 获取发票批量预赋码信息（货物运输）。真实实现：调用乐企接口 QDFPPLFM，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GetBatchPreCodeRes> getBatchPreCode(GetBatchPreCodeReq req) {
        return httpUtil.call(FreightInterfaceCode.GET_BATCH_PRE_CODE.getServiceCode(), req, GetBatchPreCodeRes.class);
    }

    /**
     * 查询授信额度（货物运输）。真实实现：调用乐企接口 CXSXED，当前走通桩链路。
     */
    @Override
    public ChannelResponse<QueryQuotaRes> queryQuota(QueryQuotaReq req) {
        return httpUtil.call(FreightInterfaceCode.QUERY_QUOTA.getServiceCode(), req, QueryQuotaRes.class);
    }

    /**
     * 下载/退回授信额度（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(DownloadOrReturnQuotaReq req) {
        return httpUtil.call(FreightInterfaceCode.DOWNLOAD_OR_RETURN_QUOTA.getServiceCode(), req, DownloadOrReturnQuotaRes.class);
    }

    /**
     * 调整授信额度有效期（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(AdjustQuotaValidityReq req) {
        return httpUtil.call(FreightInterfaceCode.ADJUST_QUOTA_VALIDITY.getServiceCode(), req, AdjustQuotaValidityRes.class);
    }

    /**
     * 查询纳税人风险信息（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(QueryTaxpayerRiskReq req) {
        return httpUtil.call(FreightInterfaceCode.QUERY_TAXPAYER_RISK.getServiceCode(), req, QueryTaxpayerRiskRes.class);
    }

    /**
     * 查询纳税人基本信息（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(QueryTaxpayerBasicReq req) {
        return httpUtil.call(FreightInterfaceCode.QUERY_TAXPAYER_BASIC.getServiceCode(), req, QueryTaxpayerBasicRes.class);
    }

    /**
     * 查询可用税率信息（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxRateRes> queryTaxRate(QueryTaxRateReq req) {
        return httpUtil.call(FreightInterfaceCode.QUERY_TAX_RATE.getServiceCode(), req, QueryTaxRateRes.class);
    }

    /**
     * 查询税收分类编码信息（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(QueryTaxCategoryReq req) {
        return httpUtil.call(FreightInterfaceCode.QUERY_TAX_CATEGORY.getServiceCode(), req, QueryTaxCategoryRes.class);
    }

    /**
     * 查询红字确认单明细信息（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(QueryRedConfirmDetailReq req) {
        return httpUtil.call(FreightInterfaceCode.QUERY_RED_CONFIRM_DETAIL.getServiceCode(), req, QueryRedConfirmDetailRes.class);
    }

    /**
     * 发票上传（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。先转为货物运输特定 req 再上送。
     */
    @Override
    public ChannelResponse<UploadInvoiceRes> uploadInvoice(UploadInvoiceReq req) {
        FreightUploadInvoiceReq sreq = FreightUploadInvoiceReq.from(req);
        ReqValidation.requireValid(sreq);
        return httpUtil.call(FreightInterfaceCode.UPLOAD_INVOICE.getServiceCode(), sreq, UploadInvoiceRes.class);
    }

    /**
     * 查询红字确认单列表信息（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(QueryRedConfirmListReq req) {
        return httpUtil.call(FreightInterfaceCode.QUERY_RED_CONFIRM_LIST.getServiceCode(), req, QueryRedConfirmListRes.class);
    }

    /**
     * 查询发票上传结果（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryUploadResultRes> queryUploadResult(QueryUploadResultReq req) {
        return httpUtil.call(FreightInterfaceCode.QUERY_UPLOAD_RESULT.getServiceCode(), req, QueryUploadResultRes.class);
    }

    /**
     * 红字确认单申请（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(ApplyRedConfirmReq req) {
        return httpUtil.call(FreightInterfaceCode.APPLY_RED_CONFIRM.getServiceCode(), req, ApplyRedConfirmRes.class);
    }

    /**
     * 红字确认单确认（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(ConfirmRedConfirmReq req) {
        return httpUtil.call(FreightInterfaceCode.CONFIRM_RED_CONFIRM.getServiceCode(), req, ConfirmRedConfirmRes.class);
    }

    /**
     * 上传发票汇总确认信息（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(UploadSummaryConfirmReq req) {
        return httpUtil.call(FreightInterfaceCode.UPLOAD_SUMMARY_CONFIRM.getServiceCode(), req, UploadSummaryConfirmRes.class);
    }

    /**
     * 查询发票汇总确认信息（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(QuerySummaryConfirmReq req) {
        return httpUtil.call(FreightInterfaceCode.QUERY_SUMMARY_CONFIRM.getServiceCode(), req, QuerySummaryConfirmRes.class);
    }

    /**
     * 扣除凭证清单相关发票数据信息查询（货物运输）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryDeductionVoucherRes> queryDeductionVoucher(QueryDeductionVoucherReq req) {
        return httpUtil.call(FreightInterfaceCode.QUERY_DEDUCTION_VOUCHER.getServiceCode(), req, QueryDeductionVoucherRes.class);
    }

    /**
     * 发票用途状态信息查询（仅基础版/不动产销售/建筑服务，货物运输不涉及）。返回空响应占位。
     */
    @Override
    public ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(QueryInvoiceUsageReq req) {
        throw new UnsupportedOperationException("货物运输无此方法");
    }


    /**
     * 查询差额征税编码（货物运输）。真实实现：调用乐企对应接口 CXCEZSBM。
     */
    @Override
    public ChannelResponse<QueryDiffTaxCodeRes> queryDiffTaxCode(QueryDiffTaxCodeReq req) {
        return httpUtil.call(FreightInterfaceCode.QUERY_DIFF_TAX_CODE.getServiceCode(), req, QueryDiffTaxCodeRes.class);
    }

}
