package ru.nsu.fit.g14201.lipatkin;

import se.sics.kompics.ComponentDefinition;
/**
 * Created by castiel on 08.09.2017.
 */
import se.sics.kompics.Handler;
import se.sics.kompics.Negative;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.sics.kompics.Positive;
import se.sics.kompics.network.Network;

public class Ponger extends ComponentDefinition {

    private static final Logger LOG = LoggerFactory.getLogger(Ponger.class);

    Positive<Network> net = requires(Network.class);

    private long counter = 0;
    private final TAddress self;

    public Ponger(Init init) {
        this.self = init.self;
    }

    // Component provides service A:
    // - handles events that are specified as requests in A (Ping)
    // - sends out events that are specified as indications in A (Pong)

    // Input: Ping
    // Output: Pong

    Handler<Ping> pingHandler = new Handler<Ping>(){
        public void handle(Ping event) {
            counter++;
            LOG.info("Got Ping #{}!", counter);
            trigger(new Pong(self, event.getSource()), net);
            //trigger(new Pong(), ppp);
            //answer(event, new Pong());
        }
    };

    {
        subscribe(pingHandler, net);
    }

    public static class Init extends se.sics.kompics.Init<Ponger> {
        public final TAddress self;
        public Init(TAddress self) {
            this.self = self;
        }
    }
}
