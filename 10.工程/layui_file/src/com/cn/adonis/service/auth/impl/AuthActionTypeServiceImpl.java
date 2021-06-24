package com.cn.adonis.service.auth.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.adonis.dao.auth.AuthActionTypeDao;
import com.cn.adonis.entity.auth.AuthActionType;
import com.cn.adonis.service.auth.ince.AuthActionTypeService;

@Service("authActionTypeService")
public class AuthActionTypeServiceImpl implements AuthActionTypeService {
	
	@Resource
	private AuthActionTypeDao authActionTypeDao;
	
	@Override
	public List<AuthActionType> findByAll() {
		return authActionTypeDao.findByAll();
	}
	
}
