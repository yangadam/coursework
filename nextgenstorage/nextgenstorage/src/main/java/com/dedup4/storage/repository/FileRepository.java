package com.dedup4.storage.repository;

import com.dedup4.storage.domain.File;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Yang Mengmeng Created on Apr 04, 2016.
 */
public interface FileRepository extends MongoRepository<File, String> {
    File findByOwner(String username);
}
