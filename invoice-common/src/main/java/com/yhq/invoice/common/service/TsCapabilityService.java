package com.yhq.invoice.common.service;

import com.yhq.invoice.common.dto.*;
import com.yhq.invoice.common.enums.Channel;
import com.yhq.invoice.common.model.ChannelResponse;

/**
 * 增值税退税勾选（乐企能力 203064）统一能力接口（契约层，定义在 common）。
 *
 * <p>与 {@link InvoiceCapabilityService}（开票能力）、{@code GxCapabilityService}（抵扣勾选能力）、
 * {@code GjCapabilityService}（归集能力）并列、互不影响：退税勾选是独立的业务域，不复用开票/抵扣/归集能力路由。
 * 共 6 个接口，覆盖退税发票/海关缴款书的批量上传与处理结果查询、未勾选数据初始化清单下载申请与反馈。
 * leqi 与 rpa 各自实现本接口，app 门面 {@code TsFacade} 按 Channel 枚举选择实现。
 */
public interface TsCapabilityService {

    /**
     * 返回本实现所属渠道（LEQI / RPA）。
     */
    Channel channel();

    /**
     * 1. 批量上传退税发票（PLFPTSGXQR）。
     */
    ChannelResponse<TsBatchUploadRefundInvoiceRes> batchUploadRefundInvoice(TsBatchUploadRefundInvoiceReq req);

    /**
     * 2. 查询发票退税勾选处理结果（CXFPTSGXQRCLJG）。
     */
    ChannelResponse<TsQueryInvoiceRefundResultRes> queryInvoiceRefundResult(TsQueryInvoiceRefundResultReq req);

    /**
     * 3. 批量上传退税海关缴款书（PLHGJKSTSGXQR）。
     */
    ChannelResponse<TsBatchUploadRefundCustomsRes> batchUploadRefundCustoms(TsBatchUploadRefundCustomsReq req);

    /**
     * 4. 查询海关缴款书退税勾选处理结果（CXHGJKSTSGXQRCLJG）。
     */
    ChannelResponse<TsQueryCustomsRefundResultRes> queryCustomsRefundResult(TsQueryCustomsRefundResultReq req);

    /**
     * 5. 未勾选数据初始化清单下载申请（WGXSJCSHQDXZSQTSGX）。
     */
    ChannelResponse<TsUnselectedDataInitDownloadApplyRes> unselectedDataInitDownloadApply(TsUnselectedDataInitDownloadApplyReq req);

    /**
     * 6. 未勾选数据初始化清单下载申请反馈（WGXSJCSHQDXZSQFKTSGX）。
     */
    ChannelResponse<TsUnselectedDataInitDownloadApplyFeedbackRes> unselectedDataInitDownloadApplyFeedback(TsUnselectedDataInitDownloadApplyFeedbackReq req);

}
