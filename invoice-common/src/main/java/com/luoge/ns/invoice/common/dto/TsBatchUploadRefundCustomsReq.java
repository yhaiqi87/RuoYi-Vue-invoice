package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 退税勾选请求：批量上传退税海关缴款书（PLHGJKSTSGXQR）。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 3「批量上传退税海关缴款书」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class TsBatchUploadRefundCustomsReq extends TsRequest {

    public TsBatchUploadRefundCustomsReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购买方统一社会信用代码/纳税人识别号（String，30，必填）。
     */
    @NotBlank(message = "购买方统一社会信用代码/纳税人识别号(gfsbh)为必填项")
    @Size(max = 30, message = "购买方统一社会信用代码/纳税人识别号(gfsbh)长度不能超过 30")
    private String gfsbh;

    /**
     * jksmx明细（List<TsBatchUploadRefundCustomsReqJksmx>）。
     */
    private List<TsBatchUploadRefundCustomsReqJksmx> jksmx;
}
