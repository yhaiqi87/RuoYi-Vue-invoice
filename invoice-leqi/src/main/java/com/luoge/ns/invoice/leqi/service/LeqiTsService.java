package com.luoge.ns.invoice.leqi.service;

import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.Channel;
import com.luoge.ns.invoice.common.enums.TsInterfaceCode;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import com.luoge.ns.invoice.common.service.TsCapabilityService;
import com.luoge.ns.invoice.leqi.annotation.DocVersion;
import com.luoge.ns.invoice.leqi.annotation.ValidateReq;
import com.luoge.ns.invoice.leqi.client.LeqiHttpUtil;
import org.springframework.stereotype.Service;

/**
 * 乐企（LEQI）增值税退税勾选能力服务：实现退税勾选统一契约 {@link TsCapabilityService} 的全部 6 个接口。
 *
 * <p>与开票能力、抵扣勾选能力（{@code LeqiGxService}）完全隔离：本类<b>不</b>实现 {@code LeqiCapabilityService}，
 * 故不会进入 {@code LeqiInvoiceService} 的开票能力路由表。每个方法直接走通桩链路
 * {@code httpUtil.call(serviceCode, req, Res.class)}，后续校验/字段处理加在对应方法内，不影响其他能力。
 */
@Service
@ValidateReq
@DocVersion(doc = "乐企增值税退税勾选能力说明文档", value = "V2.013")
public class LeqiTsService implements TsCapabilityService {

    private final LeqiHttpUtil httpUtil;

    public LeqiTsService(LeqiHttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Override
    public Channel channel() {
        return Channel.LEQI;
    }

    /**
     * 批量上传退税发票（PLFPTSGXQR）：调用乐企退税勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<TsBatchUploadRefundInvoiceRes> batchUploadRefundInvoice(TsBatchUploadRefundInvoiceReq req) {
        return httpUtil.call(TsInterfaceCode.BATCH_UPLOAD_REFUND_INVOICE.getServiceCode(), req, TsBatchUploadRefundInvoiceRes.class);
    }

    /**
     * 查询发票退税勾选处理结果（CXFPTSGXQRCLJG）：调用乐企退税勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<TsQueryInvoiceRefundResultRes> queryInvoiceRefundResult(TsQueryInvoiceRefundResultReq req) {
        return httpUtil.call(TsInterfaceCode.QUERY_INVOICE_REFUND_RESULT.getServiceCode(), req, TsQueryInvoiceRefundResultRes.class);
    }

    /**
     * 批量上传退税海关缴款书（PLHGJKSTSGXQR）：调用乐企退税勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<TsBatchUploadRefundCustomsRes> batchUploadRefundCustoms(TsBatchUploadRefundCustomsReq req) {
        return httpUtil.call(TsInterfaceCode.BATCH_UPLOAD_REFUND_CUSTOMS.getServiceCode(), req, TsBatchUploadRefundCustomsRes.class);
    }

    /**
     * 查询海关缴款书退税勾选处理结果（CXHGJKSTSGXQRCLJG）：调用乐企退税勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<TsQueryCustomsRefundResultRes> queryCustomsRefundResult(TsQueryCustomsRefundResultReq req) {
        return httpUtil.call(TsInterfaceCode.QUERY_CUSTOMS_REFUND_RESULT.getServiceCode(), req, TsQueryCustomsRefundResultRes.class);
    }

    /**
     * 未勾选数据初始化清单下载申请（WGXSJCSHQDXZSQTSGX）：调用乐企退税勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<TsUnselectedDataInitDownloadApplyRes> unselectedDataInitDownloadApply(TsUnselectedDataInitDownloadApplyReq req) {
        return httpUtil.call(TsInterfaceCode.UNSELECTED_DATA_INIT_DOWNLOAD_APPLY.getServiceCode(), req, TsUnselectedDataInitDownloadApplyRes.class);
    }

    /**
     * 未勾选数据初始化清单下载申请反馈（WGXSJCSHQDXZSQFKTSGX）：调用乐企退税勾选接口，当前走通桩链路。
     */
    @Override
    public ChannelResponse<TsUnselectedDataInitDownloadApplyFeedbackRes> unselectedDataInitDownloadApplyFeedback(TsUnselectedDataInitDownloadApplyFeedbackReq req) {
        return httpUtil.call(TsInterfaceCode.UNSELECTED_DATA_INIT_DOWNLOAD_APPLY_FEEDBACK.getServiceCode(), req, TsUnselectedDataInitDownloadApplyFeedbackRes.class);
    }

}
