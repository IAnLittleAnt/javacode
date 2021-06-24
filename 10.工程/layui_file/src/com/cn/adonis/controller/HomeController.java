package com.cn.adonis.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.adonis.service.auth.ince.AuthMenuService;
import com.cn.adonis.service.auth.ince.AuthUserService;
import com.cn.comm.UseSession;
import com.cn.comm.UseTool;
import com.cn.unit.log.ArchivesLog;

/**
 * 系统信息管理
 * @author 10011037@qq.com
 * 2017-06-30
 */
@Controller
@RequestMapping("/home")
public class HomeController {
	
	private static Logger log = Logger.getLogger(HomeController.class);
	@Resource
	private AuthUserService authUserService;
	@Resource
	private AuthMenuService authMenuService;
	
	/**
	 * 主页面
	 */
    @ArchivesLog(content = "查看主页面")
    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) throws Exception{
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	log.info("主页面["+loginUserId+"]");
    	// 用户信息对象
    	model.addAttribute("loginUser", authUserService.fainById(loginUserId));
    	// 菜单信息集合
    	model.addAttribute("menuList", authMenuService.findByUser(loginUserId));
        return "index";
    }
	
	/**
	 * 主页面
	 */
    @RequestMapping("/console")
    public String console(HttpServletRequest request, Model model) throws Exception{
        return "console";
    }
	
}
