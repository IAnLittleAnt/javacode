package com.cn.adonis.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cn.adonis.service.ligent.impl.LigentLoginServiceImpl;
import com.cn.comm.UseSession;
import com.cn.comm.UseTool;
import com.cn.unit.date.DateUtil;
import com.cn.unit.spring.SpringBeanFactoryUtil;

public class SessionInterceptor implements HandlerInterceptor {
	
	// 过滤的路径数组
	private static final String[] IGNORE_URI = {"/socket/", "/layui/", "/main/", "/account/", "/ligent/", "/upload/", "/secret/"};
	
	/**
	 * 执行Handler完成执行此方法 
	 * 应用场景：统一异常处理，统一日志处理 
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
    	
	}
	
	/**
	 * 进入Handler方法之后，返回modelAndView之前执行
	 * 应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，也可以在这里统一指定视图 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView ex) throws Exception {
		
	}
	
	/**
	 * 执行Handler方法之前执行
	 * 用于身份认证、身份授权
	 * 比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		// 允许所有外部资源访问，生产环境指定具体的站点提高安全
	    response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST");
	    response.setHeader("Access-Control-Allow-Headers", "*");
	    response.setHeader("Access-Control-Allow-Credentials", "true");
	    response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=UTF-8");
	    
		// 获取请求的url
        String url = request.getServletPath();
        System.out.println("访问日志："+DateUtil.getStrByDate(null, null)+"\t"+url);
        
        
        // ############### 开始-正式部署时删除 ###############
        /*if(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID) == null){
            request.getSession().setAttribute(UseSession.SESSIONNAME_LOGINUSERID, 1);
        }
        // 测试阶段全部可访问
        if(!UseTool.isEmpty(url)){
        	return true;
        }*/
        // ############### 结束-正式部署时删除 ###############
        
        
        
        // 手机控制器跳过验证
        boolean flag = false;
        for (String item : IGNORE_URI) {
        	if (url.contains(item)) {
        	    flag = true;
        	    break;
        	}
        }
        if(flag){
        	return true;
        }
        
        HttpSession session = request.getSession(true);
        // session已过期
		if(session.getAttribute(UseSession.SESSIONNAME_LOGINUSERID) == null){
			response.setContentType("text/html;charset=UTF-8");
			session.setAttribute(UseSession.SESSIONNAME_ERRORCODE, 501);
			PrintWriter out = response.getWriter();
			out.println("<script>window.parent.location='/layui_file/main/login';</script>");
			return false;
		}
		
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
        System.out.println("访问日志："+DateUtil.getStrByDate(null, null)+"\t"+loginUserId);
    	if(UseTool.isLong(loginUserId)){
        	// 修改最后操作时间
    		LigentLoginServiceImpl ligentLoginService = (LigentLoginServiceImpl) SpringBeanFactoryUtil.getBean("ligentLoginService");
    		ligentLoginService.updateLogin(loginUserId);
    	}
		
		return true;
	}
	
}
