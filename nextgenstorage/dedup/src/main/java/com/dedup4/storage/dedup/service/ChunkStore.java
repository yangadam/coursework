package com.dedup4.storage.dedup.service;

import com.dedup4.storage.dedup.detection.InerrantBloomFilter;
import com.dedup4.storage.dedup.entity.Chunk;
import com.dedup4.storage.dedup.entity.ChunkContainer;
import com.dedup4.storage.dedup.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ChunkStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChunkStore.class);
    File fileMetaFile;
    private ChunkContainer container;
    // set buffer
    private StringBuilder metaDataBuf = new StringBuilder();

    public ChunkStore(String filePath) {

        int containerID = Constants.CONTAINER_ID.getAndIncrement();
        LOGGER.info("initial container id is " + containerID);
        container = new ChunkContainer(containerID);

        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        String fileMetaPath = Constants.FILEMETADATA_LOCATION + "\\" + fileName + ".txt";

        // TODO
        File file = new File(fileMetaPath);
        if (file.exists()) {
            fileMetaPath = Constants.FILEMETADATA_LOCATION + "\\" + fileName + System.currentTimeMillis() + ".txt";
        }
        fileMetaFile = new File(fileMetaPath);
        if (!fileMetaFile.getParentFile().exists()) {
            fileMetaFile.getParentFile().mkdirs();
        }
        try {
            fileMetaFile.createNewFile();
        } catch (IOException e) {
            LOGGER.info("" + e);
        }
    }

    public void flushMetaDataBuf() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileMetaFile, true))) {
            writer.append(metaDataBuf);
        } catch (Exception e) {
            LOGGER.info("" + e);
        }
        metaDataBuf = new StringBuilder();
    }

    public ChunkContainer addChunk(Chunk chunk, InerrantBloomFilter bloomFilter) {
        String result = bloomFilter.contains(chunk.getFingerPrint());
        String metadata = null;

        if (result == null) {
            if (container.remainSize() > 0) {
                chunk.setId(container.getChunkCount());
                chunk.setContainerID(container.getContainerID());
                chunk.setStartPos(container.getContainerLength());
                container.addChunk(chunk);
                bloomFilter.put(chunk.getFingerPrint(), container.getContainerID(), chunk.getStartPos(), chunk.getLength());
                metadata = chunk.getFileMetadata();
            }
        } else {
            metadata = result;
        }

        metaDataBuf.append(metadata);
        return container;
    }

    public void setNewContainer() {
        container = new ChunkContainer(Constants.CONTAINER_ID.getAndIncrement());
    }
}
