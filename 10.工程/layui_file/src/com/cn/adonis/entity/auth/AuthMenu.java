package com.cn.adonis.entity.auth;

import java.util.List;

/**
 * 菜单信息表
 * @author 10011037@qq.com
 * 2018-12-31
 */
public class AuthMenu {
	
	// 菜单编号
    private Long menuId;
	// 菜单类型（1iframe模式，2全屏模式，3外部链接，4通讯系统）
    private Integer menuType;
	// 菜单名称
    private String menuName;
	// 菜单文本
    private String menuText;
	// 菜单路径
    private String menuPath;
	// 菜单图标
    private String menuIcon;
	// 菜单排序
    private Integer menuSort;
	// 父级编号（0代表根目录）
    private Long parentId;
	// 菜单等级
    private Integer menuLevel;
	// 是否根目录（1是，0否）
    private Integer isRoot;
	// 描述内容
    private String content;
	// 创建时间
    private String createTime;
	// 创建人编号
    private Long createUserId;
    // 是否有效(1有效数据，0无效数据)
    private Integer isValid;
    
    
    // 子菜单集合
    private List<AuthMenu> childList;
    // 子菜单数量
    private Integer childCount;
	// 父级菜单名称
    private String parentName;
    
    public AuthMenu(){}
    
    /**
     * 查询
     */
    public AuthMenu(String menuPath, Long createUserId){
    	this.menuPath = menuPath;
    	this.createUserId = createUserId;
    }
    
    /**
     * 新增
     */
    public AuthMenu(Integer menuType, Long parentId, String menuName, String menuText, 
    		String menuPath, Integer menuSort, Integer isRoot, Long createUserId){
    	this.menuType = menuType;
    	this.parentId = parentId;
    	this.menuName = menuName;
    	this.menuText = menuText;
    	this.menuPath = menuPath;
    	this.menuSort = menuSort;
    	this.isRoot = isRoot;
    	this.createUserId = createUserId;
    }
    
    /**
     * 编辑
     */
    public AuthMenu(Long menuId, Integer menuType, String menuName, String menuText, 
    		String menuPath, Integer menuSort){
    	this.menuId = menuId;
    	this.menuType = menuType;
    	this.menuName = menuName;
    	this.menuText = menuText;
    	this.menuPath = menuPath;
    	this.menuSort = menuSort;
    }
    
    /**
     * 编辑图标
     */
    public AuthMenu(Long menuId, String menuIcon){
    	this.menuId = menuId;
    	this.menuIcon = menuIcon;
    }
    
    /**
     * 权限
     */
    public AuthMenu(Long menuId, String menuName, Long parentId, Integer menuLevel, Integer isRoot, Integer isValid){
    	this.menuId = menuId;
    	this.menuName = menuName;
    	this.parentId = parentId;
    	this.menuLevel = menuLevel;
    	this.isRoot = isRoot;
    	this.isValid = isValid;
    }
    
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuText() {
		return menuText;
	}
	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public Integer getMenuSort() {
		return menuSort;
	}
	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Integer getIsRoot() {
		return isRoot;
	}
	public void setIsRoot(Integer isRoot) {
		this.isRoot = isRoot;
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
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public Integer getMenuType() {
		return menuType;
	}
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
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
	public Integer getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}
	public List<AuthMenu> getChildList() {
		return childList;
	}
	public void setChildList(List<AuthMenu> childList) {
		if(childList == null || childList.size() <= 0 || childList.get(0) == null){
			this.childList = null;
		}else{
			this.childList = childList;
		}
	}
    
}
