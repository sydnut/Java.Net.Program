package com.testClient.service;

import com.pubclass.Message;
import com.pubclass.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author sydnut
 * @version 1.0
 * @time 2024/10/23
 */
public class ClientConnectServerThread extends Thread{
    //need to have socket,without iy,without net
    private Socket socket;
    public ClientConnectServerThread(Socket s){
        socket=s;
    }
    @Override
    public void run(){
       while(true){
           try{
               ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
               System.out.println("Client thread is waiting for the information form server");
               Message msg=(Message) objectInputStream.readObject();//if not get the msg,it will block
               if(msg.getMsgtype().equals(MessageType.MSG_RETURN_ONLINE_LIST)){
                   String []onlineUsers=msg.getContent().split(" ");
                   System.out.println("=============cross line==============");
                   Arrays.stream(onlineUsers).forEach((s)->{System.out.println("online user: "+s);});
               }else if(msg.getMsgtype().equals(MessageType.MSG_COMMON_PGE)){
                   System.out.println(msg.getTime());
                   System.out.println(msg.getSender()+":");
                   System.out.println(msg.getContent());
               } else if (msg.getMsgtype().equals(MessageType.MSG_GROUP_CHATTING)) {

                   System.out.println(msg.getTime());
                   System.out.println(msg.getSender()+":");
                   System.out.println(msg.getContent());

               } else {
                   System.out.println("not get anything!");
               }
           }catch (Exception e){
               e.getStackTrace();
           }


       }
    }
    public Socket getSocket() {
        return socket;
    }
}
