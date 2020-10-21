package com.oym.component.web.log.annotation;

import java.lang.annotation.*;

/**
 * @author zyd
 * @date 2019/12/11 21:12
 * @desc
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogSign {
    /**
     * 日志类型
     */
    String logType() default "";

    /**
     * 日志行为
     */
    String logAction() default "";

    /**
     * 是否记录参数
     */
    boolean recordParam() default true;
}