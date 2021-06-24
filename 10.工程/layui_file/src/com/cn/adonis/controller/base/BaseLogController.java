package com.cn.adonis.controller.base;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.adonis.model.PageUnit;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.auth.ince.AuthActionService;
import com.cn.adonis.service.base.ince.BaseLogService;
import com.cn.comm.UseTool;
import com.cn.unit.date.DateConst;
import com.cn.unit.date.DateUtil;

/**
 * 操作日志接口
 * @author 10011037@qq.com
 * 2020-10-10
 */
@Controller
@RequestMapping("/base/log/")
public class BaseLogController {
	
	private static Logger log = Logger.getLogger(BaseLogController.class);
	@Resource
	private AuthActionService authActionService;
	@Resource
	private BaseLogService baseLogService;
	
	private static String MODEL_KEY = "base/log/";
	
	/**
	 * 操作日志
	 */
    @RequestMapping("list")
    public String list(HttpServletRequest request, Model model) throws Exception{
		// 获取Session数据
		String timeFrame = request.getSession().getAttribute("base_log_timeFrame") + "";
    	if(UseTool.isEmpty(timeFrame)){
    		timeFrame = DateUtil.getFixedDiverseTime(null, -1, DateConst.YYYY_MM_DD, DateConst.MONTH) + " - "
					+ DateUtil.getStrByDate(null, DateConst.YYYY_MM_DD);
    	}
    	model.addAttribute("timeFrame", timeFrame);
        return MODEL_KEY + "list";
    }
	
	/**
	 * 分页获取操作日志
	 */
	@RequestMapping(value = "/findByPage", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String findByPage(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String timeFrame = request.getParameter("timeFrame");
    	String searchKey = request.getParameter("searchKey");
    	String page = request.getParameter("page");		// 第几页
    	String limit = request.getParameter("limit");	// 每页几条
    	String field = request.getParameter("field");	// 排序字段
    	String order = request.getParameter("order");	// 排序类型
        // 参数验证
    	log.info("分页获取操作日志:timeFrame["+timeFrame+"],searchKey["+searchKey+"],page["+page+"],limit["+limit+"],field["+field+"],order["+order+"]");
    	if(!UseTool.isLong(page) || !UseTool.isLong(limit)){
    		log.error("分页获取操作日志:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	timeFrame = !UseTool.isEmpty(timeFrame)?timeFrame:UseTool.toStr(request.getSession().getAttribute("base_log_timeFrame"));
    	
		// 添加Session数据
		request.getSession().setAttribute("base_log_timeFrame", timeFrame);
		
    	// 获取登录用户编号
    	Map<String, Object> map = PageUnit.getPageMap(page, limit, searchKey);
    	map.put("timeFrame", timeFrame);
    	StateModel stateModel = PageUnit.sortList(baseLogService.findByPage(map), field, order);
    	String json = UseTool.toJson(stateModel);
    	log.info("分页获取操作日志："+json);
    	return json;
    }
    
}
