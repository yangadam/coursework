package com.dedup4.storage.webapp.repository;

import com.dedup4.storage.common.domain.LogicFile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Yang Mengmeng Created on Apr 04, 2016.
 */
public interface LogicFileRepository extends MongoRepository<LogicFile, String> {
    LogicFile findByOwner(String username);
}
