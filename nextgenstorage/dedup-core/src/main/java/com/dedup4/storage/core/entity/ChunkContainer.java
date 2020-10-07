package com.dedup4.storage.core.entity;

import com.dedup4.storage.core.util.Constants;

public class ChunkContainer {

    /**
     * Container is a array of many chunks.
     */
    Chunk[] chunkArray;

    /**
     * Chunk count of container.
     */
    int chunkCount = 0;

    /**
     * written or not.
     */
    boolean writen = false;

    /**
     * Store path of container.
     */
    String containerPath;

    /**
     * Container information store path.
     */
    String containerInfoPath;

    /**
     * Container ID.
     */
    int containerID;

    int containerLength = 0;

    public ChunkContainer(int containerID, String FileName) {
        this.containerID = containerID;
        this.containerPath = Constants.CONTAINER_LOCATION + "\\\\" + FileName + "\\\\" + this.containerID;
        this.containerInfoPath = Constants.CONTAINER_LOCATION
                + "\\\\" + FileName + "\\\\" + this.containerID + "Info.txt";
        /**
         * Mark.
         */
        chunkArray = new Chunk[Constants.CONTAINER_MAX_SIZE];
    }

    public ChunkContainer(int containerID) {
        this.containerID = containerID;
        chunkArray = new Chunk[Constants.CONTAINER_MAX_SIZE];
    }

    /**
     * Add Chunk into ChunkContainer.
     *
     * @param chunk
     */
    public void addChunk(Chunk chunk) {
        chunkArray[chunkCount++] = chunk;
        containerLength += chunk.getLength();
    }

    /**
     * Check the container is full or not.
     *
     * @return
     */
    public long remainSize() {
        return (Constants.CONTAINER_MAX_SIZE) - chunkCount;
    }

    /**
     * Getters and Setters.
     */
    public Chunk[] getChunkArray() {
        return chunkArray;
    }

    public void setChunkArray(Chunk[] chunkArray) {
        this.chunkArray = chunkArray;
    }

    public int getChunkCount() {
        return chunkCount;
    }

    public void setChunkCount(int chunkCount) {
        this.chunkCount = chunkCount;
    }

    public boolean isWriten() {
        return writen;
    }

    public void setWriten(boolean writen) {
        this.writen = writen;
    }

    public String getContainerPath() {
        return containerPath;
    }

    public void setContainerPath(String containerPath) {
        this.containerPath = containerPath;
    }

    public String getContainerInfoPath() {
        return containerInfoPath;
    }

    public void setContainerInfoPath(String containerInfoPath) {
        this.containerInfoPath = containerInfoPath;
    }

    public int getContainerID() {
        return containerID;
    }

    public void setContainerkID(int chunkID) {
        this.containerID = chunkID;
    }

    public int getContainerLength() {
        return containerLength;
    }

    public void setContainerLength(int containerLength) {
        this.containerLength = containerLength;
    }


}
