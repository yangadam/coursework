package com.dedup4.storage.webapp.repository;

import com.dedup4.storage.webapp.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
