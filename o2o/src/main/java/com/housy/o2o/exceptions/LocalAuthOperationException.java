package com.housy.o2o.exceptions;

public class LocalAuthOperationException extends RuntimeException {
	private static final long serialVersionUID = -1512771573934050550L;
	//注意：这里不需要加Override方法
	public LocalAuthOperationException(String msg) {
		super(msg);
	}

}
