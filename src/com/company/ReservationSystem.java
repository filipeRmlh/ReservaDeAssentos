package com.company;

import java.nio.file.*;

public class ReservationSystem {
    public Assentos assentos;
    public LogManager log;
    public User[] users;
    public int NUMUSERS = 12;
    public int NUMASSENTOS;

    ReservationSystem(int numAssentos, Path file){
        this.users = new User[this.NUMUSERS];
        this.NUMASSENTOS = numAssentos;
        this.log=new LogManager(file,numAssentos);
        this.assentos= new Assentos(numAssentos,this.log);
    }
    public void init(){
        for(int i=0;i<this.assentos.assentos.length;i++){ //populando o array com objetos Assento
            this.assentos.assentos[i]=0;
        }

        int num1 = 0,num2=0,num3=0;
        int random,random2;
        for(int j = 0;j<NUMUSERS;j++){
            random = (int) (Math.random()*3)+1;
            if(random == 1){
                this.users[j] = new User1(j,this.assentos);
                num1++;
            }else if(random ==2){
                random2 = (int) (Math.random()*this.NUMASSENTOS);
                this.users[j] = new User2(5,random2,this.assentos);
                num2++;
            }else{
                this.users[j] = new User3(9,this.assentos);
                num3++;
            }
        }

        for(int j = 0;j<NUMUSERS;j++){
           this.users[j].start();
        }

        System.out.println(num1+" Usuários do tipo 1 criados");
        System.out.println(num2+" Usuários do tipo 2 criados");
        System.out.println(num3+" Usuários do tipo 3 criados");

        for(int j=0;j<NUMUSERS;j++){
            try {
                this.users[j].join();
            }catch (InterruptedException ex){
                System.out.println("ERRO NO JOIN NO ARQUIVO \'ReservationSystem\'");
            }
        }

        this.log.logFinal();

//            User1 t1= new User1(1,this.assentos);
//            User1 t2= new User1(2,this.assentos);
//            User1 t3= new User1(3,this.assentos);
//            User1 t4= new User1(4,this.assentos);
//
//            User2 t5= new User2(5,5,this.assentos);
//            User2 t6= new User2(6,7,this.assentos);
//            User2 t7= new User2(7,0,this.assentos);
//            User2 t8= new User2(8,8,this.assentos);
//
//            User3 t9= new User3(9,this.assentos);
//            User3 t10= new User3(10,this.assentos);
//            User3 t11= new User3(11,this.assentos);
//            User3 t12= new User3(12,this.assentos);
//
//            t2.start();
//
//            t3.start();
//            t4.start();
//            t5.start();
//            t6.start();
//            t1.start();
//            t7.start();
//            t8.start();
//            t9.start();
//            t10.start();
//            t11.start();
//            t12.start();


    }
}