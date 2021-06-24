package com.cn.adonis.service.auth.ince;

import java.util.List;

import com.cn.adonis.entity.auth.AuthAction;
import com.cn.adonis.model.state.StateModel;

public interface AuthActionService {
	
	/**
	 * 根据编号获取按钮信息
	 * @param actionId 按钮编号
	 */
	AuthAction fainById(Long actionId);
	
	/**
	 * 根据菜单获取按钮信息
	 * @param menuId 菜单编号
	 */
	List<AuthAction> findByMenu(Long menuId);

	/**
	 * 根据用户获取按钮列表
	 * @param userId 用户编号
	 * @param menuPath 菜单路径
	 */
	List<AuthAction> findByUser(Long userId, String menuPath);
	
	/**
	 * 新增按钮信息
	 * @param model-menuId 菜单编号
	 * @param model-actionName 按钮名称
	 * @param model-actionType 按钮类型
	 * @param model-actionSeat 按钮位置
	 * @param model-callType 调用方式
	 * @param model-actionEvent 按钮事件
	 * @param model-actionIcon 按钮图标
	 * @param model-actionSort 按钮排序
	 * @param model-content 描述内容
	 * @param model-createUserId 创建人编号
	 */
	AuthAction insertAction(AuthAction model);
	
	/**
	 * 编辑按钮信息
	 * @param model-actionId 菜单编号
	 * @param model-actionName 按钮名称
	 * @param model-actionType 按钮类型
	 * @param model-actionSeat 按钮位置
	 * @param model-callType 调用方式
	 * @param model-actionEvent 按钮事件
	 * @param model-actionIcon 按钮图标
	 * @param model-actionSort 按钮排序
	 * @param model-content 描述内容
	 */
	AuthAction updateAction(AuthAction model);
	
	/**
	 * 编辑按钮图标
	 * @param model-actionId 按钮编号
	 * @param model-actionIcon 按钮图标
	 */
	AuthAction updateIcon(AuthAction model);

	/**
	 * 删除按钮信息
	 * @param actionId 按钮编号
	 */
	StateModel deleteAction(Long actionId);
	
}
