package com.housy.o2o.dto;


public class Result<T> {
	private boolean success;
	//�ɹ�ʱ�򷵻ص�����
	private T data;
	//������Ϣ
	private String errorMsg;
	
	private int errorCode;
	
	public Result() {
		
	}
	//�ɹ�ʱ��Ĺ�����
	public Result(boolean success,T data) {
		this.data = data;
		this.success = success;
	}
	
	//ʧ��ʱ��Ĺ�����
	public Result(boolean success, int erroeCode, String erroeMsg) {
		this.success = success;
		this.errorCode = erroeCode;
		this.errorMsg = erroeMsg;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
