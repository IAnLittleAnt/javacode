package com.cn.adonis.dao.file;

import com.cn.adonis.entity.file.FileType;

public interface FileTypeDao {
	
	/**
	 * 获取文件夹对象
	 */
	FileType fainByFolder();
	
}
