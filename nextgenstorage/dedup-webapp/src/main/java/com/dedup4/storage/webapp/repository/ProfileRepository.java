package com.dedup4.storage.webapp.repository;

import com.dedup4.storage.webapp.domain.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Yang Mengmeng Created on Mar 26, 2016.
 */
public interface ProfileRepository extends MongoRepository<Profile, String> {
}
