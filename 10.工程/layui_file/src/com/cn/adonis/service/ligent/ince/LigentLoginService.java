package com.cn.adonis.service.ligent.ince;

import com.cn.adonis.entity.ligent.LigentLogin;

public interface LigentLoginService {
	
	/**
	 * 处理登录记录
	 * @param userId 用户编号
	 * @param loginType 记录类型(1加入，2失效)
	 */
	LigentLogin handleLogin(Long userId, Integer loginType);
	
	/**
	 * 修改最近操作时间
	 * @param userId 用户编号
	 */
	LigentLogin updateLogin(Long userId);
	
}
