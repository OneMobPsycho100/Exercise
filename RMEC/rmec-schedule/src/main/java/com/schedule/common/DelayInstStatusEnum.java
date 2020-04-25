package com.schedule.common;

import java.util.Arrays;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/24 15:11
 */
public enum DelayInstStatusEnum {

	NOT_RUN("0", "未运行"), WAIT_RUN("1", "等待运行"), RUNNING("2", "正在运行"), COMPLETE("3", "运行完成");

	private String	code;
	private String	name;

	private DelayInstStatusEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static DelayInstStatusEnum codeOf(String code) {
		return Arrays.stream(DelayInstStatusEnum.values())
				.filter(status -> status.code.equals(code)).findFirst().get();
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public boolean isEqual(String code) {
		return this.code.equals(code);
	}
}
