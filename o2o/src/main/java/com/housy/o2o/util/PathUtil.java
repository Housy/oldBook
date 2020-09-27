package com.housy.o2o.util;

public class PathUtil {
	//獲取文件分隔符
	private static String seperator = System.getProperty("file.separator");
	
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		//如果是win系統則查詢d盤下面，否則例如linux則查詢users路徑下
		if(os.toLowerCase().startsWith("win")) {
			basePath = "D:/images/o2o";
		} else {
			basePath = "/usr/local/housy";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}
	
	public static String getShopImagePath(long shopId) {
		String imagePath = "/upload/images/item/shop/" + shopId + "/";
		return imagePath.replace("/", seperator);
	}
}
