package com.testServer.server;

import com.pubclass.Message;
import com.pubclass.MessageType;
import com.testClient.service.ManageClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author sydnut
 * @version 1.0
 * @time 2024/10/23
 */
public class ServerConnectClientThread extends Thread{
    private Socket socket;
    private String id;
    public ServerConnectClientThread(Socket s,String str){
        socket=s;id=str;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run(){
        System.out.println("Connecting with the client"+" "+id);
        while (true){
            try{
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message msg=(Message) ois.readObject();
                if(msg.getMsgtype().equals(MessageType.MSG_GET_ONLINE_LIST)){
                    System.out.println(id+" is requesting the online list");
                    Message message = new Message();
                    message.setMsgtype(MessageType.MSG_RETURN_ONLINE_LIST);message.setContent(ManageServer.getOnlineUser());
                    message.setReceiver(id);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message);
                }else if(msg.getMsgtype().equals(MessageType.MSG_GET_EXIT)) {
                    System.out.println(msg.getSender()+" has login out");
                    ManageServer.removeThread(msg.getSender());
                    socket.close();
                    break;
                }
                else if(msg.getMsgtype().equals(MessageType.MSG_COMMON_PGE)){
                    try{
                        System.out.println(msg.getTime());
                        System.out.println(msg.getSender()+" is chatting with "+msg.getReceiver());
                        ObjectOutputStream oos = new ObjectOutputStream(ManageServer.getThread(msg.getReceiver()).getSocket().getOutputStream());
                        oos.writeObject(msg);

                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }else if(msg.getMsgtype().equals(MessageType.MSG_GROUP_CHATTING)){
                    System.out.println(msg.getTime());
                    System.out.println(msg.getSender()+" is in group chatting");
                    for(String id :ManageServer.map.keySet()) {
                        if(!id.equals(msg.getSender()))
                        try {
                            ObjectOutputStream oos = new ObjectOutputStream(ManageServer.getThread(id).getSocket().getOutputStream());
                            oos.writeObject(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    System.out.println("Unknown Request!");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
