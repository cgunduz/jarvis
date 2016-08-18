package com.cemgunduz.jarvis.reader.reddit.worldnews;

import com.cemgunduz.jarvis.publish.Publishable;
import com.cemgunduz.jarvis.reader.PublishMethod;
import com.cemgunduz.jarvis.reader.Reader;
import com.cemgunduz.jarvis.reader.eksisozluk.persistence.EksiTopic;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cem on 08/08/16.
 */
public class RedditTopicReader implements Reader {

    private static final String REDDIT_WORLD_NEWS_HOME = "https://www.reddit.com/r/worldnews/";

    @Override
    public List<Publishable> read() {

        return null;
    }

    @Override
    public PublishMethod getPublishMethod() {
        return PublishMethod.NEWS;
    }

    private Set<RedditWorldNewsTopic> scrapeTopics()
    {
        Document doc = null;
        try {
            doc = Jsoup.connect(REDDIT_WORLD_NEWS_HOME).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element newsTable = doc.getElementById("siteTable");

        Set<RedditWorldNewsTopic> topicSet = new HashSet<>();
        for(Element topic : newsTable.getElementsByClass("thing"))
        {
            RedditWorldNewsTopic redditWorldNews = new RedditWorldNewsTopic();


            redditWorldNews.setRedditAuthor(topic.attr("data-author"));
            redditWorldNews.setRedditId(topic.attr("id"));
            redditWorldNews.setTimestamp(Long.valueOf(topic.attr("data-timestamp")));
            redditWorldNews.setUrl(topic.attr("data-url"));

            redditWorldNews.setUpvotes(
                    Integer.valueOf(topic.getElementsByClass("unvoted").get(0).text())
            );

            topicSet.add(redditWorldNews);
        }

        return topicSet;
    }

    public static void main(String[] args)
    {
        RedditTopicReader re = new RedditTopicReader();
        re.scrapeTopics();
    }
}
