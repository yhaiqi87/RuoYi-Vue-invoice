package com.yhq.invoice.leqi.service;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.CapabilityCode;
import com.yhq.invoice.common.model.ChannelResponse;

/**
 * 乐企（LEQI）渠道下、按开票能力（BASE / SALE / LEASE）划分的能力服务契约。
 * 独立于统一门面契约 InvoiceCapabilityService：本接口只服务于 leqi 内部，
 * 由 LeqiInvoiceService 按 CapabilityCode 选取对应实现（LeqiInvoiceXxxService）并委托执行。
 * 各实现是一个独立的 Spring Bean，承载该能力下全部 22 个概念方法的实现，便于分别加校验、分别演进。
 */
public interface LeqiCapabilityService {

    /** 本服务归属的开票能力维度（BASE / SALE / LEASE）。 */
    CapabilityCode capability();

    /** 获取发票批量预赋码信息。 */
    ChannelResponse<GetBatchPreCodeRes> getBatchPreCode(GetBatchPreCodeReq req);

    /** 查询额度（基础版发票额度 / 不动产销售授信额度）。 */
    ChannelResponse<QueryQuotaRes> queryQuota(QueryQuotaReq req);

    /** 下载/退回额度。 */
    ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(DownloadOrReturnQuotaReq req);

    /** 调整额度有效期。 */
    ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(AdjustQuotaValidityReq req);

    /** 查询纳税人风险信息。 */
    ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(QueryTaxpayerRiskReq req);

    /** 查询纳税人基本信息。 */
    ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(QueryTaxpayerBasicReq req);

    /** 查询可用税率信息。 */
    ChannelResponse<QueryTaxRateRes> queryTaxRate(QueryTaxRateReq req);

    /** 查询税收分类编码信息。 */
    ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(QueryTaxCategoryReq req);

    /** 查询红字确认单明细信息。 */
    ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(QueryRedConfirmDetailReq req);

    /** 发票上传。 */
    ChannelResponse<UploadInvoiceRes> uploadInvoice(UploadInvoiceReq req);

    /** 查询红字确认单列表信息。 */
    ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(QueryRedConfirmListReq req);

    /** 查询发票上传结果。 */
    ChannelResponse<QueryUploadResultRes> queryUploadResult(QueryUploadResultReq req);

    /** 红字确认单申请。 */
    ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(ApplyRedConfirmReq req);

    /** 红字确认单确认。 */
    ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(ConfirmRedConfirmReq req);

    /** 上传发票汇总确认信息。 */
    ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(UploadSummaryConfirmReq req);

    /** 查询发票汇总确认信息。 */
    ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(QuerySummaryConfirmReq req);

    /** 发票用途状态信息查询。 */
    ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(QueryInvoiceUsageReq req);

    /**
     * 扣除凭证清单相关发票数据信息查询（仅基础版/商品条码/不动产销售/建筑服务/货物运输）。
     * 非对应能力默认不支持。
     */
    default ChannelResponse<QueryDeductionVoucherRes> queryDeductionVoucher(QueryDeductionVoucherReq req) {
        throw new UnsupportedOperationException("非本能力专属方法，不支持");
    }

    /**
     * 查询涉税专业服务相关信息（仅基础版/商品条码）。非对应能力默认不支持。
     */
    default ChannelResponse<QueryTaxProServiceRes> queryTaxProService(QueryTaxProServiceReq req) {
        throw new UnsupportedOperationException("非本能力专属方法，不支持");
    }

    /**
     * 原差额征税业务授权信息查询（仅基础版/建筑服务/商品条码）。非对应能力默认不支持。
     */
    default ChannelResponse<QueryDiffTaxAuthRes> queryDiffTaxAuth(QueryDiffTaxAuthReq req) {
        throw new UnsupportedOperationException("非本能力专属方法，不支持");
    }

    /**
     * 批量发票下载申请（仅不动产销售/不动产经营租赁/建筑服务/成品油）。非对应能力默认不支持。
     */
    default ChannelResponse<BatchDownloadApplyRes> batchDownloadApply(BatchDownloadApplyReq req) {
        throw new UnsupportedOperationException("非本能力专属方法，不支持");
    }

    /**
     * 查询房源信息（仅不动产销售）。非对应能力默认不支持。
     */
    default ChannelResponse<QueryHouseSourceRes> queryHouseSource(QueryHouseSourceReq req) {
        throw new UnsupportedOperationException("非本能力专属方法，不支持");
    }

    /**
     * 查询建筑服务信息（仅建筑服务）。非对应能力默认不支持。
     */
    default ChannelResponse<QueryJzfwInfoRes> queryJzfwInfo(QueryJzfwInfoReq req) {
        throw new UnsupportedOperationException("非本能力专属方法，不支持");
    }

    /**
     * 跨区域涉税数据单笔查询（仅建筑服务）。非对应能力默认不支持。
     */
    default ChannelResponse<CrossRegionSingleRes> crossRegionSingleQuery(CrossRegionSingleReq req) {
        throw new UnsupportedOperationException("非本能力专属方法，不支持");
    }

    /**
     * 跨区域涉税数据批量查询（仅建筑服务）。非对应能力默认不支持。
     */
    default ChannelResponse<CrossRegionBatchRes> crossRegionBatchQuery(CrossRegionBatchReq req) {
        throw new UnsupportedOperationException("非本能力专属方法，不支持");
    }

    /**
     * 查询差额征税编码（仅货物运输/成品油）。非对应能力默认不支持。
     */
    default ChannelResponse<QueryDiffTaxCodeRes> queryDiffTaxCode(QueryDiffTaxCodeReq req) {
        throw new UnsupportedOperationException("非本能力专属方法，不支持");
    }

    /**
     * 自然人卖方限制名单阻断查询（仅二手车）。非对应能力默认不支持。
     */
    default ChannelResponse<QuerySellerBlockRes> querySellerBlock(QuerySellerBlockReq req) {
        throw new UnsupportedOperationException("非本能力专属方法，不支持");
    }

    /**
     * 可换开二手车销售统一发票批量查询（仅二手车）。非对应能力默认不支持。
     */
    default ChannelResponse<QueryEscExchangeableBatchRes> queryEscExchangeableBatch(QueryEscExchangeableBatchReq req) {
        throw new UnsupportedOperationException("非本能力专属方法，不支持");
    }

    /**
     * 二手车销售统一发票换开信息单笔查询（仅二手车）。非对应能力默认不支持。
     */
    default ChannelResponse<QueryEscExchangeInfoRes> queryEscExchangeInfo(QueryEscExchangeInfoReq req) {
        throw new UnsupportedOperationException("非本能力专属方法，不支持");
    }

    /**
     * 二手车销售统一发票上传（仅二手车，报文结构与数电票上传不同）。非对应能力默认不支持。
     */
    default ChannelResponse<UploadEscInvoiceRes> uploadEscInvoice(EscUploadInvoiceReq req) {
        throw new UnsupportedOperationException("非本能力专属方法，不支持");
    }

    /**
     * 查询自然人开票信息（仅反向开票通用）。非对应能力默认不支持。
     */
    default ChannelResponse<QueryNaturalPersonInvoicingRes> queryNaturalPersonInvoicing(QueryNaturalPersonInvoicingReq req) {
        throw new UnsupportedOperationException("非本能力专属方法，不支持");
    }

    /**
     * 查询成品油可用税收分类编码信息（仅成品油）。非成品油能力默认不支持。
     */
    default ChannelResponse<QueryTaxCategoryRes> queryCpyTaxCategory(CpyQueryTaxCategoryReq req) {
        throw new UnsupportedOperationException("非成品油能力无此方法");
    }

    /**
     * 查询成品油库存（仅成品油）。非成品油能力默认不支持。
     */
    default ChannelResponse<CpyQueryInventoryRes> queryCpyInventory(CpyQueryInventoryReq req) {
        throw new UnsupportedOperationException("非成品油能力无此方法");
    }

    /**
     * 下载或退回成品油库存（仅成品油）。非成品油能力默认不支持。
     */
    default ChannelResponse<CpyDownloadOrReturnInventoryRes> downloadOrReturnCpyInventory(CpyDownloadOrReturnInventoryReq req) {
        throw new UnsupportedOperationException("非成品油能力无此方法");
    }
}
