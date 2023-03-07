package com.CricketGame.CricketGame.model;

import java.util.ArrayList;

public class Over {
    private static int size ;
    private ArrayList<Integer> balls ;
    public static int getSize() {
        return size;
    }
    public static void setSize(int size) {
        Over.size = size;
    }
    public ArrayList<Integer> getBalls() {
        return balls;
    }
    public Over(){
        size = 6 ;
        balls = new ArrayList<>();
    }
}
