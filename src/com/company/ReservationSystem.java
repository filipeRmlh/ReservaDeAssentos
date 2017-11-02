package com.company;

import java.nio.file.*;

public class ReservationSystem {
    public Assento[] assentos;
    public LogManager log;
    public Semaphore semaphore;

    ReservationSystem(int numAssentos, Path file){
        this.assentos= new Assento[numAssentos];
        this.log=new LogManager(file);
        this.semaphore = new Semaphore(numAssentos);
    }
    public void init(){
        for(int i=0;i<this.assentos.length;i++){ //populando o array com objetos Assento
            this.assentos[i]=new Assento(i);
        }

            User1 t1= new User1(1,this.assentos,this.log, this.semaphore);
            User1 t2= new User1(2,this.assentos,this.log, this.semaphore);
            User1 t3= new User1(3,this.assentos,this.log, this.semaphore);
            User1 t4= new User1(4,this.assentos,this.log, this.semaphore);
            t1.start();
            t2.start();
            t3.start();
            t4.start();
    }
}
