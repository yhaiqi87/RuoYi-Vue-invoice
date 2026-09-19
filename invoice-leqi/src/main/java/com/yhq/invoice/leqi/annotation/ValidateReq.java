package com.yhq.invoice.leqi.annotation;

import java.lang.annotation.*;

/**
 * 类级注解：标注在能力服务类上，由 {@code RequestValidateAspect} 切面统一对其方法入参
 * （继承自 {@code InvoiceRequest} 的请求对象）执行 jakarta.validation 注解校验。
 *
 * <p>方法内无需再手写 ValidationUtil.validate() 校验块，校验失败统一抛出 InvoiceException。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateReq {
}
