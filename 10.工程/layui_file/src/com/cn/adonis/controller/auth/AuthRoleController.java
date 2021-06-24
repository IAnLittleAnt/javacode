package com.cn.adonis.controller.auth;

import java.util.ArrayList;
import java.util.List;

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
import com.cn.adonis.entity.auth.AuthRole;
import com.cn.adonis.model.TreeDown;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.auth.ince.AuthActionService;
import com.cn.adonis.service.auth.ince.AuthMenuService;
import com.cn.adonis.service.auth.ince.AuthRoleService;
import com.cn.comm.UseSession;
import com.cn.comm.UseTool;
import com.cn.unit.log.ArchivesLog;

/**
 * 角色管理接口
 * @author 10011037@qq.com
 * 2017-06-30
 */
@Controller
@RequestMapping("/auth/role/")
public class AuthRoleController {
	
	private static Logger log = Logger.getLogger(AuthRoleController.class);
	@Resource
	private AuthRoleService authRoleService;
	@Resource
	private AuthMenuService authMenuService;
	@Resource
	private AuthActionService authActionService;
	
	private static String MODEL_KEY = "auth/role/";
	
	/**
	 * 角色列表
	 */
    @ArchivesLog(content = "查看角色信息")
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
	 * 获取所有角色列表
	 */
	@RequestMapping(value = "findByAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String findByAll(HttpServletRequest request, HttpServletResponse response) {
    	String json = UseTool.toJson(new StateModel(authRoleService.findByAll()));
    	log.info("获取所有角色列表："+json);
    	return json;
    }
	
	/**
	 * 获取所有角色树形菜单
	 */
	@RequestMapping(value = "findByTree", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String findByTree(HttpServletRequest request, HttpServletResponse response) {
		List<AuthRole> roleList = authRoleService.findByAll();
		List<TreeDown> treeList = new ArrayList<TreeDown>();
		TreeDown treeDown = null;
		for (AuthRole role : roleList) {
			treeDown = new TreeDown(role);
			treeList.add(treeDown);
		}
    	String json = UseTool.toJson(new StateModel(treeList));
    	log.info("获取所有角色列表："+json);
    	return json;
    }
    
	/**
	 * 根据编号获取角色信息
	 */
    @RequestMapping(value = "fainById", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String fainById(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String roleId = request.getParameter("roleId");
        // 参数验证
    	log.info("根据编号获取角色信息:roleId["+roleId+"]");
    	if(!UseTool.isLong(roleId)){
    		log.error("根据编号获取角色信息:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	String json = UseTool.toJson(new StateModel(authRoleService.fainById(UseTool.toLong(roleId))));
    	log.info("根据编号获取角色信息："+json);
    	return json;
    }
	
	/**
	 * 新增角色信息
	 */
    @ArchivesLog(content = "新增角色信息")
    @RequestMapping(value = "insertRole", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String insertRole(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String roleName = request.getParameter("roleName");
    	String roleAlias = request.getParameter("roleAlias");
    	String parentId = request.getParameter("parentId");
    	String roleRank = request.getParameter("roleRank");
    	String content = request.getParameter("content");
        // 参数验证
    	log.info("新增角色信息:roleName["+roleName+"],roleAlias["+roleAlias
    			+"],parentId["+parentId+"],roleRank["+roleRank+"],content["+content+"]");
    	if(UseTool.isEmpty(roleName) || !UseTool.isLong(parentId) || !UseTool.isInt(roleRank)){
    		log.error("新增角色信息:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	roleAlias = UseTool.isEmpty(roleAlias) ? roleName : roleAlias;
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	AuthRole authRole = new AuthRole(roleName, roleAlias, UseTool.toLong(parentId), UseTool.toInt(roleRank), content, loginUserId);
    	String json = UseTool.toJson(new StateModel(authRoleService.insertRole(authRole)));
    	log.info("新增角色信息："+json);
    	return json;
    }
	
	/**
	 * 编辑角色信息
	 */
    @ArchivesLog(content = "编辑角色信息")
    @RequestMapping(value = "updateRole", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String updateRole(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String roleId = request.getParameter("roleId");
    	String roleName = request.getParameter("roleName");
    	String roleAlias = request.getParameter("roleAlias");
    	String parentId = request.getParameter("parentId");
    	String roleRank = request.getParameter("roleRank");
    	String content = request.getParameter("content");
        // 参数验证
    	log.info("编辑角色信息:roleId["+roleId+"],roleName["+roleName+"],roleAlias["+roleAlias
    			+"],parentId["+parentId+"],roleRank["+roleRank+"],content["+content+"]");
    	if(!UseTool.isLong(roleId) || UseTool.isEmpty(roleName) || !UseTool.isLong(parentId) || !UseTool.isInt(roleRank)){
    		log.error("编辑角色信息:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	AuthRole authRole = new AuthRole(UseTool.toLong(roleId), roleName, roleAlias, UseTool.toLong(parentId), UseTool.toInt(roleRank), content);
    	String json = UseTool.toJson(new StateModel(authRoleService.updateRole(authRole)));
    	log.info("编辑角色信息："+json);
    	return json;
    }

	/**
	 * 删除角色集合
	 */
    @ArchivesLog(content = "删除角色信息")
    @RequestMapping(value = "deleteRoles", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String deleteRoles(HttpServletRequest request, HttpServletResponse response) {
    	// 获取参数数据
    	String roleIds = request.getParameter("roleIds");
        // 参数验证
    	log.info("删除角色集合:roleIds["+roleIds+"]");
    	if(UseTool.isEmpty(roleIds)) {
    		log.error("删除角色集合:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	String[] array = roleIds.split(",");
    	for (String mineId : array) {
    		if(!UseTool.isLong(mineId)) {
				continue;
			}
    		authRoleService.deleteRole(UseTool.toLong(mineId));
		}
    	String json = UseTool.toJson(new StateModel(true));
    	log.info("删除角色集合："+json);
    	return json;
    }
	
	/**
	 * 查看角色页面
	 
    @RequestMapping("detail")
    public String detail(HttpServletRequest request, Model model) throws Exception{
    	
        return MODEL_KEY + "detail";
    }*/
	
	/**
	 * 获取所有角色列表
	 
	@RequestMapping(value = "findByPower", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String findByPower(HttpServletRequest request, HttpServletResponse response) {
		// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	List<TreeTable> revertList = new ArrayList<TreeTable>();
    	List<AuthAction> actionList = null;
    	TreeTable treeTable = null;
    	// 菜单集合
    	List<AuthMenu> list = authMenuService.findByPower(loginUserId);
    	for (AuthMenu menu : list) {
    		treeTable = new TreeTable(menu);
    		revertList.add(treeTable);
    		if(menu.getIsRoot().intValue() != 1){
        		actionList = authActionService.findByPower(loginUserId, menu.getMenuId());
        		for (AuthAction action : actionList) {
        			treeTable = new TreeTable(action, menu.getMenuLevel());
        			revertList.add(treeTable);
    			}
    		}
		}
		String json = UseTool.toJson(new StateModel(revertList));
    	log.info("获取所有角色列表："+json);
    	return json;
    }*/
	
	/**
	 * 查看角色页面
	 
    @RequestMapping("detail_")
    public String detail_(HttpServletRequest request, Model model) throws Exception{
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	System.out.println(loginUserId);
    	// 菜单集合
    	List<AuthMenu> list = authMenuService.findByPower(loginUserId);
    	// 声明一个变量存储一级菜单，封装返回的集合
		List<TreeModel> menuList = new ArrayList<TreeModel>();
		// 声明一个变量存储二级菜单
		List<TreeModel> childList = null;
		// 声明一个变量存储三级菜单
		List<TreeModel> sunList = null;
		Long menuId = null, parentId = null;
		for (AuthMenu item : list) {
			parentId = item.getParentId();
			if(parentId.intValue() == 0){
				childList = new ArrayList<TreeModel>();
				menuId = item.getMenuId();
				for (AuthMenu child : list) {
					parentId = child.getParentId();
					if(parentId.intValue()==menuId.intValue()){
						sunList = new ArrayList<TreeModel>();
						for (AuthMenu sun : list) {
							if(sun.getParentId().intValue()==child.getMenuId().intValue()){
								TreeModel sunTree = new TreeModel(sun, loginUserId, null);
								sunList.add(sunTree);
							}
						}
						TreeModel childTree = new TreeModel(child, loginUserId, sunList);
						childList.add(childTree);
					}
				}
				TreeModel tree = new TreeModel(item, loginUserId, childList);
				menuList.add(tree);
			}
		}
		TreeModel titleTree = new TreeModel(-1L, "分配权限", menuList);
		List<TreeModel> revertList = new ArrayList<TreeModel>();
		revertList.add(titleTree);
    	model.addAttribute("revertList", UseTool.toJson(revertList));
    	log.info("查看角色页面："+UseTool.toJson(new StateModel(revertList)));
        return MODEL_KEY + "detail_";
    }*/
    
}
