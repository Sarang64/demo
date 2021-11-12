package com.nexclipper.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ThreadExecutorServiceImpl {

    public void execute() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(25);
        List<EventLoggingTask> taskList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            EventLoggingTask task = new EventLoggingTask("Task-" + i);
            taskList.add(task);
        }
        executorService.invokeAll(taskList);
        executorService.shutdown();
    }
}
