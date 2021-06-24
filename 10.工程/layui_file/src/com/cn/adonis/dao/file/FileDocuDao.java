package com.cn.adonis.dao.file;

import java.util.List;

import com.cn.adonis.entity.file.FileDocu;
import com.cn.adonis.model.state.StateModel;

public interface FileDocuDao {
	
	/**
	 * 根据编号获取文件信息
	 * @param docuId 文件编号
	 */
	FileDocu fainById(Long docuId);
	
	/**
	 * 获取所有孩子节点
	 * @param model-docuId 文件夹编号
	 * @param model-docuUse 文件用途（1私人文件，2共享文件）
	 * @param model-createUserId 登录用户编号
	 */
	List<FileDocu> findAllChild(FileDocu model);
	
	/**
	 * 获取所有孩子节点
	 * @param model-docuId 文件夹编号
	 * @param model-docuUse 文件用途（1私人文件，2共享文件）
	 * @param model-createUserId 登录用户编号
	 */
	List<FileDocu> findAllChilds(FileDocu model);
	
	/**
	 * 获取所有父级节点
	 * @param docuId 文件夹编号
	 */
	List<FileDocu> findAllPater(Long docuId);
	
	/**
	 * 创建文件夹
	 * @param model-paterId 父级编号
	 * @param model-docuUse 文件用途（1私人文件，2共享文件）
	 * @param model-docuName 文件名称
	 * @param model-createUserId 创建人编号
	 */
	FileDocu insertFolder(FileDocu model);
	
	/**
	 * 上传文件
	 * @param model-paterId 父级编号
	 * @param model-docuUse 文件用途（1私人文件，2共享文件）
	 * @param model-docuName 文件名称
	 * @param model-docuPath 文件路径
	 * @param model-docuSuffix 文件后缀
	 * @param model-docuSize 文件大小（KB）
	 * @param model-docuSizeUnit 文件大小与单位
	 * @param model-docuDesc 描述内容
	 * @param model-createUserId 创建人编号
	 */
	FileDocu insertFile(FileDocu model);
	
	/**
	 * 文件重命名
	 * @param model-docuId 文件夹编号
	 * @param model-docuName 文件名称
	 */
	FileDocu updateName(FileDocu model);
	
	/**
	 * 文件描述内容
	 * @param model-docuId 文件夹编号
	 */
	FileDocu updateDesc(FileDocu model);
	
	/**
	 * 移动文件
	 * @param model-docuId 文件夹编号
	 * @param model-paterId 父级编号
	 */
	StateModel updatePater(FileDocu model);
	
	/**
	 * 更新下载数量
	 * @param docuIds 文件名称
	 */
	StateModel updateCount(String docuIds);
	
	/**
	 * 删除文件
	 * @param model-docuId 文件夹编号
	 * @param model-createUserId 登录用户编号
	 */
	StateModel deleteDocu(FileDocu model);
	
}
