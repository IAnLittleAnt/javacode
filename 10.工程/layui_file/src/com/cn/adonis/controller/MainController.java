package com.cn.adonis.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.adonis.service.auth.ince.AuthAccountService;
import com.cn.adonis.service.auth.ince.AuthUserService;
import com.cn.adonis.service.ligent.ince.LigentConstService;
import com.cn.comm.UseConst;
import com.cn.comm.UsePath;
import com.cn.comm.UseSession;
import com.cn.comm.UseTool;
import com.cn.paas.aliyun.sms.SmsConst;
import com.cn.unit.captcha.CaptchaUtil;

/**
 * 开放性接口
 * @author 10011037@qq.com
 * 2017-06-30
 */
@Controller
@RequestMapping("/main/")
public class MainController {
	
	private static Logger log = Logger.getLogger(MainController.class);
	@Resource
	private AuthAccountService authAccountService;
	@Resource
	private AuthUserService authUserService;
	@Resource
	private LigentConstService ligentConstService;
	
	private static String MODEL_KEY = "main/";
	private static String MODEL_KEY_ACCOUNT = "auth/account/";
    
	/**
	 * 移动端聊天
	 */
    @RequestMapping("mobile")
    public String mobile(HttpServletRequest request, Model model) throws Exception{
        return "mobile/index";
    }
    
	/**
	 * 图标页面
	 */
    @RequestMapping("test")
    public String test(HttpServletRequest request, Model model) throws Exception{
        return "test";
    }
	
	
	/**
	 * 登录帐号
	 */
    @RequestMapping("login")
    public String login(HttpServletRequest request, Model model) throws Exception{
    	request.setCharacterEncoding("UTF-8");
		Integer errorCode = 1;
    	log.info("登陆页");
		HttpSession session = request.getSession();
		if(session.getAttribute(UseSession.SESSIONNAME_ERRORCODE) != null){
			// 获取错误码，拦截器返回
    		errorCode = UseTool.toInt(session.getAttribute(UseSession.SESSIONNAME_ERRORCODE));
    		// 销毁session
    		session.invalidate();
		}else if(session.getAttribute(UseSession.SESSIONNAME_LOGINUSERID) != null){
    		// 销毁session
    		session.invalidate();
    	}
		model.addAttribute("errorCode", errorCode);
		model.addAttribute("isDiringCode", ligentConstService.findByKey(UseConst.DEFAULT_LOGIN_CODE));
        return MODEL_KEY_ACCOUNT + "login";
    }
    
	/**
	 * 注册帐号
	 */
    @RequestMapping("reg")
    public String reg(HttpServletRequest request, Model model) throws Exception{
		model.addAttribute("smsType", SmsConst.SMS_TYPE_YHZCYZM);
		model.addAttribute("userId", UseConst.DEFAULT_USERID);
        return MODEL_KEY_ACCOUNT + "reg";
    }
	
	/**
	 * 忘记密码
	 */
    @RequestMapping("forget")
    public String forget(HttpServletRequest request, Model model) throws Exception{
        return MODEL_KEY_ACCOUNT + "forget";
    }
    
	/**
	 * WEB配置404错误
	 */
    @RequestMapping("404")
    public String error404(HttpServletRequest request, Model model) throws Exception{
        return MODEL_KEY + "error-404";
    }
	
	/**
	 * WEB配置500错误
	 */
    @RequestMapping("500")
    public String error500(HttpServletRequest request, Model model) throws Exception{
        return MODEL_KEY + "error-500";
    }
    
	/**
	 * 图标页面
	 */
    @RequestMapping("icon")
    public String icon(HttpServletRequest request, Model model) throws Exception{
        return MODEL_KEY + "icon";
    }
    
	/**
	 * 扩展图标页面
	 */
    @RequestMapping("iconfont")
    public String iconfont(HttpServletRequest request, Model model) throws Exception{
        return MODEL_KEY + "iconfont";
    }
    
    /**
     * 生成验证码
     */
    @RequestMapping("captcha")  
    public void captcha(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		CaptchaUtil instance = new CaptchaUtil();
		// 把验证码保存到session中
		request.getSession().setAttribute(UseSession.SESSIONNAME_CAPTCHA, instance.getCode());
		Cookie cookie = new Cookie("scaptcha", instance.getCode());
		cookie.setMaxAge(1800);
		response.addCookie(cookie);
		instance.write(response.getOutputStream());
    }
    
	/**
	 * PDF浏览
	 */
    @RequestMapping("/pdf")
    public String pdf(HttpServletRequest request, Model model) throws Exception{
    	String fileName = request.getParameter("fileName");
    	model.addAttribute("fileName", fileName);
        return "main/pdf";
    }
    
    /**
     * 文件下载功能
     */
    @RequestMapping("/download")
    public void download(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String fileName = request.getParameter("fileName");
    	String filePath = request.getParameter("filePath");
    	log.info("文件下载功能:filePath["+filePath+"],fileName["+fileName+"]");
    	// 获取根目录
    	String rootPath = request.getSession().getServletContext().getRealPath("/");
        if(filePath.indexOf(UsePath.PROJECT_NAME) >= 0){
        	filePath = filePath.replaceAll(UsePath.PROJECT_NAME, "/");
        }
        if(UseTool.isEmpty(fileName)){
        	fileName = filePath.substring(filePath.lastIndexOf("/")+1, filePath.length());
            if(UseTool.isEmpty(fileName)){
            	fileName = System.currentTimeMillis() + filePath.substring(filePath.lastIndexOf("."), filePath.length());
            }
        }
        // 获取输入流  
        @SuppressWarnings("resource")
		InputStream bis = new BufferedInputStream(new FileInputStream(new File(rootPath + filePath)));
        
        fileName = fileName.replaceAll(" ", "_");
        fileName = new String(fileName.getBytes(),"ISO-8859-1");
        // 设置编码格式
        response.setCharacterEncoding("UTF-8");
        // 设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);    
        // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
    } 
    
}
