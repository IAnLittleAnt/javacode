package com.cn.adonis.entity.ligent;

/**
 * 登录记录表
 * @author 10011037@qq.com
 * 2018-09-05
 */
public class LigentLogin {

	// 自增编号
    private Long loginId;
    // 登录人编号
    private Long userId;
    // 登录时间
    private String loginTime;
    // 退出时间
    private String logoutTime;
    // 退出时间
    private String lastVisitTime;
    
    // 记录类型(1加入，2失效)
    private Integer loginType;
    
    public LigentLogin() {}
    
    /**
     * 登录、退出
     */
	public LigentLogin(Long userId, Integer loginType) {
		this.userId = userId;
		this.loginType = loginType;
	}
	
	
	public Long getLoginId() {
		return loginId;
	}
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	public Integer getLoginType() {
		return loginType;
	}
	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}
	public String getLastVisitTime() {
		return lastVisitTime;
	}
	public void setLastVisitTime(String lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}
	
}
