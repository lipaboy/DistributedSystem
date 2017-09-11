package ru.nsu.fit.g14201.lipatkin;

import se.sics.kompics.ComponentDefinition;

/**
 * Created by castiel on 08.09.2017.
 */
import se.sics.kompics.Handler;
import se.sics.kompics.Positive;
import se.sics.kompics.Start;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pinger extends ComponentDefinition {

    private static final Logger LOG = LoggerFactory.getLogger(Pinger.class);

    Positive<PingPongPort> ppp = requires(PingPongPort.class);

    private long counter = 0;

    // From Positive to Negative. Negative requests a Ping and Positive sends out a Ping.

    // Conversely a component that requires service A:
    // - sends out events that are specified as requests in A
    // - handles events that are specified as indications in A
    // (thus are in a sense replies to its own requests).

    Handler<Start> startHandler = new Handler<Start>(){
        public void handle(Start event) {
            trigger(new Ping(), ppp);       // sends Ping on channel
        }
    };
    Handler<Pong> pongHandler = new Handler<Pong>(){
        public void handle(Pong event) {
            counter++;
            LOG.info("Got Pong #{}!", counter);
            trigger(new Ping(), ppp);
        }
    };
    {
        subscribe(startHandler, control);
        subscribe(pongHandler, ppp);
    }
}
