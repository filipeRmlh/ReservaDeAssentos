package com.company;

public class User3 extends User{
    User3(int id,Assentos assentos,LogManager log, Semaphore semaphore, Monitor monitor){
        super(id,assentos,log, semaphore,monitor);
    }
    public void run() {
        this.visualizaAssentos(this.assentos);
        this.alocaAssento(this.assentos);
        this.visualizaAssentos(this.assentos);
        this.liberaAssento(this.myAssento);
        this.visualizaAssentos(this.assentos);

    }
}
