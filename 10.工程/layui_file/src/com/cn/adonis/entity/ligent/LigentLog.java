package com.cn.adonis.entity.ligent;

/**
 * 更新日志记录表
 * @author 10011037@qq.com
 * 2018-09-02
 */
public class LigentLog {
	
	// 自增编号
    private Long logId;
    // 更新对象(1后台，2安卓，3苹果)
    private Integer logMold;
    // 更新类型(新增，修复，优化，扩展，剔除，调整，回滚)
    private String logType;
    // 版本号
    private String version;
    // 更新内容
    private String content;
    // 更新日期
    private String logDate;
    
    public LigentLog(){};

    /**
     * 新增
     */
    public LigentLog(Integer logMold, String version, String logDate){
    	this.logMold = logMold;
    	this.version = version;
    	this.logDate = logDate;
    }
    
	public Long getLogId() {
		return logId;
	}
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	public Integer getLogMold() {
		return logMold;
	}
	public void setLogMold(Integer logMold) {
		this.logMold = logMold;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	
}
