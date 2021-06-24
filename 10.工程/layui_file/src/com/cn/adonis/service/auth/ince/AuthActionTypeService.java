package com.cn.adonis.service.auth.ince;

import java.util.List;

import com.cn.adonis.entity.auth.AuthActionType;

public interface AuthActionTypeService {
	
	/**
	 * 获取所有按钮类型
	 */
	List<AuthActionType> findByAll();
	
}
