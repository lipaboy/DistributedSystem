package ru.nsu.fit.g14201.lipatkin;

/**
 * Created by castiel on 07.09.2017.
 */
import se.sics.kompics.Kompics;
//import ru.nsu.fit.g14201.lipatkin.HelloComponent;

public class Main {
    public static void main(String[] args) {
        Kompics.createAndStart(HelloComponent.class);   //send Start event to HelloComponent
    }
}