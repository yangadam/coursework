package com.dedup4.storage.dedup.util;

import java.io.File;

public class FolderHelper {
    public static long getFileCount(File folder) {
        System.out.println("Folder: " + folder.getName());
        long totalFile = 0;

        File[] filelist = folder.listFiles();
        if (filelist != null) {
            for (File aFilelist : filelist) {
                if (!aFilelist.isDirectory()) {
                    totalFile++;
                }
            }
        }
        return totalFile;
    }
}
