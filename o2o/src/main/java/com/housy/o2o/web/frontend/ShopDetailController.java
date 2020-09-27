package com.housy.o2o.web.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.housy.o2o.dto.ProductExecution;
import com.housy.o2o.entity.Product;
import com.housy.o2o.entity.ProductCategory;
import com.housy.o2o.entity.Shop;
import com.housy.o2o.service.ProductCategoryService;
import com.housy.o2o.service.ProductService;
import com.housy.o2o.service.ShopService;
import com.housy.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/frontend")
public class ShopDetailController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;
	
	/**
	 * ������Ʒid��ȡ��Ʒ����
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listshopdetailpageinfo",method=RequestMethod.GET) 
	@ResponseBody
	private Map<String, Object> listShopDetailPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		Shop shop = null;
		List<ProductCategory> productCategoryList = null;
		//��ֵ�ж�
		if(shopId != -1) {
			//����shopId��ȡ��Ʒ��Ϣ��������Ʒ����ͼ�б�
			shop = shopService.getByShopId(shopId);
			productCategoryList = productCategoryService.getProductCategoryList(shopId);
			modelMap.put("shop", shop);
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;
	}
	
	/**
	 * ���ݲ�ѯ������ҳ���õ��������������Ʒ
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listproductsbyshop",method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductsByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//��ȡҳ��
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		//��ȡһҳ��Ҫ��ʾ������
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		//��ȡ����ID
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		//��ֵ�ж�
		if((pageIndex > -1) && (pageSize > -1) && (shopId) > -1) {
			//���Ի�ȡ����id
			long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
			//���Ի�ȡģ�����ҵ���Ʒ��
			String productName = HttpServletRequestUtil.getString(request, "productName");
			//��ϲ�ѯ����
			Product productCondition = compactProductCondition4Search(shopId, productCategoryId, productName);
			//���մ���Ĳ�ѯ�����Լ���ҳ��Ϣ������Ӧ����Ʒ�б��Լ�����
			ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
			modelMap.put("productList", pe.getProductList());
			modelMap.put("count", pe.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}
		return modelMap;
	}
	
	/**
	 * ��ϲ�ѯ����������������װ��ProductCondition�������淵��
	 * @param shopId
	 * @param productCategoryId
	 * @param productName
	 * @return
	 */
	private Product compactProductCondition4Search(long shopId, long productCategoryId,
			String productName) {
		Product productCondition = new Product();
		Shop shop = new Shop();
		shop.setShopId(shopId);
		productCondition.setShop(shop);
		if (productCategoryId != -1L) {
			//��ѯĳ����Ʒ����������Ʒ�б�
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			productCondition.setProductCategory(productCategory);
		} 
		if (productName != null) {
			//��ѯ�����������productName�ĵ����б�
			productCondition.setProductName(productName);
		}
		//ֻ����ѡ��״̬Ϊ�ϼܵ���Ʒ
		productCondition.setEnableStatus(1);
		return productCondition;
	}
}
