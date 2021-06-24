package com.cn.adonis.entity.base;

/**
 * 操作日志表
 * @author 10011037@qq.com
 * 2020-10-09
 */
public class BaseLog {

	// 自增编号
    private Long logId;
	// 操作人编号
    private Long userId;
	// 操作终端（1后台，2安卓）
    private Integer source;
	// IP地址
    private String ip;
	// 控制器
    private String controller;
	// 方法名
    private String method;
	// 操作说明
    private String content;
	// 参数Json数据
    private String dataJson;
	// 报错信息
    private String exMsg;
	// 操作状态（1成功，2抛异常）
    private Integer logState;
	// 操作时间
    private String logTime;
    
	// 操作人姓名
    private String userName;
    
    public BaseLog(){};
    
    public BaseLog(Long userId, Integer source){
    	this.userId = userId;
    	this.source = source;
    }
    
    public BaseLog(String controller, String method, String content){
    	this.controller = controller;
    	this.method = method;
    	this.content = content;
    }
    
    
	public Long getLogId() {
		return logId;
	}
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	public String getLogTime() {
		return logTime;
	}
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	public String getExMsg() {
		return exMsg;
	}
	public void setExMsg(String exMsg) {
		this.exMsg = exMsg;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDataJson() {
		return dataJson;
	}
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	public Integer getLogState() {
		return logState;
	}
	public void setLogState(Integer logState) {
		this.logState = logState;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	
}
