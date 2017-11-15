package com.company;

public class User3 extends User{
    User3(int id,Assentos assentos){
        super(id,assentos);
    }
    public void run() {
        this.visualizaAssentos(this.assentos);
        this.alocaAssentoLivre(this.assentos);
        this.visualizaAssentos(this.assentos);
        this.liberaAssento(this.myAssento);
        this.visualizaAssentos(this.assentos);

    }
}
