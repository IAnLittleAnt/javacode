package com.cn.adonis.service.auth.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.adonis.dao.auth.AuthUserDao;
import com.cn.adonis.entity.auth.AuthUser;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.BaseService;
import com.cn.adonis.service.auth.ince.AuthUserService;

@Service("authUserService")
public class AuthUserServiceImpl extends BaseService implements AuthUserService {
	
	@Resource
	private AuthUserDao authUserDao;
	
	@Override
	public StateModel findByPage(Map<String, Object> map) {
		List<List<?>> list = authUserDao.findByPage(map);
		return getMapByState(list);
	}
	
	@Override
	public StateModel findByLogin(Map<String, Object> map) {
		List<List<?>> list = authUserDao.findByLogin(map);
		return getMapByState(list);
	}
	
	@Override
	public AuthUser fainById(Long userId) {
		return authUserDao.fainById(userId);
	}
	
	@Override
	public AuthUser fainByAccount(Long accountId) {
		return authUserDao.fainByAccount(accountId);
	}

	@Override
	public List<AuthUser> findBySearch(Map<String, Object> map) {
		return authUserDao.findBySearch(map);
	}
	
	@Override
	public StateModel insertUser(AuthUser model) {
		return authUserDao.insertUser(model);
	}
	
	@Override
	public AuthUser updateUser(AuthUser model) {
		return authUserDao.updateUser(model);
	}

	@Override
	public AuthUser updateSign(AuthUser model) {
		return authUserDao.updateSign(model);
	}
	
	@Override
	public StateModel deleteUser(Long userId) {
		return authUserDao.deleteUser(userId);
	}
	
}
