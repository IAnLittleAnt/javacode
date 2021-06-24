package com.cn.adonis.model.chat;

import java.util.List;

public class IMGroup {

	// 编号
    private Long id;
	// 姓名
    private String groupname;
	// 群聊头像
    private String avatar;
	// 待读数量
    private Integer read;
	// 是否群主（1群主，0非群主）
    private Integer owner;
    
	// 好友列表
    private List<IMUser> list;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getRead() {
		return read;
	}
	public void setRead(Integer read) {
		this.read = read;
	}
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	public List<IMUser> getList() {
		return list;
	}
	public void setList(List<IMUser> list) {
		this.list = null;
		if(list!=null && list.size()>0){
			this.list = list;
		}
	};
	
}
