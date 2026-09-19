package com.luoge.ns.invoice.common.client;

/**
 * 对外 HTTP 调用抽象（乐企/税务局端）。当前由 leqi 提供 Stub 实现，
 * 后续替换为真实 HTTP 客户端即可，业务处理类无需改动。
 */
public interface InvoiceHttpClient {
    <T> T call(String apiCode, Object request, Class<T> responseType);
}
