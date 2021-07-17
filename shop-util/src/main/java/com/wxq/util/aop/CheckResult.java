package com.wxq.util.aop;

import java.lang.annotation.*;

/**
 * @author weixiaoqiang
 * @date 2021/6/11 17:27
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckResult {
}
