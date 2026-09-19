package com.luoge.ns.invoice.common.validation;

import cn.hutool.core.util.EnumUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@link EnumValue} 的校验实现：借助 Hutool {@link EnumUtil} 读取 {@code enumClass} 各常量的
 * {@code field}（默认 code）字段值，校验字符串取值是否落在允许编码集合内，防止超出枚举范围。
 * 不绑定具体枚举类型，任意带 code 字段的枚举均可复用。空值返回 true，交由 {@code @NotNull}/{@code @NotBlank} 负责非空校验。
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {

    private Set<String> allowedCodes;

    @Override
    public void initialize(EnumValue annotation) {
        List<Object> codes = EnumUtil.getFieldValues(annotation.enumClass(), annotation.field());
        allowedCodes = codes.stream()
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return allowedCodes.contains(value);
    }
}
