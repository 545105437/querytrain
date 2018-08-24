package com.train.aop;

import com.train.annotation.AnSearchLogHistory;
import com.train.model.SearchLogHistory;
import com.train.service.SearchLogHistoryService;
import com.xiaoleilu.hutool.date.DateTime;
import org.apache.commons.lang.StringUtils;
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

	    //获取拦截的方法对象
		MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
		Method method = sign.getMethod();
		//获取annotation对象
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
        /** 获取访问者真实ip */
        String ip = getIpAddr(request);
        searchLogHistory.setIp(request.getRemoteAddr());//操作人IP地址

		//录入数据库
		SearchLogHistory history = searchLogHistoryService.save(searchLogHistory);
		logger.info("成功记录操作数据:"+history);
	}

    /**
     * 获取访问者IP
     *
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     *
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     *
     * @param request
     * @return
     */
    public  String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

}
