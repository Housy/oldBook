package com.housy.o2o.enums;

public enum LocalAuthStateEnum {
	LOGINFAIL(-1, "密码或帐号输入有误"), SUCCESS(0, "操作成功"), NULL_AUTH_INFO(-1006,
			"注册信息为空"), ONLY_ONE_ACCOUNT(-1007,"最多只能绑定一个本地帐号");
	private int state;
	private String stateInfo;
	private LocalAuthStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	/**
	 * Enum类中并没有values方法，values方法是由编译器添加的，
	 * java编译器胡自动在enum类型中插入一些方法，其中就包括values
	 * 所以点击values没有反应，无法查看其源码
	 * @param index
	 * @return
	 */
	public static LocalAuthStateEnum stateOf(int index) {
		for(LocalAuthStateEnum state : values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
