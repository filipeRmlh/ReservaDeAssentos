package com.company;

public class Assento {
    private int userId = 0;
    public  boolean reservando = false;

    public Assento(int userId){
        this.userId=userId;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
