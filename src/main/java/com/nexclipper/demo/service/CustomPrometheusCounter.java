package com.nexclipper.demo.service;

import io.micrometer.core.instrument.AbstractMeter;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.util.MeterEquivalence;

import java.util.concurrent.atomic.DoubleAdder;

public class CustomPrometheusCounter extends AbstractMeter implements Counter {
    private DoubleAdder count = new DoubleAdder();

    public CustomPrometheusCounter(Id id) {
        super(id);
    }

    public void increment(double amount) {
        this.count.add(amount);
    }

    public double count() {
        return this.count.doubleValue();
    }

    public boolean equals(Object o) {
        return MeterEquivalence.equals(this, o);
    }

    public int hashCode() {
        return MeterEquivalence.hashCode(this);
    }
}
