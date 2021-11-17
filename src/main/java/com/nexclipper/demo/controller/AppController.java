package com.nexclipper.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
@Slf4j
public class AppController {

    @Value("${application.text}")
    String sampleLog;

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        log.info("Ping Received..");
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generate(@RequestParam Integer noOfLogs, @RequestParam Integer sizeMultiple){
        while(noOfLogs>0){
            noOfLogs--;
            String logStr = "";
            int size = sizeMultiple;
            while (size>0){
                logStr = logStr + sampleLog;
                size--;
            }
            log.info(logStr);
        }
        return ResponseEntity.ok("Success");
    }
}
