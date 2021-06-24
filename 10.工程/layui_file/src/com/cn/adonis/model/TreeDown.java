package com.cn.adonis.model;

import com.cn.adonis.entity.auth.AuthRole;
import com.cn.comm.UseTool;

/**
 * 树形下拉对象
 * @author 10011037@qq.com
 * 2019-08-08
 */
public class TreeDown {

	// 节点唯一索引
	private String id;
	// 父级索引
	private String pid;
	// 节点标题
	private String names;
	// 是否展开
	private boolean open;
	// 是否禁用
	private boolean chkDisabled;
	
	public TreeDown(){};
	
	public TreeDown(AuthRole role){
		this.id = UseTool.toStr(role.getRoleId());
		this.pid = UseTool.toStr(role.getParentId());
		this.names = role.getRoleName();
		/*this.open = true;
		this.chkDisabled = false;*/
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
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public boolean getOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	
	
}
