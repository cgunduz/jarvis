package com.cemgunduz.jarvis.controller;

import com.cemgunduz.jarvis.publish.Publishable;
import com.cemgunduz.jarvis.publish.news.News;
import com.cemgunduz.jarvis.publish.news.NewsPublisher;
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
                "a","b","c", "d", null, Publishable.Severity.HIGH);

        newsPublisher.publish(news);
    }
}
