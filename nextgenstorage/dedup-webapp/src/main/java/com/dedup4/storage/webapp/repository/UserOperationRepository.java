package com.dedup4.storage.webapp.repository;

import com.dedup4.storage.webapp.domain.UserOperation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 24, 2016.
 */
public interface UserOperationRepository extends MongoRepository<UserOperation, String> {

    List<UserOperation> findByTypeAndDateBetween(UserOperation.Type type, Date from, Date to);

    UserOperation findByTypeAndDate(UserOperation.Type login, Date date);
}
