package ca.sheridancollege.ca.assignment3sahilsahil.beans;

import lombok.Data;

import java.util.List;

@Data


public class User {

    private long userid;
    private String userName;
    //    private String password;
    private  String encryptedPassword;
    private byte enabled;


    public User(long userid , String userName , String encryptedPassword , byte enabled) {
        this.userid = userid;
        this.userName=userName;
        this.encryptedPassword=encryptedPassword;
        this.enabled=enabled;
    }

    public User(){

    }

    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    public long getUserid() {
        return userid;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }
}

