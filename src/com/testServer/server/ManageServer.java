package com.testServer.server;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author sydnut
 * @version 1.0
 * @time 2024/10/23
 */
public class ManageServer {
    public static HashMap<String ,ServerConnectClientThread>map=new HashMap<>();
    public static void addThread(String id,ServerConnectClientThread thread){
        map.put(id,thread);
    }
    public static ServerConnectClientThread getThread(String id){
        return map.get(id);
    }
        public static  String getOnlineUser(){
        StringBuilder s=new StringBuilder();
        Iterator<String> it=map.keySet().iterator();
        while(it.hasNext()){
            s.append(it.next().toString());s.append(" ");
        }
        return s.toString();

    }
    public static void removeThread(String id){
        map.remove(id);
    }
}
