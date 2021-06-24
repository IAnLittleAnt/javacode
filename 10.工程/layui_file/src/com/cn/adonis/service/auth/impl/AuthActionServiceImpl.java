package com.cn.adonis.service.auth.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.adonis.dao.auth.AuthActionDao;
import com.cn.adonis.entity.auth.AuthAction;
import com.cn.adonis.entity.auth.AuthMenu;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.auth.ince.AuthActionService;

@Service("authActionService")
public class AuthActionServiceImpl implements AuthActionService {
	
	@Resource
	private AuthActionDao authActionDao;
	
	@Override
	public AuthAction fainById(Long actionId) {
		return authActionDao.fainById(actionId);
	}
	
	@Override
	public List<AuthAction> findByMenu(Long menuId) {
		return authActionDao.findByMenu(menuId);
	}

	@Override
	public List<AuthAction> findByUser(Long userId, String menuPath) {
		AuthMenu authMenu = new AuthMenu(menuPath, userId);
		return authActionDao.findByUser(authMenu);
	}

	@Override
	public AuthAction insertAction(AuthAction model) {
		return authActionDao.insertAction(model);
	}

	@Override
	public AuthAction updateAction(AuthAction model) {
		return authActionDao.updateAction(model);
	}

	@Override
	public AuthAction updateIcon(AuthAction model) {
		return authActionDao.updateIcon(model);
	}

	@Override
	public StateModel deleteAction(Long actionId) {
		return authActionDao.deleteAction(actionId);
	}
	
}
