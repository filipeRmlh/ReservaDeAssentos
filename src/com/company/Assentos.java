package com.company;

import java.util.Arrays;

public class Assentos {
    public int[] assentos;
    public User[] users;
    private boolean[] reservando,liberando;

    Assentos(int numAssentos){
        this.assentos = new int[numAssentos];
        this.users = new User[numAssentos];
        this.reservando=new boolean[numAssentos];
        this.liberando=new boolean[numAssentos];
        for(int i = 0;i<numAssentos;i++){
            reservando[i]=false;
            liberando[i]=false;
        }
    }

    public boolean setUser(User user, int assento) throws InterruptedException{
        while(true){
            if(this.assentos[assento]==0){
                synchronized (this) {
                    if (this.reservando[assento]) {
                        this.wait();
                    } else {
                        this.reservando[assento] = true;
                        break;
                    }
                }
            }else{
                return false;
            }
        }

        this.assentos[assento]=user.id;
        this.users[assento] = user;
        this.reservando[assento]=false;
        synchronized (this){
            this.notifyAll();
        }
        return true;
    }

    public synchronized boolean clearUser(int userId,int assento) throws InterruptedException{
        while(true){
            if((this.users[assento]!=null)&&(this.assentos[assento] == userId)){
                synchronized (this) {
                    if (this.liberando[assento]) {
                        this.wait();
                    } else {
                        this.liberando[assento] = true;
                        break;
                    }
                }
            }else{
                return false;
            }
        }
        this.assentos[assento]=0;
        this.users[assento]=null;
        this.liberando[assento]=false;
        synchronized (this){
            this.notifyAll();
        }
        return true;
    }
}
