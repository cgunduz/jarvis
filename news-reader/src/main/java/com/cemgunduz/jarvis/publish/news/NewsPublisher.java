package com.cemgunduz.jarvis.publish.news;

import com.cemgunduz.jarvis.configuration.ServerNames;
import com.cemgunduz.jarvis.publish.Publishable;
import com.cemgunduz.jarvis.publish.Publisher;
import com.cemgunduz.jarvis.publish.news.persistence.Headline;
import com.cemgunduz.jarvis.publish.news.persistence.HeadlineDao;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by cem on 04/07/16.
 */
@Service
public class NewsPublisher implements Publisher {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private HeadlineDao headlineDao;

    @Autowired
    ServerNames serverNames;

    public void publish(Publishable publishable)
    {
        if(publishable.isReportable()) broadcast(publishable);
        if(publishable.isRecordable()) record(publishable);
    }

    private void broadcast(Publishable publishable)
    {
        InstanceInfo info = eurekaClient.getNextServerFromEureka(serverNames.getPUBLISHER(), false);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.postForEntity(info.getHomePageUrl()+"/email", "Hello", String.class);

        System.out.println("Sent !");
    }

    private void record(Publishable publishable)
    {
        Headline headline = Headline.toHeadline(publishable);
        headlineDao.save(headline);
    }
}
