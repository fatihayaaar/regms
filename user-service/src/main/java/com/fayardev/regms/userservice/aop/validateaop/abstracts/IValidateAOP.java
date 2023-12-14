package com.fayardev.regms.userservice.aop.validateaop.abstracts;

import org.aspectj.lang.JoinPoint;
public interface IValidateAOP {

    void afterThrowing(JoinPoint joinPoint, Throwable error);
}
