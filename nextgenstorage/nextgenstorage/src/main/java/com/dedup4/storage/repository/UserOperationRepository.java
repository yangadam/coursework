package com.dedup4.storage.repository;

import com.dedup4.storage.domain.UserOperation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 24, 2016.
 */
public interface UserOperationRepository extends MongoRepository<UserOperation, String> {
    List<UserOperation> findByType(UserOperation.Type type);

    int countByType(UserOperation.Type type);

    int countByTypeAndCreateDateBetween(UserOperation.Type type, Date from, Date to);

    List<Date> findCreateDateByTypeAndCreateDateBetween(UserOperation.Type type, Date from, Date to);
}
