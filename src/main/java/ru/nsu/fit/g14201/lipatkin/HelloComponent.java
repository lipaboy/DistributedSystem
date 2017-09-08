package ru.nsu.fit.g14201.lipatkin;

import se.sics.kompics.ComponentDefinition;
import se.sics.kompics.Handler;
import se.sics.kompics.Start;
import se.sics.kompics.Kompics;

public class HelloComponent extends ComponentDefinition {
    {
        Handler<Start> startHandler = new Handler<Start>() {
            @Override
            public void handle(Start event) {
                System.out.println("Hello World!");
                System.exit(0);
            }
        };
        subscribe(startHandler, control);
    }
}