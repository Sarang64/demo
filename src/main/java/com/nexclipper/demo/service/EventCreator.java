package com.nexclipper.demo.service;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventCreator {

    @Scheduled(fixedRate = 100)
    @Timed(description = "Time taken to create junk event log")
    public void create() {
      log.debug("Event created");
            try{
                log.info("=============================================");
                String str = null;
                str= str.substring(0);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

}
