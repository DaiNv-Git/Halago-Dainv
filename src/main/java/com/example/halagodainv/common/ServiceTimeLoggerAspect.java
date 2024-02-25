package com.example.halagodainv.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceTimeLoggerAspect {
//    ThreadLocal<Long> startTime = new ThreadLocal<>();
//
//    @Before("execution(* com.example.halagodainv.service.impl.*ServiceImpl.*(..))")
//    public void logMethodStart() {
//        startTime.set(System.currentTimeMillis());
//    }
//
//    @After("execution(* com.example.halagodainv.service.impl.*ServiceImpl.*(..))")
//    public void logMethodEnd() {
//        long endTime = System.currentTimeMillis();
//        long duration = endTime - startTime.get();
//        System.out.println("Thời gian xử lý: " + duration + " ms");
//        startTime.remove();
//    }
}
