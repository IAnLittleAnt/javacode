package com.cn.adonis.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.adonis.entity.file.FileDocu;
import com.cn.adonis.service.auth.ince.AuthMenuService;
import com.cn.adonis.service.auth.ince.AuthUserService;
import com.cn.adonis.service.file.ince.FileDocuService;
import com.cn.comm.UseTool;
import com.cn.unit.log.ArchivesLog;

/**
 * 系统信息管理
 * @author 10011037@qq.com
 * 2017-06-30
 */
@Controller
@RequestMapping
public class IndexController {
	
	private static Logger log = Logger.getLogger(IndexController.class);
	@Resource
	private AuthUserService authUserService;
	@Resource
	private AuthMenuService authMenuService;
	@Resource
	private FileDocuService fileDocuService;
	/**
	 * 主页面
	 */
    @ArchivesLog(content = "查看主页面")
    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) throws Exception{
    	// 获取登录用户编号
        return "home/index";
    }
    
    /**
	 * 获取所有孩子节点
	 */
    @ArchivesLog(content = "文件管理")
	@RequestMapping(value = "/index/findAllChilds", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String findAllChilds(HttpServletRequest request, HttpServletResponse response) {
		String docuId = request.getParameter("docuId");
		String docuUse = request.getParameter("docuUse");
		String sortMode = request.getParameter("sortMode");
		List<FileDocu> docuList = null;
		if (UseTool.isLong(docuId)) {
			FileDocu docu = new FileDocu(UseTool.toLong(docuId), UseTool.toInt(docuUse), UseTool.toInt(sortMode), null);
			docuList = fileDocuService.findAllChilds(docu);
		}
		String json = UseTool.toJson(docuList);
		log.info("获取所有孩子节点:"+json);
		return json;
	}
}
