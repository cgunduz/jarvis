package com.cemgunduz.jarvis.schedule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by cem on 13/07/16.
 */

@Configuration
@ConfigurationProperties("schedule_configuration")
public class ScheduleConfiguration {

    private List<Schedule> schedules;

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public static class Schedule {

        private String name;

        private String service;

        private String endpoint;
        private Interval interval;

        public String getName() {
            return name;
        }

        public String getService() {
            return service;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public Interval getInterval() {
            return interval;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setService(String service) {
            this.service = service;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public void setInterval(Interval interval) {
            this.interval = interval;
        }

        public static class Interval {

            private Integer hours;
            private Integer mins;
            private Integer seconds;

            public Integer getHours() {
                return hours;
            }

            public Integer getMins() {
                return mins;
            }

            public Integer getSeconds() {
                return seconds;
            }

            public void setHours(Integer hours) {
                this.hours = hours;
            }

            public void setMins(Integer mins) {
                this.mins = mins;
            }

            public void setSeconds(Integer seconds) {
                this.seconds = seconds;
            }
        }
    }

}
