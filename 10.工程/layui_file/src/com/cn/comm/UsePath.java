package com.cn.comm;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cn.adonis.entity.ligent.LigentFile;

public class UsePath {
	
	private static final Log log = LogFactory.getLog(UsePath.class);
	// 文件路径
	private static final String FILENAME = "/config/root.properties";

	/**
	 * 目录
	 */
	/* 项目域名 */
	public static String PROJECT_REALM;
	/* 项目名 */
	public static String PROJECT_NAME;
	/* 静态文件路径(css、js) */
	public static String PATH_LIGENTRES;
	/* 系统文件路径(模板文档) */
	public static String PATH_DOCUMENT;
	/* 上传文件路径(用户上传文档储存) */
	public static String PATH_UPLOAD;
	/* 文件云路径(用户上传文档储存) */
	public static String PATH_DOCU;
	
	static {
		try{
			InputStream is = UsePath.class.getResourceAsStream(FILENAME);
			Properties properties = new Properties();
			properties.load(is);
			PROJECT_REALM = properties.getProperty("project_realm");
			PROJECT_NAME = properties.getProperty("project_name");
			
			PATH_LIGENTRES = properties.getProperty("path_ligentres");
			PATH_DOCUMENT = properties.getProperty("path_document");
			PATH_UPLOAD = properties.getProperty("path_upload");
			PATH_DOCU = properties.getProperty("path_douc");
		}catch(Exception ex){
			log.debug("加载系统目录配置文件发生异常："+ex.getMessage());
		}
	}
	
	/* 上传临时路径 */
	public static final String FILE_PATH_TMP = PATH_UPLOAD + "tempFile/";
	/* 邮件文件路径 */
	public static final String FILE_PATH_MAIL = PATH_UPLOAD + LigentFile.FILE_USE_MAIL + "/";
	/* 聊天文件路径 */
	public static final String FILE_PATH_CHAT_MESSAGE = PATH_UPLOAD + LigentFile.FILE_USE_CHAT + "/message/";
	/* 群聊图标 */
	public static final String FILE_PATH_CHAT_GROUP = PATH_UPLOAD + "chat/group/";
	/* 文件云 */
	public static final String FILE_PATH_DOCU = PATH_UPLOAD + "docu/";
	
}
