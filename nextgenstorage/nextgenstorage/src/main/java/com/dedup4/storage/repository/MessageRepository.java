package com.dedup4.storage.repository;

import com.dedup4.storage.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 29, 2016.
 */
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByUsernameToIn(Collection<String> collection);
}
