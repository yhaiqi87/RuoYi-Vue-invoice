package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 抵扣勾选响应：批量上传待处理农产品发票确认清单（PLSCDCLNCPFPQRQD）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 17「批量上传待处理农产品发票确认清单」返回参数补全。
 */
@Getter
@Setter
public class GxBatchUploadAgriInvoiceConfirmListRes extends GxResponse {

    /**
     * 购方纳税人识别号/统一社会信用代码（String，30，必填）。
     */
    private String gfsbh;

    /**
     * 农产品类型（String，2，必填）。
     */
    private String ncplx;

    /**
     * 勾选类型（String，2，必填）。
     */
    private String gxlxDm;

    /**
     * fpmx明细（List<GxBatchUploadAgriInvoiceConfirmListResFpmx>）。
     */
    private java.util.List<GxBatchUploadAgriInvoiceConfirmListResFpmx> fpmx;
}
