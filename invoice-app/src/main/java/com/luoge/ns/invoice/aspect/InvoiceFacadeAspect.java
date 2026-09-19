package com.luoge.ns.invoice.aspect;

import com.luoge.ns.invoice.common.model.ChannelResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 门面兜底切面：拦截 facade 包下全部门面（InvoiceFacade / GjFacade ...）的 public 方法，做两层兜底，
 * 保证 Controller 拿到的永远是「非 null 的 ChannelResponse」：
 * <ol>
 *     <li>异常兜底：任意异常（含 InvoiceException、渠道无实现等）统一封装为 ChannelResponse，
 *         不再以异常形式穿透到 Controller；</li>
 *     <li>非空兜底：若方法正常返回 null，封装为系统错误 ChannelResponse，避免调用方 NPE。</li>
 * </ol>
 */
@Aspect
@Component
public class InvoiceFacadeAspect {

    private static final Logger log = LoggerFactory.getLogger(InvoiceFacadeAspect.class);

    /**
     * 系统级错误码，与业务成功码 {@code "00"} 区分。
     */
    private static final String SYS_ERROR_CODE = "9999";

    @Pointcut("execution(* com.luoge.ns.invoice.facade.*.*(..))")
    public void facadeMethods() {
    }

    @Around("facadeMethods()")
    public Object aroundFacade(ProceedingJoinPoint joinPoint) {
        try {
            Object result = joinPoint.proceed();
            if (result == null) {
                log.warn("{} 返回 null，兜底封装为系统错误响应",
                        joinPoint.getSignature().toShortString());
                return ChannelResponse.of(SYS_ERROR_CODE, "未获取到响应数据", null);
            }
            return result;
        } catch (Throwable t) {
            // 不吞掉 JVM 错误（如 OutOfMemoryError），原样抛出由容器处理
            if (t instanceof Error) {
                throw (Error) t;
            }
            log.error("{} 执行异常，兜底封装为 ChannelResponse", joinPoint.getSignature().toShortString());
            // log.error("{} 执行异常，兜底封装为 ChannelResponse", joinPoint.getSignature().toShortString(), t);
            String msg = t.getMessage() != null ? t.getMessage() : "系统处理异常";
            return ChannelResponse.of(SYS_ERROR_CODE, msg, null);
        }
    }
}
