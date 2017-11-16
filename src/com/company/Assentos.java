package com.company;


public class Assentos {
    public int[] assentos;
    public User[] users;
    private boolean[] reservando,liberando;
    private Monitor monitor;
    private Semaphore semaphore;
    private LogManager logManager;

    Assentos(int numAssentos,LogManager logManager){
        this.assentos = new int[numAssentos];
        this.users = new User[numAssentos];
        this.reservando=new boolean[numAssentos];
        this.liberando=new boolean[numAssentos];

        this.monitor = new Monitor();
        this.semaphore = new Semaphore(numAssentos);
        this.logManager = logManager;

        for(int i = 0;i<numAssentos;i++){
            reservando[i]=false;
            liberando[i]=false;
        }
    }
    public synchronized int[] visualizaAssentos(User user) throws InterruptedException{
        this.monitor.lockVisualiza();
        this.logManager.visualizarAssento(user,this.assentos);
        this.monitor.unlockVisualiza();
        return this.assentos;
    }
    public synchronized boolean alocaAssentoDado(User user, int assento) throws InterruptedException{
        if((assento>this.assentos.length)||(assento<0))return false;
        this.monitor.lockAlocaDado();
        while(true){
            if(this.assentos[assento]==0){
                    if (this.reservando[assento]) {
                        this.wait();
                    } else {
                        this.reservando[assento] = true;
                        this.semaphore.take();
                        this.assentos[assento]=user.id;
                        this.users[assento] = user;
                        this.logManager.alocarAssentoDado(user,assento,this.assentos);
                        user.myAssento = assento;
                        this.reservando[assento]=false;
                        this.monitor.unlockAlocaDado();
                        this.notifyAll();
                        return true;
                    }
            }else{
                this.logManager.alocarAssentoDado(user,assento,this.assentos);
                this.monitor.unlockAlocaDado();
                this.notifyAll();
                return false;
            }
        }
    }

    public synchronized boolean alocaAssentoLivre(User user) throws InterruptedException{
        int i = 0;
        for(;i<this.assentos.length;i++){
            this.monitor.lockAlocaLivre();
            while (true) {
                if (this.assentos[i] == 0) {
                    if (this.reservando[i]) {
                        this.wait();
                    } else {
                        this.reservando[i] = true;
                        this.semaphore.take();
                        this.assentos[i] = user.id;
                        this.users[i] = user;
                        this.logManager.alocarAssentoLivre(user, i, this.assentos);
                        user.myAssento = i;
                        this.reservando[i] = false;
                        this.monitor.unlockAlocaLivre();
                        this.notifyAll();
                        return true;
                    }
                } else {
                    break;
                }
            }
        }
        this.monitor.unlockAlocaLivre();
        this.logManager.alocarAssentoLivre(user,i,this.assentos);
        this.notifyAll();
        return false;
    }

    public synchronized boolean liberaAssento(User user,int assento) throws InterruptedException{
        if((assento>this.assentos.length)||(assento<0))return false;
        this.monitor.lockLibera();
        while(true){
            if((this.users[assento]!=null)&&(this.assentos[assento] == user.id)){
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
        this.logManager.liberarAssento(user,assento,this.assentos);
        this.semaphore.release();
        this.monitor.unlockLibera();
        synchronized (this){
            this.notifyAll();
        }
        return true;
    }
}
