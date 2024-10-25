package com.testClient.service;

import java.util.HashMap;

/**
 * @author sydnut
 * @version 1.0
 * @time 2024/10/23
 * manage the threads from clients to server
 */
public class ManageClient {
    private static HashMap<String ,ClientConnectServerThread>map=new HashMap<>();
    public static void addThread(String id,ClientConnectServerThread thread){
            map.put(id,thread);
    }
    public static ClientConnectServerThread getthread(String id){
        return map.get(id);
    }

    public static int getSize(){
        return map.size();
    }
}
