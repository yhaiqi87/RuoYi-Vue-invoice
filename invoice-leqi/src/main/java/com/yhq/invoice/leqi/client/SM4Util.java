package com.yhq.invoice.leqi.client;

/**
 * 乐企报文 SM4 加解密工具（占位实现）。
 *
 * <p>基础版文档「HTTP 请求调用说明」要求请求报文经 {@code SM4Util.encryptEcb(秘钥, body)} 加密、
 * 响应 data 经 {@code SM4Util.decryptEcb(秘钥, data)} 解密。当前为占位：原样透传，
 * 待接入真实 SM4（ECB/国密）实现与密钥后替换。
 */
public final class SM4Util {

    private SM4Util() {
    }

    /**
     * SM4 ECB 加密（占位：当前透传）。
     */
    public static String encryptEcb(String key, String plaintext) {
        // TODO 接入真实 SM4/国密 ECB 加密（key 非空时）
        return plaintext;
    }

    /**
     * SM4 ECB 解密（占位：当前透传）。
     */
    public static String decryptEcb(String key, String ciphertext) {
        // TODO 接入真实 SM4/国密 ECB 解密（key 非空时）
        return ciphertext;
    }
}
