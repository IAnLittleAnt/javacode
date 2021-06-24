package com.cn.adonis.service.file.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.adonis.dao.file.FileDocuDao;
import com.cn.adonis.entity.file.FileDocu;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.file.ince.FileDocuService;

@Service("fileDocuService")
public class FileDocuServiceImpl implements FileDocuService {
	
	@Resource
	private FileDocuDao fileDocuDao;

	@Override
	public FileDocu fainById(Long docuId) {
		return fileDocuDao.fainById(docuId);
	}
	
	@Override
	public List<FileDocu> findAllChild(FileDocu model) {
		return fileDocuDao.findAllChild(model);
	}
	
	@Override
	public List<FileDocu> findAllChilds(FileDocu model) {
		return fileDocuDao.findAllChilds(model);
	}
	
	@Override
	public List<FileDocu> findAllPater(Long docuId) {
		return fileDocuDao.findAllPater(docuId);
	}
	
	@Override
	public FileDocu insertFolder(FileDocu model) {
		return fileDocuDao.insertFolder(model);
	}
	
	@Override
	public FileDocu insertFile(FileDocu model) {
		return fileDocuDao.insertFile(model);
	}

	@Override
	public FileDocu updateName(FileDocu model) {
		return fileDocuDao.updateName(model);
	}

	@Override
	public StateModel updatePater(FileDocu model) {
		return fileDocuDao.updatePater(model);
	}

	@Override
	public StateModel updateCount(String docuIds) {
		return fileDocuDao.updateCount(docuIds);
	}
	
	@Override
	public StateModel deleteDocu(FileDocu model) {
		return fileDocuDao.deleteDocu(model);
	}

	@Override
	public FileDocu updateDesc(FileDocu model) {
		return fileDocuDao.updateDesc(model);
	}
	
}
