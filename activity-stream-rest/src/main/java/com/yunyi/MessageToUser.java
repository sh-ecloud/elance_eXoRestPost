package com.yunyi;

/**
 * @author yuan.zhou@hp.com
 */
public class MessageToUser extends Message {
    private String userName;

    public MessageToUser() {
    }

    public MessageToUser(String userName, String message, String iframeLink, String type) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
