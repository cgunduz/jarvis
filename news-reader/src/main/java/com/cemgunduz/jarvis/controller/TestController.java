package com.cemgunduz.jarvis.controller;

import com.cemgunduz.jarvis.publish.Publishable;
import com.cemgunduz.jarvis.publish.news.News;
import com.cemgunduz.jarvis.publish.news.NewsPublisher;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cem on 04/07/16.
 */
@RestController
public class TestController {

    @Autowired
    NewsPublisher newsPublisher;

    @RequestMapping("/test")
    public void testEndpoint()
    {
        News news = new News(
                Publishable.Type.EKSISOZLUK,"b","c", "d", null, Publishable.Severity.HIGH);

        newsPublisher.publish(news);
    }

    @RequestMapping("/hystrixTest")
    @HystrixCommand(fallbackMethod = "hystricsEndpointFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public void hystricsEndpoint()
    {
        throw new RuntimeException("asd");
    }

    public void hystricsEndpointFallback()
    {
        System.out.println("Failed!");
    }
}
