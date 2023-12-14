package com.fayardev.regms.userservice.aop.repositoryaop.abstracts;

import org.aspectj.lang.JoinPoint;

public interface IRepositoryAOP {

    void before(JoinPoint joinPoint);

    void after(JoinPoint joinPoint);
}
