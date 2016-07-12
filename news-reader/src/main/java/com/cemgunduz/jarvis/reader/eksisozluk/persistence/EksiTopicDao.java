package com.cemgunduz.jarvis.reader.eksisozluk.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by cem on 12/07/16.
 */
public interface EksiTopicDao extends MongoRepository<EksiTopic,String> {

    List<EksiTopic> findByTopic(String topic);
    List<EksiTopic> findByTopicOrderByCreationDateDesc(String topic);
}
