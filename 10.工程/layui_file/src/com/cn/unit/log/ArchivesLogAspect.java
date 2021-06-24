package com.cn.unit.log;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cn.adonis.entity.base.BaseLog;
import com.cn.adonis.service.base.impl.BaseLogServiceImpl;
import com.cn.adonis.service.base.ince.BaseLogService;
import com.cn.comm.UseSession;
import com.cn.comm.UseTool;
import com.cn.unit.spring.SpringBeanFactoryUtil;

/**
 * AOP 切面编程，结合日志框架
 * @author Administrator
 *
 */
@Aspect
@Component
public class ArchivesLogAspect {

	private static Logger log = Logger.getLogger(ArchivesLogAspect.class);
	@Resource
	private BaseLogService baseLogService;
	
	
    @Pointcut("@annotation(ArchivesLog)")
    public void controllerAspect() {
        System.out.println("切入点...");
    	// 初始化业务逻辑类
        if(this.baseLogService == null){
        	log.info("初始化业务逻辑类:baseLogService");
        	this.baseLogService = (BaseLogServiceImpl) SpringBeanFactoryUtil.getBean("baseLogService");
        }
    }
 
    /**
     * 方法调用后触发, 记录正常操作
     */
    @AfterReturning("controllerAspect()")
    public void after(JoinPoint joinPoint) throws ClassNotFoundException {
        BaseLog baseLog = getMethodDesc(joinPoint);
        BaseLog logs = getUser();
        if(baseLog!=null){
        	baseLog.setLogState(1);
        	baseLog.setUserId(logs.getUserId());
        	baseLog.setSource(logs.getSource());
        	baseLog.setIp(getIp());
        	baseLog.setDataJson(getDataJson());
        }
        baseLogService.insertLog(baseLog);
    }
    
    /**
     *  发生异常，走此方法
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void AfterThrowing(JoinPoint joinPoint, Throwable e) {
        try {
            BaseLog baseLog = getMethodDesc(joinPoint);
            BaseLog logs = getUser();
            if(baseLog!=null){
                String exMsg = e.getCause().toString();
            	baseLog.setLogState(2);
            	baseLog.setUserId(logs.getUserId());
            	baseLog.setSource(logs.getSource());
            	baseLog.setIp(getIp());
            	baseLog.setDataJson(getDataJson());
            	baseLog.setExMsg(exMsg);
                baseLogService.insertLog(baseLog);
            }
        } catch (Exception e1) {
            log.error(e1.getMessage());
        }
    }
 
    /**
     * 获取 注解中对方法的描述
     */
    public static BaseLog getMethodDesc(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        @SuppressWarnings("rawtypes")
		Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String operteContent = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                @SuppressWarnings("rawtypes")
				Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                	// 操作说明
                    operteContent = method.getAnnotation(ArchivesLog.class).content();
                    break;
                }
            }
        }
        BaseLog logInfo = new BaseLog(targetName, methodName, operteContent);
        return logInfo;
    }
 
    /**
     * 参数Json数据
     */
    public static String getDataJson() {
    	// 1:在切面方法里面获取一个request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
        Enumeration<String> enumeration = request.getParameterNames();
        String key;
        while (enumeration.hasMoreElements()) {
        	key = enumeration.nextElement();
            parameterMap.put(key, request.getParameter(key));
        }
        String revert = "";
        if(parameterMap!=null&&!parameterMap.isEmpty()){
        	revert = UseTool.toJson(parameterMap);
        }
        return revert;
    }
 
    /**
     * 得到用户信息
     */
    public static BaseLog getUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// 获取请求的url
        String url = request.getServletPath();
        Long loginUserId = null;
        Integer source = 1;
        if (url.contains("/mt/")) {
        	loginUserId = UseTool.toLong(request.getParameter("loginUserId"));
        	source = 2;
    	}else{
        	// 获取登录用户编号
        	loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
        	source = 1;
    	}
        BaseLog logInfo = new BaseLog(loginUserId, source);
        return logInfo;
    }
 
    /**
     * 获取访问客户端ip地址
     */
    public static String getIp() {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String clientIp = request.getHeader("x-forwarded-for");
		if(clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("Proxy-Client-IP");
		}
		if(clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if(clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getRemoteAddr();
		}
		if(clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			try {
				clientIp = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				clientIp = null;
			}
		}
		return clientIp;
    }
	
}
