package ru.nsu.fit.g14201.lipatkin;

import se.sics.kompics.ComponentDefinition;
import se.sics.kompics.Handler;
import se.sics.kompics.Negative;

/**
 * Created by RARETA on 09.09.2017.
 */
public class Ponger extends ComponentDefinition {
    Negative<PingPongPort> ppp = provides(PingPongPort.class);

    // Component provides service A:
    // - handles events that are specified as requests in A (Ping)
    // - sends out events that are specified as indications in A (Pong)

    // Input: Ping
    // Output: Pong

    Handler<Ping> pingHandler = new Handler<Ping>(){
        public void handle(Ping event) {
            System.out.println("Got a Ping!");
            trigger(new Pong(), ppp);
        }
    };
    {
        subscribe(pingHandler, ppp);
    }
}
