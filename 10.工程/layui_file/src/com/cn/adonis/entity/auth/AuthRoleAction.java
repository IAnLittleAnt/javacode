package com.cn.adonis.entity.auth;

/**
 * 角色按钮映射表
 * @author 10011037@qq.com
 * 2018-12-31
 */
public class AuthRoleAction {

	// 角色按钮映射编号
    private Long raId;
	// 角色编号
    private Long roleId;
	// 按钮编号
    private Long actionId;
    
	// 是否选中(1选中,0没选中)
    private Integer isCheck;
    
    public AuthRoleAction(){};

    /**
     * 处理角色按钮映射表信息
     * @param roleId 角色编号
     * @param actionId 按钮编号
     * @param isCheck 是否选中(1选中,0没选中)
     */
    public AuthRoleAction(Long roleId, Long actionId, Integer isCheck){
    	this.roleId = roleId;
    	this.actionId = actionId;
    	this.isCheck = isCheck;
    };
    
    
	public Long getRaId() {
		return raId;
	}
	public void setRaId(Long raId) {
		this.raId = raId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getActionId() {
		return actionId;
	}
	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}
	public Integer getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	
}
