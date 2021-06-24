package com.cn.adonis.service.ligent.ince;

import com.cn.adonis.entity.ligent.LigentFile;

public interface LigentFileService {
	
	/**
	 * 新增文件信息
	 * @param model-fileUse 文件用途
	 * @param model-fileTitle 文件标题
	 * @param model-filePath 文件路径
	 * @param model-createUserId 创建人编号
	 */
	LigentFile insertFile(LigentFile model);
	
}
