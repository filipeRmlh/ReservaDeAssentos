package com.company;


public class User extends Thread{
    public int id;
    public int logOrder=-1;
    protected Assentos assentos;
    protected int myAssento=-1;

    User(int id,Assentos assentos){
      this.id=id;
      this.assentos=assentos;
    }

    public synchronized boolean alocaAssentoDado(int assento){ //Assento dado
        try{
            return this.assentos.alocaAssentoDado(this,assento);
        }catch (InterruptedException ex){
            return false;
        }
    }

    public  synchronized boolean alocaAssentoLivre(Assentos assentos){ //Assento livre
        try {
            return  assentos.alocaAssentoLivre(this);
        }catch (InterruptedException ex){
            return false;
        }
    }

    public synchronized int[] visualizaAssentos(Assentos assentos){
        try{
            this.assentos.visualizaAssentos(this);
        }catch (InterruptedException doNothing){}
        return assentos.assentos;
    }

    public boolean liberaAssento(int assento){
        boolean retorno;
        try{
            retorno = this.assentos.liberaAssento(this,assento);
            return retorno;
        }catch (InterruptedException ex){
            return false;
        }
    }
}