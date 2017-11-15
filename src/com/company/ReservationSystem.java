package com.company;

import java.nio.file.*;

public class ReservationSystem {
    public Assentos assentos;
    public LogManager log;
    public Semaphore semaphore;
    public Monitor monitor;

    ReservationSystem(int numAssentos, Path file){
        this.assentos= new Assentos(numAssentos);
        this.log=new LogManager(file);
        this.semaphore = new Semaphore(numAssentos);
        this.monitor = new Monitor();
    }
    public void init(){
        for(int i=0;i<this.assentos.assentos.length;i++){ //populando o array com objetos Assento
            this.assentos.assentos[i]=0;
        }

            User1 t1= new User1(1,this.assentos,this.log, this.semaphore, this.monitor);
            User1 t2= new User1(2,this.assentos,this.log, this.semaphore, this.monitor);
            User1 t3= new User1(3,this.assentos,this.log, this.semaphore, this.monitor);
            User1 t4= new User1(4,this.assentos,this.log, this.semaphore, this.monitor);

            User2 t5= new User2(5,1,this.assentos,this.log, this.semaphore, this.monitor);
            User2 t6= new User2(6,1,this.assentos,this.log, this.semaphore, this.monitor);
            User2 t7= new User2(7,0,this.assentos,this.log, this.semaphore, this.monitor);
            User2 t8= new User2(8,3,this.assentos,this.log, this.semaphore, this.monitor);
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t7.start();
            t8.start();
    }
}