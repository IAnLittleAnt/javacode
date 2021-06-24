package com.cn.adonis.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.adonis.service.ligent.ince.LigentConstService;
import com.cn.comm.UseConst;
import com.cn.comm.UseSession;

/**
 * 根目录
 * @author 10011037@qq.com
 * 2017-06-30
 */
@Controller
@RequestMapping("")
public class RootController {

    private static Logger log = Logger.getLogger(RootController.class);
	@Resource
	private LigentConstService ligentConstService;
    
    /**
     * 登录页
     */
    @RequestMapping("")
    public String login(HttpServletRequest request, Model model) throws Exception{
        log.info("登陆页1");
        if(request.getSession(true).getAttribute(UseSession.SESSIONNAME_LOGINUSERID) == null){
            // 销毁session
            request.getSession(true).invalidate();
            /*// 在线人数
            Object object = request.getSession().getAttribute(UseSession.SESSION_COUNT);
            if(object == null){
                request.getSession().setAttribute(_Const.SESSION_COUNT, 0);
            }else{
                int sessionCount = -1 + _Tool.toInt(request.getSession().getAttribute(_Const.SESSION_COUNT));
                request.getSession().setAttribute(_Const.SESSION_COUNT, sessionCount);
            }*/
        }
        request.getSession(true).invalidate();
        
		model.addAttribute("errorCode", 1);
		model.addAttribute("isDiringCode", ligentConstService.findByKey(UseConst.DEFAULT_LOGIN_CODE));
        return "auth/account/login";
    }
    
}
