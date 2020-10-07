package com.dedup4.storage.webapp.repository;

import com.dedup4.storage.webapp.domain.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 26, 2016.
 */
public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    List<Feedback> findByUsername(String username);
}
