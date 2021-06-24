package com.cn.adonis.model.state;

import java.util.List;

/**
 * 状态工具
 * @author 10011037@qq.com
 * 2017-05-23
 */
public class StateModel {
	
	// 执行的结果码
	private Integer code;
	// 结果提示信息
	private String msg;
	// 编号
	private Long keyId;
	// 数量
	private Integer count;
	// 数据集合
	private Object data;
	
	public StateModel(){};
	
	public StateModel(boolean mark) {
		if(mark){
			this.code = StateCode.STATECODE_SUCCESS;
			this.msg = StateCode.STATEMSG_SUCCESS;
		}else{
			this.code = StateCode.STATECODE_ERROR;
			this.msg = StateCode.STATEMSG_ERROR;
		}
	}

	/**
	 * 根据状态码获取对象
	 */
	public StateModel(int code) {
		this.code = code;
		this.msg = StateCode.getHintByCode(code);
	}
	
	/**
	 * 根据失败原因获取对象
	 */
	public StateModel(String msg){
		this.code = StateCode.STATECODE_ERROR;
		this.msg = msg;
	}

	/**
	 * 
	 */
	public StateModel(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 返回集合对象
	 */
	public StateModel(List<?> data) {
		this.code = StateCode.STATECODE_SUCCESS;
		this.msg = StateCode.STATEMSG_SUCCESS;
		if(data != null && data.size() > 0){
			this.data = data;
		}else{
			this.data = null;
		}
	}
	
	public StateModel(Object data) {
		this.code = StateCode.STATECODE_SUCCESS;
		this.msg = StateCode.STATEMSG_SUCCESS;
		if (null == data) {
			this.data = null;
		}else{
			this.data = data;
		}
	}
	
	public StateModel(String msg, Object data) {
		this.code = StateCode.STATECODE_SUCCESS;
		this.msg = msg;
		if (null == data) {
			this.data = null;
		}else{
			this.data = data;
		}
		/*else if(data instanceof List){
			List<Object> list = new ArrayList<Object>();
			list.add(data);
			this.data = list;
		}*/
	}

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getKeyId() {
		return keyId;
	}
	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
