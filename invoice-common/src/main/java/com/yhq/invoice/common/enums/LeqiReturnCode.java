package com.yhq.invoice.common.enums;

import lombok.Getter;

/**
 * 乐企平台业务返回码（returncode）。
 *
 * <p>文档约定：{@code 00} 表示成功，其它值表示失败（具体失败信息以 returnmsg 为准）。
 * 个别接口在 {@code 00} 之外定义了专用返回码（如发票上传结果/用途状态查询的 {@code 01} = 未查询到发票信息）。
 */
@Getter
public enum LeqiReturnCode {

    /**
     * 成功。
     */
    SUCCESS("00", "成功"),
    /**
     * 部分接口专用：未查询到发票信息。
     */
    NOT_FOUND("01", "未查询到发票信息");

    private final String code;
    private final String desc;

    LeqiReturnCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 按返回码取枚举；未知码返回 null（失败，文案以 returnmsg 为准）。
     */
    public static LeqiReturnCode fromCode(String code) {
        for (LeqiReturnCode v : values()) {
            if (v.code.equals(code)) {
                return v;
            }
        }
        return null;
    }

    /**
     * 是否为成功返回码。
     */
    public static boolean isSuccess(String code) {
        return SUCCESS.code.equals(code);
    }
}
