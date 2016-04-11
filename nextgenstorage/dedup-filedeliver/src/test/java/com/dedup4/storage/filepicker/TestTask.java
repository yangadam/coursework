package com.dedup4.storage.filepicker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Yang Mengmeng Created on Apr 09, 2016.
 */
public class TestTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestTask.class);

    @Scheduled(fixedRate = 5L, initialDelay = 0L)
    public void test() {
        LOGGER.info("=== schedule task executed");
    }
}
