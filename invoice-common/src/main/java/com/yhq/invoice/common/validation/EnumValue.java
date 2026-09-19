package com.yhq.invoice.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 枚举取值校验注解（基于 jakarta.validation 的自定义约束）。
 *
 * <p>借助 Hutool {@code EnumUtil} 读取 {@link #enumClass()} 各常量的 {@link #field()}（默认 code）字段值，
 * 校验字符串字段取值是否落在允许编码集合内，防止超出枚举范围。该实现不绑定具体枚举类型，任意带 code 字段的枚举均可复用。
 * 空值返回 true，交由 {@code @NotNull}/{@code @NotBlank} 负责非空校验，二者配合使用。</p>
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValueValidator.class)
@Documented
public @interface EnumValue {

    String message() default "取值超出枚举范围";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 允许取值来源枚举（各常量需包含 {@link #field()} 指定的编码字段）。
     */
    Class<? extends Enum<?>> enumClass();

    /**
     * 作为编码的字段名，默认 "code"。
     */
    String field() default "code";
}
