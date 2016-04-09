package com.dedup4.storage.webapp.repository;

import com.dedup4.storage.webapp.domain.FileRecipe;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Yang Mengmeng Created on Apr 09, 2016.
 */
public interface FileRecipeRepository extends MongoRepository<FileRecipe, String> {
}
