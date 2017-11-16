package com.company;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;


public class LogManager {
    private Path log;
    private int counterIn = 0;
    private int counterOut = 0;

    public LogManager(Path log,int numAssentos){
        this.log=log;
        this.logInit(numAssentos);
    }
    
    public synchronized String  arrayAssentos(int[] assentos){
        return Arrays.toString(assentos);
    }

    public synchronized void visualizarAssento(User user,int[] assentos){
        int opcode = 1;
        String sAssentos = Arrays.toString(assentos);
//        System.out.println("op"+opcode+"("+user.id+","+sAssentos+")\n");
        this.logWrite(user,"op"+opcode+"("+user.id+","+sAssentos+", b)\n");
    }

    public synchronized void  alocarAssentoLivre(User user, int assento, int[] assentos){
        int opcode = 2;
        String sAssentos = this.arrayAssentos(assentos);
//        System.out.println("op"+opcode+"("+user.id+","+assento+","+sAssentos+")\n");
        this.logWrite(user,"op"+opcode+"("+user.id+","+assento+","+sAssentos+", b)\n");
    }

    public synchronized void  alocarAssentoDado(User user, int assento, int[] assentos){
        int opcode = 3;
        String sAssentos = this.arrayAssentos(assentos);
//        System.out.println("op"+opcode+"("+user.id+","+assento+","+sAssentos+")\n");
        this.logWrite(user,"op"+opcode+"("+user.id+","+assento+","+sAssentos+", b)\n");
    }

    public synchronized void  liberarAssento(User user, int assento, int[] assentos){
        int opcode = 4;
        String sAssentos = this.arrayAssentos(assentos);
//        System.out.println("op"+opcode+"("+user.id+","+assento+","+sAssentos+")\n");
        this.logWrite(user,"op"+opcode+"("+user.id+","+assento+","+sAssentos+", b)\n");
    }


    public synchronized boolean logWrite(User user,String command){
        try {
            while ((user.logOrder!=this.counterOut)&&(this.counterOut<this.counterIn)){
                user.logOrder = this.counterIn++;
                wait();
            }
            this.counterOut++;
            user.logOrder = -1;
            Files.write(this.log,command.getBytes(),StandardOpenOption.APPEND);
            notifyAll();
            return true;
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }catch (InterruptedException ex){
            return false;
        }
    }
    public synchronized boolean logInit(int numAssentos){
        String command = "from teste import *\nn = "+numAssentos+"\nb = [1,[0] * n]\n\n";
        try {
            Files.write(this.log,command.getBytes());
            notifyAll();
            return true;
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }
}