package com.cemgunduz.jarvis.publish.news;

import com.cemgunduz.jarvis.configuration.MailConfiguration;
import com.cemgunduz.jarvis.configuration.ServerNames;
import com.cemgunduz.jarvis.email.EmailInput;
import com.cemgunduz.jarvis.email.EmailSendingResponse;
import com.cemgunduz.jarvis.publish.Publishable;
import com.cemgunduz.jarvis.publish.Publisher;
import com.cemgunduz.jarvis.publish.persistence.Headline;
import com.cemgunduz.jarvis.publish.persistence.HeadlineDao;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by cem on 04/07/16.
 */
@Service(value = "NewsPublisher")
public class NewsPublisher implements Publisher {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private HeadlineDao headlineDao;

    @Autowired
    ServerNames serverNames;

    @Autowired
    MailConfiguration mailConfiguration;

    public void publish(Publishable publishable)
    {
        if(publishable.isReportable()) broadcast(publishable);
        if(publishable.isRecordable()) record(publishable);
    }

    private void broadcast(Publishable publishable)
    {
        EmailInput emailInput =
                new EmailInput(publishable, mailConfiguration.getFrom(), mailConfiguration.getTo());

        InstanceInfo info = eurekaClient.getNextServerFromEureka(serverNames.getPUBLISHER(), false);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EmailInput> entity = new HttpEntity<>(emailInput, headers);
        ResponseEntity<EmailSendingResponse> response =
                restTemplate.postForEntity(info.getHomePageUrl()+"/email", entity, EmailSendingResponse.class);

        if(!response.getStatusCode().equals(HttpStatus.OK)
                || !response.getBody().isSent())
        {
            // TODO : Log and warn
            response.getBody().getReason();
        }
    }

    private void record(Publishable publishable)
    {
        Headline headline = Headline.toHeadline(publishable);
        headlineDao.save(headline);
    }
}
