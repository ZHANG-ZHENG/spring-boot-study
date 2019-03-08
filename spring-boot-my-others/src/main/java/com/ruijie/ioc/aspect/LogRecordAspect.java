package com.ruijie.ioc.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



@Aspect   //定义一个切面
@Component
public class LogRecordAspect {


    // 定义切点Pointcut
    @Pointcut("execution(public * com.ruijie.ioc.controller.*.*(..))")
    public void excudeService() {
    	System.out.println("@Aspect1");
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
    	System.out.println("@Aspect2");
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        System.out.println("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}"+url+method+uri+queryString);

        Object result = pjp.proceed();
        return result;
    }
}