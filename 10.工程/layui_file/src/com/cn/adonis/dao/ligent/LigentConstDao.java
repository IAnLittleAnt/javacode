package com.cn.adonis.dao.ligent;

import java.util.List;
import java.util.Map;

import com.cn.adonis.entity.ligent.LigentConst;
import com.cn.adonis.model.state.StateModel;

public interface LigentConstDao {
	
	/**
	 * 分页获取常量信息
	 * @param map-searchKey 模糊搜索关键字
	 * @param map-start 分页开始条数
	 * @param map-limit 分页每页数量
	 */
	List<List<?>> findByPage(Map<String, Object> map);
	
	/**
	 * 根据编号获取常量信息
	 * @param constId 常量编号
	 */
	LigentConst fainById(Long constId);
	
	/**
	 * 根据标志码获取常量信息
	 * @param constKey 标志码
	 */
	LigentConst findByKey(Integer constKey);
	
	/**
	 * 新增常量信息
	 * @param model-constKey 常量编码
	 * @param model-constName 常量名称
	 * @param model-constValue 常量值
	 * @param model-valueType 常量类型
	 * @param model-constSort 常量排序
	 * @param model-used 使用场景
	 */
	StateModel insertConst(LigentConst model);
	
	/**
	 * 编辑常量信息
	 * @param model-constId 常量编号
	 * @param model-constName 常量名称
	 * @param model-constValue 常量值
	 * @param model-valueType 常量类型
	 * @param model-constSort 常量排序
	 * @param model-used 使用场景
	 */
	LigentConst updateConst(LigentConst model);
	
	/**
	 * 编辑常量信息
	 * @param model-constKey 常量编码
	 * @param model-constValue 常量值
	 */
	LigentConst updateValue(LigentConst model);
	
	/**
	 * 删除常量信息
	 * @param constId 常量编号
	 */
	StateModel deleteConst(Long constId);
	
}
