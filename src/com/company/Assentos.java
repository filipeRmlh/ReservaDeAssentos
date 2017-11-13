package com.company;

public class Assentos {
    public int[] assentos;
    public User[] users;
    private boolean[] reservando;

    Assentos(int numAssentos){
        this.assentos = new int[numAssentos];
        this.users = new User[numAssentos];
        this.reservando=new boolean[numAssentos];
    }

    public boolean setUser(User user, int assento) throws InterruptedException{
        while(true){
            if(this.assentos[assento]==0){
                synchronized (this) {
                    if (this.reservando[assento]) {
                        this.wait();
                    } else {
                        this.reservando[assento] = true;
                        this.users[assento] = user;
                        this.reservando[assento]=false;
                        synchronized (this){
                            this.notifyAll();
                        }
                        return true;
                    }
                }
            }else{
                return false;
            }
        }


    }
    public synchronized boolean clearUserId(int userId,int assento) throws InterruptedException{
        if((this.users[assento]!=null)&&(this.assentos[assento] == userId)){
            this.users[assento]=null;
            this.assentos[assento]=0;
            this.reservando[assento]=false;
            return true;
        }
        return false;
    }
}
