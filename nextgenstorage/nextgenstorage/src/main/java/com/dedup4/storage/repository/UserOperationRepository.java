package com.dedup4.storage.repository;

import com.dedup4.storage.domain.UserOperation;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Yang Mengmeng Created on Mar 24, 2016.
 */
public interface UserOperationRepository extends MongoRepository<UserOperation, String> {
}
