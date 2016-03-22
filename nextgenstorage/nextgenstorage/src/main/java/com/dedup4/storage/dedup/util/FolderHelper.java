package com.dedup4.storage.dedup.util;

import java.io.File;

public class FolderHelper {
	public static long getFileCount(File folder) {
		System.out.println("Folder: " + folder.getName());
		long totalFile = 0;
		
		File[] filelist = folder.listFiles();
		for(int i = 0; i < filelist.length; i++) {
			if(!filelist[i].isDirectory()) {
				totalFile++;
			}
		}
		return totalFile;
	}
}
