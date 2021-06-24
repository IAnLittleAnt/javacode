package com.cn.adonis.service.auth.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.adonis.dao.auth.AuthRoleDao;
import com.cn.adonis.entity.auth.AuthRole;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.auth.ince.AuthRoleService;

@Service("authRoleService")
public class AuthRoleServiceImpl implements AuthRoleService {
	
	@Resource
	private AuthRoleDao authRoleDao;

	@Override
	public AuthRole fainById(Long roleId) {
		return authRoleDao.fainById(roleId);
	}
	
	@Override
	public List<AuthRole> findByAll() {
		return authRoleDao.findByAll();
	}

	@Override
	public List<AuthRole> findByMenu(Long menuId) {
		return authRoleDao.findByMenu(menuId);
	}

	@Override
	public List<AuthRole> findByAction(Long actionId) {
		return authRoleDao.findByAction(actionId);
	}

	@Override
	public AuthRole findByUser(Long userId) {
		return authRoleDao.findByUser(userId);
	}

	@Override
	public AuthRole insertRole(AuthRole model) {
		return authRoleDao.insertRole(model);
	}

	@Override
	public AuthRole updateRole(AuthRole model) {
		return authRoleDao.updateRole(model);
	}

	@Override
	public StateModel deleteRole(Long roleId) {
		return authRoleDao.deleteRole(roleId);
	}
	
}
