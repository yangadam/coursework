package com.dedup4.storage.dedup.util;

import java.util.concurrent.atomic.AtomicInteger;

public class Constants {
	public static final int CHUNK_MAX_SIZE=16*1024;
	public static final int CHUNK_MIN_SIZE=2*1024;
	public static final int CONTAINER_MAX_SIZE= 1024;
    public static final String CONTAINER_LOCATION="F:\\testResult";
    public static final String FILEMETADATA_LOCATION="F:\\testFileMetadata";
    public static final int SIZE_1MB = 1024 * 1024;
    public static final long DMATCH = 10097;
    public static final long D2MATCH = DMATCH/2;
    public static final long CHUNK_PATTERN = D2MATCH-1;
    
    // Byte per window
    public static final int WINDOW_SIZE = 48;
    
    public static AtomicInteger CONTAINER_ID = new AtomicInteger(0);
}
