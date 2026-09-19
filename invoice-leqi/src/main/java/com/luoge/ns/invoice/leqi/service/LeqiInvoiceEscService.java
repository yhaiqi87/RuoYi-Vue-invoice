package com.luoge.ns.invoice.leqi.service;

import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.CapabilityCode;
import com.luoge.ns.invoice.common.enums.EscInterfaceCode;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import com.luoge.ns.invoice.leqi.annotation.DocVersion;
import com.luoge.ns.invoice.leqi.annotation.ValidateReq;
import com.luoge.ns.invoice.leqi.client.LeqiHttpUtil;
import com.luoge.ns.invoice.leqi.util.ReqValidation;
import org.springframework.stereotype.Service;

/**
 * 二手车（ESC）能力服务：承载二手车支持的全部概念方法。
 * 仅实现 leqi 内部契约 LeqiCapabilityService，不实现统一门面契约 InvoiceCapabilityService。
 * 真实接口使用 Esc* 请求 DTO / 接口编码枚举，走通桩链路。
 *
 * <p>能力矩阵（依据《乐企数字化电子发票（二手车）开票能力说明文档-V1.003》，能力编码 202082）：
 * <ul>
 *   <li>复用数电票通用概念 16 项：预赋码 / 额度 / 风险 / 基本信息 / 税率 / 分类编码 / 红字确认单 / 汇总确认 / 上传结果等；</li>
 *   <li>二手车专属 4 项：querySellerBlock（自然人卖方限制名单阻断查询）/ queryEscExchangeableBatch
 *       （可换开二手车销售统一发票批量查询）/ queryEscExchangeInfo（二手车销售统一发票换开信息单笔查询）/
 *       uploadEscInvoice（二手车销售统一发票上传，ESCXSTYFPSC，车辆结构独立报文）；</li>
 *   <li>uploadInvoice 映射到二手车类特定要素发票上传（ESCLTDYSFPSC），报文与数电票上传超集一致；</li>
 *   <li>不支持 10 项：queryInvoiceUsage / queryDeductionVoucher / queryTaxProService / queryDiffTaxAuth /
 *       batchDownloadApply / queryHouseSource / queryJzfwInfo / crossRegionSingleQuery / crossRegionBatchQuery / queryDiffTaxCode。</li>
 * </ul>
 *
 * <p>类级 {@code @ValidateReq} 与 BASE/SALE 一致，对全部入参执行 jakarta.validation 校验。
 */
@Service
@ValidateReq
@DocVersion(doc = "乐企数字化电子发票（二手车）开票能力说明文档", value = "V1.003")
public class LeqiInvoiceEscService implements LeqiCapabilityService {

    private final LeqiHttpUtil httpUtil;

    public LeqiInvoiceEscService(LeqiHttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Override
    public CapabilityCode capability() {
        return CapabilityCode.ESC;
    }

    /**
     * 获取数电票批量预赋码信息（二手车）。真实实现：调用乐企接口 QDFPPLFM，当前走通桩链路。
     */
    @Override
    public ChannelResponse<GetBatchPreCodeRes> getBatchPreCode(GetBatchPreCodeReq req) {
        return httpUtil.call(EscInterfaceCode.GET_BATCH_PRE_CODE.getServiceCode(), req, GetBatchPreCodeRes.class);
    }

    /**
     * 查询发票额度（二手车）。真实实现：调用乐企接口 CXSXED，当前走通桩链路。
     */
    @Override
    public ChannelResponse<QueryQuotaRes> queryQuota(QueryQuotaReq req) {
        return httpUtil.call(EscInterfaceCode.QUERY_QUOTA.getServiceCode(), req, QueryQuotaRes.class);
    }

    /**
     * 下载/退回发票额度（二手车）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<DownloadOrReturnQuotaRes> downloadOrReturnQuota(DownloadOrReturnQuotaReq req) {
        return httpUtil.call(EscInterfaceCode.DOWNLOAD_OR_RETURN_QUOTA.getServiceCode(), req, DownloadOrReturnQuotaRes.class);
    }

    /**
     * 调整发票额度有效期（二手车）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<AdjustQuotaValidityRes> adjustQuotaValidity(AdjustQuotaValidityReq req) {
        return httpUtil.call(EscInterfaceCode.ADJUST_QUOTA_VALIDITY.getServiceCode(), req, AdjustQuotaValidityRes.class);
    }

    /**
     * 查询纳税人风险信息（二手车）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxpayerRiskRes> queryTaxpayerRisk(QueryTaxpayerRiskReq req) {
        return httpUtil.call(EscInterfaceCode.QUERY_TAXPAYER_RISK.getServiceCode(), req, QueryTaxpayerRiskRes.class);
    }

    /**
     * 查询纳税人基本信息（二手车）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxpayerBasicRes> queryTaxpayerBasic(QueryTaxpayerBasicReq req) {
        return httpUtil.call(EscInterfaceCode.QUERY_TAXPAYER_BASIC.getServiceCode(), req, QueryTaxpayerBasicRes.class);
    }

    /**
     * 查询可用税率信息（二手车）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxRateRes> queryTaxRate(QueryTaxRateReq req) {
        return httpUtil.call(EscInterfaceCode.QUERY_TAX_RATE.getServiceCode(), req, QueryTaxRateRes.class);
    }

    /**
     * 查询税收分类编码信息（二手车）。真实实现：调用乐企对应接口（服务编码见枚举）。
     */
    @Override
    public ChannelResponse<QueryTaxCategoryRes> queryTaxCategory(QueryTaxCategoryReq req) {
        return httpUtil.call(EscInterfaceCode.QUERY_TAX_CATEGORY.getServiceCode(), req, QueryTaxCategoryRes.class);
    }

    /**
     * 自然人卖方限制名单阻断查询（二手车）。真实实现：调用乐企接口 ZRRMFXZMDZDCX。
     */
    @Override
    public ChannelResponse<QuerySellerBlockRes> querySellerBlock(QuerySellerBlockReq req) {
        return httpUtil.call(EscInterfaceCode.QUERY_SELLER_BLOCK.getServiceCode(), req, QuerySellerBlockRes.class);
    }

    /**
     * 可换开二手车销售统一发票批量查询（二手车）。真实实现：调用乐企接口 KHKESCXSTYFPPLCX。
     */
    @Override
    public ChannelResponse<QueryEscExchangeableBatchRes> queryEscExchangeableBatch(QueryEscExchangeableBatchReq req) {
        return httpUtil.call(EscInterfaceCode.QUERY_ESC_EXCHANGEABLE_BATCH.getServiceCode(), req, QueryEscExchangeableBatchRes.class);
    }

    /**
     * 二手车销售统一发票换开信息单笔查询（二手车）。真实实现：调用乐企接口 ESCXSTYFPHXXXDBCX。
     */
    @Override
    public ChannelResponse<QueryEscExchangeInfoRes> queryEscExchangeInfo(QueryEscExchangeInfoReq req) {
        return httpUtil.call(EscInterfaceCode.QUERY_ESC_EXCHANGE_INFO.getServiceCode(), req, QueryEscExchangeInfoRes.class);
    }

    /**
     * 二手车销售统一发票上传（二手车）。真实实现：调用乐企接口 ESCXSTYFPSC，当前走通桩链路。
     */
    @Override
    public ChannelResponse<UploadEscInvoiceRes> uploadEscInvoice(EscUploadInvoiceReq req) {
        ReqValidation.requireValid(req);
        return httpUtil.call(EscInterfaceCode.UPLOAD_ESC_INVOICE.getServiceCode(), req, UploadEscInvoiceRes.class);
    }

    /**
     * 发票上传（二手车）：映射到二手车类特定要素发票上传 ESCLTDYSFPSC（报文与数电票上传超集一致）。
     */
    @Override
    public ChannelResponse<UploadInvoiceRes> uploadInvoice(UploadInvoiceReq req) {
        ReqValidation.requireValid(req);
        return httpUtil.call(EscInterfaceCode.UPLOAD_SPECIFIC_ELEMENT_INVOICE.getServiceCode(), req, UploadInvoiceRes.class);
    }

    /**
     * 查询二手车发票上传结果（二手车）。真实实现：调用乐企对应接口 CXQDFPSCJG。
     */
    @Override
    public ChannelResponse<QueryUploadResultRes> queryUploadResult(QueryUploadResultReq req) {
        return httpUtil.call(EscInterfaceCode.QUERY_UPLOAD_RESULT.getServiceCode(), req, QueryUploadResultRes.class);
    }

    /**
     * 红字确认单申请（二手车）。真实实现：调用乐企对应接口 QDHZQRDSQ。
     */
    @Override
    public ChannelResponse<ApplyRedConfirmRes> applyRedConfirm(ApplyRedConfirmReq req) {
        return httpUtil.call(EscInterfaceCode.APPLY_RED_CONFIRM.getServiceCode(), req, ApplyRedConfirmRes.class);
    }

    /**
     * 红字确认单确认（二手车）。真实实现：调用乐企对应接口 QDHZQRDQR。
     */
    @Override
    public ChannelResponse<ConfirmRedConfirmRes> confirmRedConfirm(ConfirmRedConfirmReq req) {
        return httpUtil.call(EscInterfaceCode.CONFIRM_RED_CONFIRM.getServiceCode(), req, ConfirmRedConfirmRes.class);
    }

    /**
     * 查询红字确认单列表信息（二手车）。真实实现：调用乐企对应接口 CXQDHZQRDLB。
     */
    @Override
    public ChannelResponse<QueryRedConfirmListRes> queryRedConfirmList(QueryRedConfirmListReq req) {
        return httpUtil.call(EscInterfaceCode.QUERY_RED_CONFIRM_LIST.getServiceCode(), req, QueryRedConfirmListRes.class);
    }

    /**
     * 查询红字确认单明细信息（二手车）。真实实现：调用乐企对应接口 CXQDHZQRDMX。
     */
    @Override
    public ChannelResponse<QueryRedConfirmDetailRes> queryRedConfirmDetail(QueryRedConfirmDetailReq req) {
        return httpUtil.call(EscInterfaceCode.QUERY_RED_CONFIRM_DETAIL.getServiceCode(), req, QueryRedConfirmDetailRes.class);
    }

    /**
     * 上传发票汇总确认信息（二手车）。真实实现：调用乐企对应接口 SCFPHZQRXX。
     */
    @Override
    public ChannelResponse<UploadSummaryConfirmRes> uploadSummaryConfirm(UploadSummaryConfirmReq req) {
        return httpUtil.call(EscInterfaceCode.UPLOAD_SUMMARY_CONFIRM.getServiceCode(), req, UploadSummaryConfirmRes.class);
    }

    /**
     * 查询发票汇总确认信息（二手车）。真实实现：调用乐企对应接口 CXFPHZQRXX。
     */
    @Override
    public ChannelResponse<QuerySummaryConfirmRes> querySummaryConfirm(QuerySummaryConfirmReq req) {
        return httpUtil.call(EscInterfaceCode.QUERY_SUMMARY_CONFIRM.getServiceCode(), req, QuerySummaryConfirmRes.class);
    }

    /**
     * 发票用途状态信息查询（仅基础版/不动产，二手车不涉及）。返回空响应占位。
     */
    @Override
    public ChannelResponse<QueryInvoiceUsageRes> queryInvoiceUsage(QueryInvoiceUsageReq req) {
        throw new UnsupportedOperationException("二手车无此方法");
    }


}
