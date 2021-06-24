package com.cn.adonis.entity.auth;

/**
 * 密保问题表
 * @author 10011037@qq.com
 * 2018-12-31
 */
public class AuthSecret {
	
	// 问题编号
    private Long secretId;
	// 用户编号
    private Long userId;
	// 题目
    private String title;
	// 答案
    private String answer;
	// 最后更新时间
    private String lastUpdateTime;
	// 创建时间
    private String createTime;
	// 创建人编号
    private Long createUserId;
    // 是否有效(1有效数据，0无效数据)
    private Integer isValid;
    
    public AuthSecret(){};
    
    /**
     * 新增
     */
    public AuthSecret(Long userId, String title, String answer, Long createUserId){
    	this.userId = userId;
    	this.title = title;
    	this.answer = answer;
    	this.createUserId = createUserId;
    }
    
    /**
     * 修改
     */
    public AuthSecret(Long secretId, String title, String answer){
    	this.secretId = secretId;
    	this.title = title;
    	this.answer = answer;
    }
    
    
	public Long getSecretId() {
		return secretId;
	}
	public void setSecretId(Long secretId) {
		this.secretId = secretId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
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
    
}
