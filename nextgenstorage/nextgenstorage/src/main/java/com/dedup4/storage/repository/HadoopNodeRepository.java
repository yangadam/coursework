package com.dedup4.storage.repository;

import com.dedup4.storage.domain.HadoopNode;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Yang Mengmeng Created on Mar 26, 2016.
 */
public interface HadoopNodeRepository extends MongoRepository<HadoopNode, String> {
}
