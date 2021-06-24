package com.cn.unit.spring;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 自定义session监听器
 * @author 10011037@qq.com
 * 2016-09-20
 */
public class MySessionListener implements HttpSessionListener {
    
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		MySessionContext.AddSession(event.getSession());
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
        MySessionContext.DelSession(session);
	}
	
}