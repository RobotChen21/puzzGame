package com.xidian.domin;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

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


    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        for (int i = 0; i < 5; i++) {
            gameInfos[i] = new GameInfo();
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(passWord, user.passWord) && Arrays.equals(gameInfos, user.gameInfos);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userName, passWord);
        result = 31 * result + Arrays.hashCode(gameInfos);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", gameInfos=" + Arrays.toString(gameInfos) +
                '}';
    }
}
