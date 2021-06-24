package com.cn.adonis.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.adonis.model.state.StateModel;
import com.cn.comm.UseTool;
import com.cn.unit.data.SortList;

/**
 * 分页对象
 * @author 10011037@qq.com
 * 2016-02-12
 */
public class PageUnit {
	
	/** 分页-默认第1页 */
	public static final Integer CURRENT_PAGE = 1;
	/** 分页-默认每页10条 */
	public static final Integer CURRENT_LIMIT = 20;
	
	/**
	 * 返回对象
	 * @param page 第几页
	 * @param limit 每页几条
	 * @return
	 */
	public static Map<String, Object> getPageMap(String page, String limit, String searchKey){
		// 默认值
		int page_ = UseTool.isInt(page) ? UseTool.toInt(page) : CURRENT_PAGE;
		int limit_ = UseTool.isInt(limit) ? UseTool.toInt(limit) : CURRENT_LIMIT;
		// 开始条数
		Integer start_ = (page_ - 1) * limit_;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start_);
		map.put("limit", limit_);
		map.put("searchKey", UseTool.toQuery(searchKey));
		return map;
	}
	
	/**
	 * 集合重新排序
	 * @param state 状态对象
	 * @param field 排序的字段名
	 * @param order 排序类型：降序[desc]、升序[asc]
	 * @return 状态对象
	 */
	public static StateModel sortList(StateModel state, String field, String order){
    	if(!UseTool.isEmpty(field) && !UseTool.isEmpty(order)){
        	List<Object> list = UseTool.toList(state.getData());
    		SortList<Object> sortList = new SortList<Object>();
    	    sortList.Sort(list, "get"+field.substring(0, 1).toUpperCase()+field.substring(1), order);
    	    state.setData(list);
    	}
    	return state;
	}
	
	
}
