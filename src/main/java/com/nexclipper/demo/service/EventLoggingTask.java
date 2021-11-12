package com.nexclipper.demo.service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Slf4j
public class EventLoggingTask implements Callable<String> {
    private final String name;
    public EventLoggingTask(String name){
        this.name = name;
    }

    @Override
    public String call() throws InterruptedException {
        long duration = (long) (Math.random() * 10);
        TimeUnit.SECONDS.sleep(duration);
        log.info("Message {}", this.name);
        return "success";
    }
}
