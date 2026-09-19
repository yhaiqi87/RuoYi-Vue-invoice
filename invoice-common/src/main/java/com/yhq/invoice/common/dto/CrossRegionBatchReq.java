package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 建筑服务请求：CrossRegionBatchQuery（跨区域涉税数据批量查询，接口 KQYSSSJPLCX）。
 * 字段依据《乐企数字化电子发票（建筑服务）开票能力说明文档-V4.002》补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class CrossRegionBatchReq extends InvoiceRequest {

    public CrossRegionBatchReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 合同对方纳税人识别号（varchar，20，否）
     */
    private String htdfnsrsbh;
    /**
     * 合同对方纳税人名称（varchar，300，否）
     */
    private String htdfnsrmc;
    /**
     * 省级跨区域经营行政区划（varchar，200，否）
     */
    private String sjkqyjyxzqh;
    /**
     * 市级跨区域经营行政区划（varchar，200，否）
     */
    private String sjkqyjyxzqh1;
    /**
     * 区级跨区域经营行政区划（varchar，200，否，直辖市传空）
     */
    private String qjkqyjyxzqh;
    /**
     * 跨区域涉税事项有效期起（date，否，yyyy-MM-dd）
     */
    private String kqysssxyxqq;
    /**
     * 跨区域涉税事项有效期止（date，否，yyyy-MM-dd）
     */
    private String kqysssxyxqz;
    /**
     * 报告开具期限起（date，是，yyyy-MM-dd）
     */
    private String bgkjqxq;
    /**
     * 报告开具期限止（date，是，yyyy-MM-dd）
     */
    private String bgkjqxz;
    /**
     * 工程项目名称（varchar，300，否）
     */
    private String gcxmmc;
    /**
     * 页码（number，10，是）
     */
    @NotNull(message = "页码(pageNumber)为必填项")
    private BigDecimal pageNumber;
    /**
     * 每页数量（number，10，是，最大不超过50）
     */
    @NotNull(message = "每页数量(pageSize)为必填项")
    private BigDecimal pageSize;
}
