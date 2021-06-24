package com.cn.adonis.controller.auth;

import java.util.List;
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

import com.cn.adonis.entity.auth.AuthAction;
import com.cn.adonis.entity.auth.AuthUser;
import com.cn.adonis.model.PageUnit;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.auth.ince.AuthActionService;
import com.cn.adonis.service.auth.ince.AuthRoleService;
import com.cn.adonis.service.auth.ince.AuthUserService;
import com.cn.comm.UseSession;
import com.cn.comm.UseTool;
import com.cn.unit.log.ArchivesLog;

/**
 * 用户管理接口
 * @author 10011037@qq.com
 * 2017-06-30
 */
@Controller
@RequestMapping("/auth/user/")
public class AuthUserController {
	
	private static Logger log = Logger.getLogger(AuthUserController.class);
	@Resource
	private AuthActionService authActionService;
	@Resource
	private AuthUserService authUserService;
	@Resource
	private AuthRoleService authRoleService;
	
	private static String MODEL_KEY = "auth/user/";
	
	/**
	 * 用户列表
	 */
    @ArchivesLog(content = "查看用户信息")
    @RequestMapping("list")
    public String list(HttpServletRequest request, Model model) throws Exception{
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	List<AuthAction> actionList = authActionService.findByUser(loginUserId, request.getServletPath());
    	model.addAttribute("actionList", actionList);
    	model.addAttribute("roleList", authRoleService.findByAll());
        return MODEL_KEY + "list";
    }
	
	/**
	 * 分页获取用户信息
	 */
	@RequestMapping(value = "/findByPage", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String findByPage(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String roleId = request.getParameter("roleId");
    	String searchKey = request.getParameter("searchKey");
    	String page = request.getParameter("page");		// 第几页
    	String limit = request.getParameter("limit");	// 每页几条
    	String field = request.getParameter("field");	// 排序字段
    	String order = request.getParameter("order");	// 排序类型
        // 参数验证
    	log.info("分页获取用户信息:roleId["+roleId+"],searchKey["+searchKey+"],page["+page+"],limit["+limit+"],field["+field+"],order["+order+"]");
    	if(!UseTool.isLong(page) || !UseTool.isLong(limit)){
    		log.error("分页获取用户信息:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	roleId = UseTool.isLong(roleId) ? roleId : "0";
    	Map<String, Object> map = PageUnit.getPageMap(page, limit, searchKey);
    	map.put("roleId", roleId);
    	StateModel stateModel = PageUnit.sortList(authUserService.findByPage(map), field, order);
    	String json = UseTool.toJson(stateModel);
    	log.info("分页获取用户信息："+json);
    	return json;
    }
    
	/**
	 * 根据编号获取用户信息
	 */
    @RequestMapping(value = "fainById", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String fainById(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String userId = request.getParameter("userId");
        // 参数验证
    	log.info("根据编号获取用户信息:userId["+userId+"]");
    	if(!UseTool.isLong(userId)){
    		log.error("根据编号获取用户信息:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	String json = UseTool.toJson(new StateModel(authUserService.fainById(UseTool.toLong(userId))));
    	log.info("根据编号获取用户信息："+json);
    	return json;
    }
	
	/**
	 * 新增用户信息
	 */
    @ArchivesLog(content = "新增用户信息")
    @RequestMapping(value = "insertUser", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String insertUser(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String userCode = request.getParameter("userCode");
    	String userName = request.getParameter("userName");
    	String roleIds = request.getParameter("roleIds");
        // 参数验证
    	log.info("新增用户信息:userCode["+userCode+"],userName["+userName+"],roleIds["+roleIds+"]");
    	if(!UseTool.isMobile(userCode) || UseTool.isEmpty(userName) || UseTool.isEmpty(roleIds)){
    		log.error("新增用户信息:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	AuthUser authUser = new AuthUser(userCode, userName, roleIds, loginUserId);
    	String json = UseTool.toJson(authUserService.insertUser(authUser));
    	log.info("新增用户信息："+json);
    	return json;
    }
    
	/**
	 * 编辑我的资料页面
	 */
    @ArchivesLog(content = "编辑用户信息")
    @RequestMapping("editMine")
    public String editMine(HttpServletRequest request, Model model) throws Exception{
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	model.addAttribute("authUser", authUserService.fainById(loginUserId));
        return MODEL_KEY + "editMine";
    }
    
	/**
	 * 编辑用户信息
	 */
    @ArchivesLog(content = "编辑用户信息")
    @RequestMapping(value = "updateUser", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String updateUser(AuthUser model, HttpServletRequest request, HttpServletResponse response) {
        // 参数验证
    	log.info("编辑用户信息:"+UseTool.toJson(model));
    	if(!UseTool.isLong(model.getUserId()) || UseTool.isEmpty(model.getUserName())){
    		log.error("编辑用户信息:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	String json = UseTool.toJson(new StateModel(authUserService.updateUser(model)));
    	log.info("编辑用户信息："+json);
    	return json;
    }
	
	/**
	 * 编辑用户签名信息
	 */
    @ArchivesLog(content = "修改我的签名")
    @RequestMapping(value = "updateSign", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String updateSign(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String sign = request.getParameter("sign");
        // 参数验证
    	log.info("编辑用户签名信息:sign["+sign+"]");
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	AuthUser authUser = new AuthUser(loginUserId, sign);
    	String json = UseTool.toJson(new StateModel(authUserService.updateSign(authUser)));
    	log.info("编辑用户签名信息："+json);
    	return json;
    }
    
	/**
	 * 删除用户信息
	 */
    @ArchivesLog(content = "删除用户信息")
    @RequestMapping(value = "deleteUsers", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String deleteUsers(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String userIds = request.getParameter("userIds");
        // 参数验证
    	log.info("删除用户信息:userIds["+userIds+"]");
    	if(UseTool.isEmpty(userIds)) {
    		log.error("删除用户信息:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	String[] array = userIds.split(",");
    	for (String mineId : array) {
    		if(!UseTool.isLong(mineId)) {
				continue;
			}
    		authUserService.deleteUser(UseTool.toLong(mineId));
		}
    	String json = UseTool.toJson(new StateModel(true));
    	log.info("删除用户信息："+json);
    	return json;
    }
    
}
