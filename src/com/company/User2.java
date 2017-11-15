package com.company;

public class User2 extends User {
    private int assento;

    User2(int id, int assento,Assentos assentos){
        super(id,assentos);
        this.assento=assento;
    }
    @Override
    public void run() {
        this.visualizaAssentos(this.assentos);
        this.alocaAssentoDado(this.assento);
        this.visualizaAssentos(this.assentos);
    }
}
