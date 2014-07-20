package com.yunyi;

/**
 * Created by zhouyua on 4/10/14.
 */
public class Message {
	private String message;
	private String type;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Message [message=" + message + ", type=" + type + "]";
	}

}
