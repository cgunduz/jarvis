package com.cemgunduz.jarvis.reader.eksisozluk;

import com.cemgunduz.jarvis.publish.Publishable;
import com.cemgunduz.jarvis.publish.Publisher;
import com.cemgunduz.jarvis.publish.PublisherFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by cem on 12/07/16.
 */

@RestController
@RequestMapping(path = "/eksi")
public class EksiApi {

    @Autowired
    EksisozlukTopicReader eksisozlukTopicReader;

    @Autowired
    PublisherFactory publisherFactory;

    @RequestMapping(method = RequestMethod.GET)
    public String initiate()
    {
        List<Publishable> eksiPublishables = eksisozlukTopicReader.read();
        for(Publishable publishable : eksiPublishables)
        {
            Publisher publisher = publisherFactory.forPublishable(publishable);
            publisher.publish(publishable);
        }

        return "Done ! ";
    }

}
