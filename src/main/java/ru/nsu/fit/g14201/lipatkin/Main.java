package ru.nsu.fit.g14201.lipatkin;

/**
 * Created by castiel on 07.09.2017.
 */
import se.sics.kompics.Kompics;

public class Main {
    public static void main(String[] args) {
        Kompics.createAndStart(Parent.class, 2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.exit(1);
        }
        Kompics.shutdown();
        System.exit(0);
    }
}