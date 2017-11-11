package ru.nsu.fit.g14201.lipatkin;

import se.sics.kompics.Direct;
import se.sics.kompics.network.Transport;

/**
 * Created by RARETA on 09.09.2017.
 */
public class Pong extends TMessage {
    public Pong(TAddress src, TAddress dst) {
        super(src, dst, Transport.TCP);
    }
}