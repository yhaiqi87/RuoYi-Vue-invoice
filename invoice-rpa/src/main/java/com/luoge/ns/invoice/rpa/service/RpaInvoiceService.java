package com.luoge.ns.invoice.rpa.service;

import com.luoge.ns.invoice.common.client.InvoiceHttpClient;
import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.Channel;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import com.luoge.ns.invoice.common.service.InvoiceCapabilityService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * rpa 渠道实现：门面按 Channel=RPA 选中本服务。
 * 当前参考 leqi，对两个已落地的接口提供示例（demo）实现：
 *   - getBatchPreCode（获取发票批量预赋码信息）
 *   - queryQuota（查询额度）
 * 其余 20 个概念方法尚未接入 RPA 流程，统一抛出 UnsupportedOperationException 占位，
 * 待后续接入 RPA 流程时再逐接口实现。
 */
@Service
public class RpaInvoiceService implements InvoiceCapabilityService {

    private final InvoiceHttpClient httpClient;

    public RpaInvoiceService(@Qualifier("rpaHttpClient") InvoiceHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Channel channel() {
        return Channel.RPA;
    }

    /** 获取发票批量预赋码信息（RPA demo）。委托 RpaHttpClient 返回示例数据。 */
    @Override
    public ChannelResponse<GetBatchPreCodeRes> getBatchPreCode(GetBatchPreCodeReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 查询额度（RPA demo）。委托 RpaHttpClient 返回示例数据。 */
    @Override
    public ChannelResponse<QueryQuotaRes> queryQuota(QueryQuotaReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 下载/退回额度（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(DownloadOrReturnQuotaReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 调整额度有效期（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(AdjustQuotaValidityReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 查询纳税人风险信息（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(QueryTaxpayerRiskReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 查询纳税人基本信息（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(QueryTaxpayerBasicReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 查询可用税率信息（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<QueryTaxRateRes> queryTaxRate(QueryTaxRateReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 查询税收分类编码信息（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(QueryTaxCategoryReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 查询红字确认单明细信息（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(QueryRedConfirmDetailReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 发票上传（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<UploadInvoiceRes> uploadInvoice(UploadInvoiceReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 查询红字确认单列表信息（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(QueryRedConfirmListReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 查询发票上传结果（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<QueryUploadResultRes> queryUploadResult(QueryUploadResultReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 红字确认单申请（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(ApplyRedConfirmReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 红字确认单确认（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(ConfirmRedConfirmReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 上传发票汇总确认信息（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(UploadSummaryConfirmReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 查询发票汇总确认信息（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(QuerySummaryConfirmReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 发票用途状态信息查询（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(QueryInvoiceUsageReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 扣除凭证清单相关发票数据信息查询（RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<QueryDeductionVoucherRes> queryDeductionVoucher(QueryDeductionVoucherReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 查询涉税专业服务相关信息（仅基础版，RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<QueryTaxProServiceRes> queryTaxProService(QueryTaxProServiceReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 原差额征税业务授权信息查询（仅基础版，RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<QueryDiffTaxAuthRes> queryDiffTaxAuth(QueryDiffTaxAuthReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 批量发票下载申请（仅不动产销售，RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<BatchDownloadApplyRes> batchDownloadApply(BatchDownloadApplyReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /** 查询房源信息（仅不动产销售，RPA）。渠道尚未实现，直接抛异常占位。 */
    @Override
    public ChannelResponse<QueryHouseSourceRes> queryHouseSource(QueryHouseSourceReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /**
     * 查询建筑服务信息（仅建筑服务，RPA）。渠道尚未实现，直接抛异常占位。
     */
    @Override
    public ChannelResponse<QueryJzfwInfoRes> queryJzfwInfo(QueryJzfwInfoReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /**
     * 跨区域涉税数据单笔查询（仅建筑服务，RPA）。渠道尚未实现，直接抛异常占位。
     */
    @Override
    public ChannelResponse<CrossRegionSingleRes> crossRegionSingleQuery(CrossRegionSingleReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /**
     * 跨区域涉税数据批量查询（仅建筑服务，RPA）。渠道尚未实现，直接抛异常占位。
     */
    @Override
    public ChannelResponse<CrossRegionBatchRes> crossRegionBatchQuery(CrossRegionBatchReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /**
     * 查询差额征税编码（仅货物运输，RPA）。渠道尚未实现，直接抛异常占位。
     */
    @Override
    public ChannelResponse<QueryDiffTaxCodeRes> queryDiffTaxCode(QueryDiffTaxCodeReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /**
     * 自然人卖方限制名单阻断查询（仅二手车，RPA）。渠道尚未实现，直接抛异常占位。
     */
    @Override
    public ChannelResponse<QuerySellerBlockRes> querySellerBlock(QuerySellerBlockReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /**
     * 可换开二手车销售统一发票批量查询（仅二手车，RPA）。渠道尚未实现，直接抛异常占位。
     */
    @Override
    public ChannelResponse<QueryEscExchangeableBatchRes> queryEscExchangeableBatch(QueryEscExchangeableBatchReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /**
     * 二手车销售统一发票换开信息单笔查询（仅二手车，RPA）。渠道尚未实现，直接抛异常占位。
     */
    @Override
    public ChannelResponse<QueryEscExchangeInfoRes> queryEscExchangeInfo(QueryEscExchangeInfoReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /**
     * 二手车销售统一发票上传（仅二手车，RPA）。渠道尚未实现，直接抛异常占位。
     */
    @Override
    public ChannelResponse<UploadEscInvoiceRes> uploadEscInvoice(EscUploadInvoiceReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /**
     * 查询自然人开票信息（仅反向开票通用，RPA）。渠道尚未实现，直接抛异常占位。
     */
    @Override
    public ChannelResponse<QueryNaturalPersonInvoicingRes> queryNaturalPersonInvoicing(QueryNaturalPersonInvoicingReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /**
     * 查询成品油可用税收分类编码信息（仅成品油，RPA）。渠道尚未实现，直接抛异常占位。
     */
    @Override
    public ChannelResponse<QueryTaxCategoryRes> queryCpyTaxCategory(CpyQueryTaxCategoryReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /**
     * 查询成品油库存（仅成品油，RPA）。渠道尚未实现，直接抛异常占位。
     */
    @Override
    public ChannelResponse<CpyQueryInventoryRes> queryCpyInventory(CpyQueryInventoryReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }

    /**
     * 下载或退回成品油库存（仅成品油，RPA）。渠道尚未实现，直接抛异常占位。
     */
    @Override
    public ChannelResponse<CpyDownloadOrReturnInventoryRes> downloadOrReturnCpyInventory(CpyDownloadOrReturnInventoryReq req) {
        throw new UnsupportedOperationException("RPA 渠道尚未实现");
    }
}
