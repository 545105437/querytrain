package com.train.aop;

import com.train.annotation.AnSearchLogHistory;
import com.train.model.SearchLogHistory;
import com.train.service.SearchLogHistoryService;
import com.xiaoleilu.hutool.date.DateTime;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Aspect
public class SearchLogHistoryAop {

	@Autowired
	private SearchLogHistoryService searchLogHistoryService;

	private static Logger logger = LoggerFactory.getLogger(SearchLogHistoryAop.class);

	//匹配com.zkn.learnspringboot.web.controller包及其子包下的所有类的所有方法  
	@Pointcut("@annotation(com.train.annotation.AnSearchLogHistory)")
	private void cut() { }

	/**
	 * 前置通知
	 * @param joinPoint
	 */
	@Before("cut()")
	public void doBefore(JoinPoint joinPoint){

		MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
		Method method = sign.getMethod();
		AnSearchLogHistory annotation = method.getAnnotation(AnSearchLogHistory.class);

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		Object[] s = joinPoint.getArgs();
		SearchLogHistory searchLogHistory = new SearchLogHistory();
		searchLogHistory.setVisitTime(DateTime.now());//操作时间
		searchLogHistory.setArgs(s[0].toString());//参数
		searchLogHistory.setActionType(annotation.actiontype());//操作类型
		searchLogHistory.setAction(annotation.action());//操作描述
		searchLogHistory.setUrl(String.valueOf(request.getRequestURL()));//url
		searchLogHistory.setIp(request.getRemoteAddr());//操作人IP地址

		//录入数据库
		SearchLogHistory history = searchLogHistoryService.save(searchLogHistory);
		logger.info("成功记录操作数据:"+history);
	}

}
