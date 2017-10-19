package ru.nsu.fit.g14201.lipatkin;

import se.sics.kompics.timer.SchedulePeriodicTimeout;
import se.sics.kompics.timer.Timeout;

/**
 * Created by castiel on 19.10.2017.
 */
public class PingTimeout extends Timeout {
    public PingTimeout(SchedulePeriodicTimeout spt) {
        super(spt);
    }
}
