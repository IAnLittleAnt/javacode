package com.cn.paas.aliyun.sms;

/**
 * 短信验证常量(阿里云)
 * @author 10011037@qq.com
 * 2018-10-12
 */
public class SmsConst {
	
	/** 产品域名 */
	public static final String DOMAIN = "dysmsapi.aliyuncs.com";
	/** 云通信短信API */
	public static final String PRODUCT = "Dysmsapi";
	/** 地区编号 */
	public static final String REGION_ID = "cn-hangzhou";
	/** 端点名称 */
	public static final String ENDPOINT_NAME = "cn-hangzhou";
	
	/** 短信keyID */
	public static final String ACCESS_KEY_ID = "LTAILfpMIIiMVvy8";
	/** 短信keySecret */
	public static final String ACCESS_KEY_SECRET = "ezUSL1fHTXNOHfT3v6RBN4T2MUcC57";
	/** 短信签名 */
	public static final String SIGN_NAME = "贵州众股赢电子商务";
	
	/** 超时时间 */
	public static final String TIMEOUT = "10000";
	/** 短信有效时间 */
	public static final int ACTIVE_MIN = 30;
	/** 模板参数JSON字符串 */
	public static final String TEMPLATE_PARAM = "{\"code\":\"%s\"}";
	
	
	/**
	 * 验证码模版
	 */
	/** 身份验证验证码 模版内容:验证码${code}，您正在进行身份验证，打死不要告诉别人哦！ 模版CODE:SMS_147400047*/
	public static final int SMS_TYPE_SFTZYZM = 1;
	public static final String SMS_CODE_SFTZYZM = "SMS_147400047";
	public static final String SMS_NOTE_SFTZYZM = "验证码%s，您正在进行身份验证，打死不要告诉别人哦！";
	/** 登录确认验证码 模版内容:验证码${code}，您正在登录，若非本人操作，请勿泄露。 模版CODE:SMS_147400046*/
	public static final int SMS_TYPE_DLQRYZM = 2;
	public static final String SMS_CODE_DLQRYZM = "SMS_147400046";
	public static final String SMS_NOTE_DLQRYZM = "验证码%s，您正在登录，若非本人操作，请勿泄露。";
	/** 登录异常验证码 模版内容:验证码${code}，您正尝试异地登录，若非本人操作，请勿泄露。模版CODE:SMS_147400045*/
	public static final int SMS_TYPE_DLYCYZM = 3;
	public static final String SMS_CODE_DLYCYZM = "SMS_147400045";
	public static final String SMS_NOTE_DLYCYZM = "验证码%s，您正尝试异地登录，若非本人操作，请勿泄露。";
	/** 用户注册验证码 模版内容:验证码${code}，您正在注册成为新用户，感谢您的支持！模版CODE:SMS_147400044*/
	public static final int SMS_TYPE_YHZCYZM = 4;
	public static final String SMS_CODE_YHZCYZM = "SMS_147400044";
	public static final String SMS_NOTE_YHZCYZM = "验证码%s，您正在注册成为新用户，感谢您的支持！";
	/** 修改密码验证码 模版内容:验证码${code}，您正在尝试修改登录密码，请妥善保管账户信息。模版CODE:SMS_147400043*/
	public static final int SMS_TYPE_XGMMYZM = 5;
	public static final String SMS_CODE_XGMMYZM = "SMS_147400043";
	public static final String SMS_NOTE_XGMMYZM = "验证码%s，您正在尝试修改登录密码，请妥善保管账户信息。";
	/** 信息变更验证码 模版内容:验证码${code}，您正在尝试变更重要信息，请妥善保管账户信息。模版CODE:SMS_147400042*/
	public static final int SMS_TYPE_XXBGYZM = 6;
	public static final String SMS_CODE_XXBGYZM = "SMS_147400042";
	public static final String SMS_NOTE_XXBGYZM = "验证码%s，您正在尝试变更重要信息，请妥善保管账户信息。";
	
}
