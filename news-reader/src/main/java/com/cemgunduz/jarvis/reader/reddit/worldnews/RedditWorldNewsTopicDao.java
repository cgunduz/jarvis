package com.cemgunduz.jarvis.reader.reddit.worldnews;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by cem on 08/08/16.
 */
public interface RedditWorldNewsTopicDao extends MongoRepository<RedditWorldNewsTopic ,String> {

}
