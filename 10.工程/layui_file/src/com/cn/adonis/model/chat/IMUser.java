package com.cn.adonis.model.chat;

import com.cn.adonis.entity.auth.AuthUser;
import com.cn.comm.UseTool;

public class IMUser {
	
	// 编号
    private String id;
	// 姓名
    private String username;
	// 头像
    private String avatar;
	// 签名
    private String sign;
	// 在线状态
    private String status;
	// 待读数量
    private Integer read;
	// 类型
    private String type;
	// 群、组编号
    private Integer groupid;
    
    public IMUser(){};
    
    public IMUser(AuthUser user, boolean status){
    	this.id = UseTool.toStr(user.getUserId());
    	this.username = user.getUserName();
    	this.avatar = user.getAvatar();
    	this.sign = user.getSign();
    	this.status = status?"online":"offline";
    }
    
    /*public IMUser(String id, String username, String avatar, String sign, boolean status){
    	this.id = id;
    	this.username = username;
    	this.avatar = avatar;
    	this.sign = sign;
    	this.status = status?"online":"offline";
    	this.read = "1";
    }*/
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getRead() {
		return read;
	}
	public void setRead(Integer read) {
		this.read = read;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	
}
