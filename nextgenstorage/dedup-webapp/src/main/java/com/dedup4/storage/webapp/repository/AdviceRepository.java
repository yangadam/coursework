package com.dedup4.storage.webapp.repository;

import com.dedup4.storage.webapp.domain.Advice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 26, 2016.
 */
public interface AdviceRepository extends MongoRepository<Advice, String> {
    List<Advice> findByUsername(String username);
}
