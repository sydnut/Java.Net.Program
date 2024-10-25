package com.testClient.service;

import com.pubclass.Message;
import com.pubclass.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * @author sydnut
 * @version 1.0
 * @time 2024/10/25
 */
public class MessageService {
    private String sender;
    private String receiver;
    private String content;
    public void sendMsgToOne(String s,String r,String c)throws IOException {
        sender = s;
        receiver = r;
        content = c;
        Message msg = new Message();
        msg.setMsgtype(MessageType.MSG_COMMON_PGE);
        msg.setSender(sender);
        msg.setReceiver(receiver);
        msg.setContent(content);
        msg.setTime(new Date().toString());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClient.getthread(sender).getSocket().getOutputStream());
            oos.writeObject(msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public MessageService(){
        content="";
    }
    public void sendMsgToAll(String s,String c){
        Message msg = new Message();
        msg.setMsgtype(MessageType.MSG_GROUP_CHATTING);msg.setContent(c);
        msg.setSender(s);
        msg.setTime(new Date().toString());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClient.getthread(s).getSocket().getOutputStream());
            oos.writeObject(msg);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
