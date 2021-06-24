package com.cn.adonis.model.state;

/**
 * 状态常量
 * @author 10011037@qq.com
 * 2017-05-23
 */
public class StateCode {
	
	/** 操作成功 */
	public static final int STATECODE_SUCCESS = 0;
	public static final String STATEMSG_SUCCESS = "操作成功！";
	/** 操作失败 */
	public static final int STATECODE_ERROR = 1102;
	public static final String STATEMSG_ERROR = "系统繁忙，请稍后再试！";
	
	
	/** 回话过期 */
	public static final int STATECODE_OVERDUE = 1101;
	public static final String STATEMSG_OVERDUE = "回话已过期！";
	/** 权限不足 */
	public static final int STATECODE_POWER = 1103;
	public static final String STATEMSG_POWER = "抱歉，您没有操作权限！";
	
	
	/** 图片验证码不正确 */
	public static final int STATECODE_VERCODE = 5001;
	public static final String STATEMSG_VERCODE = "图片验证码不正确！";
	/** 账号密码不正确 */
	public static final int STATECODE_ACCOUNT = 5002;
	public static final String STATEMSG_ACCOUNT = "账号密码不正确！";
	
	
	/**
	 * 根据状态码获取提示信息
	 * @param statusCode 状态码
	 * @return 提示信息
	 */
	public static String getHintByCode(int statusCode){
		String statusHint = null;
		switch (statusCode) {
		case StateCode.STATECODE_SUCCESS:
			// 操作成功
			statusHint = StateCode.STATEMSG_SUCCESS;
			break;
		case StateCode.STATECODE_ERROR:
			// 操作失败
			statusHint = StateCode.STATEMSG_ERROR;
			break;
		case StateCode.STATECODE_OVERDUE:
			// 回话过期
			statusHint = StateCode.STATEMSG_OVERDUE;
			break;
		case StateCode.STATECODE_POWER:
			// 权限不足
			statusHint = StateCode.STATEMSG_POWER;
			break;
		case StateCode.STATECODE_VERCODE:
			// 验证码不正确
			statusHint = StateCode.STATEMSG_VERCODE;
			break;
		case StateCode.STATECODE_ACCOUNT:
			// 账号密码不正确
			statusHint = StateCode.STATEMSG_ACCOUNT;
			break;
		default:
			// 操作失败
			statusHint = StateCode.STATEMSG_ERROR;
			break;
		}
		return statusHint;
	}
	
}
