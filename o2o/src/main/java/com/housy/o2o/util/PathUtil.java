package com.housy.o2o.util;

public class PathUtil {
	//�@ȡ�ļ��ָ���
	private static String seperator = System.getProperty("file.separator");
	
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		//�����winϵ�y�t��ԃd�P���棬��t����linux�t��ԃusers·����
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
