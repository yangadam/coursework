package com.dedup4.storage.core.entity;

import com.dedup4.storage.core.service.FingerFactory;
import com.dedup4.storage.core.util.Constants;
import org.rabinfingerprint.fingerprint.RabinFingerprintLong;

public class MyBoundaryDectors {
    public static final FingerFactory.ChunkBoundaryDetector DMATCHED_BOUNDARY_DETECTOR = new DMatchBoundaryDectector(Constants.DMATCH, Constants.CHUNK_PATTERN);
    public static final FingerFactory.ChunkBoundaryDetector D2MATCHED_BOUNDARY_DETECTOR = new D2MatchBoundaryDectector(Constants.D2MATCH, Constants.CHUNK_PATTERN);

    public static class D2MatchBoundaryDectector implements FingerFactory.ChunkBoundaryDetector {
        private final long matchedD2;
        private final long chunkPattern;

        public D2MatchBoundaryDectector(long matchedD2, long chunkPattern) {
            this.matchedD2 = matchedD2;
            this.chunkPattern = chunkPattern;
        }

        @Override
        public boolean isBoundary(RabinFingerprintLong fingerprint, int chunkLength) {
            return (((fingerprint.getFingerprintLong() % matchedD2 == chunkPattern) || chunkLength >= Constants.CHUNK_MAX_SIZE) && chunkLength >= Constants.CHUNK_MIN_SIZE);
        }
    }

    public static class DMatchBoundaryDectector implements FingerFactory.ChunkBoundaryDetector {
        private final long matchedD;
        private final long chunkPattern;

        public DMatchBoundaryDectector(long matchedD, long chunkPattern) {
            this.matchedD = matchedD;
            this.chunkPattern = chunkPattern;
        }

        @Override
        public boolean isBoundary(RabinFingerprintLong fingerprint, int chunkLength) {
            return (((fingerprint.getFingerprintLong() % matchedD == chunkPattern) || chunkLength >= Constants.CHUNK_MAX_SIZE) && chunkLength >= Constants.CHUNK_MIN_SIZE);
        }
    }
}
