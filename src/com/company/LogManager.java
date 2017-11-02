package com.company;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

public class LogManager  {
    private Path log;
    public LogManager(Path log){
        this.log=log;
    }
    public synchronized String  arrayAssentos(Assento[] assentos){
        int[] myassentos = new int[assentos.length];
        for (int i=0;i<assentos.length;i++) {
            int userId = assentos[i].getUser()==null?0:assentos[i].getUser().id;
            myassentos[i]=userId;
        }
        return Arrays.toString(myassentos);
    }

    public synchronized void visualizarAssento(int userId,int[] assentos){
        int opcode = 1;
        String sAssentos = Arrays.toString(assentos);
        this.logWrite("op"+opcode+"("+userId+","+sAssentos+")\n");
        System.out.println("op"+opcode+"("+userId+","+sAssentos+")\n");

    }

    public synchronized void  alocarAssentoLivre(int userId, int assento, Assento[] assentos){
        int opcode = 2;
        String sAssentos = this.arrayAssentos(assentos);
        this.logWrite("op"+opcode+"("+userId+","+assento+","+sAssentos+")\n");
        System.out.println("op"+opcode+"("+userId+","+assento+","+sAssentos+")\n");
    }

    public synchronized void  alocarAssentoDado(int userId, int assento, Assento[] assentos){
        int opcode = 3;
        String sAssentos = this.arrayAssentos(assentos);
        this.logWrite("op"+opcode+"("+userId+","+assento+","+sAssentos+")");
    }

    public synchronized void  liberarAssento(int userId, int assento, Assento[] assentos){
        int opcode = 4;
        String sAssentos = this.arrayAssentos(assentos);
        this.logWrite("op"+opcode+"("+userId+","+assento+","+sAssentos+")");
    }

    public synchronized boolean logWrite(String comand){
        try {
            Files.write(this.log,comand.getBytes(),StandardOpenOption.APPEND);
            return true;
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }
}