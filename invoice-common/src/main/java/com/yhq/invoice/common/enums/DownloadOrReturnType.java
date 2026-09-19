package com.yhq.invoice.common.enums;

import lombok.Getter;

/**
 * 申请类型（乐企「下载/退回额度」接口入参 sqlx 的允许取值）。
 * <p>0：下载；1：退回。</p>
 */
@Getter
public enum DownloadOrReturnType {

    DOWNLOAD("0", "下载"),
    RETURN("1", "退回");

    /**
     * -- GETTER --
     * 乐企报文使用的编码值（"0"/"1"）。
     */
    private final String code;
    private final String desc;

    DownloadOrReturnType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DownloadOrReturnType fromCode(String code) {
        for (DownloadOrReturnType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("非法的申请类型(sqlx)值: " + code + "，仅支持 0(下载)/1(退回)");
    }
}
