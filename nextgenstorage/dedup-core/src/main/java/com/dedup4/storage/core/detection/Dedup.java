package com.dedup4.storage.core.detection;

import com.dedup4.storage.core.util.Constants;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Dedup implements Serializable {
    static final String FP_FILE_SUFFIX = ".rec";
    static final double DEFAULT_FPP = 0.01;
    static final int DEFAULT_EXPECT_INSERT = 10_000_000;
    static final int DEFAULT_CAPACITY = 4;
    static final String SER_FILE_NAME = "dedup.ser";
    private static final long serialVersionUID = 5656355504097076820L;
    private static final Logger LOGGER = LoggerFactory.getLogger(Dedup.class);
    static int availProcessors = Runtime.getRuntime().availableProcessors();
    static int capacityThreadPool = 2 * availProcessors;
    static private ExecutorService threadPool = Executors.newFixedThreadPool(capacityThreadPool);

    private DedupSegment[] dedupSegments;
    private String storePath;
    private int capacity;

    public Dedup(String storePath) {
        this(DEFAULT_CAPACITY, DEFAULT_EXPECT_INSERT, DEFAULT_FPP, storePath);
    }

    public Dedup(int capacity, int expectedInsertionsPerSegment, double fpp, String storePath) {
        Preconditions.checkArgument(capacity > 0,
                "The number of BloomFilter in BloomFilterArray (%d) must be > 0",
                capacity);
        this.capacity = capacity;
        this.dedupSegments = new DedupSegment[capacity];
        this.storePath = storePath;

        for (int i = 0; i < capacity; i++) {
            dedupSegments[i] = new DedupSegment(i, expectedInsertionsPerSegment, fpp, storePath + i + FP_FILE_SUFFIX);
        }
    }

    private static int hashFileName(String str) {
        return hash(str.hashCode());
    }

    private static int hash(int h) {
        h += (h << 15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h << 3);
        h ^= (h >>> 6);
        h += (h << 2) + (h << 14);
        return h ^ (h >>> 16);
    }

    public static Dedup load(String loadPath) {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(loadPath))) {
            Dedup dedup = (Dedup) is.readObject();
            Dedup.availProcessors = Runtime.getRuntime().availableProcessors();
            Dedup.capacityThreadPool = 2 * Dedup.availProcessors;
            Dedup.threadPool = Executors.newFixedThreadPool(capacityThreadPool);
            return dedup;
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return null;
    }

    public void operate(String filePath) throws InterruptedException, ExecutionException {
        operate(new File(filePath));
    }

    public void operate(final File file) throws InterruptedException, ExecutionException {
        Thread thread = new Thread(() -> {
            long startTime = System.nanoTime();
            DedupSegment dedupSegment = dedupSegmentFor(file.getName());
            dedupSegment.dedupFile(file);
            LOGGER.info("Time: " + (System.nanoTime() - startTime) / 1000_000.00 + "ms");

            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("F:\\testResult\\container.info"));
                out.write(String.valueOf(Constants.CONTAINER_ID));
                out.close();
            } catch (Exception e) {
                LOGGER.error("", e);
            }
        });
        threadPool.submit(thread);
    }

    private DedupSegment dedupSegmentFor(String fileName) {
        return dedupSegments[hashFileName(fileName) & (capacity - 1)];
    }

    public int getCapacity() {
        return capacity;
    }

    public String getStorePath() {
        return storePath;
    }

    public void save() {
        try {
            waitForAllTasksCompleted();
        } catch (InterruptedException e) {
            LOGGER.error("", e);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storePath + SER_FILE_NAME))) {
            LOGGER.info("begin");
            oos.writeObject(this);
        } catch (IOException e) {
            LOGGER.error("", e);
        }
    }

    private void waitForAllTasksCompleted() throws InterruptedException {
        threadPool.shutdown();
        while (true) {
            if (threadPool.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS)) {
                break;
            }
        }
    }
}