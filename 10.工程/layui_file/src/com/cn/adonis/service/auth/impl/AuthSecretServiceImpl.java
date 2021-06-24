package com.cn.adonis.service.auth.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.adonis.dao.auth.AuthSecretDao;
import com.cn.adonis.entity.auth.AuthSecret;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.auth.ince.AuthSecretService;

@Service("authSecretService")
public class AuthSecretServiceImpl implements AuthSecretService {

	@Resource
	private AuthSecretDao authSecretDao;
	
	@Override
	public List<AuthSecret> findByUser(Long userId) {
		return authSecretDao.findByUser(userId);
	}

	@Override
	public StateModel verifySecret(Map<String, Object> map) {
		return authSecretDao.verifySecret(map);
	}

	@Override
	public AuthSecret insertSecret(AuthSecret model) {
		return authSecretDao.insertSecret(model);
	}

	@Override
	public AuthSecret updateSecret(AuthSecret model) {
		return authSecretDao.updateSecret(model);
	}

}
