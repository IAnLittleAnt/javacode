package com.cn.adonis.controller.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.adonis.entity.auth.AuthAccount;
import com.cn.adonis.entity.auth.AuthSecret;
import com.cn.adonis.entity.auth.AuthUser;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.auth.ince.AuthAccountService;
import com.cn.adonis.service.auth.ince.AuthActionService;
import com.cn.adonis.service.auth.ince.AuthSecretService;
import com.cn.adonis.service.auth.ince.AuthUserService;
import com.cn.comm.UseSession;
import com.cn.comm.UseTool;
import com.cn.unit.log.ArchivesLog;

/**
 * 密保问题接口
 * @author 10011037@qq.com
 * 2017-06-30
 */
@Controller
@RequestMapping("/auth/secret/")
public class AuthSecretController {
	
	private static Logger log = Logger.getLogger(AuthSecretController.class);
	@Resource
	private AuthActionService authActionService;
	@Resource
	private AuthSecretService authSecretService;
	@Resource
	private AuthAccountService authAccountService;
	@Resource
	private AuthUserService authUserService;
	
	private static String MODEL_KEY = "auth/secret/";
	
	/**
	 * 密保页面
	 */
    @ArchivesLog(content = "查看密保问题")
    @RequestMapping("view")
    public String list(HttpServletRequest request, Model model) throws Exception{
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	List<AuthSecret> secretList = authSecretService.findByUser(loginUserId);
    	if(secretList==null||secretList.size()==0||secretList.get(0)==null){
    		secretList = new ArrayList<AuthSecret>();
    		for (int i = 0; i < 3; i++) {
    			AuthSecret authSecret = new AuthSecret();
    			secretList.add(authSecret);
    		}
    	}
    	model.addAttribute("secretList", secretList);
        return MODEL_KEY + "view";
    }
	
	/**
	 * 根据账号获取密保信息
	 */
	@RequestMapping(value = "findByCode", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String findByCode(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String userCode = request.getParameter("userCode");
        // 参数验证
    	log.info("根据账号获取密保信息:userCode["+userCode+"]");
    	if(UseTool.isEmpty(userCode)){
    		log.error("根据账号获取密保信息:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	AuthAccount authAccount = authAccountService.fainByCode(userCode);
    	if(authAccount==null || !UseTool.isLong(authAccount.getAccountId())){
    		log.error("根据账号获取密保信息:找不到该账号");
			return UseTool.toJson(new StateModel(101, "系统找不到该账号信息!"));
    	}
    	AuthUser authUser = authUserService.fainByAccount(authAccount.getAccountId());
    	if(authUser==null || !UseTool.isLong(authUser.getUserId())){
    		log.error("根据账号获取密保信息:找不到该账号");
			return UseTool.toJson(new StateModel(102, "系统找不到该账号信息!"));
    	}
    	if(UseTool.isInt(authUser.getSecretErrorCount())&&authUser.getSecretErrorCount().intValue()>=3){
    		log.error("根据账号获取密保信息:输入密保错误次数超过了3次");
			return UseTool.toJson(new StateModel(100, "您连续3次输入不正确，请一天后再试!"));
    	}
    	List<AuthSecret> secretList = authSecretService.findByUser(authUser.getUserId());
    	if(secretList==null||secretList.size()==0||secretList.get(0)==null){
    		log.error("根据账号获取密保信息:该账号没有设置密保信息");
			return UseTool.toJson(new StateModel(103, "您没设置密保问题，请联系上级领导更改密码!"));
    	}
    	String json = UseTool.toJson(new StateModel(secretList));
    	log.info("根据账号获取密保信息："+json);
    	return json;
    }
	
	/**
	 * 处理密保问题
	 */
    @ArchivesLog(content = "修改密保问题")
    @RequestMapping(value = "handleSecret", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String handleSecret(HttpServletRequest request, HttpServletResponse response) {
		String[] secretIds = request.getParameterValues("secretIds");
		String[] titles = request.getParameterValues("titles");
		String[] answers = request.getParameterValues("answers");
		
    	 // 参数验证
    	log.info("处理密保问题:secretIds["+StringUtils.join(secretIds)+"],titles["+StringUtils.join(titles)+"],answers["+StringUtils.join(answers)+"]");
    	if(answers==null || answers.length==0){
    		log.error("处理密保问题:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
		AuthSecret authSecret = null;
		for (int i = 0; i < answers.length; i++) {
			if(UseTool.isEmpty(answers[i])){
				continue;
			}
			if(secretIds!=null&&UseTool.isLong(secretIds[i])&&UseTool.toLong(secretIds[i]).intValue()>0){
				// 修改
				authSecret = new AuthSecret(UseTool.toLong(secretIds[i]), titles[i], answers[i]);
				authSecretService.updateSecret(authSecret);
			}else if(titles!=null&&!UseTool.isEmpty(titles[i])){
				// 新增
				authSecret = new AuthSecret(loginUserId, titles[i], answers[i], loginUserId);
				authSecretService.insertSecret(authSecret);
			}
		}
    	String json = UseTool.toJson(new StateModel(true));
    	log.info("处理密保问题："+json);
    	return json;
    }
	
	/**
	 * 校验密保问题
	 */
    @ArchivesLog(content = "校验密保问题")
    @RequestMapping(value = "verifySecret", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String verifySecret(HttpServletRequest request, HttpServletResponse response) {
		String[] secretIds = request.getParameterValues("secretIds");
		String[] answers = request.getParameterValues("answers");
		
    	 // 参数验证
    	log.info("校验密保问题:secretIds["+StringUtils.join(secretIds)+"],answers["+StringUtils.join(answers)+"]");
    	if(secretIds==null || secretIds.length==0 || answers==null || answers.length==0){
    		log.error("校验密保问题:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	Map<String, Object> map = new HashMap<String, Object>();
    	for (int i = 0; i < secretIds.length; i++) {
    		map.put("secretId"+(i+1), secretIds[i]);
    		map.put("answer"+(i+1), answers[i]);
    	}
    	String json = UseTool.toJson(authSecretService.verifySecret(map));
    	log.info("校验密保问题："+json);
    	return json;
    }
    
}
