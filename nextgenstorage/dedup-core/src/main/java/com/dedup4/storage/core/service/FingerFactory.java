package com.dedup4.storage.core.service;

import com.dedup4.storage.core.util.Constants;
import com.dedup4.storage.core.util.MD5;
import org.rabinfingerprint.fingerprint.RabinFingerprintLong;
import org.rabinfingerprint.fingerprint.RabinFingerprintLongWindowed;
import org.rabinfingerprint.polynomial.Polynomial;

import java.io.BufferedInputStream;
import java.io.IOException;

public class FingerFactory {

    private final RabinFingerprintLongWindowed fingerWindow;
    private final ChunkBoundaryDetector boundaryDetector;

    public FingerFactory(Polynomial p, long bytesPerWindow, ChunkBoundaryDetector boundaryDetector) {
        this.fingerWindow = new RabinFingerprintLongWindowed(p, bytesPerWindow);
        this.boundaryDetector = boundaryDetector;
    }

    private RabinFingerprintLongWindowed newWindowedFingerprint() {
        return new RabinFingerprintLongWindowed(fingerWindow);
    }

    /**
     * Fingerprint the file into chunks called "Fingers". The chunk boundaries
     * are determined using a windowed fingerprinter
     * {@link RabinFingerprintLongWindowed}.
     * <p>
     * The chunk detector is position independent. Therefore, even if a file is
     * rearranged or partially corrupted, the untouched chunks can be
     * efficiently discovered.
     */
    public void getChunkFingerprints(BufferedInputStream bis, ChunkVisitor visitor) throws IOException {
        final RabinFingerprintLong window = newWindowedFingerprint();
        int chunkStart = 0;
        int chunkLength = 0;

        long fileSize = bis.available();
        byte[] buf = new byte[4 * 1024 * 1024];
        byte[] content = new byte[Constants.CHUNK_MAX_SIZE];

        int numRead;
        while ((numRead = bis.read(buf)) != -1) {
            for (int i = 0; i < numRead; i++) {
                window.pushByte(buf[i]);
                content[chunkLength++] = buf[i];

                if (boundaryDetector.isBoundary(window, chunkLength)) {
                    visitor.visit(MD5.getMD5(content, chunkLength), chunkStart, chunkLength, content);
                    chunkStart += chunkLength;
                    chunkLength = 0;
                }

            } // end while.
        }

        if (chunkStart + chunkLength == fileSize) {
            visitor.visit(MD5.getMD5(content), chunkStart, chunkLength, content);
        }
    } // end while.

    public interface ChunkBoundaryDetector {
        boolean isBoundary(RabinFingerprintLong fingerprint, int chunkLength);
    }

    public interface ChunkVisitor {
        void visit(byte[] fingerprint, int chunkStart, int chunkLength, byte[] chunk) throws IOException;
    }
}