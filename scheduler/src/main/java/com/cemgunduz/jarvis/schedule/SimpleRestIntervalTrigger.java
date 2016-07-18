package com.cemgunduz.jarvis.schedule;

import com.cemgunduz.jarvis.schedule.jobs.Job;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.client.RestTemplate;

/**
 * Created by cem on 18/07/16.
 */
public class SimpleRestIntervalTrigger implements Job{

    private String endpoint;
    private Long milliseconds;

    public SimpleRestIntervalTrigger(String endpoint, Long milliseconds) {
        this.endpoint = endpoint;
        this.milliseconds = milliseconds;
    }

    @Override
    public void execute() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(endpoint, Object.class);
    }

    @Override
    public Long getFixedInterval() {
        return milliseconds;
    }
}
