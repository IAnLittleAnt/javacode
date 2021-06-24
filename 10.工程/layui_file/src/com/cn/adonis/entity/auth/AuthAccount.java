package com.cn.adonis.entity.auth;

/**
 * 账号信息表
 * @author 10011037@qq.com
 * 2018-12-31
 */
public class AuthAccount {

	// 自增编号
    private Long accountId;
	// 用户账号
    private String userCode;
	// 用户密码(HAS加密后再MD5加密)
    private String userPwd;
	// QQ账号
    private String userQQ;
	// 微信账号
    private String userWechat;
	// 微博账号
    private String userWeibo;
	// 是否被冻结（1已冻结，0没冻结）
    private Integer isFrozen;
    
    public AuthAccount(){};
    
    /**
     * 登录
     */
    public AuthAccount(String userCode, String userPwd){
    	this.userCode = userCode;
    	this.userPwd = userPwd;
    }
    
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public Integer getIsFrozen() {
		return isFrozen;
	}
	public void setIsFrozen(Integer isFrozen) {
		this.isFrozen = isFrozen;
	}
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	public String getUserWechat() {
		return userWechat;
	}
	public void setUserWechat(String userWechat) {
		this.userWechat = userWechat;
	}
	public String getUserWeibo() {
		return userWeibo;
	}
	public void setUserWeibo(String userWeibo) {
		this.userWeibo = userWeibo;
	}
	
}
