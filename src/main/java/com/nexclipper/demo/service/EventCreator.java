package com.nexclipper.demo.service;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@Slf4j
/**
 * Generate Logs
 */
public class EventCreator {

    @Value("${application.text}")
    String sampleLog;

    @Value("${application.logSize}")
    String logSize;

    @Scheduled(fixedRate = 1000)
    public void create1() {
        logToConsole(0);
    }

    @Scheduled(fixedRate = 1000)
    public void create2() {
       logToConsole(1);
    }

    @Scheduled(fixedRate = 1000)
    public void create3() {
        logToConsole(2);
//        int size = 50;
//        while(size>0){
//            log.info(sampleLog +"Unsafe statement written to the binary log using statement format since BINLOG_FORMAT = STATEMENT. The statement is unsafe because it uses a LIMIT clause. This is unsafe because the set of rows included cannot be predicted.Slave SQL thread exiting, replication stopped in log 'dbserver-2-bin.000033''.");
//            size--;
//        }
    }

    private void logToConsole(int position){
        String log1 = logSize.split(",")[position];
        int size = Integer.parseInt(log1.split("\\|")[0]);
        int multiple = Integer.parseInt(log1.split("\\|")[1]);
        while(size>0){
            String logStr = "";
            while(multiple>0){
                logStr=logStr+sampleLog;
                multiple--;
            }
            log.info(logStr);
            size--;
        }
    }

}
