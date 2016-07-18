package com.cemgunduz.jarvis.schedule;

import com.cemgunduz.jarvis.schedule.jobs.Job;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

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

            /*
            TODO : Trigger mechanism has a bug and invokes requests for multiple times ? Find out why.
            StringBuilder cronTriggerString = new StringBuilder();
            cronTriggerString.append(triggerBuilder(schedule.getInterval().getSeconds()));
            cronTriggerString.append(triggerBuilder(schedule.getInterval().getMins()));
            cronTriggerString.append(triggerBuilder(schedule.getInterval().getHours()));
            cronTriggerString.append("* * ?");

            CronTrigger cronTrigger = new CronTrigger(cronTriggerString.toString());
            */

            int milliSeconds = schedule.getInterval().getSeconds() * 1000 +
                    schedule.getInterval().getMins() * 60 * 1000 +
                    schedule.getInterval().getHours() * 60 * 60 * 1000;

            Job job = new SimpleRestIntervalTrigger(endpoint, Long.valueOf(milliSeconds));
            jobList.add(job);

            taskScheduler.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    job.execute();
                }
            }, job.getFixedInterval());
        }
    }

    private String triggerBuilder(Integer input)
    {
        if(input.equals(0)) return CRON_TRIGGER_DEFAULT;
        return "*/".concat(String.valueOf(input).concat(" "));
    }
}
