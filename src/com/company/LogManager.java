package com.company;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


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
        this.logWrite(user,"op"+opcode+"("+user.id+","+sAssentos+")\n");
    }

    public synchronized void  alocarAssentoLivre(User user, int assento, int[] assentos){
        int opcode = 2;
        String sAssentos = this.arrayAssentos(assentos);
//        System.out.println("op"+opcode+"("+user.id+","+assento+","+sAssentos+")\n");
        this.logWrite(user,"op"+opcode+"("+user.id+","+assento+","+sAssentos+")\n");
    }

    public synchronized void  alocarAssentoDado(User user, int assento, int[] assentos){
        int opcode = 3;
        String sAssentos = this.arrayAssentos(assentos);
//        System.out.println("op"+opcode+"("+user.id+","+assento+","+sAssentos+")\n");
        this.logWrite(user,"op"+opcode+"("+user.id+","+assento+","+sAssentos+")\n");
    }

    public synchronized void  liberarAssento(User user, int assento, int[] assentos){
        int opcode = 4;
        String sAssentos = this.arrayAssentos(assentos);
//        System.out.println("op"+opcode+"("+user.id+","+assento+","+sAssentos+")\n");
        this.logWrite(user,"op"+opcode+"("+user.id+","+assento+","+sAssentos+")\n");
    }

    public synchronized void logFinal(){
        String command = "\nfim()\n";
        try {
            Files.write(this.log,command.getBytes(),StandardOpenOption.APPEND);
            notifyAll();
        }catch (IOException ex){
            ex.printStackTrace();
        }
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
        /*
            Esta função verifica se o log no path dado existe e se não existe, cria;
            ela pega o template de teste chamado 'teste_Model.py' , e copia pro mesmo diretorio do log criado com o nome '<<variável_testeName>>_<<Nome_do_arquivo_de_log>>.py'
            no arquivo '<<variável_testeName>>_<<Nome_do_arquivo_de_log>>.py' criado é inserido o número de assentos e o import do log;
         */
        String testName = "teste";
        String logName = (this.log.getFileName().toString()+"").replace(".py","");
        String command = "from "+testName+"_"+logName+" import *\ninit("+numAssentos+")\n\n";
        try {
            Files.write(this.log,command.getBytes());
            Path c = Paths.get("teste_Model.py").toAbsolutePath();
            String d = this.log.getParent().toAbsolutePath().toString()+"/"+testName+"_"+logName+".py";
            Path destiny = Paths.get(d);
            Files.copy(c, destiny,REPLACE_EXISTING);
            Files.write(destiny,("\nimport "+logName+"\n").getBytes(),StandardOpenOption.APPEND);
            return true;
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }
}