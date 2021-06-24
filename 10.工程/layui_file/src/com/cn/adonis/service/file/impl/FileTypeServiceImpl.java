package com.cn.adonis.service.file.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.adonis.dao.file.FileTypeDao;
import com.cn.adonis.entity.file.FileType;
import com.cn.adonis.service.file.ince.FileTypeService;

@Service("fileTypeService")
public class FileTypeServiceImpl implements FileTypeService {
	
	@Resource
	private FileTypeDao fileTypeDao;

	@Override
	public FileType fainByFolder() {
		return fileTypeDao.fainByFolder();
	}

}
