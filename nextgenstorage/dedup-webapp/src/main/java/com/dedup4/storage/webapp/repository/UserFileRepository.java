package com.dedup4.storage.webapp.repository;

import com.dedup4.storage.webapp.domain.UserFile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface UserFileRepository extends MongoRepository<UserFile, String> {
    int countByUploadDateBetween(Date from, Date to);
}
