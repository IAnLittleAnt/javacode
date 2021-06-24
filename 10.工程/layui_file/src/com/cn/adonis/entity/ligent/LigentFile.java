package com.cn.adonis.entity.ligent;

/**
 * 文件信息管理
 * @author 10011037@qq.com
 * 2018-10-11
 */
public class LigentFile {

	/** 文件用途-邮件 */
	public static final String FILE_USE_MAIL = "mail";
	/** 文件用途-聊天 */
	public static final String FILE_USE_CHAT = "chat";
	
	
	// 自增编号
    private Long fileId;
    // 文件用途
    private String fileUse;
    // 文件类型
    private Long typeId;
    // 文件标题
    private String fileTitle;
    // 文件路径
    private String filePath;
	// 创建时间
    private String createTime;
	// 创建人编号
    private Long createUserId;
    // 是否有效(1有效数据，0无效数据)
    private Integer isValid;
    
    public LigentFile() {};
    
    /**
     * 新增
     */
    public LigentFile(String fileUse, String fileTitle, String filePath, Long createUserId) {
    	this.fileUse = fileUse;
    	this.fileTitle = fileTitle;
    	this.filePath = filePath;
    	this.createUserId = createUserId;
    }

	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public String getFileUse() {
		return fileUse;
	}
	public void setFileUse(String fileUse) {
		this.fileUse = fileUse;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getFileTitle() {
		return fileTitle;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
    
}
