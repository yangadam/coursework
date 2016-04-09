package com.dedup4.storage.webapp.repository;

import com.dedup4.storage.common.domain.UserOperation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 24, 2016.
 */
public interface UserOperationRepository extends MongoRepository<UserOperation, String> {
    List<UserOperation> findByType(UserOperation.Type type);

    int countByType(UserOperation.Type type);

    int countByTypeAndTimeBetween(UserOperation.Type type, Date from, Date to);

    List<Date> findTimeByTypeAndTimeBetween(UserOperation.Type type, Date from, Date to);
}
