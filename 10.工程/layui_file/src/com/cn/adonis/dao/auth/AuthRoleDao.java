package com.cn.adonis.dao.auth;

import java.util.List;

import com.cn.adonis.entity.auth.AuthRole;
import com.cn.adonis.model.state.StateModel;

public interface AuthRoleDao {

	/**
	 * 根据编号获取角色信息
	 */
	AuthRole fainById(Long roleId);
	
	/**
	 * 获取所有角色列表
	 */
	List<AuthRole> findByAll();

	/**
	 * 根据菜单获取角色权限信息
	 * @param menuId 菜单编号
	 */
	List<AuthRole> findByMenu(Long menuId);

	/**
	 * 根据按钮获取角色权限信息
	 * @param actionId 按钮编号
	 */
	List<AuthRole> findByAction(Long actionId);

	/**
	 * 根据用户获取下属角色列表
	 */
	AuthRole findByUser(Long userId);
	
	/**
	 * 新增角色信息
	 * @param model-roleName 角色名称
	 * @param model-roleAlias 角色别名
	 * @param model-parentId 父级编号
	 * @param model-roleRank 角色等级
	 * @param model-content 描述内容
	 * @param model-createUserId 创建人编号
	 */
	AuthRole insertRole(AuthRole model);
	
	/**
	 * 编辑角色信息
	 * @param model-roleId 角色编号
	 * @param model-roleName 角色名称
	 * @param model-roleAlias 角色别名
	 * @param model-parentId 父级编号
	 * @param model-roleRank 角色等级
	 * @param model-content 描述内容
	 */
	AuthRole updateRole(AuthRole model);

	/**
	 * 删除角色信
	 */
	StateModel deleteRole(Long roleId);
	
}
