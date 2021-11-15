package com.nexclipper.demo.service;

import com.nexclipper.demo.bean.Order;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BeerService {
    private MeterRegistry meterRegistry;
    static int i =0 ;
    Counter lightOrderCounter;
    Counter aleOrderCounter;
    List<Order> orders = new ArrayList<>();

    public BeerService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        //initOrderCounters();
    }

    private void initOrderCounters(int i) {
        lightOrderCounter = this.meterRegistry.counter("metric"+i); // 1 - create a counter
        aleOrderCounter = Counter.builder("metric"+i)    // 2 - create a counter using the fluent API
               // .tag("type", "ale")
                .description("Dummy metric for testing")
                .register(meterRegistry);
    }

    @Timed(description = "Time spent to place dummy metric")
    public void orderBeer(Order order) {
        double number = Math.random();
        initOrderCounters(i++);
        orders.add(order);

        if ("light".equals(order.type)) {
            lightOrderCounter.increment(1.0);  // 3 - increment the counter
        } else if ("ale".equals(order.type)) {
            aleOrderCounter.increment();
        }
    }
}
