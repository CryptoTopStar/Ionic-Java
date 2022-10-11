package com.smart.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledDeviceTasks {

	
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduledDeviceTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    /*@Scheduled(fixedRate = 180000, initialDelay = 5000)
    public void withFixedRate() {
    	logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
    }*/
}

