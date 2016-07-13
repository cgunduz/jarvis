package com.cemgunduz.jarvis.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
/**
 * Created by cem on 05/07/16.
 */

@Component
public class PublisherFactory {

    @Autowired
    @Qualifier("NewsPublisher")
    Publisher newsPublisher;

    public Publisher forPublishable(Publishable publishable)
    {
        if(publishable.getType().equals(Publishable.Type.EKSISOZLUK))
            return newsPublisher;

        return newsPublisher;
    }
}
