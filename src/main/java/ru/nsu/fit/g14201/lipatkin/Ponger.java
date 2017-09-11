package ru.nsu.fit.g14201.lipatkin;

import se.sics.kompics.ComponentDefinition;
/**
 * Created by castiel on 08.09.2017.
 */
import se.sics.kompics.Handler;
import se.sics.kompics.Negative;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Ponger extends ComponentDefinition {

    private static final Logger LOG = LoggerFactory.getLogger(Ponger.class);

    Negative<PingPongPort> ppp = provides(PingPongPort.class);

    // Component provides service A:
    // - handles events that are specified as requests in A (Ping)
    // - sends out events that are specified as indications in A (Pong)

    // Input: Ping
    // Output: Pong

    private long counter = 0;

    Handler<Ping> pingHandler = new Handler<Ping>(){
        public void handle(Ping event) {
            counter++;
            LOG.info("Got Ping #{}!", counter);
            trigger(new Pong(), ppp);
        }
    };
    {
        subscribe(pingHandler, ppp);
    }
}
