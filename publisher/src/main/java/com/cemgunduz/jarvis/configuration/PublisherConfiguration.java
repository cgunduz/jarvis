package com.cemgunduz.jarvis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cem on 04/07/16.
 */

@RefreshScope
@Configuration
public class PublisherConfiguration {

    @Value("${dummy}")
    private String dummy;

    @Value("${globalConfig}")
    private String globalConfig;

    public String getDummy() {
        return dummy;
    }

    public String getGlobalConfig() {
        return globalConfig;
    }
}
