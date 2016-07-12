package com.cemgunduz.jarvis.email.postfix;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cem on 12/07/16.
 */

@RefreshScope
@Configuration
public class PostfixConfiguration {

    @Value("${postfix.host}")
    private String host;

    @Value("${postfix.port}")
    private String port;

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }
}
