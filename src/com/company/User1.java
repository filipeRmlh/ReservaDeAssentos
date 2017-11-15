package com.company;

public class User1 extends User {
    User1(int id,Assentos assentos){
        super(id,assentos);
    }
    @Override
    public void run() {
        this.visualizaAssentos(this.assentos);
        this.alocaAssentoLivre(this.assentos);
        this.visualizaAssentos(this.assentos);
    }
}