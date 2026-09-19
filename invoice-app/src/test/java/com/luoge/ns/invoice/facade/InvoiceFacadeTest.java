package com.luoge.ns.invoice.facade;

import cn.hutool.json.JSONUtil;
import com.luoge.ns.invoice.common.dto.*;
import com.luoge.ns.invoice.common.enums.CapabilityCode;
import com.luoge.ns.invoice.common.model.ChannelResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@SpringBootTest
class InvoiceFacadeTest {
    private static final CapabilityCode capabilityCode = CapabilityCode.BASE;
    @Autowired
    private InvoiceFacade invoiceFacade;

    @Test
    void getBatchPreCode() {
        GetBatchPreCodeReq req = new GetBatchPreCodeReq(capabilityCode);
        req.setYwlsh("1234567890");
        req.setNsrsbh("1234567890");
        req.setLysl(BigDecimal.valueOf(5000L));
        ChannelResponse<GetBatchPreCodeRes> response = invoiceFacade.getBatchPreCode(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void queryQuota() {
        QueryQuotaReq req = new QueryQuotaReq(capabilityCode);
        req.setNsrsbh("1234567890");
        ChannelResponse<QueryQuotaRes> response = invoiceFacade.queryQuota(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void downloadOrReturnQuota() {
        DownloadOrReturnQuotaReq req = new DownloadOrReturnQuotaReq(capabilityCode);
        req.setNsrsbh("12345678901234567890");
        req.setPtbh("12345678901234567890");
        req.setSqlx("0");
        req.setSqed(BigDecimal.valueOf(1));
        req.setYwlsh("11111");
        ChannelResponse<DownloadOrReturnQuotaRes> response = invoiceFacade.downloadOrReturnQuota(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void adjustQuotaValidity() {
        AdjustQuotaValidityReq req = new AdjustQuotaValidityReq(capabilityCode);
        req.setXsfnsrsbh("1234567890");
        req.setSxedsq("2026-09");
        ChannelResponse<AdjustQuotaValidityRes> response = invoiceFacade.adjustQuotaValidity(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void queryTaxpayerRisk() {
        QueryTaxpayerRiskReq req = new QueryTaxpayerRiskReq(capabilityCode);
        req.setNsrsbh("1234567890");
        ChannelResponse<QueryTaxpayerRiskRes> response = invoiceFacade.queryTaxpayerRisk(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void queryTaxpayerBasic() {
        QueryTaxpayerBasicReq req = new QueryTaxpayerBasicReq(capabilityCode);
        req.setNsrsbh("1234567890");
        ChannelResponse<QueryTaxpayerBasicRes> response = invoiceFacade.queryTaxpayerBasic(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void queryTaxRate() {
        QueryTaxRateReq req = new QueryTaxRateReq(capabilityCode);
        ChannelResponse<QueryTaxRateRes> response = invoiceFacade.queryTaxRate(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void queryTaxCategory() {
        QueryTaxCategoryReq req = new QueryTaxCategoryReq(capabilityCode);
        req.setNsrsbh("12345678901234567890");
        ChannelResponse<QueryTaxCategoryRes> response = invoiceFacade.queryTaxCategory(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void queryRedConfirmDetail() {
        QueryRedConfirmDetailReq req = new QueryRedConfirmDetailReq(capabilityCode);
        req.setUuid("12345678901234567890123456789012");
        req.setXsfnsrsbh("12345678901234567890");
        ChannelResponse<QueryRedConfirmDetailRes> response = invoiceFacade.queryRedConfirmDetail(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void uploadInvoice() {
        UploadInvoiceReq req = new UploadInvoiceReq(capabilityCode);
        req.setFphm("12345678901234567890");
        req.setFpkjfsDm("4");
        req.setFppz("01");
        req.setGmfmc("购买方有限公司");
        req.setHjje(BigDecimal.valueOf(1));
        req.setHjse(BigDecimal.valueOf(1));
        req.setIp("12345678901234567890");
        req.setJshj(BigDecimal.valueOf(1));
        req.setKpr("张三");
        req.setKprq("2025-05-20 10:00:00");
        req.setLzfpbz("1");
        req.setMacdz("12345678901234567890");
        req.setPtbh("12345678901234567890");
        req.setQyDm("12345678901234567890");
        req.setTdys("12");
        req.setXsfmc("销售方有限公司");
        req.setXsfnsrsbh("12345678901234567890");
        ChannelResponse<UploadInvoiceRes> response = invoiceFacade.uploadInvoice(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void queryRedConfirmList() {
        QueryRedConfirmListReq req = new QueryRedConfirmListReq(capabilityCode);
        req.setYhjslx("1");
        ChannelResponse<QueryRedConfirmListRes> response = invoiceFacade.queryRedConfirmList(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void queryUploadResult() {
        QueryUploadResultReq req = new QueryUploadResultReq(capabilityCode);
        req.setSllsh("1234567890123456789012345678901234567890");
        ChannelResponse<QueryUploadResultRes> response = invoiceFacade.queryUploadResult(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void applyRedConfirm() {
        ApplyRedConfirmReq req = new ApplyRedConfirmReq(capabilityCode);
        req.setLrfsf("0");
        req.setXsfnsrsbh("12345678901234567890");
        req.setXsfmc("销售方名称");
        req.setGmfmc("购买方名称");
        req.setLzfphm("12345678901234567890");
        req.setSfzzfpbz("N");
        req.setLzkprq("2025-05-20 10:00:00");
        req.setLzhjje(BigDecimal.valueOf(1));
        req.setLzhjse(BigDecimal.valueOf(1));
        req.setLzfppzDm("01");
        req.setLzfpTdyslxDm("12");
        req.setHzcxje(BigDecimal.valueOf(1));
        req.setHzcxse(BigDecimal.valueOf(1));
        req.setChyyDm("01");
        ApplyRedConfirmReq.HzqrdmxItem item = new ApplyRedConfirmReq.HzqrdmxItem();
        item.setLzmxxh(BigDecimal.ONE);
        item.setXh(BigDecimal.ONE);
        item.setSphfwssflhbbm("1090121010000000000");
        item.setHwhyslwfwmc("软件服务");
        item.setSpfwjc("软件");
        item.setXmmc("信息技术服务");
        item.setDw("次");
        item.setJe(BigDecimal.valueOf(1));
        item.setSl1(BigDecimal.valueOf(0.13));
        item.setSe(BigDecimal.valueOf(0.13));
        req.setHzqrdmxList(List.of(item));
        ChannelResponse<ApplyRedConfirmRes> response = invoiceFacade.applyRedConfirm(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void confirmRedConfirm() {
        ConfirmRedConfirmReq req = new ConfirmRedConfirmReq(capabilityCode);
        req.setXsfnsrsbh("12345678901234567890");
        req.setUuid("12345678901234567890123456789012");
        req.setHzqrdbh("12345678901234567890");
        req.setQrlx("Y");
        ChannelResponse<ConfirmRedConfirmRes> response = invoiceFacade.confirmRedConfirm(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void uploadSummaryConfirm() {
        UploadSummaryConfirmReq req = new UploadSummaryConfirmReq(capabilityCode);
        req.setXsfnsrsbh("12345678901234567890");
        req.setXsfsjswjgdm("12345678901");
        req.setPtbh("12345678901234567890");
        req.setYf("2025-05");
        req.setYwlx("1");
        ChannelResponse<UploadSummaryConfirmRes> response = invoiceFacade.uploadSummaryConfirm(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void querySummaryConfirm() {
        QuerySummaryConfirmReq req = new QuerySummaryConfirmReq(capabilityCode);
        req.setXsfnsrsbh("12345678901234567890");
        req.setXsfsjswjgdm("12345678901");
        req.setPtbh("12345678901234567890");
        req.setYf("2025-05");
        ChannelResponse<QuerySummaryConfirmRes> response = invoiceFacade.querySummaryConfirm(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void queryInvoiceUsage() {
        QueryInvoiceUsageReq req = new QueryInvoiceUsageReq(capabilityCode);
        req.setFplx("01");
        req.setFphm("12345678901234567890");
        req.setKprq("20250520");
        ChannelResponse<QueryInvoiceUsageRes> response = invoiceFacade.queryInvoiceUsage(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void queryTaxProService() {
        QueryTaxProServiceReq req = new QueryTaxProServiceReq(capabilityCode);
        req.setWtrtyshxydm("12345678901234567890");
        req.setPageNumber(BigDecimal.valueOf(1));
        req.setPageSize(BigDecimal.valueOf(1));
        ChannelResponse<QueryTaxProServiceRes> response = invoiceFacade.queryTaxProService(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void queryDeductionVoucher() {
        QueryDeductionVoucherReq req = new QueryDeductionVoucherReq(capabilityCode);
        req.setFphm("12345678901234567890");
        req.setGmfnsrsbh("12345678901234567890");
        req.setKjrq("2026-09-09");
        ChannelResponse<QueryDeductionVoucherRes> response = invoiceFacade.queryDeductionVoucher(req);
        log.info(JSONUtil.toJsonStr(response));
    }

    @Test
    void queryDiffTaxAuth() {
        QueryDiffTaxAuthReq req = new QueryDiffTaxAuthReq(capabilityCode);
        req.setNsrsbh("12345678901234567890");
        ChannelResponse<QueryDiffTaxAuthRes> response = invoiceFacade.queryDiffTaxAuth(req);
        log.info(JSONUtil.toJsonStr(response));
    }
    //
    // @Test
    // void batchDownloadApply() {
    //     BatchDownloadApplyReq req = new BatchDownloadApplyReq(capabilityCode);
    //     req.setDjxh("12345678901234567890");
    //     req.setSjswjgdm("12345678901");
    //     req.setFdckfxmbh("12345678901234567890");
    //     ChannelResponse<BatchDownloadApplyRes> response = invoiceFacade.batchDownloadApply(req);
    //     log.info(JSONUtil.toJsonStr(response));
    // }
    //
    // @Test
    // void queryHouseSource() {
    //     QueryHouseSourceReq req = new QueryHouseSourceReq(capabilityCode);
    //     req.setDjxh("12345678901234567890");
    //     req.setSjswjgdm("12345678901");
    //     req.setFdckfxmbh("12345678901234567890");
    //     ChannelResponse<QueryHouseSourceRes> response = invoiceFacade.queryHouseSource(req);
    //     log.info(JSONUtil.toJsonStr(response));
    // }
    //
    // @Test
    // void queryJzfwInfo() {
    //     QueryJzfwInfoReq req = new QueryJzfwInfoReq(capabilityCode);
    //     req.setNsrsbh("12345678901234567890");
    //     req.setSjswjgdm("12345678901");
    //     req.setXmbh("1234567890123456");
    //     ChannelResponse<QueryJzfwInfoRes> response = invoiceFacade.queryJzfwInfo(req);
    //     log.info(JSONUtil.toJsonStr(response));
    // }
    //
    // @Test
    // void crossRegionSingleQuery() {
    //     CrossRegionSingleReq req = new CrossRegionSingleReq(capabilityCode);
    //     req.setKqysssxbyglbh("12345678901234567890123456789012345678901234567890");
    //     ChannelResponse<CrossRegionSingleRes> response = invoiceFacade.crossRegionSingleQuery(req);
    //     log.info(JSONUtil.toJsonStr(response));
    // }
    //
    // @Test
    // void crossRegionBatchQuery() {
    //     CrossRegionBatchReq req = new CrossRegionBatchReq(capabilityCode);
    //     req.setPageNumber(BigDecimal.valueOf(1));
    //     req.setPageSize(BigDecimal.valueOf(1));
    //     ChannelResponse<CrossRegionBatchRes> response = invoiceFacade.crossRegionBatchQuery(req);
    //     log.info(JSONUtil.toJsonStr(response));
    // }
    //
    // @Test
    // void queryDiffTaxCode() {
    //     QueryDiffTaxCodeReq req = new QueryDiffTaxCodeReq(capabilityCode);
    //     req.setNsrsbh("12345678901234567890");
    //
    //     ChannelResponse<QueryDiffTaxCodeRes> response = invoiceFacade.queryDiffTaxCode(req);
    //     log.info(JSONUtil.toJsonStr(response));
    // }
    //
    // @Test
    // void querySellerBlock() {
    //     QuerySellerBlockReq req = new QuerySellerBlockReq(capabilityCode);
    //     req.setXsfmc("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
    //     req.setXsfnsrsbh("12345678901234567890");
    //     ChannelResponse<QuerySellerBlockRes> response = invoiceFacade.querySellerBlock(req);
    //     log.info(JSONUtil.toJsonStr(response));
    // }
    //
    // @Test
    // void queryEscExchangeableBatch() {
    //     QueryEscExchangeableBatchReq req = new QueryEscExchangeableBatchReq(capabilityCode);
    //     req.setXsfsfzjhm("12345678901234567890");
    //     req.setTdyslxDm("12");
    //     req.setKpqsrq("1234567890");
    //     req.setKpzzrq("1234567890");
    //     req.setPageNumber(BigDecimal.valueOf(1));
    //     req.setPageSize(BigDecimal.valueOf(1));
    //     ChannelResponse<QueryEscExchangeableBatchRes> response = invoiceFacade.queryEscExchangeableBatch(req);
    //     log.info(JSONUtil.toJsonStr(response));
    // }
    //
    // @Test
    // void queryEscExchangeInfo() {
    //     QueryEscExchangeInfoReq req = new QueryEscExchangeInfoReq(capabilityCode);
    //     req.setEscxstyfphm("12345678901234567890");
    //     req.setTdyslxDm("12");
    //     req.setXsfsfzjhm("12345678901234567890");
    //     ChannelResponse<QueryEscExchangeInfoRes> response = invoiceFacade.queryEscExchangeInfo(req);
    //     log.info(JSONUtil.toJsonStr(response));
    // }
    //
    // @Test
    // void uploadEscInvoice() {
    //     EscUploadInvoiceReq req = new EscUploadInvoiceReq(capabilityCode);
    //     req.setLzfpbz("1");
    //     req.setFphm("12345678901234567890");
    //     req.setPtbh("12345678901234567890");
    //     req.setFppz("12");
    //     req.setKprq("2025-05-20 10:00:00");
    //     req.setKpr("12345678901234567890");
    //     req.setQyDm("12345678901234567890");
    //     req.setKpqykphyxz("12");
    //     req.setKpqynsrsbh("12345678901234567890");
    //     req.setKpqynsrmc("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
    //     req.setKpqylxdz("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
    //     req.setKpqykhh("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
    //     req.setKpqyyhzh("12345678901234567890123456789012345678901234567890");
    //     req.setKpqylxdh("12345678901234567890");
    //     req.setTdys("12");
    //     req.setGmfzrrbz("1");
    //     req.setGmfmc("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
    //     req.setGmfdz("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
    //     req.setGmfdh("12345678901234567890");
    //     req.setXsfzrrbz("1");
    //     req.setXsfmc("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
    //     req.setXsfdz("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
    //     req.setXsfdh("12345678901234567890");
    //     req.setFpkjfsDm("1");
    //     req.setCphm("12345678901234567890");
    //     req.setDjzh("12345678901234567890");
    //     req.setCllxDm("12345");
    //     req.setSphfwssflhbbm("1234567890123456789");
    //     req.setSpfwjc("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
    //     req.setXmmc("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
    //     req.setHwhyslwfwmc("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
    //     req.setClsbdh("12345678901234567890123");
    //     req.setCpxh1("123456789012345678901234567890123456789012345678901234567890");
    //     req.setZrdclglsmc("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
    //     req.setCjhjxx(BigDecimal.valueOf(1));
    //     req.setIp("12345678901234567890");
    //     req.setMacdz("12345678901234567890");
    //     ChannelResponse<UploadEscInvoiceRes> response = invoiceFacade.uploadEscInvoice(req);
    //     log.info(JSONUtil.toJsonStr(response));
    // }
    //
    // @Test
    // void queryNaturalPersonInvoicing() {
    //     QueryNaturalPersonInvoicingReq req = new QueryNaturalPersonInvoicingReq(capabilityCode);
    //     req.setZrrxm("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
    //     req.setSfzjlx("123");
    //     req.setSfzjhm("12345678901234567890");
    //     ChannelResponse<QueryNaturalPersonInvoicingRes> response = invoiceFacade.queryNaturalPersonInvoicing(req);
    //     log.info(JSONUtil.toJsonStr(response));
    // }
    //
    // @Test
    // void queryCpyTaxCategory() {
    //     CpyQueryTaxCategoryReq req = new CpyQueryTaxCategoryReq(capabilityCode);
    //     req.setNsrsbh("12345678901234567890");
    //     req.setSjswjgdm("12345678901");
    //     ChannelResponse<QueryTaxCategoryRes> response = invoiceFacade.queryCpyTaxCategory(req);
    //     log.info(JSONUtil.toJsonStr(response));
    // }
    //
    // @Test
    // void queryCpyInventory() {
    //     CpyQueryInventoryReq req = new CpyQueryInventoryReq(capabilityCode);
    //     req.setNsrsbh("12345678901234567890");
    //     ChannelResponse<CpyQueryInventoryRes> response = invoiceFacade.queryCpyInventory(req);
    //     log.info(JSONUtil.toJsonStr(response));
    // }
    //
    // @Test
    // void downloadOrReturnCpyInventory() {
    //     CpyDownloadOrReturnInventoryReq req = new CpyDownloadOrReturnInventoryReq(capabilityCode);
    //     req.setNsrsbh("12345678901234567890");
    //     req.setPtbh("12345678901234567890");
    //     req.setSqlx("12");
    //     req.setYwlsh("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
    //     req.setSpbm("1234567890123456789");
    //     req.setSl(BigDecimal.valueOf(1));
    //     ChannelResponse<CpyDownloadOrReturnInventoryRes> response = invoiceFacade.downloadOrReturnCpyInventory(req);
    //     log.info(JSONUtil.toJsonStr(response));
    // }
}