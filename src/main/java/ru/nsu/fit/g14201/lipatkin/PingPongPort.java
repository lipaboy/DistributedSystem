package ru.nsu.fit.g14201.lipatkin;

import se.sics.kompics.PortType;

/**
 * Created by RARETA on 09.09.2017.
 */
public class PingPongPort extends PortType {
    // It is highly recommended to only write completely immutable events.
    // Since Kompics will deliver the same event instance to all subscribed handlers in all connected components,
    // writing mutable events can lead to some nasty and difficult to find bugs.
    // For encapsulating collections in a safe manner, the reader is referred Google’s excellent
    // Guava library (which is already a dependency of Kompics core anyway) and its immutable collection types.
    {
        request(Ping.class);
        indication(Pong.class);
    }
}
