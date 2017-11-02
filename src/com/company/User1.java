package com.company;

public class User1 extends User {
    User1(int id,Assento[] assentos,LogManager log, Semaphore semaphore){
        super(id,assentos,log, semaphore);
    }
    @Override
    public void run() {
        this.visualizaAssentos(this.assentos);
        this.alocaAssento(this.assentos);
//        this.visualizaAssentos(super.assentos);
    }
}