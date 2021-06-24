package com.cn.adonis.service.ligent.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.adonis.dao.ligent.LigentLoginDao;
import com.cn.adonis.entity.ligent.LigentLogin;
import com.cn.adonis.service.ligent.ince.LigentLoginService;

@Service("ligentLoginService")
public class LigentLoginServiceImpl implements LigentLoginService {
	
	@Resource
	private LigentLoginDao ligentLoginDao;
	
	@Override
	public LigentLogin handleLogin(Long userId, Integer loginType) {
		LigentLogin model = new LigentLogin(userId, loginType);
		return ligentLoginDao.handleLogin(model);
	}
	
	@Override
	public LigentLogin updateLogin(Long userId) {
		return ligentLoginDao.updateLogin(userId);
	}
	
}
