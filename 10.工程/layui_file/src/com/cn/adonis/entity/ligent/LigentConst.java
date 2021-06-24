package com.cn.adonis.entity.ligent;

/**
 * 常量信息管理
 * @author 10011037@qq.com
 * 2018-10-11
 */
public class LigentConst {

	// 自增编号
    private Long constId;
    // 常量唯一标志码(禁止修改)
    private Integer constKey;
    // 常量名称
    private String constName;
    // 常量值
    private String constValue;
    // 常量类型
    private String valueType;
    // 常量排序
    private Integer constSort;
    // 是否固定（1固定，0可修改）
    private Integer isFixed;
    // 使用过的存储过程
    private String used;
    // 是否有效(1有效数据，0无效数据)
    private Integer isValid;
    
    public LigentConst() {}
    
    /**
     * 新增、编辑
     */
    public LigentConst(Long constId, Integer constKey, String constName, String constValue, 
    		String valueType, Integer constSort, String used) {
    	this.constId = constId;
    	this.constKey = constKey;
    	this.constName = constName;
    	this.constValue = constValue;
    	this.valueType = valueType;
    	this.constSort = constSort;
    	this.used = used;
    }
    
	public Long getConstId() {
		return constId;
	}
	public void setConstId(Long constId) {
		this.constId = constId;
	}
	public Integer getConstKey() {
		return constKey;
	}
	public void setConstKey(Integer constKey) {
		this.constKey = constKey;
	}
	public String getConstName() {
		return constName;
	}
	public void setConstName(String constName) {
		this.constName = constName;
	}
	public String getConstValue() {
		return constValue;
	}
	public void setConstValue(String constValue) {
		this.constValue = constValue;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public Integer getConstSort() {
		return constSort;
	}
	public void setConstSort(Integer constSort) {
		this.constSort = constSort;
	}
	public Integer getIsFixed() {
		return isFixed;
	}
	public void setIsFixed(Integer isFixed) {
		this.isFixed = isFixed;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	};
    
}
