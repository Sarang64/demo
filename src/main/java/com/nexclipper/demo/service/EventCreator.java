package com.nexclipper.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
//@Slf4j
/**
 * Generate Logs
 */
public class EventCreator {
    private static Logger log = LoggerFactory.getLogger(EventCreator.class);
    @Value("${application.text}")
    String sampleLog;

    @Scheduled(fixedRate = 1000)
    public void create1() {
        int size = 30;
        while(size>0){
            log.error(sampleLog);
            size--;
        }
    }

    @Scheduled(fixedRate = 1000)
    public void create2() {
        int size = 30;
        while(size>0){
            log.info(sampleLog+sampleLog);
            size--;
        }
    }

    @Scheduled(fixedRate = 1000)
    public void create3() {
        int size = 50;
        while(size>0){
            log.info(sampleLog +"Unsafe statement written to the binary log using statement format since BINLOG_FORMAT = STATEMENT. The statement is unsafe because it uses a LIMIT clause. This is unsafe because the set of rows included cannot be predicted.Slave SQL thread exiting, replication stopped in log 'dbserver-2-bin.000033''.");
            size--;
        }
    }

}