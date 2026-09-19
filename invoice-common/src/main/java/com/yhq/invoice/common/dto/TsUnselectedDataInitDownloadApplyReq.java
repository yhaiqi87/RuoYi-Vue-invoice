package com.yhq.invoice.common.dto;

import com.yhq.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 退税勾选请求：未勾选数据初始化清单下载申请（WGXSJCSHQDXZSQTSGX）。
 * 字段依据《乐企增值税退税勾选能力说明文档-V2.013》接口 5「未勾选数据初始化清单下载申请」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class TsUnselectedDataInitDownloadApplyReq extends TsRequest {

    public TsUnselectedDataInitDownloadApplyReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购方纳税人识别号/统一社会信用代码（String，30，必填）。
     */
    @NotBlank(message = "购方纳税人识别号/统一社会信用代码(gfsbh)为必填项")
    @Size(max = 30, message = "购方纳税人识别号/统一社会信用代码(gfsbh)长度不能超过 30")
    private String gfsbh;

    /**
     * 业务类型（String，2，必填）：03 退税业务。
     */
    @NotBlank(message = "业务类型(ywlx)为必填项")
    @Size(max = 2, message = "业务类型(ywlx)长度不能超过 2")
    private String ywlx;

    /**
     * 凭证种类（String，2，必填）：01 发票 / 03 海关缴款书。
     */
    @NotBlank(message = "凭证种类(pzzl)为必填项")
    @Size(max = 2, message = "凭证种类(pzzl)长度不能超过 2")
    private String pzzl;

    /**
     * 起始开票月份（String，6，否），格式 YYYYMM，申请下载起始开票月份至当前自然月，为空则默认查近三月。
     */
    @Size(max = 6, message = "起始开票月份(kpyf)长度不能超过 6")
    private String kpyf;

    /**
     * 结束开票月份（String，6，否），格式 YYYYMM，为空则默认截止到当前月份。
     */
    @Size(max = 6, message = "结束开票月份(jskpyf)长度不能超过 6")
    private String jskpyf;
}
