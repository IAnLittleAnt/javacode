package com.cn.adonis.service.base.ince;

import java.util.Map;

import com.cn.adonis.entity.base.BaseLog;
import com.cn.adonis.model.state.StateModel;

public interface BaseLogService {
	
	/**
	 * 分页获取操作日志
	 * @param map-timeFrame 日期范围
	 * @param map-searchKey 模糊搜索关键字
	 * @param map-start 分页开始条数
	 * @param map-limit 分页每页数量
	 */
	StateModel findByPage(Map<String, Object> map);
	
	/**
	 * 新增操作日志
	 */
	BaseLog insertLog(BaseLog model);
	
}
