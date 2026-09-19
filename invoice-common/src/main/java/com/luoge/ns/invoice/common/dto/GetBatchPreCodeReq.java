package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import com.luoge.ns.invoice.common.validation.BigDecimalRange;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 乐企请求：GetBatchPreCode。字段依据乐企开票能力说明文档（基础版 V6.006，接口 QDFPPLFM）补全。
 * 必填项、长度与取值范围约束均采用 jakarta.validation 注解声明，由调用方统一通过 hutool ValidationUtil.validate() 触发校验。
 */
@Getter
@Setter
@NoArgsConstructor
public class GetBatchPreCodeReq extends InvoiceRequest {

    public GetBatchPreCodeReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /** 纳税人识别号/统一社会信用代码（类型 varchar，长度 20，必填 是，申领发票的纳税人识别号或统一社会信用代码） */
    @NotBlank(message = "纳税人识别号/统一社会信用代码(nsrsbh)为必填项")
    @Size(max = 20, message = "纳税人识别号/统一社会信用代码(nsrsbh)长度不能超过 20")
    private String nsrsbh;
    /**
     * 领用数量（类型 number，长度 10，必填 是，申领数量最大值为：5000）
     */
    @NotNull(message = "领用数量(lysl)为必填项")
    @BigDecimalRange(min = "1", max = "5000", message = "领用数量(lysl)必须为 1~5000 的正整数")
    private BigDecimal lysl;
    /** 业务流水号（类型 varchar，长度 100，必填 是，“使用单位id”+“直连单位id”+“32位随机生成数”） */
    @NotBlank(message = "业务流水号(ywlsh)为必填项")
    @Size(max = 100, message = "业务流水号(ywlsh)长度不能超过 100")
    private String ywlsh;
}
