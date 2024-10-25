package com.testClient.service;

import com.pubclass.Message;
import com.pubclass.MessageType;
import com.pubclass.User;
import com.testServer.server.ManageServer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author sydnut
 * @version 1.0
 * @time 2024/10/23
 * 完成验证或注册
 */
public class ClientService {
    private  boolean ok=false;
    private  User user=new User();
    private Socket socket;
    public  boolean checkUser(String id,String pwd)throws IOException,ClassNotFoundException {
        try{
        user.setId(id);
        user.setPassword(pwd);
        socket = new Socket(InetAddress.getByName("127.0.0.1"),9999);
            //send the user
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(user);
            //read the msg
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message msg=(Message) objectInputStream.readObject();
            if(msg.getMsgtype().equals(MessageType.MSG_LOGIN_SUCCESS)) {
                //create a thread to connect with the server
                ClientConnectServerThread thread = new ClientConnectServerThread(socket);
                thread.start();
                //为了拓展，放到集合中
                ManageClient.addThread(id, thread);
                ok = true;
            }else {
                socket.close();
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return ok;

    }
    //ask the online list
    public void showOnlineList() throws IOException {
        Message msg = new Message();
        msg.setMsgtype(MessageType.MSG_GET_ONLINE_LIST);
        try{ObjectOutputStream oos = new ObjectOutputStream(ManageClient.getthread(user.getId()).getSocket().getOutputStream());
        oos.writeObject(msg);
        }catch (IOException e){
            e.printStackTrace();
        }



    }
    public void quit(){
        Message msg = new Message();
        msg.setMsgtype(MessageType.MSG_GET_EXIT);
        msg.setSender(user.getId());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClient.getthread(user.getId()).getSocket().getOutputStream());
            oos.writeObject(msg);
            System.out.println(user.getId()+" is quitting");
            System.exit(0);//结束进程
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user=new User();
        user.setId("100");user.setPassword("123456");
             Socket  socket = new Socket(InetAddress.getLocalHost(),9999);
            //send the user
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(user);
            //read the msg
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message msg=(Message) objectInputStream.readObject();
            System.out.println(msg.getMsgtype());

            //read the msg

    }
}
