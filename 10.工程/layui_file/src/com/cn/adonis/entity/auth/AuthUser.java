package com.cn.adonis.entity.auth;

/**
 * 用户信息表
 * @author 10011037@qq.com
 * 2018-12-31
 */
public class AuthUser {
	
	// 用户编号
    private Long userId;
	// 账户编号
    private Long accountId;
	// 用户姓名
    private String userName;
	// 用户头像
    private String avatar;
	// 用户签名
    private String sign;
	// 学历
    private String degree;
	// 性别
    private String sexName;
	// 地址
    private String address;
	// 个人说明
    private String description;
    // 当前输入密保错误次数
    private Integer secretErrorCount;
	// 创建时间
    private String createTime;
	// 创建人编号
    private Long createUserId;
    // 是否有效(1有效数据，0无效数据)
    private Integer isValid;

	// 用户账号
    private String userCode;
	// 是否被冻结（1已冻结，0没冻结）
    private Integer isFrozen;
	// 登录昨天
    private Integer loginState;
	// 角色编号列表
    private String roleIds;
	// 角色名称列表
    private String roleNames;
	// 邮件账号
    private String mailAccount;
    
    public AuthUser(){};
    
    /**
     * 新增
     */
    public AuthUser(String userCode, String userName, String roleIds, Long createUserId){
    	this.userCode = userCode;
    	this.userName = userName;
    	this.roleIds = roleIds;
    	this.createUserId = createUserId;
    }
    
    /**
     * 编辑
     */
    public AuthUser(Long userId, String userName, String roleIds){
    	this.userId = userId;
    	this.userName = userName;
    	this.roleIds = roleIds;
    }
    
    /**
     * 编辑签名
     */
    public AuthUser(Long userId, String sign){
    	this.userId = userId;
    	this.sign = sign;
    }
    
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Integer getIsFrozen() {
		return isFrozen;
	}
	public void setIsFrozen(Integer isFrozen) {
		this.isFrozen = isFrozen;
	}
	public String getMailAccount() {
		return mailAccount;
	}
	public void setMailAccount(String mailAccount) {
		this.mailAccount = mailAccount;
	}

	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public Integer getSecretErrorCount() {
		return secretErrorCount;
	}
	public void setSecretErrorCount(Integer secretErrorCount) {
		this.secretErrorCount = secretErrorCount;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getLoginState() {
		return loginState;
	}
	public void setLoginState(Integer loginState) {
		this.loginState = loginState;
	}
    
}
