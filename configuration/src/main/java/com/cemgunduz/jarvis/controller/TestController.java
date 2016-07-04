package com.cemgunduz.jarvis.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cem on 30/06/16.
 */
@RestController
public class TestController {

    @Autowired
    private EurekaClient discoveryClient;

    @RequestMapping("/")
    public String test()
    {
        return "Hey !";
    }

    @RequestMapping("/test")
    public String mytest()
    {
        return "Hey !";
    }
}
