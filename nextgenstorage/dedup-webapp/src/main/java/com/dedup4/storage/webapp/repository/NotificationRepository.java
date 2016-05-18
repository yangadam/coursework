package com.dedup4.storage.webapp.repository;

import com.dedup4.storage.webapp.domain.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 29, 2016.
 */
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByUsernameToIn(Collection<String> collection);
}
