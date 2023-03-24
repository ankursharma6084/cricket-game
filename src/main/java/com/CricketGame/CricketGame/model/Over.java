package com.CricketGame.CricketGame.model;

import java.util.ArrayList;

public class Over {
    private static int size ;
    private int curOver;
    private ArrayList<Integer> balls ;
    public static int getSize() {
        return size;
    }
    public int getCurOver() {
        return curOver;
    }
    public void setCurOver(int curOver) {
        this.curOver = curOver;
    }
    public static void setSize(int size) {
        Over.size = size;
    }
    public ArrayList<Integer> getBalls() {
        return balls;
    }
    public Over(int curOver){
        this.curOver = curOver;
        size = 6 ;
        balls = new ArrayList<>();
    }
}
