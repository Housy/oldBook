package com.housy.o2o.exceptions;

public class ShopOperationException extends RuntimeException {
	private static final long serialVersionUID = 236144688422298905L;
	public ShopOperationException(String msg) {
		super(msg);
	}
}