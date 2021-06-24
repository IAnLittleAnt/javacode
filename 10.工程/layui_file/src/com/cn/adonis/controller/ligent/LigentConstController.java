package com.cn.adonis.controller.ligent;

import java.util.List;
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

import com.cn.adonis.entity.auth.AuthAction;
import com.cn.adonis.entity.ligent.LigentConst;
import com.cn.adonis.model.PageUnit;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.auth.ince.AuthActionService;
import com.cn.adonis.service.ligent.ince.LigentConstService;
import com.cn.comm.UseConst;
import com.cn.comm.UseSession;
import com.cn.comm.UseTool;
import com.cn.unit.log.ArchivesLog;

/**
 * 常量管理接口
 * @author 10011037@qq.com
 * 2017-06-30
 */
@Controller
@RequestMapping("/ligent/const/")
public class LigentConstController {
	
	private static Logger log = Logger.getLogger(LigentConstController.class);
	@Resource
	private LigentConstService ligentConstService;
	@Resource
	private AuthActionService authActionService;
	
	private static String MODEL_KEY = "ligent/const/";
	
	/**
	 * 常量列表
	 */
    @ArchivesLog(content = "查看常量信息")
    @RequestMapping("list")
    public String list(HttpServletRequest request, Model model) throws Exception{
    	// 获取登录常量编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	List<AuthAction> actionList = authActionService.findByUser(loginUserId, request.getServletPath());
    	model.addAttribute("actionList", actionList);
        return MODEL_KEY + "list";
    }
	
	/**
	 * 分页获取常量信息
	 */
	@RequestMapping(value = "/findByPage", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String findByPage(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String searchKey = request.getParameter("searchKey");
    	String page = request.getParameter("page");		// 第几页
    	String limit = request.getParameter("limit");	// 每页几条
    	String field = request.getParameter("field");	// 排序字段
    	String order = request.getParameter("order");	// 排序类型
        // 参数验证
    	log.info("分页获取常量信息:searchKey["+searchKey+"],page["+page+"],limit["+limit+"],field["+field+"],order["+order+"]");
    	if(!UseTool.isLong(page) || !UseTool.isLong(limit)){
    		log.error("分页获取常量信息:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	Map<String, Object> map = PageUnit.getPageMap(page, limit, searchKey);
    	StateModel stateModel = PageUnit.sortList(ligentConstService.findByPage(map), field, order);
    	String json = UseTool.toJson(stateModel);
    	log.info("分页获取常量信息："+json);
    	return json;
    }
    
	/**
	 * 根据编号获取常量信息
	 */
    @RequestMapping(value = "fainById", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String fainById(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String constId = request.getParameter("constId");
        // 参数验证
    	log.info("根据编号获取常量信息:constId["+constId+"]");
    	if(!UseTool.isLong(constId)){
    		log.error("根据编号获取常量信息:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	String json = UseTool.toJson(new StateModel(ligentConstService.fainById(UseTool.toLong(constId))));
    	log.info("根据编号获取常量信息："+json);
    	return json;
    }
    
	/**
	 * 新增页面
	 */
    @RequestMapping("add")
    public String add(HttpServletRequest request, Model model) throws Exception{
        return MODEL_KEY + "add";
    }
	
	/**
	 * 新增常量信息
	 */
    @ArchivesLog(content = "新增常量信息")
    @RequestMapping(value = "insertConst", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String insertConst(LigentConst model, HttpServletRequest request, HttpServletResponse response) {
        // 参数验证
    	log.info("新增常量信息:"+UseTool.toJson(model));
    	if(!UseTool.isInt(model.getConstKey()) || UseTool.isEmpty(model.getConstName()) || UseTool.isEmpty(model.getConstValue())){
    		log.error("新增常量信息:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	String json = UseTool.toJson(ligentConstService.insertConst(model));
    	log.info("新增常量信息："+json);
    	return json;
    }
    
	/**
	 * 编辑页面
	 */
    @RequestMapping("edit")
    public String edit(HttpServletRequest request, Model model) throws Exception{
    	String constId = request.getParameter("constId");
    	model.addAttribute("ligentConst", ligentConstService.fainById(UseTool.toLong(constId)));
        return MODEL_KEY + "edit";
    }
	
	/**
	 * 编辑常量值
	 */
    @ArchivesLog(content = "编辑常量信息")
    @RequestMapping(value = "updateValue", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String updateValue(LigentConst model, HttpServletRequest request, HttpServletResponse response) {
        // 参数验证
    	log.info("编辑常量值:"+UseTool.toJson(model));
    	if(!UseTool.isInt(model.getConstKey())||UseTool.isEmpty(model.getConstValue())) {
    		log.error("编辑常量值:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	String json = UseTool.toJson(new StateModel(ligentConstService.updateValue(model)));
    	log.info("编辑常量值："+json);
    	return json;
    }

	/**
	 * 删除常量信息
	 */
    @ArchivesLog(content = "删除常量信息")
    @RequestMapping(value = "deleteConst", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String deleteConst(HttpServletRequest request, HttpServletResponse response) {
	    // 获取参数数据
    	String constId = request.getParameter("keyIds");
        // 参数验证
    	log.info("删除常量信息:constId["+constId+"]");
    	if(!UseTool.isLong(constId)) {
    		log.error("删除常量信息:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	String json = UseTool.toJson(ligentConstService.deleteConst(UseTool.toLong(constId)));
    	log.info("删除常量信息："+json);
    	return json;
    }
	
	/**
	 * 是否启动登录页面验证码
	 */
    @ArchivesLog(content = "查看登录页面验证码状态")
    @RequestMapping("view")
    public String view(HttpServletRequest request, Model model) throws Exception{
    	int constKey = UseConst.DEFAULT_LOGIN_CODE;
		model.addAttribute("constKey", constKey);
		model.addAttribute("isDiringCode", ligentConstService.findByKey(constKey));
        return MODEL_KEY + "view";
    }
    
}
