package com.dedup4.storage.dedup.detection;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;

import com.dedup4.storage.dedup.service.ContentStore;

public class DedupSegment extends ReentrantLock 
	implements Serializable {
	private static final long serialVersionUID = 7296450167873935960L;
	
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
			e.printStackTrace();
		}
		unlock();
	}
	
	public int getId() {
		return id;
	}
}
