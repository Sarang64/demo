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
    Counter aleOrderCounter;
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
        lightOrderCounter = this.meterRegistry.counter("metric"+i); // 1 - create a counter
        aleOrderCounter = Counter.builder("metric"+i)    // 2 - create a counter using the fluent API
               // .tag("type", "ale")
                .description("Dummy metric for testing")
                .register(meterRegistry);
    }

    public void orderBeer(Order order) {
        if(i<=limit) {
            double number = Math.random();
            initOrderCounters(i);
            orders.add(order);

            if ("light".equals(order.type)) {
                lightOrderCounter.increment(metricValue);  // 3 - increment the counter
            } else if ("ale".equals(order.type)) {
                aleOrderCounter.increment(metricValue);
            }
            i++;
        }
    }
}
