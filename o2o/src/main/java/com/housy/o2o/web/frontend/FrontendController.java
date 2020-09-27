package com.housy.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/frontend")
public class FrontendController {
	/**
	 * ��ҳ·��
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	private String index() {
		return "/frontend/index";
	}
	/**
	 * �����б�·��
	 * @return
	 */
	@RequestMapping(value = "/shoplist", method = RequestMethod.GET)
	private String shopList() {
		return "/frontend/shoplist";
	}
	/**
	 * ��������ҳ
	 * @return
	 */
	@RequestMapping(value = "/shopdetail", method = RequestMethod.GET)
	private String shopDetail() {
		return "/frontend/shopdetail";
	}
	@RequestMapping(value = "/productdetail", method = RequestMethod.GET)
	private String productDetail() {
		return "/frontend/productdetail";
	}
}
