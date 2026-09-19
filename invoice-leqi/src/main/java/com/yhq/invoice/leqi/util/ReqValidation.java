package com.yhq.invoice.leqi.util;

import cn.hutool.extra.validation.ValidationUtil;
import com.yhq.invoice.common.exception.InvoiceException;
import com.yhq.invoice.leqi.aspect.RequestValidateAspect;
import jakarta.validation.ConstraintViolation;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 入参校验工具：对目标对象执行 Jakarta Bean Validation，校验不通过则抛出 {@link InvoiceException}。
 *
 * <p>供 {@link RequestValidateAspect}（校验方法入参超集）与
 * 各能力服务（校验 {@code from()} 投影后的能力子集）复用，保证校验失败文案一致。
 */
public final class ReqValidation {

    private ReqValidation() {
    }

    /**
     * 校验对象，不通过抛出「入参校验失败：...」的 {@link InvoiceException}。
     *
     * @param target 待校验对象（通常是 {@code InvoiceRequest} 及其子类）
     */
    public static void requireValid(Object target) {
        Set<ConstraintViolation<Object>> violations = ValidationUtil.validate(target);
        if (!violations.isEmpty()) {
            String detail = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("；"));
            throw new InvoiceException("入参校验失败：" + detail);
        }
    }
}
