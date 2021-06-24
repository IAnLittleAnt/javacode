package com.cn.adonis.service.auth.ince;

import java.util.List;
import java.util.Map;

import com.cn.adonis.entity.auth.AuthUser;
import com.cn.adonis.model.state.StateModel;

public interface AuthUserService {
	
	/**
	 * 分页获取用户信息
	 * @param map-roleId 角色编号
	 * @param map-searchKey 模糊搜索关键字
	 * @param map-start 分页开始条数
	 * @param map-limit 分页每页数量
	 */
	StateModel findByPage(Map<String, Object> map);
	
	/**
	 * 分页获取用户在线情况
	 * @param map-roleId 角色编号
	 * @param map-searchKey 模糊搜索关键字
	 * @param map-start 分页开始条数
	 * @param map-limit 分页每页数量
	 */
	StateModel findByLogin(Map<String, Object> map);
	
	/**
	 * 根据编号获取用户信息
	 * @param userId 账号编号
	 */
	AuthUser fainById(Long userId);
	
	/**
	 * 根据账号获取用户信息
	 * @param accountId 账号编号
	 */
	AuthUser fainByAccount(Long accountId);
	
	/**
	 * 添加好友-查找用户列表
	 * @param userId 用户编号
	 * @param roleId 角色编号
	 * @param searchKey 模糊搜索关键字
	 */
	List<AuthUser> findBySearch(Map<String, Object> map);
	
	/**
	 * 新增用户信息
	 * @param model-userCode 用户账号
	 * @param model-userName 用户姓名
	 * @param model-createUserId 创建人编号
	 * @param model-roleIds 用户角色列表
	 */
	StateModel insertUser(AuthUser model);
	
	/**
	 * 编辑用户信息
	 * @param model-userId 用户编号
	 * @param model-userName 用户姓名
	 * @param model-roleIds 用户角色列表
	 */
	AuthUser updateUser(AuthUser model);
	
	/**
	 * 编辑用户签名信息
	 * @param model-userId 用户编号
	 * @param model-sign 用户签名
	 */
	AuthUser updateSign(AuthUser model);
	
	/**
	 * 删除用户信息
	 * @param userId 用户编号
	 */
	StateModel deleteUser(Long userId);
	
}
