package com.yhq.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/** 统一响应基类：封装文档约定的通用信封字段。 */
@Getter
@Setter
public class InvoiceResponse {
    /** 返回码，00 表示成功。 */
    private String returncode;
    /** 返回信息。 */
    private String returnmsg;
    /** 请求唯一标识。 */
    private String requestId;

    /** 是否成功：返回码为 "00" 视为成功。 */
    public boolean isSuccess() {
        return "00".equals(returncode);
    }
}
