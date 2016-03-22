package com.dedup4.storage.dedup.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.dedup4.storage.dedup.entity.Chunk;
import com.dedup4.storage.dedup.entity.ChunkContainer;
import com.dedup4.storage.dedup.util.Constants;

public class ContainerManager {
	
	String containerPath;
	String fileName;
	long createTime;
	
	public ContainerManager() {
	}
	
	public ContainerManager(String filePath) {
		fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
		String folderPath = String.format(Constants.CONTAINER_LOCATION);
		File folder = new File(folderPath);
		
		if(!folder.exists()) {
			if(folder.mkdirs()) {
				System.out.println("create folder " + folderPath + " success!");
			}
		}
	}

	public void saveContainer(ChunkContainer container) {
		String containerPath = Constants.CONTAINER_LOCATION + "\\" + container.getContainerID();
		this.setContainerPath(containerPath);
		Chunk[] chunkArray = container.getChunkArray();
		
		try(BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(containerPath))) {
			for(int i = 0; i < container.getChunkCount(); i++) {
				out.write(chunkArray[i].getContent());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getContainerPath() {
		return containerPath;
	}

	public void setContainerPath(String containerPath) {
		try {
			Files.deleteIfExists(Paths.get(containerPath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.containerPath = containerPath;
	}
}
