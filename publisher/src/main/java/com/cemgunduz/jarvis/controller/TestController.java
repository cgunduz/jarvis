package com.cemgunduz.jarvis.controller;

import com.cemgunduz.jarvis.email.gmail.GmailConfiguration;
import com.cemgunduz.jarvis.configuration.PublisherConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cem on 04/07/16.
 */

@RestController
public class TestController {

    @Autowired
    PublisherConfiguration publisherConfiguration;

    @Autowired
    GmailConfiguration gmailConfiguration;

    @RequestMapping("/test")
    public String testEndpoint()
    {
        return publisherConfiguration.getDummy() + " - " + publisherConfiguration.getGlobalConfig();
    }

    @RequestMapping("/test2")
    public String testEndpoint2()
    {
        return gmailConfiguration.getPassword();
    }
}
