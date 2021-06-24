package com.cn.adonis.service.ligent.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.adonis.dao.ligent.LigentFileDao;
import com.cn.adonis.entity.ligent.LigentFile;
import com.cn.adonis.service.ligent.ince.LigentFileService;

@Service("ligentFileService")
public class LigentFileServiceImpl implements LigentFileService {
	
	@Resource
	private LigentFileDao ligentFileDao;
	
	@Override
	public LigentFile insertFile(LigentFile model) {
		return ligentFileDao.insertFile(model);
	}

}
