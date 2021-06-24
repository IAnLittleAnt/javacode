package com.cn.adonis.service.auth.ince;

import java.util.Map;

import com.cn.adonis.entity.auth.AuthAccount;
import com.cn.adonis.model.state.StateModel;

public interface AuthAccountService {
	
	/**
	 * 根据账号获取账号信息
	 * @param userCode 用户账号
	 */
	AuthAccount fainByCode(String userCode);
	
	/**
	 * 用户登录
	 * @param model-userCode 用户账号
	 * @param model-userPwd 用户密码
	 */
	StateModel login(AuthAccount model);
	
	/**
	 * 根据原密码修改密码
	 * @param map-userId 用户编号
	 * @param map-oldPwd 旧密码
	 * @param map-nowPwd 新密码
	 * @param map-password 原始密码
	 */
	StateModel updatePwdByOld(Map<String, Object> map);

}
