package com.cn.adonis.entity.auth;

/**
 * 角色菜单映射表
 * @author 10011037@qq.com
 * 2018-12-31
 */
public class AuthRoleMenu {

	// 角色菜单映射编号
    private Long rmId;
	// 角色编号
    private Long roleId;
	// 菜单编号
    private Long menuId;
    
	// 是否选中(1选中,0没选中)
    private Integer isCheck;
    
    public AuthRoleMenu(){};

    /**
     * 处理角色菜单映射表信息
     * @param roleId 角色编号
     * @param menuId 菜单编号
     * @param isCheck 是否选中(1选中,0没选中)
     */
    public AuthRoleMenu(Long roleId, Long menuId, Integer isCheck){
    	this.roleId = roleId;
    	this.menuId = menuId;
    	this.isCheck = isCheck;
    };
    
    
    
	public Long getRmId() {
		return rmId;
	}
	public void setRmId(Long rmId) {
		this.rmId = rmId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public Integer getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
    
}
