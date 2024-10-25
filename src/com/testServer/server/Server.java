package com.testServer.server;

import com.pubclass.Message;
import com.pubclass.MessageType;
import com.pubclass.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author sydnut
 * @version 2.0
 * @time 2024/10/24
 */
public class Server {
    private static HashMap<String, User> UserMap=new HashMap<>();
    static {
        UserMap.put("100", new User("100", "123456"));
        UserMap.put("zzc", new User("zzc", "654321"));
        UserMap.put("kd", new User("kd", "000000"));
        UserMap.put("james", new User("james", "000001"));
        UserMap.put("curry", new User("curry", "000002"));

    }

    private ServerSocket server = null;
    private boolean checkUser(User user){
        User u=UserMap.get(user.getId());
        return u!=null&&u.getPassword().equals(user.getPassword());
    }
    public Server() throws IOException, ClassNotFoundException {
        try {
            server = new ServerSocket(9999);
            System.out.println("SERVER IS LAUNCHING!\nListening to the 9999 port");
            while (true) {
                Socket s = server.accept();
                //get msg
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                User user = (User) ois.readObject();
                Message message = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                if (checkUser(user)) {
                    message.setMsgtype(MessageType.MSG_LOGIN_SUCCESS);
                    oos.writeObject(message);
                    ServerConnectClientThread thread = new ServerConnectClientThread(s, user.getId());
                    thread.start();
                    ManageServer.addThread(user.getId(), thread);
                } else {
                    System.out.println(user.getId() + "login error!");
                    message.setMsgtype(MessageType.MSG_LOGIN_FALIURE);
                    oos.writeObject(message);
                    s.close();
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            server.close();
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Server();
    }
}
