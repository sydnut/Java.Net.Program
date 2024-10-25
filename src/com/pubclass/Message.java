package com.pubclass;

import java.io.Serializable;

/**
 * @version 1.0
 * 表示服务端和用户端通信的信息
 */
public class Message implements Serializable {
        private static final long serialVersionUID=1L;
    private String sender;
    private String receiver;
    private String content;
    private String msgtype;
    private String time;
    public Message(){}
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
