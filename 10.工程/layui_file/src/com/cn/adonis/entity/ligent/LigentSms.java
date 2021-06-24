package com.cn.adonis.entity.ligent;

import com.cn.paas.aliyun.sms.SmsModel;

/**
 * 短信验证码
 * @author 10011037@qq.com
 * 2018-10-11
 */
public class LigentSms {

	// 自增编号
    private Long smsId;
    // 短信类型(4注册)
    private Integer smsType;
    // 手机号码
    private String mobile;
    // 模板编号
    private String tempId;
    // 验证码
    private String sendCode;
    // 短信内容
    private String content;
    // 请求编号(返回)
    private String requestId;
    // 回执编号(返回)
    private String bizId;
    // 申请发送人编号
    private Long createUserId;
    // 发送时间
    private String sendTime;
    // 过期时间
    private String expireTime;
    // 有效时间(分钟)
    private Integer activeMin;
    // 是否有效(1有效数据，0无效数据)
    private Integer isValid;
    
    public LigentSms() {};
    
    /**
     * 发送短信请求
     * @param loginUserId 发送人
     * @param smsType 短信类型（4注册）
     * @param mobile 手机号码
     */
    public LigentSms(Long loginUserId, Integer smsType, String mobile) {
    	this.createUserId = loginUserId;
    	this.smsType = smsType;
    	this.mobile = mobile;
    }
    
    /**
     * 判断验证码是否匹配
     */
    public LigentSms(Integer smsType, String mobile, String sendCode) {
    	this.smsType = smsType;
    	this.mobile = mobile;
    	this.sendCode = sendCode;
    }
    
    /**
     * 新增
     */
    public LigentSms(SmsModel smsModel, Long createUserId) {
    	this.smsType = smsModel.getSmsType();
    	this.mobile = smsModel.getMobile();
    	this.tempId = smsModel.getTempId();
    	this.sendCode = smsModel.getSendCode();
    	this.content = smsModel.getContent();
    	this.requestId = smsModel.getRequestId();
    	this.bizId = smsModel.getBizId();
    	this.activeMin = smsModel.getActiveMin();
    	this.createUserId = createUserId;
    }

	public Long getSmsId() {
		return smsId;
	}
	public void setSmsId(Long smsId) {
		this.smsId = smsId;
	}
	public Integer getSmsType() {
		return smsType;
	}
	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTempId() {
		return tempId;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	public String getSendCode() {
		return sendCode;
	}
	public void setSendCode(String sendCode) {
		this.sendCode = sendCode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public Integer getActiveMin() {
		return activeMin;
	}
	public void setActiveMin(Integer activeMin) {
		this.activeMin = activeMin;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	
}
