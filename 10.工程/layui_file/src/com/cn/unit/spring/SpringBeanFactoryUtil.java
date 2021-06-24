package com.cn.unit.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 外层调用service工具类
 * @author 10011037@qq.com
 * 2017-07-14
 */
public class SpringBeanFactoryUtil implements ApplicationContextAware {
	
	private static ApplicationContext appCtx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		appCtx = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
        return appCtx;
    }
	
	public static Object getBean(String beanName) {
        return appCtx.getBean(beanName);
    }

}
