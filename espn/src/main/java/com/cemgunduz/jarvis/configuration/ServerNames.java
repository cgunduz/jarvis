package com.cemgunduz.jarvis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cem on 03/02/17.
 */

@RefreshScope
@Configuration
public class ServerNames {

    @Value("${server.names.publisher}")
    private String PUBLISHER;

    public String getPUBLISHER() {
        return PUBLISHER;
    }
}
