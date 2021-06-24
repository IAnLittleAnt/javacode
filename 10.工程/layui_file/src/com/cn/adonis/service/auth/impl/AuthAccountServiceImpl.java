package com.cn.adonis.service.auth.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.adonis.dao.auth.AuthAccountDao;
import com.cn.adonis.entity.auth.AuthAccount;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.auth.ince.AuthAccountService;

@Service("authAccountService")
public class AuthAccountServiceImpl implements AuthAccountService {
	
	@Resource
	private AuthAccountDao authAccountDao;
	
	@Override
	public AuthAccount fainByCode(String userCode) {
		return authAccountDao.fainByCode(userCode);
	}

	@Override
	public StateModel login(AuthAccount model) {
		return authAccountDao.login(model);
	}

	@Override
	public StateModel updatePwdByOld(Map<String, Object> map) {
		return authAccountDao.updatePwdByOld(map);
	}

}
