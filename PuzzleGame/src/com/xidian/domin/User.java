package com.xidian.domin;

import java.io.Serializable;

public class User implements Serializable{
    private String userName;
    private String passWord;
    private GameInfo[] gameInfos = new GameInfo[5];

    public GameInfo[] getGameInfos() {
        return gameInfos;
    }

    public void setGameInfos(GameInfo[] gameInfos) {
        this.gameInfos = gameInfos;
    }

    public User() {
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
