package com.cn.unit.data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.cn.adonis.entity.auth.AuthUser;
import com.cn.comm.UseTool;

/**
 * List排列
 * @author 10011037@qq.com
 * 2016-12-31
 */
public class SortList<E> {
	
	public static void main(String[] args) {
		List<AuthUser> userList = new ArrayList<AuthUser>();
		AuthUser user2 = new AuthUser(2L, "小焕", "1,2,3");
		userList.add(user2);
		AuthUser user1 = new AuthUser(1L, "小升", "1,2,3");
		userList.add(user1);

		System.out.println(UseTool.toJson(userList));
		SortList<AuthUser> sortList = new SortList<AuthUser>();  
	    sortList.Sort(userList, "getUserId", "asc");
		System.out.println(UseTool.toJson(userList));
	}
	
	/**
	 * List集合根据某个字段进行升降序排列
	 * @param list 集合
	 * @param method 排序方法名
	 * @param sort 排序标志(正序[asc],倒序[desc])
	 */
	public void Sort(List<E> list, final String method, final String sort) {
        Collections.sort(list, new Comparator<E>() {
    		@Override
            public int compare(Object a, Object b) {
                int ret = 0;
                try {
                    Method m1 = ((E) a).getClass().getMethod(method, null);
                    Method m2 = ((E) b).getClass().getMethod(method, null);
                    // 正序
                    Object va = m1.invoke(((E) a), null);
                    Object vb = m2.invoke(((E) b), null);
                    if (sort != null && "desc".equals(sort)){
                    	// 倒序
                    	va = vb + ((vb = va) == "" ? "" : "");
                    	/*Object vc = va; va = vb; vb = vc;*/
                    }
                    if(UseTool.isInt(va) && UseTool.isInt(vb)){
                		// 数字类型
                		ret = UseTool.toInt(va).compareTo(UseTool.toInt(vb));
                		// ret = UseTool.toLong(va).intValue()-UseTool.toLong(vb).intValue();
                    }else if(UseTool.isTime(va) && UseTool.isTime(vb)){
                		// 时间类型
                		// ret = DateUtil.getTimeRange(UseTool.toStr(va), UseTool.toStr(vb), DateConst.SECOND);
                		ret = UseTool.toTime(va).compareTo(UseTool.toTime(vb));
                	}else{
                		// 其他类型
                        ret = UseTool.toStr(va).compareTo(UseTool.toStr(vb));
                	}
                } catch (NoSuchMethodException ne) {
                    System.out.println(ne);
                } catch (IllegalAccessException ie) {
                    System.out.println(ie);
                } catch (InvocationTargetException it) {
                    System.out.println(it);
                }
                return ret;
            }
        });
    }
	
}
