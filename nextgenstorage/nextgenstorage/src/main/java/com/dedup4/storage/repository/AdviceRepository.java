package com.dedup4.storage.repository;

import com.dedup4.storage.domain.Advice;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Yang Mengmeng Created on Mar 26, 2016.
 */
public interface AdviceRepository extends MongoRepository<Advice, String> {
}
