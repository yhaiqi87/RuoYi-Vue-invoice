package com.luoge.ns.invoice.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * BigDecimal 取值范围校验注解（基于 jakarta.validation 的自定义约束）。
 *
 * <p>仅校验「有值时的范围」，空值交由 {@code @NotNull} 处理，二者配合使用。
 * 需配合 hutool 的 {@code ValidationUtil.validate()} 触发，会被 Bean Validation 引擎自动发现。
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BigDecimalRangeValidator.class)
@Documented
public @interface BigDecimalRange {

    String message() default "数值超出允许范围";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 最小值（可解析为 BigDecimal 的字符串），为空表示不限制下界
     */
    String min() default "";

    /**
     * 最大值（可解析为 BigDecimal 的字符串），为空表示不限制上界
     */
    String max() default "";

    /**
     * 是否包含最小值（含等号）
     */
    boolean minInclusive() default true;

    /**
     * 是否包含最大值（含等号）
     */
    boolean maxInclusive() default true;
}
