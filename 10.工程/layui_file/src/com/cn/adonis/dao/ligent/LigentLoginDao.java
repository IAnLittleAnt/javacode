package com.cn.adonis.dao.ligent;

import com.cn.adonis.entity.ligent.LigentLogin;

public interface LigentLoginDao {
	
	/**
	 * 处理登录记录
	 * @param model-userId 用户编号
	 * @param model-loginType 记录类型(1加入，2失效)
	 */
	LigentLogin handleLogin(LigentLogin model);
	
	/**
	 * 修改最近操作时间
	 * @param userId 用户编号
	 */
	LigentLogin updateLogin(Long userId);
	
}
