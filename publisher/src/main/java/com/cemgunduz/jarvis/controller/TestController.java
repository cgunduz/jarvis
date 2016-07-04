package com.cemgunduz.jarvis.controller;

import com.cemgunduz.jarvis.configuration.PublisherConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cem on 04/07/16.
 */

@RestController
public class TestController {

    @Autowired
    PublisherConfiguration publisherConfiguration;

    @RequestMapping("/test")
    public String testEndpoint()
    {
        return publisherConfiguration.getDummy() + " - " + publisherConfiguration.getGlobalConfig();
    }
}
