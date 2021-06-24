package com.cn.adonis.service.base.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.adonis.dao.base.BaseLogDao;
import com.cn.adonis.entity.base.BaseLog;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.BaseService;
import com.cn.adonis.service.base.ince.BaseLogService;

@Service("baseLogService")
public class BaseLogServiceImpl extends BaseService implements BaseLogService {
	
	@Resource
	private BaseLogDao baseLogDao;

	@Override
	public StateModel findByPage(Map<String, Object> map) {
		List<List<?>> list = baseLogDao.findByPage(map);
		return getMapByState(list);
	}
	
	@Override
	public BaseLog insertLog(BaseLog model) {
		return baseLogDao.insertLog(model);
	}
	
}
