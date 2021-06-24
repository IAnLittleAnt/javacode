package com.cn.adonis.service;

import java.util.List;

import com.cn.adonis.model.state.StateModel;

public class BaseService {
	
	/**
	 * 把结果集转换为状态对象
	 * @param list 结果集
	 * @return 状态对象
	 */
	public StateModel getMapByState(List<List<?>> list){
		if(list == null || list.size() == 0 || list.size() < 2){
			return null;
		}
		StateModel stateModel = new StateModel(true);
		// 遍历结果集
		for (List<?> modelList : list) {
			if(modelList == null){
				continue;
			}
			// 判断结果对象
			for (Object value : modelList) {
				if (value instanceof StateModel){
					// 状态对象
					StateModel state = (StateModel) value;
					stateModel.setCount(state.getCount());
					break;
				}else{
					// 集合
					stateModel.setData(modelList);
					break;
				}
			}
		}
		return stateModel;
	}
	
}
