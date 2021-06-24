package com.cn.adonis.dao.auth;

import java.util.List;
import java.util.Map;

import com.cn.adonis.entity.auth.AuthSecret;
import com.cn.adonis.model.state.StateModel;

public interface AuthSecretDao {
	
	/**
	 * 根据用户获取密保问题
	 * @param userId 用户编号
	 */
	List<AuthSecret> findByUser(Long userId);
	
	/**
	 * 校验密保问题
	 * @param map-secretId1 问题一编号
	 * @param map-answer1 问题一答案
	 * @param map-secretId2  问题二编号
	 * @param map-answer2  问题二答案
	 * @param map-secretId3  问题三编号
	 * @param map-answer3  问题三答案
	 */
	StateModel verifySecret(Map<String, Object> map);
	
	/**
	 * 新增密保问题
	 */
	AuthSecret insertSecret(AuthSecret model);
	
	/**
	 * 修改密保问题
	 */
	AuthSecret updateSecret(AuthSecret model);
	
}
