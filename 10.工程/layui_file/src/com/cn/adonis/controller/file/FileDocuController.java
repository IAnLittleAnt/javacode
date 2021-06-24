package com.cn.adonis.controller.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.adonis.entity.auth.AuthAction;
import com.cn.adonis.entity.file.FileDocu;
import com.cn.adonis.model.state.StateModel;
import com.cn.adonis.service.auth.ince.AuthActionService;
import com.cn.adonis.service.file.ince.FileDocuService;
import com.cn.adonis.service.file.ince.FileTypeService;
import com.cn.comm.UsePath;
import com.cn.comm.UseSession;
import com.cn.comm.UseTool;
import com.cn.unit.log.ArchivesLog;

/**
 * 文件管理接口
 * @author 10011037@qq.com
 * 2017-08-08
 */
@Controller
@RequestMapping("/file/docu/")
public class FileDocuController {
	
	private static Logger log = Logger.getLogger(FileDocuController.class);
	@Resource
	private FileDocuService fileDocuService;
	@Resource
	private FileTypeService fileTypeService;
	@Resource
	private AuthActionService authActionService;
	
	private static String MODEL_KEY = "file/";
	
	/**
	 * 共享文件云
	 */
    @RequestMapping("view")
    public String view(HttpServletRequest request, Model model) throws Exception{
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	List<AuthAction> actionList = authActionService.findByUser(loginUserId, request.getServletPath());
    	model.addAttribute("actionList", actionList);
    	// 文件用途（1私人文件，2共享文件）
    	Integer docuUse = 2;
    	model.addAttribute("docuUse", docuUse);
    	// 获取所有孩子节点
    	FileDocu docu = new FileDocu(0L, docuUse, 0, loginUserId);
    	model.addAttribute("docuList", fileDocuService.findAllChild(docu));
    	// 获取文件夹对象
    	model.addAttribute("folder", fileTypeService.fainByFolder());
        return MODEL_KEY + "view";
    }
	
	/**
	 * 私人文件云
	 */
    @RequestMapping("visus")
    public String visus(HttpServletRequest request, Model model) throws Exception{
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	List<AuthAction> actionList = authActionService.findByUser(loginUserId, request.getServletPath());
    	model.addAttribute("actionList", actionList);
    	Integer docuUse = 1;
    	model.addAttribute("docuUse", docuUse);
    	// 获取所有孩子节点
    	FileDocu docu = new FileDocu(0L, docuUse, 0, loginUserId);
    	model.addAttribute("docuList", fileDocuService.findAllChild(docu));
    	// 获取文件夹对象
    	model.addAttribute("folder", fileTypeService.fainByFolder());
        return MODEL_KEY + "visus";
    }
	
	/**
	 * 文件云盘
	 */
    @RequestMapping("cloud")
    public String cloud(HttpServletRequest request, Model model) throws Exception{
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	List<AuthAction> actionList = authActionService.findByUser(loginUserId, request.getServletPath());
    	model.addAttribute("actionList", actionList);
    	Integer docuUse = 1;
    	model.addAttribute("docuUse", docuUse);
    	/*// 获取所有孩子节点
    	FileDocu docu = new FileDocu(0L, docuUse, 0, loginUserId);
    	model.addAttribute("docuList", fileDocuService.findAllChild(docu));*/
    	// 获取文件夹对象
    	model.addAttribute("folder", fileTypeService.fainByFolder());
        return MODEL_KEY + "cloud";
    }
    
    /**
	 * 根据编号获取文件信息
	 */
	@RequestMapping(value = "/fainById", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String fainById(HttpServletRequest request, HttpServletResponse response) {
		String docuId = request.getParameter("docuId");
		log.info("根据编号获取文件信息:docuId["+docuId+"]");
		if(!UseTool.isLong(docuId)){
    		log.error("根据编号获取文件信息:参数错误");
			return UseTool.toJson(new StateModel(false));
		}
		String json = UseTool.toJson(new StateModel(fileDocuService.fainById(UseTool.toLong(docuId))));
		log.info("根据编号获取文件信息:"+json);
		return json;
	}
    
    /**
	 * 获取所有孩子节点
	 */
    @ArchivesLog(content = "文件管理")
	@RequestMapping(value = "/findAllChild", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String findAllChild(HttpServletRequest request, HttpServletResponse response) {
		String docuId = request.getParameter("docuId");
		String docuUse = request.getParameter("docuUse");
		String sortMode = request.getParameter("sortMode");
		List<FileDocu> docuList = null;
		if(UseTool.isLong(docuId)){
	    	// 获取登录用户编号
	    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
	    	FileDocu docu = new FileDocu(UseTool.toLong(docuId), UseTool.toInt(docuUse), UseTool.toInt(sortMode), loginUserId);
	    	docuList = fileDocuService.findAllChild(docu);
		}
		String json = UseTool.toJson(docuList);
		log.info("获取所有孩子节点:"+json);
		return json;
	}
    
    /**
	 * 获取所有孩子节点
	 */
    @ArchivesLog(content = "获取所有的文件夹")
	@RequestMapping(value = "/findAllFile", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String findAllFile(HttpServletRequest request, HttpServletResponse response) {
		String docuId = request.getParameter("docuId");
		String docuUse = request.getParameter("docuUse");
		String sortMode = request.getParameter("sortMode");
		List<FileDocu> docuList = null;
		List<FileDocu> docuFilesList = new ArrayList<FileDocu>();
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
		if(UseTool.isLong(docuId)){

	    	FileDocu docu = new FileDocu(UseTool.toLong(docuId), UseTool.toInt(docuUse), UseTool.toInt(sortMode), loginUserId);
	    	docuList = fileDocuService.findAllChild(docu);
	    	docuFilesList.addAll(docuList);
		}
		extracted(docuUse, sortMode, docuList, docuFilesList, loginUserId);
		String json = UseTool.toJson(docuFilesList);
		log.info("获取所有孩子节点:"+json);
		return json;
	}

	private List<FileDocu> extracted(String docuUse, String sortMode, List<FileDocu> docuList,
			List<FileDocu> docuFilesList, Long loginUserId) {
		if (docuList.size() > 0) {
			// 递归查询数据
			 for (FileDocu fileDocu : docuList) {
				if(fileDocu.getTypeId()==1) {
					FileDocu docu = new FileDocu(UseTool.toLong(fileDocu.getDocuId()), UseTool.toInt(docuUse), UseTool.toInt(sortMode), loginUserId);
			    	docuList = fileDocuService.findAllChild(docu);
			    	docuFilesList.addAll(docuList);
			    	extracted(docuUse, sortMode, docuList, docuFilesList, loginUserId);
				}
			}
		}
		return docuFilesList;
	}
    
    /**
	 * 获取所有父级节点
	 */
	@RequestMapping(value = "/findAllPater", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String findAllPater(HttpServletRequest request, HttpServletResponse response) {
		String docuId = request.getParameter("docuId");
		List<FileDocu> docuList = null;
		if(UseTool.isLong(docuId)){
	    	// 获取登录用户编号
	    	docuList = fileDocuService.findAllPater(UseTool.toLong(docuId));
		}
		String json = UseTool.toJson(docuList);
		log.info("获取所有父级节点:"+json);
		return json;
	}
    
    /**
	 * 创建文件夹
	 */
    @ArchivesLog(content = "创建文件夹")
	@RequestMapping(value = "/insertFolder", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String insertFolder(HttpServletRequest request, HttpServletResponse response) {
		String paterId = request.getParameter("paterId");
		String docuUse = request.getParameter("docuUse");
		String docuName = request.getParameter("docuName");
		log.info("创建文件夹:paterId["+paterId+"],docuUse["+docuUse+"],docuName["+docuName+"]");
		if(!UseTool.isLong(paterId) || !UseTool.isInt(docuUse) || UseTool.isEmpty(docuName)){
    		log.error("创建文件夹:参数错误");
			return UseTool.toJson(new StateModel(false));
		}
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
		FileDocu docu = new FileDocu(UseTool.toLong(paterId), UseTool.toInt(docuUse), docuName, loginUserId);
		String json = UseTool.toJson(new StateModel(fileDocuService.insertFolder(docu)));
		log.info("创建文件夹:"+json);
		return json;
	}
    
    /**
	 * 文件重命名
	 */
    @ArchivesLog(content = "文件重命名")
	@RequestMapping(value = "/updateName", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String updateName(HttpServletRequest request, HttpServletResponse response) {
		String docuId = request.getParameter("docuId");
		String docuName = request.getParameter("docuName");
		log.info("文件重命名:docuId["+docuId+"],docuName["+docuName+"]");
		if(!UseTool.isLong(docuId) || UseTool.isEmpty(docuName)){
    		log.error("文件重命名:参数错误");
			return UseTool.toJson(new StateModel(false));
		}
		FileDocu docu = new FileDocu(UseTool.toLong(docuId), null, docuName);
		String json = UseTool.toJson(new StateModel(fileDocuService.updateName(docu)));
		log.info("文件重命名:"+json);
		return json;
	}
    
    /**
	 * 修改文件描述内容
	 */
    @ArchivesLog(content = "修改文件描述内容")
	@RequestMapping(value = "/updateDesc", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String updateDesc(HttpServletRequest request, HttpServletResponse response) {
		String docuId = request.getParameter("docuId");
		String docuDesc = request.getParameter("docuDesc");
		log.info("修改文件描述内容:docuId["+docuId+"],docuDesc["+docuDesc+"]");
		if(!UseTool.isLong(docuId) || UseTool.isEmpty(docuDesc)){
    		log.error("修改文件描述内容:参数错误");
			return UseTool.toJson(new StateModel(false));
		}
		FileDocu docu = new FileDocu(UseTool.toLong(docuId), docuDesc);
		String json = UseTool.toJson(new StateModel(fileDocuService.updateDesc(docu)));
		log.info("修改文件描述内容:"+json);
		return json;
	}
    
    /**
	 * 删除文件
	 */
    @ArchivesLog(content = "删除文件")
	@RequestMapping(value = "/deleteDocu", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String deleteDocu(HttpServletRequest request, HttpServletResponse response) {
		String docuId = request.getParameter("docuId");
		log.info("删除文件:docuId["+docuId+"]");
		if(!UseTool.isLong(docuId)){
    		log.error("删除文件:参数错误");
			return UseTool.toJson(new StateModel(false));
		}
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
		FileDocu docu = new FileDocu(UseTool.toLong(docuId), loginUserId);
		String json = UseTool.toJson(fileDocuService.deleteDocu(docu));
		log.info("删除文件:"+json);
		return json;
	}
	
	/**
	 * 移动文件
	 */
    @ArchivesLog(content = "移动文件")
	@RequestMapping(value = "/updatePater", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String updatePater(HttpServletRequest request, HttpServletResponse response) {
		// 文件编号
		String docuId = request.getParameter("docuId");
    	// 移动到那个文件下面
    	String paterId = request.getParameter("paterId");
    	log.info("移动文件：docuId["+docuId+"],paterId["+paterId+"]");
		// 参数校验
		if(!UseTool.isLong(docuId) || !UseTool.isLong(paterId)){
        	log.error("移动文件:参数错误");
			return UseTool.toJson(new StateModel(false));
		}
		FileDocu docu = new FileDocu(UseTool.toLong(docuId), UseTool.toLong(paterId), null);
		String json = UseTool.toJson(fileDocuService.updatePater(docu));
		log.info("移动文件:"+json);
		return json;
	}
    
    /**
	 * 更新下载数量
	 */
    @ArchivesLog(content = "下载文件")
	@RequestMapping(value = "/updateCount", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String updateCount(HttpServletRequest request, HttpServletResponse response) {
		String docuId = request.getParameter("docuId");
		log.info("更新下载数量:docuId["+docuId+"]");
		if(!UseTool.isLong(docuId)){
    		log.error("更新下载数量:参数错误");
			return UseTool.toJson(new StateModel(false));
		}
		StateModel state = fileDocuService.updateCount(docuId);
		state.setData(fileDocuService.fainById(UseTool.toLong(docuId)));
		String json = UseTool.toJson(state);
		log.info("更新下载数量:"+json);
		return json;
	}
    
    /**
	 * 上传文件页面
	 */
    @RequestMapping("upload")
    public String upload(HttpServletRequest request, Model model) throws Exception{
    	String paterId = request.getParameter("paterId");
    	String docuUse = request.getParameter("docuUse");
    	model.addAttribute("docuUse", docuUse);
    	model.addAttribute("paterId", paterId);
        return MODEL_KEY + "upload";
    }
    
    /**
	 * 上传文件
	 */
	/*@RequestMapping(value = "/insertFile", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String insertFile(HttpServletRequest request, HttpServletResponse response) {
		String paterId = request.getParameter("paterId");
		String docuName = request.getParameter("docuName");
		log.info("创建文件夹:paterId["+paterId+"],docuName["+docuName+"]");
		if(!UseTool.isLong(paterId) || UseTool.isEmpty(docuName)){
    		log.error("创建文件夹:参数错误");
			return UseTool.toJson(new StateModel(false));
		}
    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	Long paterId, String docuName, String docuPath, String docuSuffix, 
		Long docuSize, String docuSizeUnit, String docuDesc, Long createUserId
		FileDocu docu = new FileDocu(UseTool.toLong(paterId), docuName, loginUserId);
		String json = UseTool.toJson(new StateModel(fileDocuService.insertFile(docu)));
		log.info("创建文件夹:"+json);
		return json;
	}*/
	
	/**
	 * 上传文档
	 */
    @ArchivesLog(content = "上传文件")
	@RequestMapping(value = "/insertFile", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String insertFile(HttpServletRequest request, HttpServletResponse response) throws Exception { 
	    // 设置request编码，主要是为了处理普通输入框中的中文问题
        request.setCharacterEncoding("UTF-8");
        // 这里对request进行封装，RequestContext提供了对request多个访问方法
        RequestContext requestContext = new ServletRequestContext(request);
        
        // 父级编号,上传人编号,返回json数据
        // String paterId = request.getParameter("paterId");
        String paterId = null, docuUse = null;
		String docuName = "", docuPath = "", docuSuffix = "", docuSizeUnit = "";
		Long docuSize = null;
		
        // 判断表单是否是Multipart类型的。这里可以直接对request进行判断
        if (!FileUpload.isMultipartContent(requestContext)) {
        	return UseTool.toJson(new StateModel("文件错误!"));
        }
        
        // 临时路径
        String rootPath = request.getSession().getServletContext().getRealPath("/");
    	String tmpPath = rootPath + UsePath.FILE_PATH_TMP;
    	File tmp = new File(tmpPath);
        if (!tmp.exists()) {
        	tmp.mkdirs();
        }
    	
        // 设置文件的缓存路径
    	DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(tmpPath));
        
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置上传文件大小的上限，-1表示无上限
        upload.setSizeMax(50 * 1024 * 1024);
        List<FileItem> items = new ArrayList<FileItem>();
        try {
            // 上传文件，并解析出所有的表单字段，包括普通字段和文件字段
            items = upload.parseRequest(request);
        } catch (FileUploadException e1) {
            log.error("文件上传发生错误" + e1.getMessage());
        	return UseTool.toJson(new StateModel("文件上传发生错误"));
        }
        // 下面对每个字段进行处理，分普通字段和文件字段
		Iterator<FileItem> it = items.iterator();
		
		// 一次循环获取字段参数
        while (it.hasNext()) {
            DiskFileItem fileItem = (DiskFileItem) it.next();
        	// 普通字段
            if (fileItem.isFormField()) {
            	String fieldName = fileItem.getFieldName();
            	String str = new String(fileItem.getString().getBytes("iso8859-1"), "utf-8");
            	log.info(fileItem.getFieldName() + "\t" + fileItem.getName() + "\t" + str);
				if ("paterId".equals(fieldName)) {
					paterId = str;
				}else if ("docuUse".equals(fieldName)) {
					docuUse = str;
				}
            }
        }
        if(!UseTool.isLong(paterId) || !UseTool.isInt(docuUse)){
        	log.error("参数不正确!");
        	return UseTool.toJson(new StateModel(false));
        }

    	// 获取登录用户编号
    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
		
        // 二次循环上传文件
        it = items.iterator();
        while (it.hasNext()) {
            DiskFileItem fileItem = (DiskFileItem) it.next();
        	// 普通字段
            if (fileItem.isFormField()) {
                continue;
            }
            
            // 文件
        	log.info(fileItem.getFieldName() + "\t" + fileItem.getName() + "\t"
                    + fileItem.isInMemory() + "\t" + fileItem.getContentType() + "\t" + fileItem.getSize());
            
        	// 保存文件，其实就是把缓存里的数据写到目标路径下
            if(UseTool.isEmpty(fileItem.getName()) || fileItem.getSize() <= 0){
            	log.error("文件损坏:[" + fileItem.getName()+"]");
            	return UseTool.toJson(new StateModel("文件已损坏!"));
            }
        	// 获取文件名称
            docuName = fileItem.getName();
            // 获取后缀
            if (!UseTool.isEmpty(docuName) && docuName.indexOf(".") >= 0) {
            	docuSuffix = docuName.substring(
            			docuName.lastIndexOf(".") + 1, docuName.length()).toLowerCase();
            }
    		if(UseTool.isEmpty(docuSuffix)){
            	log.error("文件格式不正确:[" + docuName+"]");
            	return UseTool.toJson(new StateModel("文件格式不正确!"));
    		}
    		// 过滤jsp和jspx文件
    		if(docuSuffix=="jsp"||docuSuffix=="jspx"){
            	log.error("文件格式不正确:[" + docuName+"]");
            	return UseTool.toJson(new StateModel("文件格式不正确!"));
    		}
    		// 获取文件大小
    		docuSize = fileItem.getSize();
    		docuSizeUnit = UseTool.formetFileSize(docuSize);
            
        	// 文件存放路径
    		docuPath = UsePath.PATH_DOCU + loginUserId + "/";
            File dir = new File(docuPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            docuPath += System.currentTimeMillis() + "." + docuSuffix;
            
            try {
                fileItem.write(new File(docuPath));
            } catch (Exception e) {
            	log.error("文件上传时发生异常:[" + e + "]");
            	return UseTool.toJson(new StateModel("系统繁忙,请稍后再试!"));
            }
        }
        
		FileDocu docu = new FileDocu(UseTool.toLong(paterId), UseTool.toInt(docuUse), docuName, docuPath, docuSuffix, docuSize, docuSizeUnit, null, loginUserId);
		String json = UseTool.toJson(new StateModel(fileDocuService.insertFile(docu)));
        
        // 设置响应码
        response.setStatus(200);
		return json;
	}
    
    /**
     * 文件下载功能
     */
    @RequestMapping("/download")
    public void download(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String docuId = request.getParameter("docuId");
    	log.info("文件下载功能:docuId["+docuId+"]");
    	FileDocu fileDocu = fileDocuService.fainById(UseTool.toLong(docuId));
        // 获取输入流  
        @SuppressWarnings("resource")
		InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileDocu.getDocuPath())));

        String fileName = fileDocu.getDocuName();
        fileName = new String(fileName.getBytes(),"ISO-8859-1");
        
        // 设置编码格式
        response.setCharacterEncoding("UTF-8");
        // 设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);    
        // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
    } 
    
    /**
     * 打包Zip并下载
     */
    @ArchivesLog(content = "打包下载文件")
    @RequestMapping("/downloadZips")
    public void downloadZips(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String docuId = request.getParameter("docuId");
    	// 获取当前文件夹
    	FileDocu docu = fileDocuService.fainById(UseTool.toLong(docuId));
    	// 文件名
    	String docuName = docu.getDocuName();
    	if(UseTool.isEmpty(docuName)){
    		docuName = System.currentTimeMillis()+"";
        }
    	docuName = docuName.replaceAll(" ", "_");
    	docuName += ".zip";
    	docuName = new String(docuName.getBytes(),"ISO-8859-1");
        // 设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + docuName);    
        // 设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");
    	
    	List<FileDocu> revertList = new ArrayList<FileDocu>();
    	// 获取目录下面的所有文件

    	Long loginUserId = UseTool.toLong(request.getSession().getAttribute(UseSession.SESSIONNAME_LOGINUSERID));
    	docu.setCreateUserId(loginUserId);
    	List<FileDocu> docuList = fileDocuService.findAllChild(docu);
		if(docuList != null && docuList.size() > 0){
	    	String docuIds = "";
			for (FileDocu item : docuList) {
				if(item.getTypeId().intValue() != 1){
					revertList.add(item);
					docuIds+=item.getDocuId()+",";
				}
			}
			if(!UseTool.isEmpty(docuIds)){
				// 更新下载数量
				fileDocuService.updateCount(docuIds);
			}
		}
    	
		// 循环加入压缩文件
    	ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
		for (FileDocu item : revertList) {
			File f = new File(item.getDocuPath());
			zos.putNextEntry(new ZipEntry(item.getDocuName()));
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
			} catch (Exception e) {
				continue;
			}
		    byte[] buffer = new byte[1024];
		    int r = 0;
		    while ((r = fis.read(buffer)) != -1) {
		    	zos.write(buffer, 0, r);
		    }
		    fis.close();
		}
        zos.flush();
        zos.close();
    } 
    
}
