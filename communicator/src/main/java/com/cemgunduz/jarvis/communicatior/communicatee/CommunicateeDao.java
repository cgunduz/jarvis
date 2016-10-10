package com.cemgunduz.jarvis.communicatior.communicatee;

import com.cemgunduz.jarvis.communicatior.people.People;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by cem on 28/09/16.
 */
public interface CommunicateeDao extends MongoRepository<Communicatee,String> {

}
