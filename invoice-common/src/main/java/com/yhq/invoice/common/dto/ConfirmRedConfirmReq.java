package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import com.yhq.invoice.common.enums.Qrlx;
import com.yhq.invoice.common.validation.EnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 乐企请求：ConfirmRedConfirm（数电红字确认单确认，接口 QDHZQRDQR）。
 * 请求参数严格依据基础版 V6.006 第 14.2 节，仅 4 个字段：
 * xsfnsrsbh / uuid / hzqrdbh / qrlx（均为必填）。
 */
@Getter
@Setter
@NoArgsConstructor
public class ConfirmRedConfirmReq extends InvoiceRequest {

    public ConfirmRedConfirmReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }

    /**
     * 统一社会信用代码/纳税人识别号/身份证件号码（类型 varchar，长度 20，必填 是）
     */
    @NotBlank(message = "统一社会信用代码/纳税人识别号/身份证件号码(xsfnsrsbh)为必填项")
    @Size(max = 20, message = "统一社会信用代码/纳税人识别号/身份证件号码(xsfnsrsbh)长度不能超过 20")
    private String xsfnsrsbh;

    /**
     * 红字确认单UUID（类型 varchar，长度 32，必填 是）
     */
    @NotBlank(message = "红字确认单UUID(uuid)为必填项")
    @Size(max = 32, message = "红字确认单UUID(uuid)长度不能超过 32")
    private String uuid;

    /**
     * 红字确认单编号（类型 varchar，长度 20，必填 是）
     */
    @NotBlank(message = "红字确认单编号(hzqrdbh)为必填项")
    @Size(max = 20, message = "红字确认单编号(hzqrdbh)长度不能超过 20")
    private String hzqrdbh;

    /**
     * 确认类型（类型 varchar，长度 1，必填 是，Y：同意 N：不同意 C：撤销）
     */
    @NotBlank(message = "确认类型(qrlx)为必填项")
    @Size(max = 1, message = "确认类型(qrlx)长度不能超过 1")
    @EnumValue(enumClass = Qrlx.class, message = "确认类型(qrlx)取值必须为 Y(同意) / N(不同意) / C(撤销)")
    private String qrlx;
}
