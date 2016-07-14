package com.cemgunduz.jarvis.schedule;

import com.cemgunduz.jarvis.schedule.jobs.Job;
import org.springframework.http.*;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by cem on 14/07/16.
 */
public class SimpleRestTrigger implements Job {

    private String endpoint;
    private CronTrigger cronTrigger;

    public SimpleRestTrigger(String endpoint, CronTrigger cronTrigger) {
        this.endpoint = endpoint;
        this.cronTrigger = cronTrigger;
    }

    @Override
    public void execute() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(endpoint, Object.class);
    }

    @Override
    public CronTrigger getCronTrigger() {
        return cronTrigger;
    }
}
