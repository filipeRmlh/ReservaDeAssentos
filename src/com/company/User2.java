package com.company;

public class User2 extends User {
    int assento;

    User2(int id, int assento,Assentos assentos,LogManager log, Semaphore semaphore, Monitor monitor){
        super(id,assentos,log, semaphore,monitor);
        this.assento=assento;

    }
    @Override
    public void run() {
        this.visualizaAssentos(this.assentos);
        this.alocaAssento(this.assento);
        this.visualizaAssentos(this.assentos);
    }
}
