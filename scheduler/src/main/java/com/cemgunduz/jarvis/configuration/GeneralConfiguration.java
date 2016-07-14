package com.cemgunduz.jarvis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Created by cem on 13/07/16.
 */

@Configuration
@EnableScheduling
public class GeneralConfiguration {

    @Bean
    public TaskScheduler taskScheduler() {
        //org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
        return new ThreadPoolTaskScheduler();
    }
}
