package com.company;
import java.io.IOException;
import java.nio.file.*;


public class Main {
    public static void main(String[] args) {//A main serve apenas para parsear os argumentos do programa e instanciar o sistema de Reserva.
        Path p = Paths.get(args[0]);

        if (!Files.exists(p, LinkOption.NOFOLLOW_LINKS)){
            try {
                Files.createFile(p);
            }catch (IOException ex){
                System.err.println("Erro ao criar arquivo de log");
            }
        }

        ReservationSystem sys = new ReservationSystem(Integer.parseInt(args[1]),p);
        sys.init();
    }
}
