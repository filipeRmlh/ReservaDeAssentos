package com.company;

import java.io.File;

public class ReservationSystem {
    protected Assento[] assentos;
    public File log;
    ReservationSystem(int numAssentos, File file){
        this.assentos= new Assento[numAssentos];
        this.log=file;
    }
    public void init(){
        User1 t1 = new User1(1,this.log);
    }

}
