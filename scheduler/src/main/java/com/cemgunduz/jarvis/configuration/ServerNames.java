package com.cemgunduz.jarvis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cem on 04/07/16.
 */

@RefreshScope
@Configuration
public class ServerNames {

    @Value("${server.names.news_reader}")
    private String NEWS_READER;

    public String getNEWS_READER() {
        return NEWS_READER;
    }
}
