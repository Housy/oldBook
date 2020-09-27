package com.housy.o2o.util;

public class PageCalculate {
	public static int claculateRowIndex(int pageIndex, int pageSize) {
		return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
	}
}
