package com.company;

import java.io.File;
import java.util.Random;

public class User extends Thread{
    private int id;
    private File log;

    User(int id,File log){
      this.log=log;
      this.id=id;
    }

    public boolean alocaAssento(Assento assento){//Produtor
        //Semáforo.take();
        if(assento.reservando)return false;
        assento.reservando = true;
        assento.setUserId(this.id); //se duas quiserem escrever, uma escreve e a outra tenta outro assento.
        assento.reservando = false;
        return true;
    }
    public void alocaAssento(Assento[] assentos){//Produtor
        File log = this.log;
        int n;
        do{
            Random random = new Random();
            n = random.nextInt(assentos.length);
        }while(!this.alocaAssento(assentos[n]));
    }
    public int[] visualizaAssentos(Assento[] assentos){//Consumidor
        //A fazer.
        File log = this.log;
        int[] myassentos = new int[assentos.length];
        for (int i=0;i<assentos.length;i++) {
            myassentos[i]=assentos[i].getUserId();
        }

        return myassentos;
    }
    public void liberaAssento(File log){//Produtor
        //A fazer
    }
}