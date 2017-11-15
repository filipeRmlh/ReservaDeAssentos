package com.company;

public class User extends Thread{
    public int id;
    public int logOrder=-1;
    private LogManager log;
    protected Assentos assentos;
    private Semaphore semaphore;
    private  Monitor monitor;
    protected int myAssento=-1;

    User(int id,Assentos assentos,LogManager log, Semaphore semaphore, Monitor monitor){
      this.log=log;
      this.id=id;
      this.assentos=assentos;
      this.semaphore = semaphore;
      this.monitor=monitor;
    }

    private synchronized boolean alocaAssentoMaster(int assento){
        boolean retorno;
        try {
            this.monitor.lockAloca();
            this.semaphore.take();
            retorno = assentos.setUser(this,assento);
            if(retorno)this.myAssento=assento;
            this.monitor.unlockAloca();
            return retorno;
        }catch (InterruptedException ex){
            System.err.println("Erro ao alocar Assento dado");
            return false;
        }
    }

    //***********************************************

    public boolean alocaAssento(int assento){ //Assento dado
        boolean retorno = this.alocaAssentoMaster(assento);
        this.log.alocarAssentoDado(this,assento,this.assentos.assentos);
        return retorno;
    }

    public boolean alocaAssento(Assentos assentos){ //Assento livre
        boolean retorno;
        for (int i=0;i<assentos.assentos.length;i++) {
            if(assentos.assentos[i]==0){
                retorno = this.alocaAssentoMaster(i);
                if(retorno)this.log.alocarAssentoLivre(this,i,assentos.assentos);
                return retorno;
            }
        }
        return false;
    }
    public synchronized int[] visualizaAssentos(Assentos assentos){
        try{
            this.monitor.lockVisualiza();
            this.log.visualizarAssento(this,assentos.assentos);
            this.monitor.unlockVisualiza();
            return assentos.assentos;
        }catch (InterruptedException doNothing){
            return assentos.assentos;
        }
    }
    public boolean liberaAssento(int assento){
        boolean retorno;
        if(this.myAssento==-1)return false;
        try{
            this.monitor.lockLibera();
            retorno = this.assentos.clearUser(this.id,assento);
            if(retorno)this.myAssento = -1;
            this.semaphore.release();
            this.log.liberarAssento(this,assento,this.assentos.assentos);
            this.monitor.unlockLibera();
            return retorno;
        }catch (InterruptedException ex){
            return false;
        }
    }
}