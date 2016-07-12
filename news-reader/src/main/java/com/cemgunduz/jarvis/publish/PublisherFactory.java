package com.cemgunduz.jarvis.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.inject.Named;

/**
 * Created by cem on 05/07/16.
 */

@Component
public class PublisherFactory {

    @Named("NewsPublisher")
    Publisher newsPublisher;

    public Publisher forPublishable(Publishable publishable)
    {
        if(publishable.getType().equals(Publishable.Type.EKSISOZLUK))
            return newsPublisher;

        return newsPublisher;
    }
}
