package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * 乐企响应：QueryHouseSource。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class QueryHouseSourceRes extends InvoiceResponse {
    private String fphm;
    private String fpdm;
    private String tdyslxDm;
    private BigDecimal jshj;
    private String fppzDm;
    private String sfyytqr;
    private String fdckfxmbh;
    private String fdckfxmmc;
    private String fdckfxmdz;
    private String fwzh;
    private String dyh;
    private String lc;
    private String fh;
    private BigDecimal ycjzmj;
    private BigDecimal yctnmj;
    private BigDecimal scjzmj;
    private BigDecimal sctnmj;
    private String bdcdwdm;
    private String wqhtbh;
    private BigDecimal htje;
    private String gfrmc;
    private String gfrzjlxdm;
    private String gfrzjhm;
}
