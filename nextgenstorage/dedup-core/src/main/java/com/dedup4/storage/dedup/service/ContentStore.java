package com.dedup4.storage.dedup.service;

import com.dedup4.storage.dedup.detection.InerrantBloomFilter;
import com.dedup4.storage.dedup.entity.Chunk;
import com.dedup4.storage.dedup.entity.ChunkContainer;
import com.dedup4.storage.dedup.entity.MyBoundaryDectors;
import com.dedup4.storage.dedup.util.Constants;
import org.rabinfingerprint.polynomial.Polynomial;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ContentStore {

    public static long totalTime2 = 0;
    private ChunkStore chunkStore;
    private ContainerManager containerManager;

    public ContentStore() {
    }

    public void getChunks(String filePath, final InerrantBloomFilter bloomFilter) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
        final int fileSize = bis.available();
        System.out.println("In getChunks: Open file " + filePath);

        Polynomial poly = Polynomial.createFromLong(10923124345206883L);
        FingerFactory fingerFactory = new FingerFactory(poly, Constants.WINDOW_SIZE, MyBoundaryDectors.DMATCHED_BOUNDARY_DETECTOR);
        chunkStore = new ChunkStore(filePath);
        containerManager = new ContainerManager(filePath);

        fingerFactory.getChunkFingerprints(bis, (fingerprint, chunkStart, chunkLength, buf) -> {
            Chunk chunk = new Chunk(fingerprint, buf, chunkLength, chunkStart);
            ChunkContainer container = chunkStore.addChunk(chunk, bloomFilter);

            if (container.remainSize() == 0) {
                containerManager.saveContainer(container);
                chunkStore.setNewContainer();
            }

            if (chunkLength + chunkStart == fileSize) {
                if (container.getChunkCount() == 0) {
                    Constants.CONTAINER_ID.getAndDecrement();
                } else {
                    containerManager.saveContainer(container);
                }
            }
        });
        chunkStore.flushMetaDataBuf();
    }
}
