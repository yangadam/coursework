package com.dedup4.storage.webapp.repository;

import com.dedup4.storage.common.domain.Metadata;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Yang Mengmeng Created on Apr 20, 2016.
 */
public interface MetadataRepository extends MongoRepository<Metadata, String> {
}
