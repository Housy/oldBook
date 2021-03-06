package com.housy.o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.housy.o2o.dto.ImageHolder;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	/**
	 * getContextClassLoader()是当前线程的类加载器
	 * .getResource("")表示从当前class类文件所在目录开始搜索
	 * .getResource(“/”)表示文件的搜索路径从项目当前classpath的根路径开始搜索（编译时从src目录下开始搜索；运行时从bin目录下开始搜索）
	 */
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	private static Logger logger =  LoggerFactory.getLogger(ImageUtil.class);
	
	/**
	 * 处理详情图，并返回新生成图片的相对值路径
	 * 
	 * @param thumbnail
	 * @param targetAddr
	 * @return
	 */
	public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
		// 获取不重复的随机名
		String realFileName = getRandomFileName();
		// 获取文件的扩展名如png,jpg等
		String extension = getFileExtension(thumbnail.getImageName());
		// 如果目标路径不存在，则自动创建
		makeDirPath(targetAddr);
		// 获取文件存储的相对路径(带文件名)
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is :" + relativeAddr);
		// 获取文件要保存到的目标路径
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
		// 调用Thumbnails生成带有水印的图片
		try {
			Thumbnails.of(thumbnail.getImage()).size(337, 640)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/logo.jpg")), 0.25f)
					.outputQuality(0.9f).toFile(dest);
		} catch (IOException e) {
			logger.error(e.toString());
			throw new RuntimeException("创建缩图片失败：" + e.toString());
		}
		// 返回图片相对路径地址
		return relativeAddr;
	}
	/**
	 * 将CommonsMultipartFile转换成File类
	 * 
	 * @param cFile
	 * @return
	 */
	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}

	
	public static void main(String[] args) throws IOException {
		System.out.println(basePath);
		Thumbnails.of(new File("D:/images/o2o/test.jpg")).size(200,200)
		.watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "/zbdx.jpg")),0.25f)
.outputQuality(0.8f).toFile("D:/images/o2o/testnew.jpg");
		
	}
	//由於傳入的是文件流，所以直接調用系統自帶的CommonsMultipartFile，
	public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
		
		// 获取不重复的随机名
		String realFileName = getRandomFileName();
		// 获取文件的扩展名如png,jpg等
		String extension = getFileExtension(thumbnail.getImageName());
		// 如果目标路径不存在，则自动创建
		makeDirPath(targetAddr);
		// 获取文件存储的相对路径(带文件名)
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is :" + relativeAddr);
		// 获取文件要保存到的目标路径
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("basePath is :" + basePath);
		// 调用Thumbnails生成带有水印的图片
		try {
			Thumbnails.of(thumbnail.getImage()).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/zbdx.jpg")), 0.25f)
					.outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		// 返回图片相对路径地址
		return relativeAddr;
	}
	/**
	 * 创建目标路径所涉及到的目录，即/home/work/xiangze/xxx.jpg, 那么 home work xiangze
	 * 这三个文件夹都得自动创建
	 * 
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		// TODO Auto-generated method stub
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if(!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}
	private static String getRandomFileName() {
		int random = r.nextInt(89999) + 10000;
		//返回規定時間格式
		String nowtime = sDateFormat.format(new Date());
		return nowtime + random;
	}
	/**
	 * 獲取輸入文件名的拓展名
	 */
	private static String getFileExtension(String fileName) {
	
		return fileName.substring(fileName.lastIndexOf("."));
	}
	/**
	 * store是文件的路径还是目录的路径
	 * 如果storePath的文件路径则删除该文件
	 * 如果storePath是目录路径则删除该目录下的所有文件
	 * 
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
		if(fileOrPath.exists()) {
			if(fileOrPath.isDirectory()) {
				File files[] = fileOrPath.listFiles();
				for(int i = 0; i < files.length; i ++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
}
