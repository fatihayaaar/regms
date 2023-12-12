package com.fayardev.regms.userservice.aop.validateaop;

import com.fayardev.regms.userservice.aop.validateaop.abstracts.IValidateAOP;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class UserValidateAOP implements IValidateAOP {

    @Override
    @AfterThrowing(value = "execution(* com.fayardev.regms.userservice.validates.UserValidate.*(..))", throwing = "error")
    public void afterThrowing(JoinPoint joinPoint, Throwable error) {

    }
}
