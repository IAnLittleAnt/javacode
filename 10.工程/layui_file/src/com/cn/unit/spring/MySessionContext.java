package com.cn.unit.spring;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * 自定义session 
 * @author 10011037@qq.com
 * 2016-09-20
 */
public class MySessionContext {
	
	private static HashMap<String, HttpSession> mymap = new HashMap<String, HttpSession>();

    public static synchronized void AddSession(HttpSession session) {
        if (session != null) {
            mymap.put(session.getId(), session);
        }
    }
    
    public static synchronized void DelSession(HttpSession session) {
        if (session != null) {
            mymap.remove(session.getId());
        }
    }

    public static synchronized HttpSession getSession(String session_id) {
        if (session_id == null)
        	return null;
        return (HttpSession) mymap.get(session_id);
    }
}
