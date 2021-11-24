package com.nexclipper.demo.service;

import io.micrometer.core.instrument.MeterRegistry;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
/**
 * Produce custom metrics
 */
public class CustomMetricService {
    private MeterRegistry meterRegistry;
//    Counter lightOrderCounter;
    private AtomicInteger testGauge;
    static int i=0;
    static int limit1St=0;
    @Value("${application.limit1}")
    public Integer limit1;

    @Value("${application.limit2}")
    public Integer limit2;

    @Value("${application.metric-value.start}")
    public Integer startValue;

    @Value("${application.metric-value.end}")
    public Integer endValue;

    public CustomMetricService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Scheduled(fixedRate = 2000)
    public void metric1() {
        if(i<= limit1) {
            meterRegistry.gauge("metric"+i+"_total", randomInt());
            i++;
        }else {
            i=0;
        }
    }

    @Scheduled(fixedRate = 2000)
    public void metric2() {
        if(limit1St==0)
            limit1St=limit1+1;

        if(limit1St <= limit2) {
            meterRegistry.gauge("metric"+limit1St+randomStr()+"_total", randomInt());
            limit1St++;
        }else {
            limit1St=limit1+1;
        }
    }

    public String randomStr() {

        int length = 15;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedString;
    }

    public int randomInt(){
        Random r = new Random();
        int result = r.nextInt(endValue-startValue) + startValue;
        return result;
    }
}
