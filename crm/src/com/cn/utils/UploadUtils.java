package com.cn.utils;

import java.util.UUID;

public class UploadUtils {
	
	
	/**
	 * 传入文件的名称，返回唯一的名称
	 * @param fileName
	 * @return
	 */
	public static String getUUIDName(String fileName){
		//先查找
		int index = fileName.lastIndexOf(".");
		//截取
		String lastName = fileName.substring(index,fileName.length());
		//唯一字符串
		String uuid = UUID.randomUUID().toString().replace("-", "");
		
		return uuid+lastName;
	}

}
