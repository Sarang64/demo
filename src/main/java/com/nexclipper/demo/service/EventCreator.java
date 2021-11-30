package com.nexclipper.demo.service;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
@Slf4j
/**
 * Generate Logs
 */
public class EventCreator {

    @Value("${application.text}")
    String sampleLog;

    @Value("${application.logs.enable}")
    Boolean logsEnable;

    @Scheduled(fixedRate = 1000)
    public void create1() {
        if(logsEnable){
            int size = 30;
            while(size>0){
                //log.info(sampleLog);
                logException();
                size--;
            }
        }
    }

    @Scheduled(fixedRate = 1000)
    public void create2() {
        if(logsEnable){
            int size = 30;
            while(size>0){
                log.info(sampleLog+sampleLog);
                size--;
            }
        }
    }

    @Scheduled(fixedRate = 1000)
    public void create3() {
        if(logsEnable){
            int size = 50;
            while(size>0){
                log.info(sampleLog +"Unsafe statement written to the binary log using statement format since BINLOG_FORMAT = STATEMENT. The statement is unsafe because it uses a LIMIT clause. This is unsafe because the set of rows included cannot be predicted.Slave SQL thread exiting, replication stopped in log 'dbserver-2-bin.000033''.");
                size--;
            }
        }
    }

    //sample file based exception
    private void logException() {
        try {
            FileWriter fw = new FileWriter(new
                    File("/home/path/doesnot/exist/myFile.txt"));
            fw.write("Sample data");
            fw.close();
        } catch (IOException e) {
            log.error("Unable to open file", e);
        }
    }

}
