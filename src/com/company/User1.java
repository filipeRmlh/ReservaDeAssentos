package com.company;

public class User1 extends User {
    User1(int id,Assentos assentos,LogManager log, Semaphore semaphore, Monitor monitor){
        super(id,assentos,log, semaphore,monitor);
    }
    @Override
    public void run() {
        this.visualizaAssentos(this.assentos);
        this.alocaAssento(this.assentos);
//        this.visualizaAssentos(super.assentos);
    }
}