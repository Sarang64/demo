package com.nexclipper.demo.controller;

import com.nexclipper.demo.service.ThreadExecutorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @GetMapping("/generate")
    public ResponseEntity<String> generate(){
        int limit = 10000;
        while(limit>0){
            try{
                String str = null;
                str= str.substring(0);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            limit--;
        }
        return ResponseEntity.ok("Success");
    }
}
