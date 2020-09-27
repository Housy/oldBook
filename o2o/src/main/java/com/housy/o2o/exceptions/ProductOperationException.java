package com.housy.o2o.exceptions;

public class ProductOperationException extends RuntimeException {
	private static final long serialVersionUID = 236144688422298905L;
	public ProductOperationException(String msg) {
		super(msg);
	}
}
