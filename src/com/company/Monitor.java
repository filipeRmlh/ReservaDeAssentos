package com.company;

public class Monitor {
    boolean visualizando=false,alocando =false,liberando=false;
    public synchronized void lockVisualiza() throws InterruptedException{
        while(this.alocando||this.liberando){
            this.wait();
        }
        this.visualizando=true;
    }
    public synchronized void unlockVisualiza() throws InterruptedException{
        this.visualizando=false;
        this.notifyAll();
    }
    public synchronized void lockAloca() throws InterruptedException{
        while(this.visualizando){
            this.wait();
        }
        this.alocando=true;
    }
    public synchronized void unlockAloca() throws InterruptedException{
        this.alocando=false;
        this.notifyAll();
    }
    public synchronized void lockLibera() throws InterruptedException{
        while(this.visualizando){
            this.wait();
        }
        this.liberando=true;
    }
    public synchronized void unlockLibera() throws InterruptedException{
        this.liberando=false;
        this.notifyAll();
    }

}
