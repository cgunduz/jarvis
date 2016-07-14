package com.cemgunduz.jarvis.controller;

import com.cemgunduz.jarvis.schedule.ScheduleConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cem on 13/07/16.
 */

@RestController
public class ScheduleController {

    @Autowired
    ScheduleConfiguration scheduleConfiguration;

    @RequestMapping("/schedule")
    public String test()
    {
        String asd = scheduleConfiguration.getSchedules().get(0).getName();
        return asd;
    }
}
