package com.wxq.util.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author weixiaoqiang
 * @date 2021/6/11 16:53
 * 校验新增、修改、删除是否成功 失败抛出异常
 */
@Aspect
@Component
public class CheckResultAop {

  @Pointcut("@annotation(com.wxq.util.aop.CheckResult)")
  public void customerPointCut(){}

  @AfterReturning(value = "customerPointCut()", returning = "result")
  public void before(JoinPoint joinPoint, Integer result){
    if(result != 1){
      throw new RuntimeException();
    }
  }
}
