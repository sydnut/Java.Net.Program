package com.pubclass;

import java.io.Serializable;

/**
 * @version 1.0
 * 用户类
 */
public class User implements Serializable {
        private static final long serialVersionUID=1L;
    private String id;
    private String password;

    public User(String id,String pwd) {
        this.id=id;password=pwd ;
    }
    public User(){}
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}