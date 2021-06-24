package com.cn.paas.aliyun.sms;

import org.apache.log4j.Logger;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.cn.comm.UseTool;

/**
 * 阿里短信
 * @author 10011037@qq.com
 * 2018-10-12
 */
public class SmsSendMessage {
	
	private static Logger log = Logger.getLogger(SmsSendMessage.class);
    
    /**
     * 发送短信
     */
    public static SmsModel sendMessage(SmsModel smsModel){
    	SendSmsResponse response = null;
    	try {
			response = sendSms(smsModel);
		} catch (ClientException e) {
			response = null;
		}
    	if(response != null && "OK".equals(response.getCode().toUpperCase())){
        	log.info("发送短信成功：手机号["+smsModel.getMobile()+"],模板["+smsModel.getSmsType()+"],验证码["+smsModel.getSendCode()+"]");
        	smsModel.setBizId(response.getBizId());
        	smsModel.setRequestId(response.getRequestId());
        	return smsModel;
    	}
    	log.error("发送短信失败：手机号["+smsModel.getMobile()+"],模板["+smsModel.getSmsType()+"],返回码["+response.getCode()+"]");
    	return null;
    }
    

    /**
     * 开始请求第三方接口
     */
    public static SendSmsResponse sendSms(SmsModel smsModel) throws ClientException {
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", SmsConst.TIMEOUT);
        System.setProperty("sun.net.client.defaultReadTimeout", SmsConst.TIMEOUT);

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile(SmsConst.REGION_ID, 
        		SmsConst.ACCESS_KEY_ID, SmsConst.ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint(SmsConst.ENDPOINT_NAME, SmsConst.REGION_ID, 
        		SmsConst.PRODUCT, SmsConst.DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(smsModel.getMobile());
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(SmsConst.SIGN_NAME);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsModel.getTempId());
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        // request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"8866\"}");
        request.setTemplateParam(smsModel.getTemplateParam());
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }
    
    
    
    public static void main(String[] args) throws ClientException, InterruptedException {
    	SmsModel smsModel = new SmsModel(4, "13202368890", "12283");
    	smsModel = sendMessage(smsModel);
        System.out.println(UseTool.toJson(smsModel));
    }
    
}
