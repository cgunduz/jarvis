package com.cemgunduz.jarvis.reader.eksisozluk;

import com.cemgunduz.jarvis.popularity.PopularityChecker;
import com.cemgunduz.jarvis.popularity.impl.ExponentialPopularityChecker;
import com.cemgunduz.jarvis.popularity.impl.LinearPopularityChecker;
import com.cemgunduz.jarvis.publish.Publishable;
import com.cemgunduz.jarvis.publish.Publisher;
import com.cemgunduz.jarvis.publish.PublisherFactory;
import com.cemgunduz.jarvis.reader.PublishMethod;
import com.cemgunduz.jarvis.reader.Reader;
import com.cemgunduz.jarvis.reader.eksisozluk.persistence.EksiTopic;
import com.cemgunduz.jarvis.reader.eksisozluk.persistence.EksiTopicDao;
import com.netflix.discovery.converters.Auto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cem on 12/07/16.
 */

@Component
public class EksisozlukTopicReader implements Reader {

    private static final String EKSI_HOME = "http://www.eksisozluk.com";

    private static final Integer TOPICS_TO_CONSIDER = 10;
    private static final Integer MAX_CAP_PER_DAY = 400;

    @Autowired
    EksiTopicDao eksiTopicDao;

    @Override
    public List<Publishable> read() {

        List<Publishable> publishables = new ArrayList<>();

        for(EksiTopic eksiTopic : scrapeTopics())
        {
            List<EksiTopic> topics = eksiTopicDao.findByTopicOrderByCreationDateDesc(eksiTopic.getTopic());

            if(!isProcessableTopic(topics, eksiTopic)) continue;

            boolean notify = false;

            if(eksiTopic.getEntriesToday() > MAX_CAP_PER_DAY) notify = true;
            else
            {
                for(PopularityChecker popularityChecker : getCheckers(topics))
                {
                    if(popularityChecker.isPopular())
                    {
                        notify = true;
                        break;
                    }
                }
            }

            if(notify)
            {
                publishables.add(
                        eksiTopic.toPublishable(Publishable.Severity.HIGH)
                );

                eksiTopic.setNotified(true);
            }

            eksiTopicDao.save(eksiTopic);
        }

        return publishables;
    }

    @Override
    public PublishMethod getPublishMethod() {
        return PublishMethod.NEWS;
    }

    private Set<EksiTopic> scrapeTopics()
    {
        Document doc = null;
        try {
            doc = Jsoup.connect(EKSI_HOME).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements topics = doc.getElementsByClass("topic-list").get(0).getElementsByTag("li");

        Set<EksiTopic> topicSet = new HashSet<>();
        for(Element topic : topics)
        {
            EksiTopic eksiTopic = new EksiTopic();
            eksiTopic.setLink(EKSI_HOME.concat(topic.getElementsByTag("a").attr("href")));

            String entriesToday = topic.getElementsByTag("small").text();

            // Skip this one just an add
            if(entriesToday.isEmpty())
                continue;

            eksiTopic.setEntriesToday(Integer.valueOf(entriesToday));

            String topicName = topic.getElementsByTag("a").text();
            topicName = topicName.substring(0, topicName.indexOf(entriesToday));
            eksiTopic.setTopic(topicName);

            topicSet.add(eksiTopic);
        }

        return topicSet;
    }

    private boolean isProcessableTopic(List<EksiTopic> topics, EksiTopic eksiTopic)
    {
        // First entry, too early to notify
        if(topics == null || topics.isEmpty())
        {
            eksiTopicDao.save(eksiTopic);
            return false;
        }

        // Old News, ignore
        if(topics.get(0).getNotified())
        {
            //return false;
        }

        return true;
    }

    private List<PopularityChecker> getCheckers(List<EksiTopic> topics)
    {
        List<PopularityChecker> checkers = new ArrayList<>();
        if(topics.size() >= ExponentialPopularityChecker.getMinimumAmountOfItemsNeeded())
        {
            checkers.add(new ExponentialPopularityChecker(topics));
        }
        else if(topics.size() >= LinearPopularityChecker.getMinimumAmountOfItemsNeeded())
        {
            checkers.add(new LinearPopularityChecker(topics));
        }

        return checkers;
    }
}
