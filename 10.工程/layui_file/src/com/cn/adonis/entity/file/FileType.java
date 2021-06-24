package com.cn.adonis.entity.file;

/**
 * 文件类型表
 * @author 10011037@qq.com
 * 2020-04-23
 */
public class FileType {

	// 自增编号
    private Long typeId;
	// 类型公共字符
    private String typeKey;
	// 类型名称
    private String typeName;
	// 类型小图标
    private String typeIcon;
	// 类型大图标
    private String typeIcong;
	// 浏览方式
    private Integer browseMode;
    
    public FileType(){}
    
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getTypeKey() {
		return typeKey;
	}
	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeIcon() {
		return typeIcon;
	}
	public void setTypeIcon(String typeIcon) {
		this.typeIcon = typeIcon;
	}
	public String getTypeIcong() {
		return typeIcong;
	}
	public void setTypeIcong(String typeIcong) {
		this.typeIcong = typeIcong;
	}
	public Integer getBrowseMode() {
		return browseMode;
	}
	public void setBrowseMode(Integer browseMode) {
		this.browseMode = browseMode;
	};
    
}
