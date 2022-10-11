package com.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.smart.property.AppProperties;
import com.smart.property.FileStorageProperties;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
@EnableConfigurationProperties({
    FileStorageProperties.class,
    AppProperties.class
})
public class DemoApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		 SpringApplication.run(DemoApplication.class, args);
	}
	
	
	
}

