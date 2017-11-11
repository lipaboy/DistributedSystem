package ru.nsu.fit.g14201.lipatkin;

import se.sics.kompics.Direct;
import se.sics.kompics.network.Transport;

/**
 * Created by RARETA on 09.09.2017.
 */
public class Ping extends TMessage
{
    public Ping(TAddress src, TAddress dst) {
        super(src, dst, Transport.TCP);
    }
}
