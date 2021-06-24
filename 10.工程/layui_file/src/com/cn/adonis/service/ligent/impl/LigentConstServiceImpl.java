package com.cn.adonis.service.ligent.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.adonis.dao.ligent.LigentConstDao;
import com.cn.adonis.entity.ligent.LigentConst;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.BaseService;
import com.cn.adonis.service.ligent.ince.LigentConstService;

@Service("ligentConstService")
public class LigentConstServiceImpl extends BaseService implements LigentConstService {
	
	@Resource
	private LigentConstDao ligentConstDao;
	
	@Override
	public StateModel findByPage(Map<String, Object> map) {
		List<List<?>> list = ligentConstDao.findByPage(map);
		return getMapByState(list);
	}

	@Override
	public LigentConst fainById(Long constId) {
		return ligentConstDao.fainById(constId);
	}

	@Override
	public String findByKey(Integer constKey) {
		LigentConst ligentConst = ligentConstDao.findByKey(constKey);
		String constValue = ligentConst!=null?ligentConst.getConstValue():null;
		return constValue;
	}

	@Override
	public StateModel insertConst(LigentConst model) {
		return ligentConstDao.insertConst(model);
	}

	@Override
	public LigentConst updateConst(LigentConst model) {
		return ligentConstDao.updateConst(model);
	}

	@Override
	public LigentConst updateValue(LigentConst model) {
		return ligentConstDao.updateValue(model);
	}

	@Override
	public StateModel deleteConst(Long constId) {
		return ligentConstDao.deleteConst(constId);
	}
	
}
