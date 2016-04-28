package com.dedup4.storage.webapp.service;

import com.dedup4.storage.common.domain.FileRecipe;

import java.io.InputStream;

/**
 * @author Yang Mengmeng Created on Apr 20, 2016.
 */
public interface DownloadService {
    InputStream download(FileRecipe file);
}
