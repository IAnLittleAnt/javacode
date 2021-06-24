package com.cn.adonis.service.auth.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.adonis.dao.auth.AuthPowerDao;
import com.cn.adonis.entity.auth.AuthRoleAction;
import com.cn.adonis.entity.auth.AuthRoleMenu;
import com.cn.adonis.service.auth.ince.AuthPowerService;

@Service("authPowerService")
public class AuthPowerServiceImpl implements AuthPowerService {
	
	@Resource
	private AuthPowerDao authPowerDao;
	
	@Override
	public void handleRoleAction(AuthRoleAction model) {
		authPowerDao.handleRoleAction(model);
	}
	
	@Override
	public void handleRoleMenu(AuthRoleMenu model) {
		authPowerDao.handleRoleMenu(model);
	}
	
}
