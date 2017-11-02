package com.company;

public class Assento {
    public int id;
    private User myUser = null;
    private boolean reservando=false;

    Assento(int id){
        this.id = id;
    }

    public User getUser() {
        return this.myUser;
    }

    public boolean setUser(User user) throws InterruptedException{
        while(true){
            if(this.myUser==null){
                synchronized (this) {
                    if (this.reservando) {
                        this.wait();
                    } else {
                        this.reservando = true;
                        break;
                    }
                }
            }else{
                return false;
            }
        }

        this.myUser = user;
        this.reservando=false;
        synchronized (this){
            this.notifyAll();
        }
        return true;
    }
    public synchronized boolean clearUserId(long userId) throws InterruptedException{
        if((this.myUser!=null)&&(this.myUser.getId() == userId)){
            this.myUser=null;
            return true;
        }
        return false;
    }
}
