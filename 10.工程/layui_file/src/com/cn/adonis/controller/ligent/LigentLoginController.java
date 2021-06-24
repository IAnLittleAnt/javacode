package com.cn.adonis.controller.ligent;

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
import com.cn.adonis.model.PageUnit;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.auth.ince.AuthActionService;
import com.cn.adonis.service.auth.ince.AuthRoleService;
import com.cn.adonis.service.auth.ince.AuthUserService;
import com.cn.comm.UseSession;
import com.cn.comm.UseTool;
import com.cn.unit.log.ArchivesLog;

/**
 * 用户登录记录
 * @author 10011037@qq.com
 * 2021-04-15
 */
@Controller
@RequestMapping("/ligent/login/")
public class LigentLoginController {
	
	private static Logger log = Logger.getLogger(LigentLoginController.class);
	@Resource
	private AuthActionService authActionService;
	@Resource
	private AuthUserService authUserService;
	@Resource
	private AuthRoleService authRoleService;
	
	private static String MODEL_KEY = "ligent/login/";
	
	/**
	 * 用户登录状态
	 */
    @ArchivesLog(content = "查看用户登录状态")
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
	 * 分页获取用户在线情况
	 */
	@RequestMapping(value = "/findByLogin", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String findByLogin(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String roleId = request.getParameter("roleId");
    	String searchKey = request.getParameter("searchKey");
    	String page = request.getParameter("page");		// 第几页
    	String limit = request.getParameter("limit");	// 每页几条
    	String field = request.getParameter("field");	// 排序字段
    	String order = request.getParameter("order");	// 排序类型
        // 参数验证
    	log.info("分页获取用户在线情况:roleId["+roleId+"],searchKey["+searchKey+"],page["+page+"],limit["+limit+"],field["+field+"],order["+order+"]");
    	if(!UseTool.isLong(page) || !UseTool.isLong(limit)){
    		log.error("分页获取用户在线情况:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	roleId = UseTool.isLong(roleId) ? roleId : "0";
    	Map<String, Object> map = PageUnit.getPageMap(page, limit, searchKey);
    	map.put("roleId", roleId);
    	StateModel stateModel = PageUnit.sortList(authUserService.findByLogin(map), field, order);
    	String json = UseTool.toJson(stateModel);
    	log.info("分页获取用户在线情况："+json);
    	return json;
    }
	
}
