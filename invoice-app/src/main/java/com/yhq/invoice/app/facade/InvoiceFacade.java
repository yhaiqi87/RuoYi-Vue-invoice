package com.yhq.invoice.app.facade;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.exception.InvoiceException;
import com.yhq.invoice.common.model.ChannelResponse;
import com.yhq.invoice.common.service.InvoiceCapabilityService;
import com.yhq.invoice.leqi.service.LeqiInvoiceService;
import com.yhq.invoice.rpa.service.RpaInvoiceService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * app 门面层：按 Channel 枚举参数选择对应渠道实现（leqi / rpa）。
 * 只做「选渠道」这一层判断，能力维度（BASE / SALE / LEASE）的拆分完全在 leqi 内部完成。
 * 门面直接持有 RpaInvoiceService 与 LeqiInvoiceService 两个渠道实现，按 channel() 建立路由表。
 */
@Service
public class InvoiceFacade {

    /**
     * 默认渠道：乐企（LEQI）。重载方法在未显式传入 Channel 时统一走此渠道。
     */
    private static final Channel DEFAULT_CHANNEL = Channel.LEQI;

    private final Map<Channel, InvoiceCapabilityService> services;

    /** 注入两个渠道实现并构建 Channel -> 渠道服务的路由表。 */
    public InvoiceFacade(RpaInvoiceService rpaInvoiceService, LeqiInvoiceService leqiInvoiceService) {
        this.services = Map.of(
                rpaInvoiceService.channel(), rpaInvoiceService,
                leqiInvoiceService.channel(), leqiInvoiceService);
    }

    /** 按 Channel 选出对应的渠道服务；无匹配实现时抛异常。 */
    private InvoiceCapabilityService select(Channel channel) {
        InvoiceCapabilityService svc = services.get(channel);
        if (svc == null) {
            throw new InvoiceException("无对应渠道实现: " + channel);
        }
        return svc;
    }

    /** 获取发票批量预赋码信息：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<GetBatchPreCodeRes> getBatchPreCode(Channel channel, GetBatchPreCodeReq req) {
        return select(channel).getBatchPreCode(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code getBatchPreCode(GetBatchPreCodeReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<GetBatchPreCodeRes> getBatchPreCode(GetBatchPreCodeReq req) {
        return getBatchPreCode(DEFAULT_CHANNEL, req);
    }


    /** 查询额度：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<QueryQuotaRes> queryQuota(Channel channel, QueryQuotaReq req) {
        return select(channel).queryQuota(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code queryQuota(QueryQuotaReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<QueryQuotaRes> queryQuota(QueryQuotaReq req) {
        return queryQuota(DEFAULT_CHANNEL, req);
    }


    /** 下载/退回额度：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(Channel channel, DownloadOrReturnQuotaReq req) {
        return select(channel).downloadOrReturnQuota(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code downloadOrReturnQuota(DownloadOrReturnQuotaReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(DownloadOrReturnQuotaReq req) {
        return downloadOrReturnQuota(DEFAULT_CHANNEL, req);
    }


    /** 调整额度有效期：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(Channel channel, AdjustQuotaValidityReq req) {
        return select(channel).adjustQuotaValidity(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code adjustQuotaValidity(AdjustQuotaValidityReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(AdjustQuotaValidityReq req) {
        return adjustQuotaValidity(DEFAULT_CHANNEL, req);
    }


    /** 查询纳税人风险信息：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(Channel channel, QueryTaxpayerRiskReq req) {
        return select(channel).queryTaxpayerRisk(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code queryTaxpayerRisk(QueryTaxpayerRiskReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(QueryTaxpayerRiskReq req) {
        return queryTaxpayerRisk(DEFAULT_CHANNEL, req);
    }


    /** 查询纳税人基本信息：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(Channel channel, QueryTaxpayerBasicReq req) {
        return select(channel).queryTaxpayerBasic(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code queryTaxpayerBasic(QueryTaxpayerBasicReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(QueryTaxpayerBasicReq req) {
        return queryTaxpayerBasic(DEFAULT_CHANNEL, req);
    }


    /** 查询可用税率信息：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<QueryTaxRateRes> queryTaxRate(Channel channel, QueryTaxRateReq req) {
        return select(channel).queryTaxRate(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code queryTaxRate(QueryTaxRateReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<QueryTaxRateRes> queryTaxRate(QueryTaxRateReq req) {
        return queryTaxRate(DEFAULT_CHANNEL, req);
    }


    /** 查询税收分类编码信息：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(Channel channel, QueryTaxCategoryReq req) {
        return select(channel).queryTaxCategory(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code queryTaxCategory(QueryTaxCategoryReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(QueryTaxCategoryReq req) {
        return queryTaxCategory(DEFAULT_CHANNEL, req);
    }


    /** 查询红字确认单明细信息：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(Channel channel, QueryRedConfirmDetailReq req) {
        return select(channel).queryRedConfirmDetail(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code queryRedConfirmDetail(QueryRedConfirmDetailReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(QueryRedConfirmDetailReq req) {
        return queryRedConfirmDetail(DEFAULT_CHANNEL, req);
    }


    /** 发票上传：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<UploadInvoiceRes> uploadInvoice(Channel channel, UploadInvoiceReq req) {
        return select(channel).uploadInvoice(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code uploadInvoice(UploadInvoiceReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<UploadInvoiceRes> uploadInvoice(UploadInvoiceReq req) {
        return uploadInvoice(DEFAULT_CHANNEL, req);
    }


    /** 查询红字确认单列表信息：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(Channel channel, QueryRedConfirmListReq req) {
        return select(channel).queryRedConfirmList(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code queryRedConfirmList(QueryRedConfirmListReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(QueryRedConfirmListReq req) {
        return queryRedConfirmList(DEFAULT_CHANNEL, req);
    }


    /** 查询发票上传结果：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<QueryUploadResultRes> queryUploadResult(Channel channel, QueryUploadResultReq req) {
        return select(channel).queryUploadResult(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code queryUploadResult(QueryUploadResultReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<QueryUploadResultRes> queryUploadResult(QueryUploadResultReq req) {
        return queryUploadResult(DEFAULT_CHANNEL, req);
    }


    /** 红字确认单申请：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(Channel channel, ApplyRedConfirmReq req) {
        return select(channel).applyRedConfirm(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code applyRedConfirm(ApplyRedConfirmReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(ApplyRedConfirmReq req) {
        return applyRedConfirm(DEFAULT_CHANNEL, req);
    }


    /** 红字确认单确认：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(Channel channel, ConfirmRedConfirmReq req) {
        return select(channel).confirmRedConfirm(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code confirmRedConfirm(ConfirmRedConfirmReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(ConfirmRedConfirmReq req) {
        return confirmRedConfirm(DEFAULT_CHANNEL, req);
    }


    /** 上传发票汇总确认信息：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(Channel channel, UploadSummaryConfirmReq req) {
        return select(channel).uploadSummaryConfirm(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code uploadSummaryConfirm(UploadSummaryConfirmReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(UploadSummaryConfirmReq req) {
        return uploadSummaryConfirm(DEFAULT_CHANNEL, req);
    }


    /** 查询发票汇总确认信息：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(Channel channel, QuerySummaryConfirmReq req) {
        return select(channel).querySummaryConfirm(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code querySummaryConfirm(QuerySummaryConfirmReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(QuerySummaryConfirmReq req) {
        return querySummaryConfirm(DEFAULT_CHANNEL, req);
    }


    /** 发票用途状态信息查询：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(Channel channel, QueryInvoiceUsageReq req) {
        return select(channel).queryInvoiceUsage(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code queryInvoiceUsage(QueryInvoiceUsageReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(QueryInvoiceUsageReq req) {
        return queryInvoiceUsage(DEFAULT_CHANNEL, req);
    }


    /** 扣除凭证清单相关发票数据信息查询：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<QueryDeductionVoucherRes> queryDeductionVoucher(Channel channel, QueryDeductionVoucherReq req) {
        return select(channel).queryDeductionVoucher(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code queryDeductionVoucher(QueryDeductionVoucherReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<QueryDeductionVoucherRes> queryDeductionVoucher(QueryDeductionVoucherReq req) {
        return queryDeductionVoucher(DEFAULT_CHANNEL, req);
    }


    /** 查询涉税专业服务相关信息：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<QueryTaxProServiceRes> queryTaxProService(Channel channel, QueryTaxProServiceReq req) {
        return select(channel).queryTaxProService(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code queryTaxProService(QueryTaxProServiceReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<QueryTaxProServiceRes> queryTaxProService(QueryTaxProServiceReq req) {
        return queryTaxProService(DEFAULT_CHANNEL, req);
    }


    /** 原差额征税业务授权信息查询：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<QueryDiffTaxAuthRes> queryDiffTaxAuth(Channel channel, QueryDiffTaxAuthReq req) {
        return select(channel).queryDiffTaxAuth(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code queryDiffTaxAuth(QueryDiffTaxAuthReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<QueryDiffTaxAuthRes> queryDiffTaxAuth(QueryDiffTaxAuthReq req) {
        return queryDiffTaxAuth(DEFAULT_CHANNEL, req);
    }


    /** 批量发票下载申请：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<BatchDownloadApplyRes> batchDownloadApply(Channel channel, BatchDownloadApplyReq req) {
        return select(channel).batchDownloadApply(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code batchDownloadApply(BatchDownloadApplyReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<BatchDownloadApplyRes> batchDownloadApply(BatchDownloadApplyReq req) {
        return batchDownloadApply(DEFAULT_CHANNEL, req);
    }


    /** 查询房源信息：按 Channel 选渠道后委托其执行。 */
    public ChannelResponse<QueryHouseSourceRes> queryHouseSource(Channel channel, QueryHouseSourceReq req) {
        return select(channel).queryHouseSource(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code queryHouseSource(QueryHouseSourceReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<QueryHouseSourceRes> queryHouseSource(QueryHouseSourceReq req) {
        return queryHouseSource(DEFAULT_CHANNEL, req);
    }


    /**
     * 查询建筑服务信息：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<QueryJzfwInfoRes> queryJzfwInfo(Channel channel, QueryJzfwInfoReq req) {
        return select(channel).queryJzfwInfo(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code queryJzfwInfo(QueryJzfwInfoReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<QueryJzfwInfoRes> queryJzfwInfo(QueryJzfwInfoReq req) {
        return queryJzfwInfo(DEFAULT_CHANNEL, req);
    }


    /**
     * 跨区域涉税数据单笔查询：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<CrossRegionSingleRes> crossRegionSingleQuery(Channel channel, CrossRegionSingleReq req) {
        return select(channel).crossRegionSingleQuery(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code crossRegionSingleQuery(CrossRegionSingleReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<CrossRegionSingleRes> crossRegionSingleQuery(CrossRegionSingleReq req) {
        return crossRegionSingleQuery(DEFAULT_CHANNEL, req);
    }


    /**
     * 跨区域涉税数据批量查询：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<CrossRegionBatchRes> crossRegionBatchQuery(Channel channel, CrossRegionBatchReq req) {
        return select(channel).crossRegionBatchQuery(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code crossRegionBatchQuery(CrossRegionBatchReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<CrossRegionBatchRes> crossRegionBatchQuery(CrossRegionBatchReq req) {
        return crossRegionBatchQuery(DEFAULT_CHANNEL, req);
    }


    /**
     * 查询差额征税编码：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<QueryDiffTaxCodeRes> queryDiffTaxCode(Channel channel, QueryDiffTaxCodeReq req) {
        return select(channel).queryDiffTaxCode(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code queryDiffTaxCode(QueryDiffTaxCodeReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<QueryDiffTaxCodeRes> queryDiffTaxCode(QueryDiffTaxCodeReq req) {
        return queryDiffTaxCode(DEFAULT_CHANNEL, req);
    }


    /**
     * 自然人卖方限制名单阻断查询：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<QuerySellerBlockRes> querySellerBlock(Channel channel, QuerySellerBlockReq req) {
        return select(channel).querySellerBlock(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code querySellerBlock(QuerySellerBlockReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<QuerySellerBlockRes> querySellerBlock(QuerySellerBlockReq req) {
        return querySellerBlock(DEFAULT_CHANNEL, req);
    }


    /**
     * 可换开二手车销售统一发票批量查询：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<QueryEscExchangeableBatchRes> queryEscExchangeableBatch(Channel channel, QueryEscExchangeableBatchReq req) {
        return select(channel).queryEscExchangeableBatch(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code queryEscExchangeableBatch(QueryEscExchangeableBatchReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<QueryEscExchangeableBatchRes> queryEscExchangeableBatch(QueryEscExchangeableBatchReq req) {
        return queryEscExchangeableBatch(DEFAULT_CHANNEL, req);
    }


    /**
     * 二手车销售统一发票换开信息单笔查询：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<QueryEscExchangeInfoRes> queryEscExchangeInfo(Channel channel, QueryEscExchangeInfoReq req) {
        return select(channel).queryEscExchangeInfo(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code queryEscExchangeInfo(QueryEscExchangeInfoReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<QueryEscExchangeInfoRes> queryEscExchangeInfo(QueryEscExchangeInfoReq req) {
        return queryEscExchangeInfo(DEFAULT_CHANNEL, req);
    }


    /**
     * 二手车销售统一发票上传：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<UploadEscInvoiceRes> uploadEscInvoice(Channel channel, EscUploadInvoiceReq req) {
        return select(channel).uploadEscInvoice(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code uploadEscInvoice(EscUploadInvoiceReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<UploadEscInvoiceRes> uploadEscInvoice(EscUploadInvoiceReq req) {
        return uploadEscInvoice(DEFAULT_CHANNEL, req);
    }


    /**
     * 查询自然人开票信息：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<QueryNaturalPersonInvoicingRes> queryNaturalPersonInvoicing(Channel channel, QueryNaturalPersonInvoicingReq req) {
        return select(channel).queryNaturalPersonInvoicing(req);
    }

    /**
     * 默认乐企(LEQI)渠道：等价调用 {@code queryNaturalPersonInvoicing(QueryNaturalPersonInvoicingReq)} 并传入 Channel.LEQI。
     */
    public ChannelResponse<QueryNaturalPersonInvoicingRes> queryNaturalPersonInvoicing(QueryNaturalPersonInvoicingReq req) {
        return queryNaturalPersonInvoicing(DEFAULT_CHANNEL, req);
    }


    /**
     * 查询成品油可用税收分类编码信息：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<QueryTaxCategoryRes> queryCpyTaxCategory(Channel channel, CpyQueryTaxCategoryReq req) {
        return select(channel).queryCpyTaxCategory(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code queryCpyTaxCategory(CpyQueryTaxCategoryReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<QueryTaxCategoryRes> queryCpyTaxCategory(CpyQueryTaxCategoryReq req) {
        return queryCpyTaxCategory(DEFAULT_CHANNEL, req);
    }


    /**
     * 查询成品油库存：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<CpyQueryInventoryRes> queryCpyInventory(Channel channel, CpyQueryInventoryReq req) {
        return select(channel).queryCpyInventory(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code queryCpyInventory(CpyQueryInventoryReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<CpyQueryInventoryRes> queryCpyInventory(CpyQueryInventoryReq req) {
        return queryCpyInventory(DEFAULT_CHANNEL, req);
    }


    /**
     * 下载或退回成品油库存：按 Channel 选渠道后委托其执行。
     */
    public ChannelResponse<CpyDownloadOrReturnInventoryRes> downloadOrReturnCpyInventory(Channel channel, CpyDownloadOrReturnInventoryReq req) {
        return select(channel).downloadOrReturnCpyInventory(req);
    }

    /** 默认乐企(LEQI)渠道：等价调用 {@code downloadOrReturnCpyInventory(CpyDownloadOrReturnInventoryReq)} 并传入 Channel.LEQI。 */
    public ChannelResponse<CpyDownloadOrReturnInventoryRes> downloadOrReturnCpyInventory(CpyDownloadOrReturnInventoryReq req) {
        return downloadOrReturnCpyInventory(DEFAULT_CHANNEL, req);
    }

}
