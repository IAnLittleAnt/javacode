package com.cn.adonis.dao.base;

import java.util.List;
import java.util.Map;

import com.cn.adonis.entity.base.BaseLog;

public interface BaseLogDao {
	
	/**
	 * 分页获取操作日志
	 * @param map-timeFrame 日期范围
	 * @param map-searchKey 模糊搜索关键字
	 * @param map-start 分页开始条数
	 * @param map-limit 分页每页数量
	 */
	List<List<?>> findByPage(Map<String, Object> map);
	
	/**
	 * 新增操作日志
	 */
	BaseLog insertLog(BaseLog model);
	
}
