package com.yhq.invoice.leqi.annotation;

import java.lang.annotation.*;

/**
 * 类级注解：标注某个能力服务类所依据实现的乐企能力说明文档及版本。
 *
 * <p>用于把「能力服务实现」与「权威字段/接口编码来源文档」关联起来，便于核对 DTO 字段、接口编码、返回码时
 * 快速定位到对应的 {@code doc/} 文档（如《乐企数字化电子发票（基础版）开票能力说明文档-V6.006》）。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DocVersion {

    /**
     * 乐企能力说明文档名称，如「乐企数字化电子发票（基础版）开票能力说明文档」。
     */
    String doc();

    /**
     * 文档版本号，如「V6.006」。
     */
    String value();
}
