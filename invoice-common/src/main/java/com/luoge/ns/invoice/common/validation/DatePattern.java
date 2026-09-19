package com.luoge.ns.invoice.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 日期格式校验注解（基于 jakarta.validation 的自定义约束）。
 *
 * <p>校验字符串是否符合指定日期格式（如 {@code yyyy-MM}），底层使用 hutool 的
 * {@code DateUtil.isMatch(format, dateStr)} 进行匹配。空值返回 true，交由 {@code @NotBlank} 负责非空校验。
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DatePatternValidator.class)
@Documented
public @interface DatePattern {

    String message() default "日期格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 期望的日期格式，遵循 hutool / SimpleDateFormat 的 pattern，如 yyyy-MM、yyyy-MM-dd
     */
    String pattern();
}
