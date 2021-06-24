package com.cn.adonis.service.auth.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.adonis.dao.auth.AuthMenuDao;
import com.cn.adonis.entity.auth.AuthMenu;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.auth.ince.AuthMenuService;
import com.cn.comm.UseTool;

@Service("authMenuService")
public class AuthMenuServiceImpl implements AuthMenuService {
	
	@Resource
	private AuthMenuDao authMenuDao;

	@Override
	public AuthMenu fainById(Long menuId) {
		return authMenuDao.fainById(menuId);
	}

	@Override
	public List<AuthMenu> findByIds(String menuIds) {
		return authMenuDao.findByIds(menuIds);
	}

	@Override
	public List<AuthMenu> findByAll() {
		return authMenuDao.findByAll();
	}
	
	@Override
	public List<AuthMenu> findByUser(Long userId) {
		// 菜单集合
		List<AuthMenu> list = null;
		if(UseTool.isLong(userId)){
			// 根据用户获取所有菜单
			list = authMenuDao.findByUser(userId);
		}else{
			// 获取所有菜单列表
			list = authMenuDao.findByAll();
		}
		// 声明一个变量存储一级菜单，封装返回的集合
		List<AuthMenu> revertList = new ArrayList<AuthMenu>();
		// 声明一个变量存储二级菜单
		List<AuthMenu> childList = null;
		// 声明一个变量存储三级菜单
		List<AuthMenu> sunList = null;
		Long menuId = null, parentId = null;
		for (AuthMenu item : list) {
			parentId = item.getParentId();
			if(parentId.intValue() == 0){
				childList = new ArrayList<AuthMenu>();
				menuId = item.getMenuId();
				for (AuthMenu child : list) {
					parentId = child.getParentId();
					if(parentId.intValue()==menuId.intValue()){
						sunList = new ArrayList<AuthMenu>();
						for (AuthMenu sun : list) {
							if(sun.getParentId().intValue()==child.getMenuId().intValue()){
								sunList.add(sun);
							}
						}
						child.setChildList(sunList);
						childList.add(child);
					}
				}
				item.setChildList(childList);
				revertList.add(item);
			}
		}
		return revertList;
	}
	
	@Override
	public AuthMenu insertMenu(AuthMenu model) {
		return authMenuDao.insertMenu(model);
	}
	
	@Override
	public AuthMenu updateMenu(AuthMenu model) {
		return authMenuDao.updateMenu(model);
	}

	@Override
	public AuthMenu updateIcon(AuthMenu model) {
		return authMenuDao.updateIcon(model);
	}
	
	@Override
	public StateModel deleteMenu(Long menuId) {
		return authMenuDao.deleteMenu(menuId);
	}
	
}
