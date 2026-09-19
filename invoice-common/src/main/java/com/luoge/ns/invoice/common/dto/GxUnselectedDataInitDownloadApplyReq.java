package com.luoge.ns.invoice.common.dto;

import com.luoge.ns.invoice.common.enums.CapabilityCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 抵扣勾选请求：未勾选数据初始化清单下载申请（WGXSJCSHQDXZSQDKGX）。
 * 字段依据《乐企增值税抵扣勾选能力说明文档-V3.025》接口 15「未勾选数据初始化清单下载申请」补全。
 */
@Getter
@Setter
@NoArgsConstructor
public class GxUnselectedDataInitDownloadApplyReq extends GxRequest {

    public GxUnselectedDataInitDownloadApplyReq(CapabilityCode capabilityCode) {
        super(capabilityCode);
    }


    /**
     * 购方纳税人识别号/统一社会信用代码（String，30，必填）。
     */
    @NotBlank(message = "购方纳税人识别号/统一社会信用代码(gfsbh)为必填项")
    @Size(max = 30, message = "购方纳税人识别号/统一社会信用代码(gfsbh)长度不能超过 30")
    private String gfsbh;

    /**
     * 业务类型（String，2，必填）。
     */
    @NotBlank(message = "业务类型(ywlx)为必填项")
    @Size(max = 2, message = "业务类型(ywlx)长度不能超过 2")
    private String ywlx;

    /**
     * 凭证种类（String，2，必填）。
     */
    @NotBlank(message = "凭证种类(pzzl)为必填项")
    @Size(max = 2, message = "凭证种类(pzzl)长度不能超过 2")
    private String pzzl;

    /**
     * 起始开票月份（String，6，否）。
     */
    @Size(max = 6, message = "起始开票月份(kpyf)长度不能超过 6")
    private String kpyf;

    /**
     * 结束开票月份（String，6，否）。
     */
    @Size(max = 6, message = "结束开票月份(jskpyf)长度不能超过 6")
    private String jskpyf;
}
