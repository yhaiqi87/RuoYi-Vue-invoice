package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 乐企响应：DownloadOrReturnQuota。字段依据乐企开票能力说明文档补全。
 */
@Getter
@Setter
public class DownloadOrReturnQuotaRes extends InvoiceResponse {
    /**
     * 授信额度使用区间起（类型 date，必填 否，下载授信额度时，有此节点服务发票开票日期在授信额度使用区间（含）时才可以使用.格式：yyyymmdd）
     */
    private String syqjq;
    /**
     * 授信额度使用区间止（类型 date，必填 否，下载授信额度时，有此节点服务发票开票日期在授信额度使用区间（含）时才可以使用.格式：yyyymmdd）
     */
    private String syqjz;
}
