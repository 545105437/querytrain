package com.train.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component  
@Aspect  
public class ApiControllerAop {

	private static Logger logger = LoggerFactory.getLogger(ApiControllerAop.class);

	//匹配com.zkn.learnspringboot.web.controller包及其子包下的所有类的所有方法  
	@Pointcut("execution(public * com.train.controller.*(..))")
	public void executeService(){  
	  
	}

	@Before("execution(* com.train.controller.*.*(..))")
	public void doBefore(JoinPoint joinPoint){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		//url
		logger.info("url={}",request.getRequestURL());

		//method
		logger.info("method={}",request.getMethod());

		//ip
		logger.info("ip={}",request.getRemoteAddr());

		//类方法
		logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName() + "," + joinPoint.getSignature().getName());

		//参数
		logger.info("args={}",joinPoint.getArgs());

	}

	/** 
	 * 环绕通知： 
	 *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型 
	 */  
	@Around("execution(* com.train.controller.*.*(..))")
	public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){  
		logger.info("调用接口：" + proceedingJoinPoint.getSignature().getName());
	    try {  
	        Object obj = proceedingJoinPoint.proceed();  
	        return obj;  
	    } catch (Throwable throwable) {  
	        throwable.printStackTrace();  
	    }  
	    return null;  
	}

}
