package com.luoge.ns.invoice.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

/**
 * {@link BigDecimalRange} 的校验实现：校验 BigDecimal 是否落在 [min, max] 区间内，
 * 是否含边界由 minInclusive / maxInclusive 控制。空值返回 true，交由 {@code @NotNull} 负责非空校验。
 */
public class BigDecimalRangeValidator implements ConstraintValidator<BigDecimalRange, BigDecimal> {

    private BigDecimal min;
    private BigDecimal max;
    private boolean minInclusive;
    private boolean maxInclusive;

    @Override
    public void initialize(BigDecimalRange annotation) {
        this.min = annotation.min().isEmpty() ? null : new BigDecimal(annotation.min());
        this.max = annotation.max().isEmpty() ? null : new BigDecimal(annotation.max());
        this.minInclusive = annotation.minInclusive();
        this.maxInclusive = annotation.maxInclusive();
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (min != null) {
            int cmp = value.compareTo(min);
            if (minInclusive ? cmp < 0 : cmp <= 0) {
                return false;
            }
        }
        if (max != null) {
            int cmp = value.compareTo(max);
            if (maxInclusive ? cmp > 0 : cmp >= 0) {
                return false;
            }
        }
        return true;
    }
}
