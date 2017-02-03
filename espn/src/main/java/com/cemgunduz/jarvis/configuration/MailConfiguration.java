package com.cemgunduz.jarvis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cem on 05/07/16.
 */

@RefreshScope
@Configuration
public class MailConfiguration {

    @Value("${broadcast.address}")
    private String from;

    @Value("${broadcast.recipients}")
    private String to;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
