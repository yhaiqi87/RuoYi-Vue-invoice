package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：QueryHouseSource。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryHouseSourceReq extends InvoiceRequest {

    public QueryHouseSourceReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 登记序号（类型 varchar，长度 20，必填 是）
     */
    @NotBlank(message = "登记序号(djxh)为必填项")
    @Size(max = 20, message = "登记序号(djxh)长度不能超过 20")
    private String djxh;
    /**
     * 省级税务机关代码（类型 varchar，长度 11，必填 是，销售方省级税务机关代码）
     */
    @NotBlank(message = "省级税务机关代码(sjswjgdm)为必填项")
    @Size(max = 11, message = "省级税务机关代码(sjswjgdm)长度不能超过 11")
    private String sjswjgdm;
    /**
     * 房地产开发项目编号（类型 varchar，长度 20，必填 是）
     */
    @NotBlank(message = "房地产开发项目编号(fdckfxmbh)为必填项")
    @Size(max = 20, message = "房地产开发项目编号(fdckfxmbh)长度不能超过 20")
    private String fdckfxmbh;
    /**
     * 不动产单位代码（类型 varchar，长度 28，必填 否）
     */
    private String bdcdwdm;
    /**
     * 网签合同编号（类型 varchar，长度 28，必填 否）
     */
    private String wqhtbh;
}
