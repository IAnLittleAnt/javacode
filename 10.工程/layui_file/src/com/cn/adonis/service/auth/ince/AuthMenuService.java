package com.cn.adonis.service.auth.ince;

import java.util.List;

import com.cn.adonis.entity.auth.AuthMenu;
import com.cn.adonis.model.state.StateModel;

public interface AuthMenuService {
	
	/**
	 * 根据编号获取菜单信息
	 * @param menuId 菜单编号
	 */
	AuthMenu fainById(Long menuId);
	
	/**
	 * 获取所有菜单列表
	 */
	List<AuthMenu> findByAll();
	
	/**
	 * 根据用户获取菜单列表
	 * @param userId 用户编号
	 */
	List<AuthMenu> findByUser(Long userId);
	
	/**
	 * 根据编号集合获取菜单列表
	 * @param menuIds 菜单编号集合
	 */
	List<AuthMenu> findByIds(String menuIds);
	
	/**
	 * 新增菜单信息
	 * @param model-menuType 菜单类型（1iframe模式，2全屏模式，3外部链接，4通讯系统）
	 * @param model-parentId 父级编号（0代表根目录）
	 * @param model-menuName 菜单名称
	 * @param model-menuText 菜单文本
	 * @param model-menuPath 菜单路径
	 * @param model-menuSort 菜单排序
	 * @param model-isRoot 是否根目录（1是，0否）
	 * @param model-createUserId 创建人编号
	 */
	AuthMenu insertMenu(AuthMenu model);
	
	/**
	 * 编辑菜单信息
	 * @param model-menuId 菜单编号
	 * @param model-menuType 菜单类型（1iframe模式，2全屏模式，3外部链接，4通讯系统）
	 * @param model-menuName 菜单名称
	 * @param model-menuText 菜单文本
	 * @param model-menuPath 菜单路径
	 * @param model-menuSort 菜单排序
	 */
	AuthMenu updateMenu(AuthMenu model);
	
	/**
	 * 编辑菜单图标
	 * @param model-menuId 菜单编号
	 * @param model-menuIcon 菜单图标
	 */
	AuthMenu updateIcon(AuthMenu model);
	
	/**
	 * 删除菜单信息
	 * @param menuId 菜单编号
	 */
	StateModel deleteMenu(Long menuId);
	
}
