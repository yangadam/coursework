package com.dedup4.storage.dedup.detection;

import com.dedup4.storage.dedup.util.MD5;
import com.google.common.base.Preconditions;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.io.Serializable;
import java.nio.charset.Charset;

public class InerrantBloomFilter implements Serializable {
    private static final long serialVersionUID = 1312487368722774590L;

    private BloomFilter<CharSequence> bloomFilter = null;
    private RecordFileIndex index = null;
    private int expectedInsertions = 0;
    private int numInserted = 0;

    public InerrantBloomFilter(int expectedInsertions, double fpp, String fingerprintPath) {
        Preconditions.checkNotNull(fingerprintPath);
        this.expectedInsertions = expectedInsertions;
        this.bloomFilter = BloomFilter.create(
                Funnels.stringFunnel(Charset.defaultCharset()),
                expectedInsertions,
                fpp);
        this.index = new RecordFileIndex(fingerprintPath, expectedInsertions);
    }

    public String contains(byte[] fingerprint) {
        if (!bloomFilter.mightContain(MD5.getMD5Str(fingerprint))) {
            return null;
        }
        return index.contains(fingerprint);
    }

    public void put(byte[] fingerprint, int containerID, int startPos, int chunkSize) {
        if (numInserted >= expectedInsertions) {
            expandExpectedInsertions();
        }

        if (index.add(fingerprint, containerID, startPos, chunkSize)) {
            bloomFilter.put(MD5.getMD5Str(fingerprint));
            numInserted++;
        }
    }

    private void expandExpectedInsertions() {
        this.expectedInsertions *= 2;
        BloomFilter<CharSequence> newBloomFilter = BloomFilter.create(
                Funnels.stringFunnel(Charset.defaultCharset()),
                expectedInsertions,
                this.bloomFilter.expectedFpp());
        newBloomFilter.putAll(bloomFilter);
        this.bloomFilter = newBloomFilter;
    }

    public int getExpectedInsertions() {
        return this.expectedInsertions;
    }

    public int getNumInserted() {
        return numInserted;
    }

    public double getFpp() {
        return bloomFilter.expectedFpp();
    }
}
