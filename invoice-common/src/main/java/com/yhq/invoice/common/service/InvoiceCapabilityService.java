package com.yhq.invoice.common.service;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.model.ChannelResponse;

/**
 * 统一开票能力接口（契约层，定义在 common）。
 * 每概念一个方法，按 base / 不动产销售 文档并集去重后得到 22 个概念。
 * leqi 与 rpa 各自实现本接口，app 门面按 Channel 枚举选择实现。
 */
public interface InvoiceCapabilityService {

    /** 返回本实现所属渠道（LEQI / RPA）。 */
    Channel channel();

    // 1. 获取发票批量预赋码信息（首个真实实现）
    ChannelResponse<GetBatchPreCodeRes> getBatchPreCode(GetBatchPreCodeReq req);

    // 2. 查询额度（基础版：发票额度 / 不动产销售：授信额度）
    ChannelResponse<QueryQuotaRes> queryQuota(QueryQuotaReq req);

    // 3. 下载/退回额度
    ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(DownloadOrReturnQuotaReq req);

    // 4. 调整额度有效期
    ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(AdjustQuotaValidityReq req);

    // 5. 查询纳税人风险信息
    ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(QueryTaxpayerRiskReq req);

    // 6. 查询纳税人基本信息
    ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(QueryTaxpayerBasicReq req);

    // 7. 查询可用税率信息
    ChannelResponse<QueryTaxRateRes> queryTaxRate(QueryTaxRateReq req);

    // 8. 查询税收分类编码信息
    ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(QueryTaxCategoryReq req);

    // 9. 查询红字确认单明细信息
    ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(QueryRedConfirmDetailReq req);

    // 10. 发票上传
    ChannelResponse<UploadInvoiceRes> uploadInvoice(UploadInvoiceReq req);

    // 11. 查询红字确认单列表信息
    ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(QueryRedConfirmListReq req);

    // 12. 查询发票上传结果
    ChannelResponse<QueryUploadResultRes> queryUploadResult(QueryUploadResultReq req);

    // 13. 红字确认单申请
    ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(ApplyRedConfirmReq req);

    // 14. 红字确认单确认
    ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(ConfirmRedConfirmReq req);

    // 15. 上传发票汇总确认信息
    ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(UploadSummaryConfirmReq req);

    // 16. 查询发票汇总确认信息
    ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(QuerySummaryConfirmReq req);

    // 17. 发票用途状态信息查询
    ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(QueryInvoiceUsageReq req);

    // 18. 扣除凭证清单相关发票数据信息查询
    ChannelResponse<QueryDeductionVoucherRes> queryDeductionVoucher(QueryDeductionVoucherReq req);

    // 19. 查询涉税专业服务相关信息（仅基础版）
    ChannelResponse<QueryTaxProServiceRes> queryTaxProService(QueryTaxProServiceReq req);

    // 20. 原差额征税业务授权信息查询（仅基础版）
    ChannelResponse<QueryDiffTaxAuthRes> queryDiffTaxAuth(QueryDiffTaxAuthReq req);

    // 21. 批量发票下载申请（仅不动产销售）
    ChannelResponse<BatchDownloadApplyRes> batchDownloadApply(BatchDownloadApplyReq req);

    // 22. 查询房源信息（仅不动产销售）
    ChannelResponse<QueryHouseSourceRes> queryHouseSource(QueryHouseSourceReq req);

    // 23. 查询建筑服务信息（仅建筑服务）
    ChannelResponse<QueryJzfwInfoRes> queryJzfwInfo(QueryJzfwInfoReq req);

    // 24. 跨区域涉税数据单笔查询（仅建筑服务）
    ChannelResponse<CrossRegionSingleRes> crossRegionSingleQuery(CrossRegionSingleReq req);

    // 25. 跨区域涉税数据批量查询（仅建筑服务）
    ChannelResponse<CrossRegionBatchRes> crossRegionBatchQuery(CrossRegionBatchReq req);

    // 26. 查询差额征税编码（仅货物运输）
    ChannelResponse<QueryDiffTaxCodeRes> queryDiffTaxCode(QueryDiffTaxCodeReq req);

    // 27. 自然人卖方限制名单阻断查询（仅二手车）
    ChannelResponse<QuerySellerBlockRes> querySellerBlock(QuerySellerBlockReq req);

    // 28. 可换开二手车销售统一发票批量查询（仅二手车）
    ChannelResponse<QueryEscExchangeableBatchRes> queryEscExchangeableBatch(QueryEscExchangeableBatchReq req);

    // 29. 二手车销售统一发票换开信息单笔查询（仅二手车）
    ChannelResponse<QueryEscExchangeInfoRes> queryEscExchangeInfo(QueryEscExchangeInfoReq req);

    // 30. 二手车销售统一发票上传（仅二手车，报文结构与数电票上传不同）
    ChannelResponse<UploadEscInvoiceRes> uploadEscInvoice(EscUploadInvoiceReq req);

    // 31. 查询自然人开票信息（仅反向开票通用）
    ChannelResponse<QueryNaturalPersonInvoicingRes> queryNaturalPersonInvoicing(QueryNaturalPersonInvoicingReq req);

    // 32. 查询成品油可用税收分类编码信息（仅成品油）
    ChannelResponse<QueryTaxCategoryRes> queryCpyTaxCategory(CpyQueryTaxCategoryReq req);

    // 33. 查询成品油库存（仅成品油）
    ChannelResponse<CpyQueryInventoryRes> queryCpyInventory(CpyQueryInventoryReq req);

    // 34. 下载或退回成品油库存（仅成品油）
    ChannelResponse<CpyDownloadOrReturnInventoryRes> downloadOrReturnCpyInventory(CpyDownloadOrReturnInventoryReq req);
}
