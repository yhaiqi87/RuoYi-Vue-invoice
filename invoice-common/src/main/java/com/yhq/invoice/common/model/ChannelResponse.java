package com.yhq.invoice.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelResponse<T> {
    private String code;
    private String msg;
    private T data;

    /**
     * 构造通道响应。
     */
    public static <T> ChannelResponse<T> of(String code, String msg, T data) {
        ChannelResponse<T> r = new ChannelResponse<>();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
