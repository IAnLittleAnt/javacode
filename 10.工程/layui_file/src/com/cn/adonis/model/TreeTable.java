package com.cn.adonis.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.adonis.entity.auth.AuthAction;
import com.cn.adonis.entity.auth.AuthMenu;
import com.cn.adonis.entity.auth.AuthRole;

/**
 * 树形表格对象
 * @author 10011037@qq.com
 * 2019-08-08
 */
public class TreeTable {
	
	// 节点唯一索引
	private String id;
	// 父级索引
	private String pid;
	// 节点标题
	private String title;
	// 节点等级
	private Integer treeLevel;
	// 节点排序
	private Integer treeSort;
	// 是否根目录
	private boolean isRoot;
	// 选中Map
	private Map<String, Boolean> checkMap;
	
	public TreeTable(){};
	
	public TreeTable(AuthMenu menu, List<AuthRole> roleList){
		this.id = "menu-" + menu.getMenuId();
		this.pid = menu.getParentId().intValue() == 0 ? "0" : "menu-" + menu.getParentId();
		this.title = menu.getMenuName();
		this.treeLevel = menu.getMenuLevel();
		this.treeSort = menu.getMenuSort();
		this.isRoot = menu.getIsRoot() == 1;
		Map<String, Boolean> itemMap = new HashMap<String, Boolean>();
		if(roleList != null && roleList.size() > 0 && roleList.get(0) != null){
			for (AuthRole authRole : roleList) {
				itemMap.put("isCheck_"+authRole.getRoleId(), authRole.getIsValid() > 0);
			}
		}
		this.checkMap = itemMap;
	}
	
	public TreeTable(AuthAction action, Integer menuLevel, List<AuthRole> roleList){
		this.id = "action-" + action.getActionId();
		this.pid = "menu-" + action.getMenuId();
		this.title = action.getActionName();
		this.treeLevel = menuLevel + 1;
		this.treeSort = action.getActionSort();
		this.isRoot = false;
		Map<String, Boolean> itemMap = new HashMap<String, Boolean>();
		if(roleList != null && roleList.size() > 0 && roleList.get(0) != null){
			for (AuthRole authRole : roleList) {
				itemMap.put("isCheck_"+authRole.getRoleId(), authRole.getIsValid() > 0);
			}
		}
		this.checkMap = itemMap;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getTreeLevel() {
		return treeLevel;
	}
	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}
	public boolean getIsRoot() {
		return isRoot;
	}
	public void setIsRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	public Integer getTreeSort() {
		return treeSort;
	}
	public void setTreeSort(Integer treeSort) {
		this.treeSort = treeSort;
	}
	public Map<String, Boolean> getCheckMap() {
		return checkMap;
	}
	public void setCheckMap(Map<String, Boolean> checkMap) {
		this.checkMap = checkMap;
	}
	
}
