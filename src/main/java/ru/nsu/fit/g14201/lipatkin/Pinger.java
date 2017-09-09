package ru.nsu.fit.g14201.lipatkin;

import se.sics.kompics.ComponentDefinition;
import se.sics.kompics.Handler;
import se.sics.kompics.Positive;
import se.sics.kompics.Start;

/**
 * Created by RARETA on 09.09.2017.
 */
public class Pinger extends ComponentDefinition {
    Positive<PingPongPort> ppp = requires(PingPongPort.class);

    Handler<Start> startHandler = new Handler<Start>(){
        public void handle(Start event) {
            trigger(new Ping(), ppp);
        }
    };
    Handler<Pong> pongHandler = new Handler<Pong>(){
        public void handle(Pong event) {
            System.out.println("Got a Pong!");
            trigger(new Ping(), ppp);
        }
    };
    {
        subscribe(startHandler, control);
        subscribe(pongHandler, ppp);
    }
}
