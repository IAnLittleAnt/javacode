package com.cn.adonis.entity.auth;

/**
 * 按钮类型信息表
 * @author 10011037@qq.com
 * 2018-12-31
 */
public class AuthActionType {

	// 按钮类型编号
    private Long typeId;
	// 按钮类型名称
    private String typeName;
	// 按钮类型排序
    private Integer typeSort;
    // 是否有效(1有效数据，0无效数据)
    private Integer isValid;
    
    public AuthActionType(){}

	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getTypeSort() {
		return typeSort;
	}
	public void setTypeSort(Integer typeSort) {
		this.typeSort = typeSort;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	};
    
}
