package ru.nsu.fit.g14201.lipatkin;

import se.sics.kompics.*;

/**
 * Created by castiel on 08.09.2017.
 */

import se.sics.kompics.ComponentDefinition;
import se.sics.kompics.Positive;
import se.sics.kompics.Handler;
import se.sics.kompics.Start;
import se.sics.kompics.timer.*;
import se.sics.kompics.network.Network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;

public class Pinger extends ComponentDefinition {

    private static final Logger LOG = LoggerFactory.getLogger(Pinger.class);

    Positive<Network> net = requires(Network.class);
    //Positive<PingPongPort> ppp = requires(PingPongPort.class);
    Positive<Timer> timer = requires(Timer.class);

    private long counter = 0;
    private UUID timerId;
    private final TAddress self;

    public Pinger(Init init) {
        this.self = init.self;
    }

    // From Positive to Negative. Negative requests a Ping and Positive sends out a Ping.

    // Conversely a component that requires service A:
    // - sends out events that are specified as requests in A (Ping)
    // - handles events that are specified as indications in A  (Pong)
    // (thus are in a sense replies to its own requests).

    Handler<Start> startHandler = new Handler<Start>(){
        public void handle(Start event) {
            SchedulePeriodicTimeout spt = new SchedulePeriodicTimeout(0, 1000);
            PingTimeout timeout = new PingTimeout(spt);
            spt.setTimeoutEvent(timeout);
            trigger(spt, timer);
            timerId = timeout.getTimeoutId();
        }
    };
    Handler<Pong> pongHandler = new Handler<Pong>(){
        public void handle(Pong event) {
            counter++;
            LOG.info("Got Pong #{}!", counter);
        }
    };
    Handler<PingTimeout> timeoutHandler = new Handler<PingTimeout>() {
        public void handle(PingTimeout event) {
            trigger(new Ping(self, self), net);
        }
    };
    {
        subscribe(startHandler, control);
        subscribe(pongHandler, net);
        subscribe(timeoutHandler, timer);
    }

    public static class PingTimeout extends Timeout {
        public PingTimeout(SchedulePeriodicTimeout spt) {
            super(spt);
        }
    }

    public static class Init extends se.sics.kompics.Init<Pinger> {
        public final TAddress self;
        public Init(TAddress self) {
            this.self = self;
        }
    }
}


