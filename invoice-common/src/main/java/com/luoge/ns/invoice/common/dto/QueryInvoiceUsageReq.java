package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import com.luoge.ns.invoice.common.enums.Fplx;
import com.luoge.ns.invoice.common.validation.DatePattern;
import com.luoge.ns.invoice.common.validation.EnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：QueryInvoiceUsage（FPZTXXCX，发票用途状态信息查询）。字段依据基础版 V6.006 文档 §17.2 请求参数。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryInvoiceUsageReq extends InvoiceRequest {

    public QueryInvoiceUsageReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 发票类型（类型 String，长度 2，必填 是，取值范围见 {@link Fplx}）
     */
    @NotBlank(message = "发票类型(fplx)为必填项")
    @Size(max = 2, message = "发票类型(fplx)长度不能超过 2")
    @EnumValue(enumClass = Fplx.class, message = "发票类型(fplx)取值必须为 01/02/03/04/08/10/11/14/15/51/61/81/82/83/84/85/86/87/88")
    private String fplx;
    /**
     * 发票代码（类型 String，长度 12，必填 否，发票类型为：01、02、03、04、08、10、11、14、15传纸质发票代码，其他发票类型无需传入）
     */
    private String fpdm;
    /**
     * 发票号码（类型 String，长度 20，必填 是，发票类型为：01、02、03、04、08、10、11、14、15传纸质发票号码；发票类型为：51、61、81、82、83、84、85、86、87、88传20位数字化电子发票号码）
     */
    @NotBlank(message = "发票号码(fphm)为必填项")
    @Size(max = 20, message = "发票号码(fphm)长度不能超过 20")
    private String fphm;
    /**
     * 开票日期（类型 String，长度 8，必填 是，格式 YYYYMMDD）
     */
    @NotBlank(message = "开票日期(kprq)为必填项")
    @Size(max = 8, message = "开票日期(kprq)长度不能超过 8")
    @DatePattern(pattern = "yyyyMMdd", message = "开票日期(kprq)格式必须为 yyyyMMdd")
    private String kprq;
}
