package com.luoge.ns.invoice.common.exception;

/** 开票业务统一异常。 */
public class InvoiceException extends RuntimeException {
    public InvoiceException(String message) {
        super(message);
    }

    public InvoiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
