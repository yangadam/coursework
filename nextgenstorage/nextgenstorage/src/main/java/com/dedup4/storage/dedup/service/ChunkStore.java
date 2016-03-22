package com.dedup4.storage.dedup.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.dedup4.storage.dedup.detection.InerrantBloomFilter;
import com.dedup4.storage.dedup.entity.Chunk;
import com.dedup4.storage.dedup.entity.ChunkContainer;
import com.dedup4.storage.dedup.util.Constants;


public class ChunkStore {
	private ChunkContainer container;
	private int containerID = 0;
	File fileMetaFile;
	
	public ChunkStore(String filePath) {
		
		this.containerID = Constants.CONTAINER_ID.getAndIncrement();
		System.out.println("initial container id is " + this.containerID);
		container = new ChunkContainer(containerID);
		
		String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
		String fileMetaPath = String.format(Constants.FILEMETADATA_LOCATION + "\\" + fileName + ".txt");
		
		// TODO
		File file = new File(fileMetaPath);
		if(file.exists()) {
			fileMetaPath = String.format(Constants.FILEMETADATA_LOCATION + "\\" + fileName + System.currentTimeMillis() + ".txt");
		}
		fileMetaFile = new File(fileMetaPath);
		if(!fileMetaFile.getParentFile().exists()) {
			fileMetaFile.getParentFile().mkdirs();
		}
		try {
			fileMetaFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// set buffer
	private StringBuilder metaDataBuf = new StringBuilder();
	
	public void flushMetaDataBuf() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileMetaFile, true))) {
			writer.append(metaDataBuf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		metaDataBuf = new StringBuilder();
	}
	
	public ChunkContainer addChunk(Chunk chunk, InerrantBloomFilter bloomFilter) {
		String result = bloomFilter.contains(chunk.getFingerPrint());
		String metadata = null;
		
		if(result == null) {
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
