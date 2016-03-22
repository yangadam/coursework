package com.dedup4.storage.dedup.service;

import java.io.BufferedInputStream;
import java.io.IOException;

import org.rabinfingerprint.fingerprint.RabinFingerprintLong;
import org.rabinfingerprint.fingerprint.RabinFingerprintLongWindowed;
import org.rabinfingerprint.polynomial.Polynomial;

import com.dedup4.storage.dedup.util.Constants;
import com.dedup4.storage.dedup.util.MD5;

public class FingerFactory {

	public static interface ChunkBoundaryDetector {
		public boolean isBoundary(RabinFingerprintLong fingerprint, int chunkLength);
	}

	public static interface ChunkVisitor {
		public void visit(byte[] fingerprint, int chunkStart, int chunkLength, byte[] chunk) throws IOException;
	}

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
	 * 
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

		int numRead = 0;
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
}