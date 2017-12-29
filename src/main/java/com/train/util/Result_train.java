package com.train.util;

import com.alibaba.fastjson.JSONObject;

public class Result_train {
	private String code;
	private String message;
	private Object data;

	public Result_train() {
		this.code = "0";
		this.message = "接口调用成功";
		this.data = new JSONObject();
	}

	public Result_train(String code, String message) {
		this.code = code;
		this.message = message;
		this.data = new JSONObject();
	}

	public Result_train(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
