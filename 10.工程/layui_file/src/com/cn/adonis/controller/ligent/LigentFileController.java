package com.cn.adonis.controller.ligent;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cn.adonis.entity.ligent.LigentFile;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.ligent.ince.LigentFileService;
import com.cn.comm.UseSession;
import com.cn.comm.UseTool;
import com.cn.comm.UsePath;
import com.cn.unit.img.ImageReduceUtil;

/**
 * 文件信息接口
 * @author 10011037@qq.com
 * 2020-03-06
 */
@Controller
@RequestMapping("/ligent/file/")
public class LigentFileController {
	
	private static Logger log = Logger.getLogger(LigentFileController.class);
	@Resource
	private LigentFileService ligentFileService;
	
	private static String MODEL_KEY = "ligent/file/";
	
	
    /**
	 * 根据用途上传文件
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String uploadFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		// 取得客户端传来的值
    	String fileUse = request.getParameter("use");
		// 参数校验
    	log.info("根据用途上传文件:fileUse["+fileUse+"]");
    	if(file.isEmpty() || UseTool.isEmpty(fileUse)) {
    		log.error("根据用途上传文件:参数错误");
			return UseTool.toJson(new StateModel(false));
    	}
    	// 文件完整路径
    	String filePath = "";
    	// 获取文件名
    	String fileTitle = file.getOriginalFilename();
    	// 获取文件后缀
    	String suffix = UseTool.getSuffix(fileTitle);
    	if(UseTool.isEmpty(suffix)){
        	log.error("根据用途上传文件:文件格式不正确["+fileTitle+"]");
    		return UseTool.toJson(new StateModel("文件格式不正确"));
		}
    	// 文件上传临时路径
		String rootPath = request.getSession().getServletContext().getRealPath("/");
        String tempPath = null;
    	// 获取文件路径
		if(fileUse.equals(LigentFile.FILE_USE_MAIL)){
			// 邮件上传
			filePath = UsePath.FILE_PATH_MAIL + System.currentTimeMillis() + "." + suffix;
		}else if(fileUse.equals(LigentFile.FILE_USE_CHAT)){
			// 邮件上传
			filePath = UsePath.FILE_PATH_CHAT_MESSAGE + System.currentTimeMillis() + "." + suffix;
		}else{
        	log.error("根据用途上传文件:参数错误");
    		return UseTool.toJson(new StateModel("参数错误"));
		}
		tempPath = rootPath + filePath;
		filePath = UsePath.PROJECT_REALM + filePath;
		
        try {
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(tempPath)));
            stream.write(bytes);
            stream.close();
            if(!UseTool.isEmpty(UseTool.isPicture(fileTitle))){
                // 压缩图片
                ImageReduceUtil imageReduce = new ImageReduceUtil(tempPath, tempPath, 960, 1);
                imageReduce.resizeFix();
            }
        } catch (IOException e) {
        	log.error("根据用途上传文件:文件上传失败"+e);
    		return UseTool.toJson(new StateModel("图片上传失败"));
        }
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	LigentFile ligentFile = new LigentFile(fileUse, fileTitle, filePath, loginUserId);
    	ligentFile = ligentFileService.insertFile(ligentFile);
    	StateModel stateModel = new StateModel(false);
    	if(ligentFile != null){
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("src", ligentFile.getFilePath());
    		map.put("title", ligentFile.getFileTitle());
    		stateModel = new StateModel(map);
    	}
		String json = UseTool.toJson(stateModel);
		log.info("根据用途上传文件:"+json);
		return json;
	}
	
}
