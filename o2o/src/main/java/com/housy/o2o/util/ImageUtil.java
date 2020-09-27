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
	 * getContextClassLoader()�ǵ�ǰ�̵߳��������
	 * .getResource("")��ʾ�ӵ�ǰclass���ļ�����Ŀ¼��ʼ����
	 * .getResource(��/��)��ʾ�ļ�������·������Ŀ��ǰclasspath�ĸ�·����ʼ����������ʱ��srcĿ¼�¿�ʼ����������ʱ��binĿ¼�¿�ʼ������
	 */
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	private static Logger logger =  LoggerFactory.getLogger(ImageUtil.class);
	
	/**
	 * ��������ͼ��������������ͼƬ�����ֵ·��
	 * 
	 * @param thumbnail
	 * @param targetAddr
	 * @return
	 */
	public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
		// ��ȡ���ظ��������
		String realFileName = getRandomFileName();
		// ��ȡ�ļ�����չ����png,jpg��
		String extension = getFileExtension(thumbnail.getImageName());
		// ���Ŀ��·�������ڣ����Զ�����
		makeDirPath(targetAddr);
		// ��ȡ�ļ��洢�����·��(���ļ���)
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is :" + relativeAddr);
		// ��ȡ�ļ�Ҫ���浽��Ŀ��·��
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
		// ����Thumbnails���ɴ���ˮӡ��ͼƬ
		try {
			Thumbnails.of(thumbnail.getImage()).size(337, 640)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/logo.jpg")), 0.25f)
					.outputQuality(0.9f).toFile(dest);
		} catch (IOException e) {
			logger.error(e.toString());
			throw new RuntimeException("������ͼƬʧ�ܣ�" + e.toString());
		}
		// ����ͼƬ���·����ַ
		return relativeAddr;
	}
	/**
	 * ��CommonsMultipartFileת����File��
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
	//��춂�������ļ���������ֱ���{��ϵ�y�Ԏ���CommonsMultipartFile��
	public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
		
		// ��ȡ���ظ��������
		String realFileName = getRandomFileName();
		// ��ȡ�ļ�����չ����png,jpg��
		String extension = getFileExtension(thumbnail.getImageName());
		// ���Ŀ��·�������ڣ����Զ�����
		makeDirPath(targetAddr);
		// ��ȡ�ļ��洢�����·��(���ļ���)
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is :" + relativeAddr);
		// ��ȡ�ļ�Ҫ���浽��Ŀ��·��
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("basePath is :" + basePath);
		// ����Thumbnails���ɴ���ˮӡ��ͼƬ
		try {
			Thumbnails.of(thumbnail.getImage()).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/zbdx.jpg")), 0.25f)
					.outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			throw new RuntimeException("��������ͼʧ�ܣ�" + e.toString());
		}
		// ����ͼƬ���·����ַ
		return relativeAddr;
	}
	/**
	 * ����Ŀ��·�����漰����Ŀ¼����/home/work/xiangze/xxx.jpg, ��ô home work xiangze
	 * �������ļ��ж����Զ�����
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
		//����Ҏ���r�g��ʽ
		String nowtime = sDateFormat.format(new Date());
		return nowtime + random;
	}
	/**
	 * �@ȡݔ���ļ�������չ��
	 */
	private static String getFileExtension(String fileName) {
	
		return fileName.substring(fileName.lastIndexOf("."));
	}
	/**
	 * store���ļ���·������Ŀ¼��·��
	 * ���storePath���ļ�·����ɾ�����ļ�
	 * ���storePath��Ŀ¼·����ɾ����Ŀ¼�µ������ļ�
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
