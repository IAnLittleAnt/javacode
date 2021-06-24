package com.cn.adonis.model.chat;

public class IMMessage {
	
	// 消息id
    private Long cid;
	// 消息的来源编号（如果是私聊，则是用户id，如果是群聊，则是群组id）
    private Long id;
	// 来源用户名
    private String username;
	// 来源用户头像
    private String avatar;
	// 来源类型(好友friend，群聊group)
    private String type;
	// 消息内容
    private String content;
	// 服务端时间戳毫秒数
    private Long timestamp;
	// 是否我发送的消息，如果为true，则会显示在右方[群聊]
    private boolean mine;
	// 消息的发送者id（比如群组中的某个消息发送者）
    private Long fromid;
    
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public boolean getMine() {
		return mine;
	}
	public void setMine(boolean mine) {
		this.mine = mine;
	}
	public Long getFromid() {
		return fromid;
	}
	public void setFromid(Long fromid) {
		this.fromid = fromid;
	}
	
}
