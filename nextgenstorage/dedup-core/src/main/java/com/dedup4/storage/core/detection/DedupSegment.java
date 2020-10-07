package com.dedup4.storage.core.detection;

import com.dedup4.storage.core.service.ContentStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;

public class DedupSegment extends ReentrantLock
        implements Serializable {
    private static final long serialVersionUID = 7296450167873935960L;

    private static final Logger LOGGER = LoggerFactory.getLogger(DedupSegment.class);

    private int id;
    private InerrantBloomFilter bloomFilter;

    public DedupSegment(int id, int expectedInsertions, double fpp, String fingerprintPath) {
        this.id = id;
        this.bloomFilter = new InerrantBloomFilter(expectedInsertions, fpp, fingerprintPath);
    }

    public void dedupFile(File file) {
        lock();
        ContentStore contentStore = new ContentStore();
        try {
            contentStore.getChunks(file.getAbsolutePath(), bloomFilter);
        } catch (IOException e) {
            LOGGER.info("" + e);
        }
        unlock();
    }

    public int getId() {
        return id;
    }
}
