package com.yunyi;

/**
 * @author yuan.zhou@hp.com
 */
public class MessageToSpace extends Message {
	private String spaceName;

	public MessageToSpace() {
	}

	public MessageToSpace(String userName) {
		this.spaceName = userName;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String userName) {
		this.spaceName = userName;
	}
}
