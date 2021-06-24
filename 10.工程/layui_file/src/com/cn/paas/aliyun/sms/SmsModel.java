package com.cn.paas.aliyun.sms;

/**
 * 短信验证信息对象(阿里云)
 * @author 10011037@qq.com
 * 2018-10-12
 */
public class SmsModel {
	
	// 短信类型（4注册）
	private Integer smsType;
	// 发送手机号
	private String mobile;
	// 验证码
	private String sendCode;
	// 模板编号
	private String tempId;
	// 短信内容
	private String content;
	// 请求编号（返回）
	private String requestId;
	// 回执编号（返回）
	private String bizId;
	// 有效时间（分钟）
	private Integer activeMin;
	// 模板参数JSON字符串
	private String templateParam;
	
	/**
	 * 请求参数
	 */
	public SmsModel(Integer smsType, String mobile, String sendCode) {
		this.smsType = smsType;
		this.mobile = mobile;
		this.sendCode = sendCode;
		this.activeMin = SmsConst.ACTIVE_MIN;
		
		// 短信类型(1-7)模板参数Json
		this.templateParam = String.format(SmsConst.TEMPLATE_PARAM, sendCode);
		
		switch (smsType.intValue()) {
		case SmsConst.SMS_TYPE_SFTZYZM:	// 身份验证验证码
			this.tempId = SmsConst.SMS_CODE_SFTZYZM;
			this.content = String.format(SmsConst.SMS_NOTE_SFTZYZM, sendCode);
			break;
		case SmsConst.SMS_TYPE_DLQRYZM:	// 登录确认验证码
			this.tempId = SmsConst.SMS_CODE_DLQRYZM;
			this.content = String.format(SmsConst.SMS_NOTE_DLQRYZM, sendCode);
			break;
		case SmsConst.SMS_TYPE_DLYCYZM:	// 登录异常验证码
			this.tempId = SmsConst.SMS_CODE_DLYCYZM;
			this.content = String.format(SmsConst.SMS_NOTE_DLYCYZM, sendCode);
			break;
		case SmsConst.SMS_TYPE_YHZCYZM:	// 用户注册验证码
			this.tempId = SmsConst.SMS_CODE_YHZCYZM;
			this.content = String.format(SmsConst.SMS_NOTE_YHZCYZM, sendCode);
			break;
		case SmsConst.SMS_TYPE_XGMMYZM:	// 修改密码验证码
			this.tempId = SmsConst.SMS_CODE_XGMMYZM;
			this.content = String.format(SmsConst.SMS_NOTE_XGMMYZM, sendCode);
			break;
		case SmsConst.SMS_TYPE_XXBGYZM:	// 信息变更验证码
			this.tempId = SmsConst.SMS_CODE_XXBGYZM;
			this.content = String.format(SmsConst.SMS_NOTE_XXBGYZM, sendCode);
			break;
		default:
			break;
		}
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
	public Integer getActiveMin() {
		return activeMin;
	}
	public void setActiveMin(Integer activeMin) {
		this.activeMin = activeMin;
	}
	public String getTemplateParam() {
		return templateParam;
	}
	public void setTemplateParam(String templateParam) {
		this.templateParam = templateParam;
	}
	
}
