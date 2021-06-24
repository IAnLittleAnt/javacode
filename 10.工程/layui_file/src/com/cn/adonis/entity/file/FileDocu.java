package com.cn.adonis.entity.file;

/**
 * 文件信息表
 * @author 10011037@qq.com
 * 2020-04-23
 */
public class FileDocu {

	// 自增编号
    private Long docuId;
	// 文件用途（1私人文件，2共享文件）
    private Integer docuUse;
	// 父级编号
    private Long paterId;
	// 文件类型编号
    private Long typeId;
	// 文件名称
    private String docuName;
	// 文件路径
    private String docuPath;
	// 本地目录
    private String localPath;
	// 文件后缀
    private String docuSuffix;
	// 文件大小（KB）
    private Long docuSize;
	// 文件大小与单位
    private String docuSizeUnit;
	// 子目录文件数量
    private Integer childCount;
	// 下载次数
    private Integer downloadCount;
	// 描述内容
    private String docuDesc;
	// 最后更改时间
    private String lastUpdateTime;
	// 最后更改用户
    private Long lastUpdateUser;
	// 创建时间
    private String createTime;
	// 创建人编号
    private Long createUserId;
	// 是否有效(1有效数据，0无效数据)
    private Integer isValid;
    
	// 共享文件类型图标
    private String typeIcon;
	// 私有文件类型图标
    private String typeIcong;
	// 浏览方式
    private Integer browseMode;
	// 上传人
    private String userName;
    
    public FileDocu(){};
    
    /**
     * 重命名
     */
    public FileDocu(Long docuId, String docuDesc){
    	this.docuId = docuId;
    	this.docuDesc = docuDesc;
    }
    
    /**
     * 查询
     */
    public FileDocu(Long docuId, Integer docuUse, Integer browseMode, Long createUserId){
    	this.docuId = docuId;
    	this.docuUse = docuUse;
    	this.browseMode = browseMode;
    	this.createUserId = createUserId;
    }
    
    /**
     * 删除
     */
    public FileDocu(Long docuId, Long createUserId){
    	this.docuId = docuId;
    	this.createUserId = createUserId;
    }
    
    /**
     * 重命名
     */
    public FileDocu(Long docuId, Long paterId, String docuName){
    	this.docuId = docuId;
    	this.paterId = paterId;
    	this.docuName = docuName;
    }
    
    /**
     * 创建文件夹
     */
    public FileDocu(Long paterId, Integer docuUse, String docuName, Long createUserId){
    	this.paterId = paterId;
    	this.docuUse = docuUse;
    	this.docuName = docuName;
    	this.createUserId = createUserId;
    }
    
    /**
     * 上传文件
     */
    public FileDocu(Long paterId, Integer docuUse, String docuName, String docuPath, String docuSuffix, 
    		Long docuSize, String docuSizeUnit, String docuDesc, Long createUserId){
    	this.paterId = paterId;
    	this.docuUse = docuUse;
    	this.docuName = docuName;
    	this.docuPath = docuPath;
    	this.docuSuffix = docuSuffix;
    	this.docuSize = docuSize;
    	this.docuSizeUnit = docuSizeUnit;
    	this.docuDesc = docuDesc;
    	this.createUserId = createUserId;
    }
    
	public Long getDocuId() {
		return docuId;
	}
	public void setDocuId(Long docuId) {
		this.docuId = docuId;
	}
	public Integer getDocuUse() {
		return docuUse;
	}
	public void setDocuUse(Integer docuUse) {
		this.docuUse = docuUse;
	}
	public Long getPaterId() {
		return paterId;
	}
	public void setPaterId(Long paterId) {
		this.paterId = paterId;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getDocuName() {
		return docuName;
	}
	public void setDocuName(String docuName) {
		this.docuName = docuName;
	}
	public String getDocuPath() {
		return docuPath;
	}
	public void setDocuPath(String docuPath) {
		this.docuPath = docuPath;
	}
	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	public String getDocuSuffix() {
		return docuSuffix;
	}
	public void setDocuSuffix(String docuSuffix) {
		this.docuSuffix = docuSuffix;
	}
	public Long getDocuSize() {
		return docuSize;
	}
	public void setDocuSize(Long docuSize) {
		this.docuSize = docuSize;
	}
	public String getDocuSizeUnit() {
		return docuSizeUnit;
	}
	public void setDocuSizeUnit(String docuSizeUnit) {
		this.docuSizeUnit = docuSizeUnit;
	}
	public Integer getChildCount() {
		return childCount;
	}
	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}
	public Integer getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}
	public String getDocuDesc() {
		return docuDesc;
	}
	public void setDocuDesc(String docuDesc) {
		this.docuDesc = docuDesc;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Long getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(Long lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
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
	public String getTypeIcon() {
		return typeIcon;
	}
	public void setTypeIcon(String typeIcon) {
		this.typeIcon = typeIcon;
	}
	public Integer getBrowseMode() {
		return browseMode;
	}
	public void setBrowseMode(Integer browseMode) {
		this.browseMode = browseMode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTypeIcong() {
		return typeIcong;
	}
	public void setTypeIcong(String typeIcong) {
		this.typeIcong = typeIcong;
	}
    
}
