package com.luoge.ns.invoice.leqi.aspect;

import com.luoge.ns.invoice.common.dto.InvoiceRequest;
import com.luoge.ns.invoice.leqi.util.ReqValidation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 请求入参校验切面：拦截被 {@link com.luoge.ns.invoice.leqi.annotation.ValidateReq} 标注的类，
 * 对其方法入参中所有 {@link InvoiceRequest} 类型的请求对象执行 jakarta.validation 注解校验。
 *
 * <p>校验逻辑复用 {@link ReqValidation#requireValid(Object)}（底层 hutool 的 {@link cn.hutool.extra.validation.ValidationUtil}，
 * 即 Jakarta Bean Validation），与方法中手写校验块行为一致；校验不通过抛出 {@link com.luoge.ns.invoice.common.exception.InvoiceException}。
 */
@Aspect
@Component
public class RequestValidateAspect {

    @Pointcut("@within(com.luoge.ns.invoice.leqi.annotation.ValidateReq)")
    public void validateReqPointcut() {
    }

    @Before("validateReqPointcut()")
    public void doValidate(JoinPoint joinPoint) {
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof InvoiceRequest request) {
                ReqValidation.requireValid(request);
            }
        }
    }
}
