package com.cn.adonis.entity.auth;

/**
 * 按钮信息表（目前只做左菜单下的页码按钮）
 * @author 10011037@qq.com
 * 2018-12-31
 */
public class AuthAction {
	
	// 按钮编号
    private Long actionId;
	// 菜单编号
    private Long menuId;
	// 按钮名称
    private String actionName;
	// 按钮类型(来自类型表，用作前端条件判断)
    private Long actionType;
	// 按钮位置（1头部按钮，2表格按钮）
    private Integer actionSeat;
	// 调用方式（1样式，2函数，3Layui）
    private Integer callType;
	// 按钮事件
    private String actionEvent;
	// 按钮图标
    private String actionIcon;
	// 按钮排序
    private Integer actionSort;
	// 描述内容
    private String content;
	// 创建时间
    private String createTime;
	// 创建人编号
    private Long createUserId;
    // 是否有效(1有效数据，0无效数据)
    private Integer isValid;
    
	// 菜单名称
    private String menuName;
	// 按钮类型
    private String typeName;
    
    public AuthAction(){};
    
    /**
     * 新增
     */
    public AuthAction(Long menuId, String actionName, Long actionType, Integer actionSeat, Integer callType, 
    		String actionEvent, String actionIcon, Integer actionSort, String content, Long createUserId){
    	this.menuId = menuId;
    	this.actionName = actionName;
    	this.actionType = actionType;
    	this.actionSeat = actionSeat;
    	this.callType = callType;
    	this.actionEvent = actionEvent;
    	this.actionIcon = actionIcon;
    	this.actionSort = actionSort;
    	this.content = content;
    	this.createUserId = createUserId;
    }
    
    /**
     * 编辑
     */
    public AuthAction(Long actionId, String actionName, Long actionType, Integer actionSeat, Integer callType, 
    		String actionEvent, String actionIcon, Integer actionSort, String content){
    	this.actionId = actionId;
    	this.actionName = actionName;
    	this.actionType = actionType;
    	this.actionSeat = actionSeat;
    	this.callType = callType;
    	this.actionEvent = actionEvent;
    	this.actionIcon = actionIcon;
    	this.actionSort = actionSort;
    	this.content = content;
    }
    
    /**
     * 编辑图标
     */
    public AuthAction(Long actionId, String actionIcon){
    	this.actionId = actionId;
    	this.actionIcon = actionIcon;
    }
    
    /**
     * 权限查询
     */
    public AuthAction(Long createUserId, Long menuId){
    	this.createUserId = createUserId;
    	this.menuId = menuId;
    }
    
    
	public Long getActionId() {
		return actionId;
	}
	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionEvent() {
		return actionEvent;
	}
	public void setActionEvent(String actionEvent) {
		this.actionEvent = actionEvent;
	}
	public String getActionIcon() {
		return actionIcon;
	}
	public void setActionIcon(String actionIcon) {
		this.actionIcon = actionIcon;
	}
	public Integer getActionSort() {
		return actionSort;
	}
	public void setActionSort(Integer actionSort) {
		this.actionSort = actionSort;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Integer getActionSeat() {
		return actionSeat;
	}
	public void setActionSeat(Integer actionSeat) {
		this.actionSeat = actionSeat;
	}
	public Integer getCallType() {
		return callType;
	}
	public void setCallType(Integer callType) {
		this.callType = callType;
	}
	public Long getActionType() {
		return actionType;
	}
	public void setActionType(Long actionType) {
		this.actionType = actionType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
    
}
