package com.cn.adonis.dao.auth;

import java.util.List;

import com.cn.adonis.entity.auth.AuthActionType;

public interface AuthActionTypeDao {
	
	/**
	 * 获取所有按钮类型
	 */
	List<AuthActionType> findByAll();
	
}
