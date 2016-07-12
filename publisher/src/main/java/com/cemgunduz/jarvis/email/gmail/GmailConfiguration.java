package com.cemgunduz.jarvis.email.gmail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cem on 05/07/16.
 */

@RefreshScope
@Configuration
public class GmailConfiguration {

    @Value("${gmail.jarvis.username}")
    private String username;

    @Value("${gmail.jarvis.password}")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
