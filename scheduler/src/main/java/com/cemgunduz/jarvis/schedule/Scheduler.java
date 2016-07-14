package com.cemgunduz.jarvis.schedule;

import com.cemgunduz.jarvis.schedule.jobs.Job;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cem on 14/07/16.
 */

@Component
public class Scheduler {

    @Autowired
    EurekaClient eurekaClient;

    @Autowired
    ScheduleConfiguration scheduleConfiguration;

    private final String CRON_TRIGGER_DEFAULT = "* ";

    private List<Job> jobList = new ArrayList<>();

    @Autowired
    TaskScheduler taskScheduler;

    @PostConstruct
    public void init()
    {
        for(ScheduleConfiguration.Schedule schedule : scheduleConfiguration.getSchedules())
        {
            String serviceName = schedule.getService();
            String serviceUrl = eurekaClient.getNextServerFromEureka(serviceName, false).getHomePageUrl();
            String endpoint = serviceUrl.concat(schedule.getEndpoint());

            StringBuilder cronTriggerString = new StringBuilder();
            cronTriggerString.append(triggerBuilder(schedule.getInterval().getSeconds()));
            cronTriggerString.append(triggerBuilder(schedule.getInterval().getMins()));
            cronTriggerString.append(triggerBuilder(schedule.getInterval().getHours()));
            cronTriggerString.append("* * *");

            CronTrigger cronTrigger = new CronTrigger(cronTriggerString.toString());

            Job job = new SimpleRestTrigger(endpoint, cronTrigger);
            jobList.add(job);

            taskScheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    job.execute();
                }
            }, job.getCronTrigger());
        }
    }

    private String triggerBuilder(Integer input)
    {
        if(input.equals(0)) return CRON_TRIGGER_DEFAULT;
        return "*/".concat(String.valueOf(input).concat(" "));
    }
}
