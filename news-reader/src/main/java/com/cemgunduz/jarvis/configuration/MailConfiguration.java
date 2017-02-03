package com.cemgunduz.jarvis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * Created by cem on 05/07/16.
 */

@RefreshScope
@Configuration
public class MailConfiguration {

    //@Value("${broadcast.address}")
    private String from = "alert@jarvis.com";

    //@Value("${broadcast.recipients}")
    private String to = "dogancemgunduz@gmail.com, zeyneptunalioglu@gmail.com";

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
