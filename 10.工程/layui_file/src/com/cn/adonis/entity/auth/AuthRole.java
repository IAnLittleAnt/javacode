package com.cn.adonis.entity.auth;

/**
 * 角色信息表
 * @author 10011037@qq.com
 * 2018-12-31
 */
public class AuthRole {

	// 角色编号
    private Long roleId;
	// 角色名称
    private String roleName;
	// 角色别名
    private String roleAlias;
	// 父级编号
    private Long parentId;
	// 角色等级
    private Integer roleRank;
	// 描述内容
    private String content;
	// 创建时间
    private String createTime;
	// 创建人编号
    private Long createUserId;
    // 是否有效(1有效数据，0无效数据)
    private Integer isValid;
    
	// 父级角色名称
    private String parentName;
	// 下属角色数量
    private Integer childCount;
	// 按钮数量
    private Integer actionCount;
	// 菜单数量
    private Integer menuCount;
	// 用户数量
    private Integer userCount;
    
    public AuthRole(){};
    
    /**
     * 新增
     */
    public AuthRole(String roleName, String roleAlias, Long parentId, Integer roleRank, String content, Long createUserId){
    	this.roleName = roleName;
    	this.roleAlias = roleAlias;
    	this.parentId = parentId;
    	this.roleRank = roleRank;
    	this.content = content;
    	this.createUserId = createUserId;
    }
    
    /**
     * 编辑
     */
    public AuthRole(Long roleId, String roleName, String roleAlias, Long parentId, Integer roleRank, String content){
    	this.roleId = roleId;
    	this.roleName = roleName;
    	this.roleAlias = roleAlias;
    	this.parentId = parentId;
    	this.roleRank = roleRank;
    	this.content = content;
    }
    
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleAlias() {
		return roleAlias;
	}
	public void setRoleAlias(String roleAlias) {
		this.roleAlias = roleAlias;
	}
	public Integer getRoleRank() {
		return roleRank;
	}
	public void setRoleRank(Integer roleRank) {
		this.roleRank = roleRank;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Integer getActionCount() {
		return actionCount;
	}
	public void setActionCount(Integer actionCount) {
		this.actionCount = actionCount;
	}
	public Integer getMenuCount() {
		return menuCount;
	}
	public void setMenuCount(Integer menuCount) {
		this.menuCount = menuCount;
	}
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getChildCount() {
		return childCount;
	}
	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}
	
}
