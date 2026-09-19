package com.luoge.ns.invoice.leqi.service;

import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.CapabilityCode;
import com.luoge.ns.invoice.common.enums.Channel;
import com.luoge.ns.invoice.common.exception.InvoiceException;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import com.luoge.ns.invoice.common.service.InvoiceCapabilityService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 乐企（LEQI）渠道的统一入口，实现 InvoiceCapabilityService，供 app 门面按 Channel 选取。
 * 门面只按 Channel 选到本服务；能力维度（BASE / SALE / LEASE）的拆分与判断都在本服务内部完成：
 * 按请求的 CapabilityCode 直接选取对应的能力服务（LeqiInvoiceBaseService / LeqiInvoiceBdcxsService ...）并委托其执行。
 * 结构：收集 List<LeqiCapabilityService> -> Map<CapabilityCode, LeqiInvoiceXxxService>。
 */
@Component
public class LeqiInvoiceService implements InvoiceCapabilityService {

    private final Map<CapabilityCode, LeqiCapabilityService> registry;

    public LeqiInvoiceService(List<LeqiCapabilityService> services) {
        this.registry = services.stream()
                .collect(Collectors.toMap(LeqiCapabilityService::capability, s -> s));
    }

    @Override
    public Channel channel() {
        return Channel.LEQI;
    }

    /** 按能力维度直接选取对应的能力服务（LeqiInvoiceXxxService）。capabilityCode 为空或不受支持时抛异常。 */
    private LeqiCapabilityService route(CapabilityCode cap) {
        if (cap == null) {
            throw new InvoiceException("capabilityCode 必填");
        }
        LeqiCapabilityService svc = registry.get(cap);
        if (svc == null) {
            throw new InvoiceException("不支持的开票能力: " + cap);
        }
        return svc;
    }

    /** 获取发票批量预赋码信息：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<GetBatchPreCodeRes> getBatchPreCode(GetBatchPreCodeReq req) {
        return route(req.getCapabilityCode()).getBatchPreCode(req);
    }

    /** 查询额度（基础版发票额度 / 不动产销售授信额度）：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<QueryQuotaRes> queryQuota(QueryQuotaReq req) {
        return route(req.getCapabilityCode()).queryQuota(req);
    }

    /** 下载/退回额度：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(DownloadOrReturnQuotaReq req) {
        return route(req.getCapabilityCode()).downloadOrReturnQuota(req);
    }

    /** 调整额度有效期：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(AdjustQuotaValidityReq req) {
        return route(req.getCapabilityCode()).adjustQuotaValidity(req);
    }

    /** 查询纳税人风险信息：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(QueryTaxpayerRiskReq req) {
        return route(req.getCapabilityCode()).queryTaxpayerRisk(req);
    }

    /** 查询纳税人基本信息：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(QueryTaxpayerBasicReq req) {
        return route(req.getCapabilityCode()).queryTaxpayerBasic(req);
    }

    /** 查询可用税率信息：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<QueryTaxRateRes> queryTaxRate(QueryTaxRateReq req) {
        return route(req.getCapabilityCode()).queryTaxRate(req);
    }

    /** 查询税收分类编码信息：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(QueryTaxCategoryReq req) {
        return route(req.getCapabilityCode()).queryTaxCategory(req);
    }

    /** 查询红字确认单明细信息：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(QueryRedConfirmDetailReq req) {
        return route(req.getCapabilityCode()).queryRedConfirmDetail(req);
    }

    /** 发票上传：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<UploadInvoiceRes> uploadInvoice(UploadInvoiceReq req) {
        return route(req.getCapabilityCode()).uploadInvoice(req);
    }

    /** 查询红字确认单列表信息：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(QueryRedConfirmListReq req) {
        return route(req.getCapabilityCode()).queryRedConfirmList(req);
    }

    /** 查询发票上传结果：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<QueryUploadResultRes> queryUploadResult(QueryUploadResultReq req) {
        return route(req.getCapabilityCode()).queryUploadResult(req);
    }

    /** 红字确认单申请：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(ApplyRedConfirmReq req) {
        return route(req.getCapabilityCode()).applyRedConfirm(req);
    }

    /** 红字确认单确认：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(ConfirmRedConfirmReq req) {
        return route(req.getCapabilityCode()).confirmRedConfirm(req);
    }

    /** 上传发票汇总确认信息：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(UploadSummaryConfirmReq req) {
        return route(req.getCapabilityCode()).uploadSummaryConfirm(req);
    }

    /** 查询发票汇总确认信息：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(QuerySummaryConfirmReq req) {
        return route(req.getCapabilityCode()).querySummaryConfirm(req);
    }

    /** 发票用途状态信息查询：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(QueryInvoiceUsageReq req) {
        return route(req.getCapabilityCode()).queryInvoiceUsage(req);
    }

    /** 扣除凭证清单相关发票数据信息查询：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<QueryDeductionVoucherRes> queryDeductionVoucher(QueryDeductionVoucherReq req) {
        return route(req.getCapabilityCode()).queryDeductionVoucher(req);
    }

    /** 查询涉税专业服务相关信息：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<QueryTaxProServiceRes> queryTaxProService(QueryTaxProServiceReq req) {
        return route(req.getCapabilityCode()).queryTaxProService(req);
    }

    /** 原差额征税业务授权信息查询：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<QueryDiffTaxAuthRes> queryDiffTaxAuth(QueryDiffTaxAuthReq req) {
        return route(req.getCapabilityCode()).queryDiffTaxAuth(req);
    }

    /** 批量发票下载申请：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<BatchDownloadApplyRes> batchDownloadApply(BatchDownloadApplyReq req) {
        return route(req.getCapabilityCode()).batchDownloadApply(req);
    }

    /** 查询房源信息：按 capabilityCode 路由到对应能力服务执行。 */
    @Override
    public ChannelResponse<QueryHouseSourceRes> queryHouseSource(QueryHouseSourceReq req) {
        return route(req.getCapabilityCode()).queryHouseSource(req);
    }

    /**
     * 查询建筑服务信息：按 capabilityCode 路由到对应能力服务执行。
     */
    @Override
    public ChannelResponse<QueryJzfwInfoRes> queryJzfwInfo(QueryJzfwInfoReq req) {
        return route(req.getCapabilityCode()).queryJzfwInfo(req);
    }

    /**
     * 跨区域涉税数据单笔查询：按 capabilityCode 路由到对应能力服务执行。
     */
    @Override
    public ChannelResponse<CrossRegionSingleRes> crossRegionSingleQuery(CrossRegionSingleReq req) {
        return route(req.getCapabilityCode()).crossRegionSingleQuery(req);
    }

    /**
     * 跨区域涉税数据批量查询：按 capabilityCode 路由到对应能力服务执行。
     */
    @Override
    public ChannelResponse<CrossRegionBatchRes> crossRegionBatchQuery(CrossRegionBatchReq req) {
        return route(req.getCapabilityCode()).crossRegionBatchQuery(req);
    }

    /**
     * 查询差额征税编码：按 capabilityCode 路由到对应能力服务执行。
     */
    @Override
    public ChannelResponse<QueryDiffTaxCodeRes> queryDiffTaxCode(QueryDiffTaxCodeReq req) {
        return route(req.getCapabilityCode()).queryDiffTaxCode(req);
    }

    /**
     * 自然人卖方限制名单阻断查询：按 capabilityCode 路由到对应能力服务执行。
     */
    @Override
    public ChannelResponse<QuerySellerBlockRes> querySellerBlock(QuerySellerBlockReq req) {
        return route(req.getCapabilityCode()).querySellerBlock(req);
    }

    /**
     * 可换开二手车销售统一发票批量查询：按 capabilityCode 路由到对应能力服务执行。
     */
    @Override
    public ChannelResponse<QueryEscExchangeableBatchRes> queryEscExchangeableBatch(QueryEscExchangeableBatchReq req) {
        return route(req.getCapabilityCode()).queryEscExchangeableBatch(req);
    }

    /**
     * 二手车销售统一发票换开信息单笔查询：按 capabilityCode 路由到对应能力服务执行。
     */
    @Override
    public ChannelResponse<QueryEscExchangeInfoRes> queryEscExchangeInfo(QueryEscExchangeInfoReq req) {
        return route(req.getCapabilityCode()).queryEscExchangeInfo(req);
    }

    /**
     * 二手车销售统一发票上传：按 capabilityCode 路由到对应能力服务执行。
     */
    @Override
    public ChannelResponse<UploadEscInvoiceRes> uploadEscInvoice(EscUploadInvoiceReq req) {
        return route(req.getCapabilityCode()).uploadEscInvoice(req);
    }

    /**
     * 查询自然人开票信息：按 capabilityCode 路由到对应能力服务执行。
     */
    @Override
    public ChannelResponse<QueryNaturalPersonInvoicingRes> queryNaturalPersonInvoicing(QueryNaturalPersonInvoicingReq req) {
        return route(req.getCapabilityCode()).queryNaturalPersonInvoicing(req);
    }

    /**
     * 查询成品油可用税收分类编码信息：按 capabilityCode 路由到对应能力服务执行。
     */
    @Override
    public ChannelResponse<QueryTaxCategoryRes> queryCpyTaxCategory(CpyQueryTaxCategoryReq req) {
        return route(req.getCapabilityCode()).queryCpyTaxCategory(req);
    }

    /**
     * 查询成品油库存：按 capabilityCode 路由到对应能力服务执行。
     */
    @Override
    public ChannelResponse<CpyQueryInventoryRes> queryCpyInventory(CpyQueryInventoryReq req) {
        return route(req.getCapabilityCode()).queryCpyInventory(req);
    }

    /**
     * 下载或退回成品油库存：按 capabilityCode 路由到对应能力服务执行。
     */
    @Override
    public ChannelResponse<CpyDownloadOrReturnInventoryRes> downloadOrReturnCpyInventory(CpyDownloadOrReturnInventoryReq req) {
        return route(req.getCapabilityCode()).downloadOrReturnCpyInventory(req);
    }
}
