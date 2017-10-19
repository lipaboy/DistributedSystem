package ru.nsu.fit.g14201.lipatkin;

import se.sics.kompics.Component;
import se.sics.kompics.ComponentDefinition;
import se.sics.kompics.Init;
import se.sics.kompics.timer.Timer;
import se.sics.kompics.timer.java.JavaTimer;

/**
 * Created by castiel on 08.09.2017.
 */

public class Parent extends ComponentDefinition {
    Component pinger = create(Pinger.class, Init.NONE); // Has positive port
    Component pinger2 = create(Pinger.class, Init.NONE); // Has positive port
    Component ponger = create(Ponger.class, Init.NONE); // Has negative port
    Component timer = create(JavaTimer.class, Init.NONE);

    //            port    port
    // [ Pinger   +]=-    +=[-   Ponger ]

    {
        // Create channel
        connect(pinger.getNegative(PingPongPort.class), // getNegative == port's request == Ping
                ponger.getPositive(PingPongPort.class));// getPositive == port's indication == Pong
        connect(pinger.getNegative(Timer.class), timer.getPositive(Timer.class));
        connect(pinger2.getNegative(PingPongPort.class), // getNegative == port's request == Ping
                ponger.getPositive(PingPongPort.class));// getPositive == port's indication == Pong
        connect(pinger2.getNegative(Timer.class), timer.getPositive(Timer.class));
    }
}
