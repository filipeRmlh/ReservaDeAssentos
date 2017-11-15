package com.company;

import java.nio.file.*;

public class ReservationSystem {
    public Assentos assentos;
    public LogManager log;

    ReservationSystem(int numAssentos, Path file){
        this.log=new LogManager(file);
        this.assentos= new Assentos(numAssentos,this.log);
    }
    public void init(){
        for(int i=0;i<this.assentos.assentos.length;i++){ //populando o array com objetos Assento
            this.assentos.assentos[i]=0;
        }

            User1 t1= new User1(1,this.assentos);
            User1 t2= new User1(2,this.assentos);
            User1 t3= new User1(3,this.assentos);
            User1 t4= new User1(4,this.assentos);

            User2 t5= new User2(5,5,this.assentos);
            User2 t6= new User2(6,7,this.assentos);
            User2 t7= new User2(7,0,this.assentos);
            User2 t8= new User2(8,8,this.assentos);

            User3 t9= new User3(9,this.assentos);
            User3 t10= new User3(10,this.assentos);
            User3 t11= new User3(11,this.assentos);
            User3 t12= new User3(12,this.assentos);

            t2.start();

            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t1.start();
            t7.start();
            t8.start();
            t9.start();
            t10.start();
            t11.start();
            t12.start();
    }
}