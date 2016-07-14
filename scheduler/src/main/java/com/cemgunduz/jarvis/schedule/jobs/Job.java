package com.cemgunduz.jarvis.schedule.jobs;

import org.springframework.scheduling.support.CronTrigger;

/**
 * Created by cem on 14/07/16.
 */
public interface Job {

    void execute();
    CronTrigger getCronTrigger();
}
