package com.company;

import java.io.File;

public class Main {

    public static void main(String[] args) {
	    File log = new File(args[2]);
	    ReservationSystem sys = new ReservationSystem(Integer.parseInt(args[1]),log);
    }
}
