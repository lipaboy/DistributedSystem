package ru.nsu.fit.g14201.lipatkin;

import se.sics.kompics.Component;
import se.sics.kompics.ComponentDefinition;
import se.sics.kompics.Init;

/**
 * Created by castiel on 08.09.2017.
 */
public class Parent extends ComponentDefinition {
    Component pinger = create(Pinger.class, Init.NONE);
    Component ponger = create(Ponger.class, Init.NONE);
}