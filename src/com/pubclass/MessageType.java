package com.pubclass;

public interface MessageType {
    String MSG_LOGIN_SUCCESS = "1";
    String MSG_LOGIN_FALIURE = "2";
    String MSG_COMMON_PGE="3";//transport the chatting information
    String MSG_GET_ONLINE_LIST="4";//request the online list
    String MSG_RETURN_ONLINE_LIST="5";//return it
    String MSG_GET_EXIT="6";//client ask to exit
    String MSG_GROUP_CHATTING="7";//group chatting

}
