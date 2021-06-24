package com.cn.adonis.entity.auth;

/**
 * 角色用户映射表
 * @author 10011037@qq.com
 * 2018-12-31
 */
public class AuthRoleUser {
	
	/**
	 * 若删除用户信息，用户角色关系做逻辑删除
	 * 若修改用户信息，用户角色关系做物理删除
	 */
	
	// 角色用户映射编号
    private Long ruId;
	// 角色编号
    private Long roleId;
	// 用户编号
    private Long userId;
    // 是否有效(1有效数据，0无效数据)
    private Integer isValid;
    
    
    
	public Long getRuId() {
		return ruId;
	}
	public void setRuId(Long ruId) {
		this.ruId = ruId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	
}
