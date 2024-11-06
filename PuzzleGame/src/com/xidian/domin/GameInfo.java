package com.xidian.domin;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class GameInfo implements Serializable {
    private int x;
    private int y;
    private int count;
    private String path;
    private int[][] arrRandom = new int[4][4];

    public GameInfo(int x, int y, int count, String path, int[][] arrRandom) {
        this.x = x;
        this.y = y;
        this.count = count;
        this.path = path;
        this.arrRandom = arrRandom;
    }

    public GameInfo() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int[][] getArrRandom() {
        return arrRandom;
    }

    public void setArrRandom(int[][] arrRandom) {
        this.arrRandom = arrRandom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameInfo gameInfo = (GameInfo) o;
        return x == gameInfo.x && y == gameInfo.y && count == gameInfo.count && Objects.equals(path, gameInfo.path) && Arrays.equals(arrRandom, gameInfo.arrRandom);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(x, y, count, path);
        result = 31 * result + Arrays.hashCode(arrRandom);
        return result;
    }

    @Override
    public String toString() {
        return "GameInfo{" +
                "x=" + x +
                ", y=" + y +
                ", count=" + count +
                ", path='" + path + '\'' +
                ", arrRandom=" + Arrays.toString(arrRandom) +
                '}';
    }
}
