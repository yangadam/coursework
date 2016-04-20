package com.dedup4.storage.core;

import java.io.File;

/**
 * @author Yang Mengmeng Created on Apr 20, 2016.
 */
public interface FileService {
    /**
     * Contract:
     * 1. Deduplicate file
     * 2. Save container information and metadata to mongo database
     * 1). Create a repository for container and add a metadata column to FileRecipe
     * 2). Register FileServiceImpl as spring bean and inject Repository using autowired annotation
     * 3. upload container file to hdfs
     * To operate hdfs, inject FsShell using autowired annotation
     *
     * @param file file to deduplication
     */
    void dedupFile(File file);
}
