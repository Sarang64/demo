package com.nexclipper.demo.controller;

import com.nexclipper.demo.service.ThreadExecutorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
@Slf4j
public class AppController {

    @Autowired
    ThreadExecutorServiceImpl impl;

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        log.info("Ping Received..");
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/execute")
    public ResponseEntity<String> execute(){
        try {
            impl.execute();
        } catch (InterruptedException e) {
            log.error("Error occurred {}", e.getMessage());
        }
        return ResponseEntity.ok("Executed");
    }
}
