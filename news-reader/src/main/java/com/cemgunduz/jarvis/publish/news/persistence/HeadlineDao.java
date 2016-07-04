package com.cemgunduz.jarvis.publish.news.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by cem on 04/07/16.
 */
public interface HeadlineDao extends MongoRepository<Headline,String> {}
