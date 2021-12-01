package com.nexclipper.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

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
    volatile AtomicLong logCounter = new AtomicLong();

    @Value("${application.logSize}")
    Integer logSize;

    @Scheduled(fixedRate = 1000)
    public void create1() {
        synchronized (this) {
            if (logsEnable) {
                int size = 30;
                while (size > 0) {
                    //log.info(sampleLog);
                    logMessage(1);
                    size--;
                }
            }
        }
    }

    @Scheduled(fixedRate = 1000)
    public void create2() {
        if (logsEnable && logCounter.get() <= logSize) {
            int size = 30;
            while(size>0){
                size--;
                logMessage(2);
            }
        }
    }

    @Scheduled(fixedRate = 1000)
    public void create3() {
        if (logsEnable && logCounter.get() <= logSize) {
            int size = 50;
            while(size>0){
                //log.info(sampleLog +"Unsafe statement written to the binary log using statement format since BINLOG_FORMAT = STATEMENT. The statement is unsafe because it uses a LIMIT clause. This is unsafe because the set of rows included cannot be predicted.Slave SQL thread exiting, replication stopped in log 'dbserver-2-bin.000033''.");
                size--;
                //logCounter.incrementAndGet();
                logMessage(3);
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
            log.error(logCounter.incrementAndGet() +" "+ "Unable to open file", e);
        }
    }

    private void logMessage(int multiple){
        if(logCounter.get() <= logSize){
            switch (multiple){
                case 1:
                    //logging multiline exception log
                    logException();
                    break;
                case 2:
                    //logging single line 1000 bytes log
                    log.info(logCounter.incrementAndGet() +" "+ sampleLog+sampleLog);
                    break;
                case 3:
                    //logging single line 1300 bytes log
                    log.info(logCounter.incrementAndGet() +" "+sampleLog +"Unsafe statement written to the binary log using statement format since BINLOG_FORMAT = STATEMENT. The statement is unsafe because it uses a LIMIT clause. This is unsafe because the set of rows included cannot be predicted.Slave SQL thread exiting, replication stopped in log 'dbserver-2-bin.000033''.");
                    break;
            }
        }
    }

    public Long getCount(){
        return logCounter.get();
    }

}
