package com.yhq.invoice.common.validation;

import cn.hutool.core.date.DateException;
import cn.hutool.core.date.DateUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * {@link DatePattern} 的校验实现：基于 hutool 的 {@code DateUtil.parse(dateStr, pattern)}
 * 判断字符串是否符合指定日期格式，无法解析（抛出 {@code DateException}）即视为不匹配。
 * 空值返回 true，交由 {@code @NotBlank} 负责非空校验。
 */
public class DatePatternValidator implements ConstraintValidator<DatePattern, String> {

    private String pattern;

    @Override
    public void initialize(DatePattern annotation) {
        this.pattern = annotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        try {
            DateUtil.parse(value, pattern);
            return true;
        } catch (DateException e) {
            return false;
        }
    }
}
