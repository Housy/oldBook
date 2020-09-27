package com.housy.o2o.dto;

import java.util.List;

import com.housy.o2o.entity.Shop;
import com.housy.o2o.enums.ShopStateEnum;

public class ShopExecution {
	// ���״̬
	private int state;
	// ״̬��ʶ
	private String stateInfo;
	// ��������
	private int count;
	// ������shop ��ɾ�Ĳ���̵�ʱ���õ�
	private Shop shop;
	// shop�б� ��ѯ�����б��ʱ���õ�
	private List<Shop> shopList;

	public ShopExecution() {

	}

	// ���̲���ʧ�ܵ�ʱ��ʹ�õĹ�����
	public ShopExecution(ShopStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// ���̲����ɹ���ʱ��ʹ�õĹ�����
	public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
	}

	// ���̲����ɹ���ʱ��ʹ�õĹ�����
	public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopList = shopList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
}
