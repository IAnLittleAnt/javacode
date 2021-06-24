package com.cn.adonis.controller.auth;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.adonis.entity.auth.AuthAccount;
import com.cn.adonis.entity.auth.AuthUser;
import com.cn.adonis.model.state.StateCode;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.auth.ince.AuthAccountService;
import com.cn.adonis.service.auth.ince.AuthUserService;
import com.cn.adonis.service.ligent.ince.LigentConstService;
import com.cn.comm.UseConst;
import com.cn.comm.UseSession;
import com.cn.comm.UseTool;
import com.cn.unit.encode.EncodeMD5Util;
import com.cn.unit.encode.EncodeSHAUtil;
import com.cn.unit.log.ArchivesLog;

/**
 * 账号管理接口
 * @author 10011037@qq.com
 * 2017-06-30
 */
@Controller
@RequestMapping("/auth/account/")
public class AuthAccountController {
	
	private static Logger log = Logger.getLogger(AuthAccountController.class);
	@Resource
	private AuthAccountService authAccountService;
	@Resource
	private AuthUserService authUserService;
	@Resource
	private LigentConstService ligentConstService;
	
	private static String MODEL_KEY = "auth/account/";
	
	/**
	 * 登录验证
	 */
    @ArchivesLog(content = "登录验证")
    @RequestMapping(value = "logon", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String logon(HttpServletRequest request, HttpServletResponse response) {
    	// 获取参数数据
    	String account = request.getParameter("account");
    	String password = request.getParameter("password");
    	String vercode = request.getParameter("vercode");
        // 参数验证
    	log.info("登录验证:account["+account+"],password["+password+"],vercode["+vercode+"]");
    	if(UseTool.isEmpty(account) || UseTool.isEmpty(password)){
    		log.error("登录验证:参数错误");
			return UseTool.toJson(new StateModel(StateCode.STATECODE_ACCOUNT));
    	}
    	
    	// 是否启动登录页面验证码（1启动，0不启动）
    	String isDiringCode = ligentConstService.findByKey(UseConst.DEFAULT_LOGIN_CODE);
    	if(UseTool.isInt(isDiringCode)&&UseTool.toInt(isDiringCode).intValue()==1){
    		if(UseTool.isEmpty(vercode)){
        		log.error("登录验证:参数错误");
    			return UseTool.toJson(new StateModel(StateCode.STATECODE_ACCOUNT));
    		}else{
    			// 获取验证码
        		String captcha = UseTool.toStr(request.getSession().getAttribute(UseSession.SESSIONNAME_CAPTCHA));
        		if(!vercode.toUpperCase().equals(captcha)){
            		log.error("登录验证:验证码不正确");
        			return UseTool.toJson(new StateModel(StateCode.STATECODE_VERCODE));
        		}
    		}
		}
    	
    	// HAS加密后再MD5加密(不可逆加密)
    	String userPwd = EncodeMD5Util.MD5Encode((EncodeSHAUtil.SHAEncode(password)));
    	// 登录验证
    	AuthAccount authAccount = new AuthAccount(account, userPwd);
    	StateModel stateModel = authAccountService.login(authAccount);
    	if(stateModel != null && stateModel.getCode() == StateCode.STATECODE_SUCCESS){
    		// 操作账号信息，接下来判断用户是否有效
    		Long accountId = stateModel.getKeyId();
    		AuthUser authUser = authUserService.fainByAccount(accountId);
    		if(authUser == null){
        		log.error("登录验证:用户信息无效");
    			return UseTool.toJson(new StateModel(StateCode.STATECODE_ACCOUNT));
    		}
    		// 获取用户编号
    		Long userId = authUser.getUserId();
    		// 清除session中验证码的值
    		request.getSession().removeAttribute(UseSession.SESSIONNAME_CAPTCHA);
    		// 把用户编号添加在状态对象
    		stateModel.setKeyId(userId);
    		// 把用户编号添加到session中
    		request.getSession().setAttribute(UseSession.SESSIONNAME_LOGINUSERID, userId);
    		// 把用户账号添加到session中
    		request.getSession().setAttribute(UseSession.SESSIONNAME_LOGINACCOUNT, account);
    	}
    	String json = UseTool.toJson(stateModel);
    	log.info("登录验证："+json);
    	return json;
    }
	
	/**
	 * 修改密码(原密码方式)
	 */
    @RequestMapping("pwd")
    public String pwd(HttpServletRequest request, Model model) throws Exception{
        return MODEL_KEY + "pwd";
    }
    
	/**
	 * 根原密码修改密码
	 */
    @ArchivesLog(content = "重置密码")
    @RequestMapping(value = "updatePwdByOld", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String updatePwdByOld(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String oldPwd = request.getParameter("oldPwd");
    	String nowPwd = request.getParameter("nowPwd");
        // 参数验证
    	log.info("根原密码修改密码:oldPwd["+oldPwd+"],nowPwd["+nowPwd+"]");
    	if(UseTool.isEmpty(oldPwd) || UseTool.isEmpty(nowPwd)){
    		log.error("根原密码修改密码:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("userId", loginUserId);
    	map.put("oldPwd", EncodeMD5Util.MD5Encode((EncodeSHAUtil.SHAEncode(oldPwd))));
    	map.put("nowPwd", EncodeMD5Util.MD5Encode((EncodeSHAUtil.SHAEncode(nowPwd))));
    	map.put("password", nowPwd);
    	String json = UseTool.toJson(authAccountService.updatePwdByOld(map));
    	log.info("根原密码修改密码："+json);
    	return json;
    }
    
}
