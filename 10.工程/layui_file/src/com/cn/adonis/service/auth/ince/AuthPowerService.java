package com.cn.adonis.service.auth.ince;

import com.cn.adonis.entity.auth.AuthRoleAction;
import com.cn.adonis.entity.auth.AuthRoleMenu;

public interface AuthPowerService {
	
	/**
	 * 处理角色按钮映射表数据
	 * @param model-roleId 角色编号
	 * @param model-actionId 按钮编号
	 * @param model-isCheck 是否选中
	 */
	void handleRoleAction(AuthRoleAction model);
	
	/**
	 * 处理角色菜单映射表数据
	 * @param model-roleId 角色编号
	 * @param model-menuId 菜单编号
	 * @param model-isCheck 是否选中
	 */
	void handleRoleMenu(AuthRoleMenu model);

}
