package com.dedup4.storage.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;

@SpringBootApplication
@EnableJms
public class DedupCoreApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(DedupCoreApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DedupCoreApplication.class, args);
    }

    @JmsListener(destination = "queue.pickfile.dedup")
    public void receiveDefault(String text) {
        LOGGER.info(text);
        // TODO deduplicate file
    }

}
