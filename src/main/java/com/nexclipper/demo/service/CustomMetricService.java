package com.nexclipper.demo.service;

import com.nexclipper.demo.bean.Order;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
/**
 * Produce custom metrics
 */
public class CustomMetricService {
    private MeterRegistry meterRegistry;
    Counter lightOrderCounter;
    List<Order> orders = new ArrayList<>();
    static int i=0;
    @Value("${application.limit}")
    public Integer limit;

    @Value("${application.metricValue}")
    public Integer metricValue;
    public CustomMetricService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    private void initOrderCounters(int i) {
        lightOrderCounter = this.meterRegistry.counter("demoapp_number"+i); // 1 - create a counter
    }

    public void orderBeer(Order order) {
        if(i<=limit) {
            initOrderCounters(i);
            orders.add(order);
            lightOrderCounter.increment(metricValue);  // 3 - increment the counter
            i++;
        }
    }
}
