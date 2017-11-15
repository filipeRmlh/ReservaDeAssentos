package com.company;

public class Monitor {
    boolean visualizando=false,alocandolivre =false,alocandodado =false,liberando=false;
    public synchronized void lockVisualiza() throws InterruptedException{
        while(this.alocandolivre||this.liberando||this.alocandodado){
            this.wait();
        }
        this.visualizando=true;
    }
    public synchronized void unlockVisualiza() throws InterruptedException{
        this.visualizando=false;
        this.notifyAll();
    }
    public synchronized void lockAlocaLivre() throws InterruptedException{
        while(this.visualizando||this.alocandodado){
            this.wait();
        }
        this.alocandolivre=true;
    }

    public synchronized void unlockAlocaLivre() throws InterruptedException{
        this.alocandolivre=false;
        this.notifyAll();
    }
    public synchronized void lockAlocaDado() throws InterruptedException{
        while(this.visualizando||this.alocandolivre){
            this.wait();
        }
        this.alocandodado=true;
    }

    public synchronized void unlockAlocaDado() throws InterruptedException{
        this.alocandodado=false;
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
