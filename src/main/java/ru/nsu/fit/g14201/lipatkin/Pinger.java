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
import se.sics.kompics.timer.SchedulePeriodicTimeout;
import se.sics.kompics.timer.Timer;

import java.util.UUID;

public class Pinger extends ComponentDefinition {

    private static final Logger LOG = LoggerFactory.getLogger(Pinger.class);

    Positive<PingPongPort> ppp = requires(PingPongPort.class);
    Positive<Timer> timer = requires(Timer.class);

    private long counter = 0;
    private UUID timerId;

    // From Positive to Negative. Negative requests a Ping and Positive sends out a Ping.

    // Conversely a component that requires service A:
    // - sends out events that are specified as requests in A
    // - handles events that are specified as indications in A
    // (thus are in a sense replies to its own requests).

    Handler<Start> startHandler = new Handler<Start>(){
        public void handle(Start event) {
            SchedulePeriodicTimeout spt = new SchedulePeriodicTimeout(0, 1000);
            PingTimeout timeout = new PingTimeout(spt);
            spt.setTimeoutEvent(timeout);
            trigger(spt, timer);
            timerId = timeout.getTimeoutId();

            //trigger(new Ping(), ppp);       // sends Ping on channel
        }
    };
    Handler<Pong> pongHandler = new Handler<Pong>(){
        public void handle(Pong event) {
            counter++;
            LOG.info("Got Pong #{}!", counter);
            //trigger(new Ping(), ppp);
            //trigger(new Ping(), ppp);
            //answer(event, new )
        }
    };
    Handler<PingTimeout> timeoutHandler = new Handler<PingTimeout>() {
        public void handle(PingTimeout event) {
            trigger(new Ping(), ppp);
        }
    };
    {
        subscribe(startHandler, control);
        subscribe(pongHandler, ppp);
        subscribe(timeoutHandler, timer);
    }
}
