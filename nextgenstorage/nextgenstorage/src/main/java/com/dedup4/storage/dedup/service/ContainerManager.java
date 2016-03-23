package com.dedup4.storage.dedup.service;

import com.dedup4.storage.dedup.entity.Chunk;
import com.dedup4.storage.dedup.entity.ChunkContainer;
import com.dedup4.storage.dedup.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ContainerManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContainerManager.class);

    String containerPath;
    String fileName;
    long createTime;

    public ContainerManager() {
    }

    public ContainerManager(String filePath) {
        fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        String folderPath = Constants.CONTAINER_LOCATION;
        File folder = new File(folderPath);

        if (!folder.exists()) {
            if (folder.mkdirs()) {
                LOGGER.info("create folder " + folderPath + " success!");
            }
        }
    }

    public void saveContainer(ChunkContainer container) {
        String containerPath = Constants.CONTAINER_LOCATION + "\\" + container.getContainerID();
        this.setContainerPath(containerPath);
        Chunk[] chunkArray = container.getChunkArray();

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(containerPath))) {
            for (int i = 0; i < container.getChunkCount(); i++) {
                out.write(chunkArray[i].getContent());
            }
        } catch (IOException e) {
            LOGGER.info("" + e);
        }
    }

    public String getContainerPath() {
        return containerPath;
    }

    public void setContainerPath(String containerPath) {
        try {
            Files.deleteIfExists(Paths.get(containerPath));
        } catch (IOException e) {
            LOGGER.info("" + e);
        }
        this.containerPath = containerPath;
    }
}
