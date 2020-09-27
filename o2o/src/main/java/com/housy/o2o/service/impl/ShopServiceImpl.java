package com.housy.o2o.service.impl;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housy.o2o.dao.ShopDao;
import com.housy.o2o.dto.ImageHolder;
import com.housy.o2o.dto.ShopExecution;
import com.housy.o2o.entity.Shop;
import com.housy.o2o.enums.ShopStateEnum;
import com.housy.o2o.exceptions.ShopOperationException;
import com.housy.o2o.service.ShopService;
import com.housy.o2o.util.ImageUtil;
import com.housy.o2o.util.PageCalculate;
import com.housy.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculate.claculateRowIndex(pageIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		ShopExecution se = new ShopExecution();
		if(shopList != null) {
			se.setShopList(shopList);
			se.setCount(count);
		} else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}
	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
		//��ֵ�ж�
		if(shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			//��������Ϣ����ֵ
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			//��ӵ�����Ϣ
			int effectedNum = shopDao.insertShop(shop);
			if(effectedNum <= 0)
			throw new ShopOperationException("��������ʧ��");
			else {
				//�惦�DƬ
				if(thumbnail.getImage() != null) {
					try {
						addShopImg(shop, thumbnail);
					}catch(Exception e) {
						throw new ShopOperationException("addShopImg error:" + e.getMessage());
					}
					//���µ�䁵ĈDƬ��ַ��Ϣ
					effectedNum = shopDao.updateShop(shop);
					if(effectedNum <= 0) {
						throw new ShopOperationException("���DƬ��ַʧ��");
					}
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}

	private void addShopImg(Shop shop, ImageHolder thumbnail) throws UnsupportedEncodingException {
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		shop.setShopImg(shopImgAddr);
		
	}
	@Override
	public Shop getByShopId(long shopId) {
		return shopDao.queryByShopId(shopId);
	}
	@Override
	public ShopExecution modifyShop(Shop shop,ImageHolder thumbnail)
	throws ShopOperationException {
		//1���ж��Ƿ���Ҫ����ͼƬ�����µ�ͼƬ��Ѿ�ͼƬɾ������������Ҫһ��ɾ��������
		try {
			if(thumbnail.getImage() != null && thumbnail.getImageName() != null && !"".equals(thumbnail.getImageName())) {
				Shop tempShop = shopDao.queryByShopId(shop.getShopId());
				if(tempShop.getShopImg() != null) {
					ImageUtil.deleteFileOrPath(tempShop.getShopImg());
				}
			}
			addShopImg(shop, thumbnail);
			//2�����µ�����Ϣ
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.updateShop(shop);
			if (effectedNum <= 0) {
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
			} else {
				shop = shopDao.queryByShopId(shop.getShopId());
				return new ShopExecution(ShopStateEnum.SUCCESS,shop);
			}
		} catch(Exception e) {
			throw new ShopOperationException("modifyShop error:" + e.getMessage());
		}
		
		
	}
}
