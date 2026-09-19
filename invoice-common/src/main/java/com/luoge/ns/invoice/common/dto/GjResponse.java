package com.luoge.ns.invoice.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 归集（乐企能力 203067）响应基类。
 *
 * <p>沿用 {@link InvoiceResponse} 的通用信封字段（returncode / returnmsg / requestId），
 * 便于 {@code LeqiHttpUtil} 统一回填成功标识；归集响应继承本类并补充专属字段。
 */
@Getter
@Setter
public class GjResponse extends InvoiceResponse {
}
